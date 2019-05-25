package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import DAO.RequisicaoDAO;
import Model.Requisicao;
import org.jetbrains.annotations.Contract;

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

    private DefaultTableModel dm = new DefaultTableModel(0,0) {
        @Contract(pure = true)
        @Override
        public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
        @Contract(pure = true)
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private List<Object[]> removedRows = new ArrayList<Object[]>();
    private int coloredIcon = -1;

    private ImageIcon stocaki_icon = new ImageIcon(Framework.ICONE_CAIXA);
    private ImageIcon approve_icon = new ImageIcon(Framework.ICONE_APROVAR);
    private ImageIcon reject_icon = new ImageIcon(Framework.ICONE_DELETAR);
    private ImageIcon approve_green = new ImageIcon(Framework.ICONE_APROVAR_VERDE);
    private ImageIcon reject_red = new ImageIcon(Framework.ICONE_DELETAR_VERMELHO);
    private ImageIcon search_icon = new ImageIcon(Framework.ICONE_BUSCA);

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
                for (int k = 0; k < 9; k++) {
                    if (dm.getColumnName(k).contains("▼ ")) {
                        List<Object> values = new ArrayList<Object>();
                        for (int i = 0; i < dm.getRowCount(); i++) {
                            values.add(requisicoesTable.getValueAt(i,k));
                            for (int j = 0; j < i; j++) {
                                if (requisicoesTable.getValueAt(i,k).toString().compareToIgnoreCase(values.get(j).toString()) < 0) {
                                    dm.moveRow(i,i,j);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        });
        requisicoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int answer;
                switch (requisicoesTable.columnAtPoint(e.getPoint())) {
                    case 7:
                        answer = JOptionPane.showConfirmDialog(requisicoesTable, "Tem certeza que deseja APROVAR a requisição?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Requisicao requisicao = new Requisicao();
                            requisicao.setStatus_aprovacao("A");
                            requisicao.setNome(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),1).toString());
                            requisicao.setModelo(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),2).toString());
                            requisicao.setDescricao(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),3).toString());
                            requisicao.setClassificacao(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),4).toString());
                            requisicao.setLote(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),5).toString());
                            requisicao.setCor(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),6).toString());
                            requisicao.setSaldo(Integer.parseInt(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),7).toString()));
                            RequisicaoDAO.approveRequisicao(requisicao);
                        }
                        break;
                    case 8:
                        answer = JOptionPane.showConfirmDialog(requisicoesTable, "Tem certeza que deseja NEGAR a requisição?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Requisicao requisicao = new Requisicao();
                            requisicao.setStatus_aprovacao("E");
                            RequisicaoDAO.approveRequisicao(requisicao);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        requisicoesTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                if (coloredIcon != -1) {
                    dm.setValueAt(approve_icon, coloredIcon, 7);
                    dm.setValueAt(reject_icon, coloredIcon, 8);
                    coloredIcon = -1;
                }
                if (requisicoesTable.columnAtPoint(e.getPoint()) == 7) {
                    requisicoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    dm.setValueAt(approve_green, requisicoesTable.rowAtPoint(e.getPoint()), 7);
                    coloredIcon = requisicoesTable.rowAtPoint(e.getPoint());
                } else if (requisicoesTable.columnAtPoint(e.getPoint()) == 8) {
                    requisicoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    dm.setValueAt(reject_red, requisicoesTable.rowAtPoint(e.getPoint()), 8);
                    coloredIcon = requisicoesTable.rowAtPoint(e.getPoint());
                } else {
                    requisicoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        requisicoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                requisicoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if (coloredIcon != -1) {
                    dm.setValueAt(approve_icon, coloredIcon, 7);
                    dm.setValueAt(reject_icon, coloredIcon, 8);
                    coloredIcon = -1;
                }
            }
        });
        requisicoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (dm.getColumnName(requisicoesTable.columnAtPoint(e.getPoint())).contains("▼ ")) {
                    return;
                }
                String[] header = { "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "Aprovar", "Negar"};
                List<Object> values = new ArrayList<Object>();
                for (int i = 0; i < dm.getRowCount(); i++) {
                    values.add(requisicoesTable.getValueAt(i,requisicoesTable.columnAtPoint(e.getPoint())));
                    for (int j = 0; j < i; j++) {
                        if (requisicoesTable.getValueAt(i,requisicoesTable.columnAtPoint(e.getPoint())).toString().compareToIgnoreCase(values.get(j).toString()) < 0) {
                            dm.moveRow(i,i,j);
                            break;
                        }
                    }
                }
                header[requisicoesTable.columnAtPoint(e.getPoint())] = "▼ " + header[requisicoesTable.columnAtPoint(e.getPoint())];
                dm.setColumnIdentifiers(header);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                requisicoesTable.getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));

            }
        });
    }

    private void initComponents() {
        List<Requisicao> requisicoes;

        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        stocakiLabel.setIcon(stocaki_icon);

        searchLabel.setIcon(search_icon);
        searchLabel.setText("");

        requisicoesTable.getTableHeader().setReorderingAllowed(false);
        requisicoesTable.getTableHeader().setFont(Framework.TABLE_HEADER);
        requisicoesTable.setFont(Framework.TABLE_BODY);

        requisicoes = RequisicaoDAO.readRequisicoes();

        String[] header = { "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "Aprovar", "Negar"};

        dm.setColumnIdentifiers(header);
        requisicoesTable.setModel(dm);

        dm.addRow(new Object[]{"teste1","teste","teste teste","testeC","testeX","Branca","6",approve_icon,reject_icon});
        dm.addRow(new Object[]{"teste2","teste","teste","testeD","testeL","Preta","7",approve_icon,reject_icon});
        dm.addRow(new Object[]{"teste3","teste","teste","testeD","testeL","Preta","7",approve_icon,reject_icon});
        dm.addRow(new Object[]{"micro geladeira","T13I173","Mini geladeira sem freezer com garantia de 2 anos","T13","Q2","Branca","0",approve_icon,reject_icon});

        for (Requisicao requisicao:
                requisicoes) {
            dm.addRow(new Object[]{requisicao.getNome(), requisicao.getModelo(), requisicao.getDescricao(), requisicao.getClassificacao(), requisicao.getLote(), requisicao.getCor(), requisicao.getSaldo(),"V","X"});
        }

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOES_ADM);
        // Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM); DISABLED!!!
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }
}
