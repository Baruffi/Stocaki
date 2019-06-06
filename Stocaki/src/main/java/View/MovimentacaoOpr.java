package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;


import DAO.MovimentacaoDAO;
import DAO.ArmazemDAO;
import DAO.ProdutoDAO;
import Model.Movimentacao;
import Model.Produto;
import org.jetbrains.annotations.Contract;



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
        public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }
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
    private ImageIcon sum_icon_black = new ImageIcon(Framework.ICONE_SOMAR_PRETO);
    private ImageIcon sub_icon_black = new ImageIcon(Framework.ICONE_SUBTRAIR_PRETO);
    private ImageIcon sum_icon_grey = new ImageIcon(Framework.ICONE_SOMAR_CINZA);
    private ImageIcon sub_icon_grey = new ImageIcon(Framework.ICONE_SUBTRAIR_CINZA);

    private static final int SOMAR = 6,
                             SUBTRAIR = 7;


    MovimentacaoOpr() {
        initComponents();
        Framework.setup(this, menuPanel);

        movimentacoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int answer;

                switch (movimentacoesTable.columnAtPoint(e.getPoint())) {
                    case SOMAR:
                        answer = JOptionPane.showConfirmDialog(movimentacoesTable, "Tem certeza que deseja RECEBER este material?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Movimentacao movimentacao = new Movimentacao();
                            Produto produto = new Produto();

                            try {
                                int values = Integer.parseInt(JOptionPane.showInputDialog(movimentacoesTable, "Informe a quantidade"));
                                int sum = Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()), 4).toString()) + values;

                                movimentacao.setMovimentacaoType("E");
                                movimentacao.setId_produto(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),0).toString()));
                                movimentacao.setSaldo(sum);
                                movimentacao.setId_funcionario(Framework.getCurrentUser().getId_funcionario());

                                produto.setId_produto(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),0).toString()));
                                produto.setSaldo(sum);

                                try{
                                    MovimentacaoDAO.regristateMovimentacao(movimentacao);
                                    produtoDAO.updateSaldo(produto);
                                    new MovimentacaoOpr();
                                    dispose();


                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                                    System.out.println(ex);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                                System.out.println(ex);
                            }
                        }
                        break;
                    case SUBTRAIR:
                        int values = Integer.parseInt(JOptionPane.showInternalInputDialog(movimentacoesTable, "Informe a quantidade"));
                        if (Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()), 4).toString()) > 0)  {
                            answer = JOptionPane.showConfirmDialog(movimentacoesTable, "Tem certeza que deseja RETIRAR este material? \n Quantidade: " + values, "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                            if (answer == JOptionPane.YES_OPTION) {
                                Movimentacao movimentacao = new Movimentacao();
                                Produto produto = new Produto();

                                int sub = Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()), 4).toString()) - values;
                                try{
                                    movimentacao.setMovimentacaoType("S");
                                    movimentacao.setId_produto(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),0).toString()));
                                    movimentacao.setSaldo(sub);
                                    movimentacao.setId_funcionario(Framework.getCurrentUser().getId_funcionario());

                                    produto.setId_produto(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),0).toString()));
                                    produto.setSaldo(sub);

                                    try{
                                        MovimentacaoDAO.regristateMovimentacao(movimentacao);
                                        produtoDAO.updateSaldo(produto);
                                        new MovimentacaoOpr();
                                        dispose();

                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                                        System.out.println(ex);
                                    }


                                }catch (Exception ex) {
                                    JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                                    System.out.println(ex);
                                }
                            }

                        }
                        else{
                            JOptionPane.showConfirmDialog(movimentacoesTable, "Saldo insuficiente!\n Movimentação não realizada.", "Aviso Stocaki", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                            new MovimentacaoOpr();
                            dispose();
                        }

                        break;
                    default:
                        break;
                }

            }
        });

        movimentacoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                if (coloredIcon != -1) {
                    try {
                        movimentacoesTable.setValueAt(sum_icon_grey, coloredIcon, SOMAR);
                        movimentacoesTable.setValueAt(sub_icon_grey, coloredIcon, SUBTRAIR);
                    } catch (Exception ex) {
                        //pass
                    }
                    coloredIcon = -1;
                }
            }
        });
        movimentacoesTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (coloredIcon != -1) {
                    try {
                        movimentacoesTable.setValueAt(sum_icon_black, coloredIcon, SOMAR);
                        movimentacoesTable.setValueAt(sub_icon_black, coloredIcon, SUBTRAIR);
                    } catch (Exception ex) {
                        //pass
                    }
                    coloredIcon = -1;
                }

                if (movimentacoesTable.columnAtPoint(e.getPoint()) == SOMAR) {
                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    movimentacoesTable.setValueAt(sum_icon_black, movimentacoesTable.rowAtPoint(e.getPoint()), SOMAR);
                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
                } else if (movimentacoesTable.columnAtPoint(e.getPoint()) == SUBTRAIR) {
                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    movimentacoesTable.setValueAt(sub_icon_black, movimentacoesTable.rowAtPoint(e.getPoint()), SUBTRAIR);
                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
                } else {
                    movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        movimentacoesTable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (coloredIcon != -1) {
                    try {
                        movimentacoesTable.setValueAt(sum_icon_grey, coloredIcon, SOMAR);
                        movimentacoesTable.setValueAt(sub_icon_grey, coloredIcon, SUBTRAIR);
                    } catch (Exception ex) {
                        //pass
                    }
                    coloredIcon = -1;
                }

                if (movimentacoesTable.columnAtPoint(e.getPoint()) == SOMAR) {
                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    movimentacoesTable.setValueAt(sum_icon_grey, movimentacoesTable.rowAtPoint(e.getPoint()), SOMAR);
                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
                } else if (movimentacoesTable.columnAtPoint(e.getPoint()) == SUBTRAIR) {
                    movimentacoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    movimentacoesTable.setValueAt(sub_icon_grey, movimentacoesTable.rowAtPoint(e.getPoint()), SUBTRAIR);
                    coloredIcon = movimentacoesTable.rowAtPoint(e.getPoint());
                } else {
                    movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });

        movimentacoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] header = {"ID", "Nome", "Modelo", "Lote", "Saldo", "Armazém", "Receber", "Retirar" };

                if(!(movimentacoesTable.columnAtPoint(e.getPoint()) == SOMAR || movimentacoesTable.columnAtPoint(e.getPoint()) == SUBTRAIR)) {
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

                    //movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID"));
                    //movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID Requerente"));
                }
            }
        });
        movimentacoesTable.getTableHeader().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (movimentacoesTable.columnAtPoint(e.getPoint()) == SOMAR || movimentacoesTable.columnAtPoint(e.getPoint()) == SUBTRAIR) {
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
                dm.addRow(new Object[]{produto.getId_produto(), produto.getNome(), produto.getModelo(), /*produto.getDescricao(), produto.getClassificacao(),*/
                        produto.getLote(), /*produto.getCor(),*/ produto.getSaldo(), produto.getId_armazem(), sum_icon_black, sub_icon_black});
            }
        }

    }

}

