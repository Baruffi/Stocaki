package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.MovimentacaoDAO;
import DAO.ArmazemDAO;
import DAO.ProdutoDAO;
import Model.Movimentacao;
import Model.Produto;
import org.jetbrains.annotations.Contract;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;


public class MovimentacaoOpr extends JFrame{
    private JPanel rootPanel;
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JLabel nomeLabel;
    private JTextField nomeField;
    private JLabel modeloLabel;
    private JTextField modeloField;
    private JLabel descricaoLabel;
    private JTextField descricaoField;
    private JLabel classificacaoLabel;
    private JTextField classificacaoField;
    private JLabel loteLabel;
    private JTextField loteField;
    private JLabel corLabel;
    private JTextField corField;
    private JLabel saldoLabel;
    private JTextField saldoField;
    private JButton requisitarButton;
    private JSeparator nomeSeparator;
    private JSeparator descricaoSeparator;
    private JSeparator loteSeparator;
    private JSeparator saldoSeparator;
    private JSeparator corSeparator;
    private JSeparator classificacaoSeparator;
    private JSeparator modeloSeparator;
    private JPanel sidePanel;
    private JPanel pgroupPanel;
    private JPanel ptitlePanel;
    private JLabel ptitleLabel;
    private JPanel produtosPanel;
    private JPanel rlistPanel;
    private JLabel rlistLabel;
    private JPanel mlistPanel;
    private JLabel mlistLabel;
    private JScrollPane movimentacoesScroll;
    private JTable movimentacoesTable;


