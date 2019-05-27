package View;

import DAO.ArmazemDAO;
import DAO.MovimentacaoDAO;
import Model.Movimentacao;
import Model.Requisicao;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Movimentacoes extends JFrame{
    private JPanel rootPanel;
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel searchPanel;
    private JTextField searchBar;
    private JLabel searchLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JScrollPane movimentacoesScroll;
    private JTable movimentacoesTable;
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

    private List<Movimentacao> movimentacaoes = null;
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private ArmazemDAO armazemDAO = new ArmazemDAO();

    private List<Object[]> removedRows = new ArrayList<Object[]>();
    private List<Integer> removedRowsPos = new ArrayList<Integer>();
    private int coloredIcon = -1;

    private ImageIcon stocaki_icon = new ImageIcon(Framework.ICONE_CAIXA);
    private ImageIcon approve_icon = new ImageIcon(Framework.ICONE_APROVAR);
    private ImageIcon reject_icon = new ImageIcon(Framework.ICONE_DELETAR);
    private ImageIcon approve_green = new ImageIcon(Framework.ICONE_APROVAR_VERDE);
    private ImageIcon reject_red = new ImageIcon(Framework.ICONE_DELETAR_VERMELHO);
    private ImageIcon search_icon = new ImageIcon(Framework.ICONE_BUSCA);

    private static final int APPROVE = 8,
            REPROVE = 9;

    Movimentacoes() {
        initComponents();
        Framework.setup(this, menuPanel);

        /*searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                for (int i = dm.getRowCount()-1; i >= 0; i--) {
                    List<Object> removedRow = new ArrayList<Object>();
                    boolean remove = true;
                    for (int j = 0; j < dm.getColumnCount(); j++) {
                        if (j < dm.getColumnCount()-2) {
                            if (movimentacoesTable.getValueAt(i,j).toString().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                                remove = false;
                                break;
                            }
                        }
                        removedRow.add(dm.getValueAt(i,j));
                    }
                    if (remove) {
                        removedRows.add(removedRow.toArray());
                        removedRowsPos.add(i);
                        dm.removeRow(i);
                    }
                }

                int count = 0;

                for (int i = removedRows.size()-1; i >= 0; i--) {
                    Object[] removedRow = removedRows.get(i);
                    for (Object removedCell:
                            removedRow) {
                        if (removedCell.toString().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                            dm.addRow(removedRow);
                            removedRows.remove(removedRow);
                            count++;
                            break;
                        }
                    }
                }

                int check = 0;
                int k;

                for (k = 0; k < movimentacoesTable.getColumnCount()-2; k++) {
                    if (movimentacoesTable.getColumnName(k).contains("▼ ")) {
                        check = 1;
                        break;
                    }
                    if (movimentacoesTable.getColumnName(k).contains("▲ ")) {
                        check = 2;
                        break;
                    }
                }

                while(count > 0) {
                    switch (check) {
                        case 2:
                            for (int j = 0; j < dm.getRowCount()-count; j++) {
                                if (movimentacoesTable.getValueAt(dm.getRowCount()-count,k).toString().compareToIgnoreCase(movimentacoesTable.getValueAt(j,k).toString()) > 0) {
                                    dm.moveRow(dm.getRowCount()-count,dm.getRowCount()-count,j);
                                    break;
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < dm.getRowCount()-count; j++) {
                                if (movimentacoesTable.getValueAt(dm.getRowCount()-count,k).toString().compareToIgnoreCase(movimentacoesTable.getValueAt(j,k).toString()) < 0) {
                                    dm.moveRow(dm.getRowCount()-count,dm.getRowCount()-count,j);
                                    break;
                                }
                            }
                            break;
                        case 0:
                            dm.moveRow(dm.getRowCount()-count, dm.getRowCount()-count, removedRowsPos.get(removedRowsPos.size()-1));
                            break;
                    }

                    removedRowsPos.remove(removedRowsPos.size()-1);
                    count--;
                }
            }
        });*/
        movimentacoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                //int answer;
                /*
                switch (movimentacoesTable.columnAtPoint(e.getPoint())) {
                    case APPROVE:
                        answer = JOptionPane.showConfirmDialog(movimentacoesTable, "Tem certeza que deseja APROVAR a requisição?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Requisicao requisicao = new Requisicao();
                            Object[] selection_values;

                            try {
                                selection_values = armazemDAO.readIdArmazens().toArray();

                                answer = Integer.parseInt(JOptionPane.showInputDialog(movimentacoesTable, "Insira o ID do armazem no qual o produto deve ser cadastrado", "Escolha do Armazem", JOptionPane.INFORMATION_MESSAGE, stocaki_icon,selection_values,selection_values[0]).toString());

                                requisicao.setId_armazem(answer);
                                requisicao.setStatus_aprovacao("A");

                                requisicao.setId_requisicao(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),0).toString()));
                                requisicao.setId_funcionario(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),1).toString()));
                                requisicao.setNome(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),2).toString());
                                requisicao.setModelo(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),3).toString());
                                requisicao.setDescricao(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),4).toString());
                                requisicao.setClassificacao(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),5).toString());
                                requisicao.setLote(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),6).toString());
                                requisicao.setCor(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),7).toString());
                                requisicao.setSaldo(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),8).toString()));

                                try{
                                    requisicaoDAO.approveRequisicao(requisicao);
                                    dm.removeRow(movimentacoesTable.rowAtPoint(e.getPoint()));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                            }
                        }
                        break;
                    case REPROVE:
                        answer = JOptionPane.showConfirmDialog(movimentacoesTable, "Tem certeza que deseja REPROVAR a requisição?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Requisicao requisicao = new Requisicao();

                            requisicao.setStatus_aprovacao("R");

                            requisicao.setId_requisicao(Integer.parseInt(dm.getValueAt(movimentacoesTable.rowAtPoint(e.getPoint()),0).toString()));

                            try{
                                requisicaoDAO.approveRequisicao(requisicao);
                                dm.removeRow(movimentacoesTable.rowAtPoint(e.getPoint()));
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                            }
                        }
                        break;
                    default:
                        break;
                } */
            }
        });
        /*movimentacoesTable.addMouseMotionListener(new MouseMotionAdapter() {
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
        }); */
        /*movimentacoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);

                movimentacoesTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

                if (coloredIcon != -1) {
                    try {
                        movimentacoesTable.setValueAt(approve_icon, coloredIcon, APPROVE);
                        movimentacoesTable.setValueAt(reject_icon, coloredIcon, REPROVE);
                    } catch (Exception ex) {
                        //pass
                    }
                    coloredIcon = -1;
                }
            }
        });*/
        /*movimentacoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] header = { "ID", "ID Requerente", "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "Requerente", "Aprovar", "Reprovar" };

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
                        header[movimentacoesTable.columnAtPoint(e.getPoint())+2] = "▲ " + header[movimentacoesTable.columnAtPoint(e.getPoint())+2];
                    } else {
                        for (int i = 0; i < dm.getRowCount(); i++) {
                            for (int j = 0; j < i; j++) {
                                if (movimentacoesTable.getValueAt(i, movimentacoesTable.columnAtPoint(e.getPoint())).toString().compareToIgnoreCase(movimentacoesTable.getValueAt(j, movimentacoesTable.columnAtPoint(e.getPoint())).toString()) < 0) {
                                    dm.moveRow(i,i,j);
                                    break;
                                }
                            }
                        }
                        header[movimentacoesTable.columnAtPoint(e.getPoint())+2] = "▼ " + header[movimentacoesTable.columnAtPoint(e.getPoint())+2];
                    }
                    dm.setColumnIdentifiers(header);

                    //movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID"));
                    //movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID Requerente"));
                }
            }
        });*/
        /*movimentacoesTable.getTableHeader().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (movimentacoesTable.columnAtPoint(e.getPoint()) == APPROVE || movimentacoesTable.columnAtPoint(e.getPoint()) == REPROVE) {
                    movimentacoesTable.getTableHeader().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    movimentacoesTable.getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });*/
        /*movimentacoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                movimentacoesTable.getTableHeader().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });*/
    }

    private void initComponents() {
        String[] header = {"ID", "ID Requerente", "Data/Hora", "Movimento", "Saldo", "Produto", "Funcionario", "Aprovar", "Reprovar" };

        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        stocakiLabel.setIcon(stocaki_icon);

        searchLabel.setIcon(search_icon);
        searchLabel.setText("");

        movimentacoesTable.setSelectionBackground(Framework.SOFTGRAY);
        movimentacoesTable.setFont(Framework.TABLE_BODY);

        movimentacoesTable.getTableHeader().setReorderingAllowed(false);
        movimentacoesTable.getTableHeader().setFont(Framework.TABLE_HEADER);

        try{
            movimentacaoes = movimentacaoDAO.readMovimentacoes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(movimentacoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
        }

        dm.setColumnIdentifiers(header);
        movimentacoesTable.setModel(dm);

        if (movimentacaoes != null) {
            for (Movimentacao movimentacoes:
                    movimentacaoes) {
                dm.addRow(new Object[]{movimentacoes.getDataEHora(), movimentacoes.getMovimentacaoType(), movimentacoes.getSaldo(), movimentacoes.getId_produto(), movimentacoes.getId_funcionario(), approve_icon, reject_icon});
            }
        }

        movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID"));
        movimentacoesTable.getColumnModel().removeColumn(movimentacoesTable.getColumn("ID Requerente"));

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOES_ADM);
        Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM);
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }

}
