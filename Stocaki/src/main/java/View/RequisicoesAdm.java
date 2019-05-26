package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import DAO.ArmazemDAO;
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

    private List<Requisicao> requisicoes = null;
    private RequisicaoDAO requisicaoDAO = new RequisicaoDAO();
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

    private static final String[] header = { "ID", "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "ID Requerente", "Requerente", "Aprovar", "Reprovar" };

    private static final int APPROVE = 10,
                             REPROVE = 11;

    RequisicoesAdm() {
        initComponents();
        Framework.setup(this, menuPanel);

        searchBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                for (int i = dm.getRowCount()-1; i >= 0; i--) {
                    List<Object> removedRow = new ArrayList<Object>();
                    boolean remove = true;
                    for (int j = 0; j < dm.getColumnCount(); j++) {
                        if (requisicoesTable.getValueAt(i,j).toString().toLowerCase().contains(searchBar.getText().toLowerCase())) {
                            remove = false;
                            break;
                        }
                        removedRow.add(requisicoesTable.getValueAt(i,j));
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

                for (k = 0; k < dm.getColumnCount()-2; k++) {
                    if (dm.getColumnName(k).contains("▼ ")) {
                        check = 1;
                        break;
                    }
                    if (dm.getColumnName(k).contains("▲ ")) {
                        check = 2;
                        break;
                    }
                }

                while(count > 0) {
                    switch (check) {
                        case 2:
                            for (int j = 0; j < dm.getRowCount()-count; j++) {
                                if (requisicoesTable.getValueAt(dm.getRowCount()-count,k).toString().compareToIgnoreCase(requisicoesTable.getValueAt(j,k).toString()) > 0) {
                                    dm.moveRow(dm.getRowCount()-count,dm.getRowCount()-count,j);
                                    break;
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < dm.getRowCount()-count; j++) {
                                if (requisicoesTable.getValueAt(dm.getRowCount()-count,k).toString().compareToIgnoreCase(requisicoesTable.getValueAt(j,k).toString()) < 0) {
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
        });
        requisicoesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int answer;

                switch (requisicoesTable.columnAtPoint(e.getPoint())) {
                    case APPROVE:
                        answer = JOptionPane.showConfirmDialog(requisicoesTable, "Tem certeza que deseja APROVAR a requisição?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Requisicao requisicao = new Requisicao();
                            Object[] selection_values;

                            try {
                                selection_values = armazemDAO.readIdArmazens().toArray();

                                answer = Integer.parseInt(JOptionPane.showInputDialog(requisicoesTable, "Insira o ID do armazem no qual o produto deve ser cadastrado", "Escolha do Armazem", JOptionPane.INFORMATION_MESSAGE, stocaki_icon,selection_values,selection_values[0]).toString());

                                requisicao.setId_armazem(answer);
                                requisicao.setStatus_aprovacao("A");

                                requisicao.setId_requisicao(Integer.parseInt(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),0).toString()));
                                requisicao.setNome(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),1).toString());
                                requisicao.setModelo(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),2).toString());
                                requisicao.setDescricao(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),3).toString());
                                requisicao.setClassificacao(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),4).toString());
                                requisicao.setLote(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),5).toString());
                                requisicao.setCor(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),6).toString());
                                requisicao.setSaldo(Integer.parseInt(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),7).toString()));
                                requisicao.setId_funcionario(Integer.parseInt(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),8).toString()));

                                try{
                                    requisicaoDAO.approveRequisicao(requisicao);
                                    dm.removeRow(requisicoesTable.rowAtPoint(e.getPoint()));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(requisicoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(requisicoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                            }
                        }
                        break;
                    case REPROVE:
                        answer = JOptionPane.showConfirmDialog(requisicoesTable, "Tem certeza que deseja REPROVAR a requisição?", "Aviso Stocaki", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, stocaki_icon);
                        if (answer == JOptionPane.YES_OPTION) {
                            Requisicao requisicao = new Requisicao();

                            requisicao.setStatus_aprovacao("R");

                            requisicao.setId_requisicao(Integer.parseInt(requisicoesTable.getValueAt(requisicoesTable.rowAtPoint(e.getPoint()),0).toString()));

                            try{
                                requisicaoDAO.approveRequisicao(requisicao);
                                dm.removeRow(requisicoesTable.rowAtPoint(e.getPoint()));
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(requisicoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
                            }
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
                    dm.setValueAt(approve_icon, coloredIcon, APPROVE);
                    dm.setValueAt(reject_icon, coloredIcon, REPROVE);
                    coloredIcon = -1;
                }

                if (requisicoesTable.columnAtPoint(e.getPoint()) == APPROVE) {
                    requisicoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    dm.setValueAt(approve_green, requisicoesTable.rowAtPoint(e.getPoint()), APPROVE);
                    coloredIcon = requisicoesTable.rowAtPoint(e.getPoint());
                } else if (requisicoesTable.columnAtPoint(e.getPoint()) == REPROVE) {
                    requisicoesTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    dm.setValueAt(reject_red, requisicoesTable.rowAtPoint(e.getPoint()), REPROVE);
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
                    dm.setValueAt(approve_icon, coloredIcon, APPROVE);
                    dm.setValueAt(reject_icon, coloredIcon, REPROVE);
                    coloredIcon = -1;
                }
            }
        });
        requisicoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String[] new_header = { "ID", "Nome", "Modelo", "Descrição", "Classificação", "Lote", "Cor", "Saldo", "ID Requerente", "Requerente", "Aprovar", "Reprovar" };

                if(!(requisicoesTable.columnAtPoint(e.getPoint()) == APPROVE || requisicoesTable.columnAtPoint(e.getPoint()) == REPROVE)) {
                    if (dm.getColumnName(requisicoesTable.columnAtPoint(e.getPoint())).contains("▼ ")) {
                        for (int i = 0; i < dm.getRowCount(); i++) {
                            for (int j = 0; j < i; j++) {
                                if (requisicoesTable.getValueAt(i,requisicoesTable.columnAtPoint(e.getPoint())).toString().compareToIgnoreCase(requisicoesTable.getValueAt(j,requisicoesTable.columnAtPoint(e.getPoint())).toString()) > 0) {
                                    dm.moveRow(i,i,j);
                                    break;
                                }
                            }
                        }
                        new_header[requisicoesTable.columnAtPoint(e.getPoint())] = "▲ " + new_header[requisicoesTable.columnAtPoint(e.getPoint())];
                        dm.setColumnIdentifiers(new_header);
                    } else {
                        for (int i = 0; i < dm.getRowCount(); i++) {
                            for (int j = 0; j < i; j++) {
                                if (requisicoesTable.getValueAt(i,requisicoesTable.columnAtPoint(e.getPoint())).toString().compareToIgnoreCase(requisicoesTable.getValueAt(j,requisicoesTable.columnAtPoint(e.getPoint())).toString()) < 0) {
                                    dm.moveRow(i,i,j);
                                    break;
                                }
                            }
                        }
                        new_header[requisicoesTable.columnAtPoint(e.getPoint())] = "▼ " + new_header[requisicoesTable.columnAtPoint(e.getPoint())];
                        dm.setColumnIdentifiers(new_header);
                    }
                }
            }
        });
        requisicoesTable.getTableHeader().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);

                if (requisicoesTable.columnAtPoint(e.getPoint()) == APPROVE || requisicoesTable.columnAtPoint(e.getPoint()) == REPROVE) {
                    requisicoesTable.getTableHeader().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    requisicoesTable.getTableHeader().setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
        requisicoesTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                requisicoesTable.getTableHeader().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        stocakiLabel.setIcon(stocaki_icon);

        searchLabel.setIcon(search_icon);
        searchLabel.setText("");

        requisicoesTable.setSelectionBackground(Framework.SOFTGRAY);
        requisicoesTable.setFont(Framework.TABLE_BODY);

        requisicoesTable.getTableHeader().setReorderingAllowed(false);
        requisicoesTable.getTableHeader().setFont(Framework.TABLE_HEADER);

        try{
            requisicoes = requisicaoDAO.readRequisicoes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(requisicoesTable, "Erro inesperado!", "ERRO", JOptionPane.ERROR_MESSAGE, new ImageIcon(Framework.ICONE_CAIXA));
        }

        dm.setColumnIdentifiers(header);
        requisicoesTable.setModel(dm);

//        dm.addRow(new Object[]{"1","teste1","teste","teste teste","testeC","testeX","Branca","6","1","Ronaldo",approve_icon,reject_icon});
//        dm.addRow(new Object[]{"2","teste2","teste","teste","testeD","testeL","Preta","7","2","Geraldo",approve_icon,reject_icon});
//        dm.addRow(new Object[]{"teste3","teste","teste","testeD","testeL","Preta","7","3","Michael",approve_icon,reject_icon});
//        dm.addRow(new Object[]{"4","micro geladeira","T13I173","Mini geladeira sem freezer com garantia de 2 anos","T13","Q2","Branca","0","2","Geraldo",approve_icon,reject_icon});

        if (requisicoes != null) {
            for (Requisicao requisicao:
                    requisicoes) {
                dm.addRow(new Object[]{requisicao.getId_requisicao(), requisicao.getNome(), requisicao.getModelo(), requisicao.getDescricao(), requisicao.getClassificacao(), requisicao.getLote(), requisicao.getCor(), requisicao.getSaldo(), requisicao.getId_funcionario(), requisicao.getNome_funcionario(), approve_icon, reject_icon});
            }
        }

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOES_ADM);
        // Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM); DISABLED!!!
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }
}