    private DefaultTableModel dm = new DefaultTableModel(0,0) {
        @Contract(pure = true)
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private List<Produto> produtos = null;
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private ArmazemDAO armazemDAO = new ArmazemDAO();

    private List<Object[]> removedRows = new ArrayList<Object[]>();
    private List<Integer> removedRowsPos = new ArrayList<Integer>();
    private int coloredIcon = -1;

    private ImageIcon stocaki_icon = new ImageIcon(Framework.ICONE_CAIXA);
    private ImageIcon approve_icon = new ImageIcon(Framework.ICONE_APROVAR);
    private ImageIcon reject_icon = new ImageIcon(Framework.ICONE_DELETAR);
    private ImageIcon approve_green = new ImageIcon(Framework.ICONE_APROVAR_VERDE);
    private ImageIcon reject_red = new ImageIcon(Framework.ICONE_DELETAR_VERMELHO);
    private ImageIcon sum_icon = new ImageIcon(Framework.ICONE_SOMAR);
    private ImageIcon sub_icon = new ImageIcon(Framework.ICONE_SUBTRAIR);

    private static final int APPROVE = 8,
                             REPROVE = 9;


    MovimentacaoOpr() {
        initComponents();
        Framework.setup(this, menuPanel);

        //Implementar barra de pesquisa de material
        //Implmentar botoes
        //Implementar logica de soma e subtração
        movimentacoesTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (coloredIcon != -1) {
                    try {
                        movimentacoesTable.setValueAt(approve_icon, coloredIcon, APPROVE);
                        movimentacoesTable.setValueAt(reject_icon, coloredIcon, REPROVE);
                    } catch (Exception ex) {
                        //pass
                    }
                    coloredIcon = -1;
                }

                if (movimentacoesTable.columnAtPoint(e.getPoint()) == APPROVE) {
                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    movimentacoesTable.setValueAt(approve_green, movimentacoesTable.rowAtPoint(e.getPoint()), APPROVE);
                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
                } else if (movimentacoesTable.columnAtPoint(e.getPoint()) == REPROVE) {
                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    movimentacoesTable.setValueAt(reject_red, movimentacoesTable.rowAtPoint(e.getPoint()), REPROVE);
                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
                } else {
                    movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
//        movimentacoesTable.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                super.mouseMoved(e);
//
//                if (coloredIcon != -1) {
//                    try {
//                        movimentacoesTable.setValueAt(sum_icon, coloredIcon, APPROVE);
//                        movimentacoesTable.setValueAt(sub_icon, coloredIcon, REPROVE);
//                    } catch (Exception ex) {
//                        //pass
//                    }
//                    coloredIcon = -1;
//                }
//
//                if (movimentacoesTable.columnAtPoint(e.getPoint()) == APPROVE) {
//                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                    //movimentacoesTable.setValueAt(approve_green, movimentacoesTable.rowAtPoint(e.getPoint()), APPROVE);
//                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
//                } else if (movimentacoesTable.columnAtPoint(e.getPoint()) == REPROVE) {
//                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
//                    //movimentacoesTable.setValueAt(reject_red, movimentacoesTable.rowAtPoint(e.getPoint()), REPROVE);
//                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
//                } else {
//                    movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//                }
//            }
//        });
        movimentacoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                if (coloredIcon != -1) {
                    try {
                        movimentacoesTable.setValueAt(sum_icon, coloredIcon, APPROVE);
                        movimentacoesTable.setValueAt(sub_icon, coloredIcon, REPROVE);
                    } catch (Exception ex) {
                        //pass
                    }
                    coloredIcon = -1;
                }
            }
        });
        movimentacoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] header = {"ID", "Nome", "Modelo", "Lote", "Saldo", "Armazém", "Receber", "Retirar" };

                if(!(movimentacoesTable.columnAtPoint(e.getPoint()) == APPROVE || movimentacoesTable.columnAtPoint(e.getPoint()) == REPROVE)) {
                    if (movimentacoesTable.getColumnName(movimentacoesTable.columnAtPoint(e.getPoint())).contains("▼ ")) {
                        for (int i = 0; i < dm.getRowCount(); i++) {
                            for (int j = 0; j < i; j++) {
                                if (movimentacoesTable.getValueAt(i, movimentacoesTable.columnAtPoint(e.getPoint())).toString().compareToIgnoreCase(movimentacoesTable.getValueAt(j, movimentacoesTable.columnAtPoint(e.getPoint())).toString()) > 0) {
                                    dm.moveRow(i,i,j);
                                    break;
                                }
                            }
                        }
                        header[movimentacoesTable.columnAtPoint(e.getPoint())] = "▲ " + header[movimentacoesTable.columnAtPoint(e.getPoint())];
                    } else {
                        for (int i = 0; i < dm.getRowCount(); i++) {
                            for (int j = 0; j < i; j++) {
                                if (movimentacoesTable.getValueAt(i, movimentacoesTable.columnAtPoint(e.getPoint())).toString().compareToIgnoreCase(movimentacoesTable.getValueAt(j, movimentacoesTable.columnAtPoint(e.getPoint())).toString()) < 0) {
                                    dm.moveRow(i,i,j);
                                    break;
                                }
                            }
                        }
                        header[movimentacoesTable.columnAtPoint(e.getPoint())] = "▼ " + header[movimentacoesTable.columnAtPoint(e.getPoint())];
                    }
                    dm.setColumnIdentifiers(header);

                    // movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID"));
                    //movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID Requerente"));
                }
            }
        });
        movimentacoesTable.getTableHeader().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (movimentacoesTable.columnAtPoint(e.getPoint()) == APPROVE || movimentacoesTable.columnAtPoint(e.getPoint()) == REPROVE) {
                    movimentacoesTable.getTableHeader().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    movimentacoesTable.getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        movimentacoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                movimentacoesTable.getTableHeader().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void initComponents() {
        String[] header = {"ID", "Nome", "Modelo", "Lote", "Saldo", "Armazém", "Receber", "Retirar" };

        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        stocakiLabel.setIcon(stocaki_icon);

        Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICAO_OPR);
        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACAO_OPR);

        //searchLabel.setIcon(search_icon);
        //searchLabel.setText("");

        movimentacoesTable.setSelectionBackground(Framework.SOFTGRAY);
        movimentacoesTable.setFont(Framework.TABLE_BODY);

        movimentacoesTable.getTableHeader().setReorderingAllowed(false);
        movimentacoesTable.getTableHeader().setFont(Framework.TABLE_HEADER);

        try{
            produtos = produtoDAO.readProdutos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
        }

        dm.setColumnIdentifiers(header);
        movimentacoesTable.setModel(dm);

        if (produtos != null) {
            for (Produto produto:
                    produtos) {
                dm.addRow(new Object[]{produto.getId_produto(), produto.getNome(), produto.getModelo(), /*produto.getDescricao(), produto.getClassificacao(),*/ produto.getLote(), /*produto.getCor(),*/ produto.getSaldo(), produto.getId_armazem(), sum_icon, sub_icon});
            }
        }

    }

}

