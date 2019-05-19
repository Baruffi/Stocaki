package View;

import javax.swing.*;

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

    RequisicoesAdm() {
        initComponents();
        Framework.setup(this, menuPanel);
    }

    private void initComponents() {
        menuPanel.setOpaque(false);
        topbarPanel.setOpaque(false);
        bodyPanel.setOpaque(false);

        ImageIcon icon = new ImageIcon(Framework.ICONE_CAIXA);
        stocakiLabel.setIcon(icon);

        Framework.addToMenu(mlistPanel,this, Framework.VIEW.MOVIMENTACOES_ADM);
        // Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM); DISABLED!!!
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }

}
