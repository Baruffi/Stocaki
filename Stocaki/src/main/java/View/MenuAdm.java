package View;

import javax.swing.*;

public class MenuAdm extends JFrame {
    private JPanel menuPanel;
    private JPanel titlePanel;
    private JLabel titleLabel;
    private JPanel funcionariosPanel;
    private JLabel flistLabel;
    private JLabel fcadLabel;
    private JPanel topbarPanel;
    private JLabel stocakiLabel;
    private JPanel bodyPanel;
    private JPanel sidePanel;
    private JPanel ptitlePanel;
    private JLabel ptitleLabel;
    private JPanel ftitlePanel;
    private JLabel ftitleLabel;
    private JPanel rlistPanel;
    private JPanel mlistPanel;
    private JPanel plistPanel;
    private JPanel pcadPanel;
    private JPanel flistPanel;
    private JPanel fcadPanel;
    private JLabel rlistLabel;
    private JLabel mlistLabel;
    private JLabel plistLabel;
    private JLabel pcadLabel;
    private JPanel produtosPanel;
    private JPanel pgroupPanel;
    private JPanel fgroupPanel;

    MenuAdm() {
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
        Framework.addToMenu(rlistPanel,this, Framework.VIEW.REQUISICOES_ADM);
        Framework.addToMenu(plistPanel,this, Framework.VIEW.PRODUTOS_ADM);
        Framework.addToMenu(pcadPanel,this, Framework.VIEW.PRODUTO_ADM);
        Framework.addToMenu(flistPanel,this, Framework.VIEW.FUNCIONARIOS_ADM);
        Framework.addToMenu(fcadPanel,this, Framework.VIEW.FUNCIONARIO_ADM);
    }

}
