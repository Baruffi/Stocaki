package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.File;
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

    private DefaultTableModel dm = new DefaultTableModel(0, 0);
    private List<Object[]> removedRows = new ArrayList<Object[]>();

    RequisicoesAdm() {
        initComponents();
        Framework.setup(this, menuPanel);
        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                for (int i = dm.getRowCount()-1; i >= 0; i--) {
                    List<Object> removedRow = new ArrayList<Object>();
                    boolean remove = true;
                    for (int j = 0; j < dm.getColumnCount(); j++) {
                        if (requisicoesTable.getValueAt(i,j).toString().contains(searchBar.getText())) {
                            remove = false;
                            break;
                        }
                        removedRow.add(requisicoesTable.getValueAt(i,j));
                    }
                    if (remove) {
                        removedRows.add(removedRow.toArray());
                        dm.removeRow(i);
                    }
                }
                for (int i = removedRows.size()-1; i >= 0; i--) {
                    Object[] removedRow = removedRows.get(i);
                    for (Object removedCell:
                            removedRow) {
                        if (removedCell.toString().contains(searchBar.getText())) {
                            dm.addRow(removedRow);
                            removedRows.remove(removedRow);
                            break;
                        }
                    }
                }
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

        String[] header = { "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "Aprovar", "Negar"};

        dm.setColumnIdentifiers(header);
        requisicoesTable.setModel(dm);

        dm.addRow(new Object[]{"teste1","teste","teste teste","testeC","testeL","Branca","6","V","X"});
        dm.addRow(new Object[]{"teste2","teste","teste teste","testeC","testeL","Preta","7","V","X"});

        for (Requisicao requisicao:
                requisicoes) {
            dm.addRow(new Object[]{requisicao.getNome(), requisicao.getModelo(), requisicao.getDescricao(), requisicao.getClassificacao(), requisicao.getLote(), requisicao.getCor(), requisicao.getSaldo(),"V","X"});
        }

        requisicoesTable.getColumn("Aprovar").setCellRenderer(new ButtonRenderer());
        requisicoesTable.getColumn("Aprovar").setCellEditor(new ButtonEditor(new JCheckBox()));

        requisicoesTable.getColumn("Negar").setCellRenderer(new ButtonRenderer());
        requisicoesTable.getColumn("Negar").setCellEditor(new ButtonEditor(new JCheckBox()));

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOES_ADM);
        // Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM); DISABLED!!!
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }
}
