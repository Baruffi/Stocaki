package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.util.ArrayList;
import java.util.List;

import DAO.RequisicaoDAO;
import Model.Requisicao;

public class RequisicoesAdm extends JFrame{
    private JPanel rootPanel;
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JPanel sidePanel;
    private JPanel pgroupPanel;
    private JPanel ptitlePanel;
    private JLabel ptitleLabel;
    private JPanel produtosPanel;
    private JPanel rlistPanel;
    private JLabel rlistLabel;
    private JPanel mlistPanel;
    private JLabel mlistLabel;
    private JPanel plistPanel;
    private JLabel plistLabel;
    private JPanel pcadPanel;
    private JLabel pcadLabel;
    private JPanel fgroupPanel;
    private JPanel funcionariosPanel;
    private JPanel flistPanel;
    private JLabel flistLabel;
    private JPanel fcadPanel;
    private JLabel fcadLabel;
    private JPanel ftitlePanel;
    private JLabel ftitleLabel;
    private JTable requisicoesTable;
    private JScrollPane requisicoesScroll;
    private JTextField searchBar;
    private JLabel searchLabel;
    private JPanel searchPanel;

    private List<Object[]> removedRows = new ArrayList<Object[]>();
    private DefaultTableModel dm = new DefaultTableModel(0, 0);

    RequisicoesAdm() {
        initComponents();
        Framework.setup(this, menuPanel);
        searchBar.addInputMethodListener(new InputMethodListener() {
            public void inputMethodTextChanged(InputMethodEvent event) {
                String search = searchBar.getText().trim();
                for (int i = 1; i < requisicoesTable.getRowCount(); i++) {
                    if (!(search.contains(requisicoesTable.getValueAt(i,1).toString()) || requisicoesTable.getValueAt(i,1).toString().contains(search))) {
                        List<Object> removedRow = new ArrayList<Object>();
                        for (int j = 1; j < requisicoesTable.getColumnCount(); j++) {
                            removedRow.add(requisicoesTable.getValueAt(i,1));
                        }
                        removedRows.add(removedRow.toArray());
                        requisicoesTable.remove(i);
                    }
                }
                for (Object[] removedRow :
                     removedRows) {
                    for (Object removedCell:
                         removedRow) {
                        if (removedCell.toString().contains(search) || search.contains(removedCell.toString())) {
                            dm.addRow(removedRow);
                            break;
                        }
                    }
                }
            }
            public void caretPositionChanged(InputMethodEvent event) {

            }
        });
    }

    private void initComponents() {
        List<Requisicao> requisicoes;

        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(Framework.ICONE_CAIXA);
        stocakiLabel.setIcon(icon);

        ImageIcon search_icon = new ImageIcon(Framework.SEARCH_IMAGE);
        searchLabel.setIcon(search_icon);
        searchLabel.setText("");

        requisicoesTable.getTableHeader().setFont(Framework.TABLE_HEADER);
        requisicoesTable.setFont(Framework.TABLE_BODY);

        requisicoes = RequisicaoDAO.readRequisicoes();

        String[] header = { "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "Aprovar"};

        dm.setColumnIdentifiers(header);
        requisicoesTable.setModel(dm);

        for (Requisicao requisicao:
             requisicoes) {
            dm.addRow(new Object[]{requisicao.getNome(), requisicao.getModelo(), requisicao.getDescricao(), requisicao.getClassificacao(), requisicao.getLote(), requisicao.getCor()});
        }

        requisicoesTable.getColumn("Aprovar").setCellRenderer(new ButtonRenderer());
        requisicoesTable.getColumn("Aprovar").setCellEditor(new ButtonEditor(new JCheckBox()));

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOES_ADM);
        // Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM); DISABLED!!!
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }
}
