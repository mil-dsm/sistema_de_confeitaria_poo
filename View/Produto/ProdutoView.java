
package View.Produto;
import java.awt.BorderLayout;
import javax.swing.*;
//import View.Encomenda.EncomendaView;

// import View.Produto.Listeners.*;

public class ProdutoView extends JFrame {

    private JButton btnVoltar; //volta pra EncomendaView

   // EncomendaView EncomendaAtual;

    //Personalizar Produto
    JMenuBar menuBar = new JMenuBar();
    JMenu menuOpcoes = new JMenu("Produtos");
    JMenuItem btnDoce = new JMenuItem("Doce");
    JMenuItem btnBolo = new JMenuItem("Bolo");
    JMenuItem btnDonut = new JMenuItem("Donut");

    public ProdutoView() { //EncomendaView encomendaAtual
        setTitle("Menu de Produtos");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null);

       // this.EncomendaAtual = encomendaAtual;
        //Layout
        setLayout(new BorderLayout());

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 400, 100, 30);
        JPanel painelBotaoVoltar = new JPanel();
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);
        painelBotaoVoltar.add(btnVoltar);

        // Menu Personalizar Produto
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuOpcoes);
        
        //itens do menu
        menuOpcoes.add(btnDoce);
        menuOpcoes.add(btnBolo);
        menuOpcoes.add(btnDonut);

        
        ListenerBtnDoce l2 = new ListenerBtnDoce(this);
        btnDoce.addActionListener(l2);
        ListenerBtnBolo l3 = new ListenerBtnBolo(this);
        btnBolo.addActionListener(l3);
        ListenerBtnDonut l4 = new ListenerBtnDonut(this);
        btnDonut.addActionListener(l4);

        add(painelBotaoVoltar);
        setJMenuBar(menuBar);
        setVisible(true);
    }
    //getters
        public JButton getBtnVoltar() { return btnVoltar; }
        public JMenuItem getBtnDoce() { return btnDoce; }
        public JMenuItem getBtnBolo() { return btnBolo; }
        public JMenuItem getBtnDonut() { return btnDonut; }

    public static void main(String[] args) {
        new ProdutoView();
    }
}
