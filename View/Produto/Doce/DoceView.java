package View.Produto.Doce;

import View.Produto.Doce.Listeners.*;
import View.Produto.ProdutoView;
import java.awt.BorderLayout;
import  java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class DoceView extends JFrame {
    // Variáveis importantes
    private ProdutoView paginaProduto;
    //botão que retorna pro botão menu de produto
    private JButton btnVoltar;
    //tipo de doce selecionado
    private String doceSelecionado = null;
    private JButton btnFinalizarBr; 

    //escolher tipo do doce (brigadeiro, trufa, beijinho)
    JMenu menuOpcoes = new JMenu("Tipo");
    JMenuItem itemBrigadeiro = new JMenuItem("Brigadeiro");
    JMenuItem itemTrufa = new JMenuItem("Trufa");
    JMenuItem itemBeijinho = new JMenuItem("Beijinho");

    private JPanel painelCentro;
    private JPanel painelFinalizar;
    //quantidade de doce
    JTextField txtNumero = new JTextField(10);

    public DoceView(ProdutoView paginaProduto) {
        setTitle("Personalização de Doces");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //Layout
        setLayout(new BorderLayout());

        // Inicializa a página anterior
        this.paginaProduto = paginaProduto;

        // Botão Voltar
        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 400, 100, 30);
        ListenerBtnVoltarDoce l1 = new ListenerBtnVoltarDoce(this);
        btnVoltar.addActionListener(l1);
        painelVoltar.add(btnVoltar);
        add(painelVoltar, BorderLayout.SOUTH);

        // Menu Escolher Tipo do Doce
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuOpcoes);
        setJMenuBar(menuBar);

        //itens do menu
        menuOpcoes.add(itemBrigadeiro);
        menuOpcoes.add(itemTrufa);
        menuOpcoes.add(itemBeijinho);

        //Painel Central
        JPanel painelCentro = new JPanel(new GridLayout(3, 1, 10, 10));


        // Campo de texto para quantidade do doce
        JPanel painelQtd = new JPanel();
        painelQtd.add(new JLabel("Quantidade:"));
        painelQtd.add(txtNumero);
       // txtNumero.setBounds(150, 50, 100, 30);

        //JMenuItem brigadeiro
        ListenerItemBrigadeiro l4 = new ListenerItemBrigadeiro(this);
        itemBrigadeiro.addActionListener(l4);
        //JMenuItem trufa
        ListenerItemTrufa l5 = new ListenerItemTrufa(this);
        itemTrufa.addActionListener(l5);
        //JMenuItem beijinho
        ListenerItemBeijinho l6 = new ListenerItemBeijinho(this);
        itemBeijinho.addActionListener(l6);

        //Btn Finalizar
        JPanel painelFinalizar = new JPanel();
        btnFinalizarBr = new JButton("Finalizar");
        btnFinalizarBr.setBounds(150, 100, 100, 30);
        add(btnFinalizarBr);
        ListenerBtnFinalizar l2 = new ListenerBtnFinalizar(this);
        btnFinalizarBr.addActionListener(l2);
            
       painelCentro.add(painelQtd);
        painelCentro.add(painelFinalizar);

        add(painelCentro, BorderLayout.CENTER);
        setJMenuBar(menuBar);
        setVisible(true);
    }
    
    //getters
    public JButton getBtnVoltar() {
        return btnVoltar;
    }

    public JMenuItem getItemBrigadeiro() {
        return itemBrigadeiro;
    }

    public JMenuItem getItemTrufa() {
        return itemTrufa;
    }

    public JMenuItem getItemBeijinho() {
        return itemBeijinho;
    }

    public String getTxtNumero() {
        return txtNumero.getText();
    }
    
    public void setDoceSelecionado(String doce) {
        this.doceSelecionado = doce;
    }

    public String getDoceSelecionado() {
        return doceSelecionado;
    }

    public ProdutoView getProdutoView() {
        return paginaProduto;
    }
    
}
