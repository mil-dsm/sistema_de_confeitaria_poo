package View.Produto;

import javax.swing.*;
import java.awt.*;
import View.Encomenda.EncomendaView;
import View.Produto.Listeners.*;

// Finalizado
public class ProdutoView extends JFrame {
    // Atributos de manipulação
    EncomendaView encomendaAtual;
    // Botões
    private JButton btnVoltar; //volta pra EncomendaView
    //Personalizar Produto
    JMenuBar menuBar;
    JMenu menuOpcoes;
    JMenuItem btnDoce;
    JMenuItem btnBolo;
    JMenuItem btnDonut;

    public ProdutoView(EncomendaView encomendaAtual) {
        setTitle("Menu de Produtos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //Layout
        setLayout(new BorderLayout());

        // Inicialização de atributos
        this.encomendaAtual = encomendaAtual;

        // Menu Personalizar Produto
        menuBar = new JMenuBar();
        menuOpcoes = new JMenu("Produtos");

        //itens do menu
        btnDoce = new JMenuItem("Doce");
        btnBolo = new JMenuItem("Bolo");
        btnDonut = new JMenuItem("Donut");
        
        menuOpcoes.add(btnDoce);
        menuOpcoes.add(btnBolo);
        menuOpcoes.add(btnDonut);
        
        menuBar.add(menuOpcoes);
        setJMenuBar(menuBar);
        
        // Botões
        ListenerBtnDoce l2 = new ListenerBtnDoce(this);
        btnDoce.addActionListener(l2);
        ListenerBtnBolo l3 = new ListenerBtnBolo(this);
        btnBolo.addActionListener(l3);
        ListenerBtnDonut l4 = new ListenerBtnDonut(this);
        btnDonut.addActionListener(l4);
        
        // Painel central
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.add(new JLabel("Selecione um produto no menu acima"));
        add(painelCentral, BorderLayout.CENTER);
        
        // Painel inferior
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ListenerBtnVoltarProduto(this));
        painelInferior.add(btnVoltar);
        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }
    public JButton getBtnVoltar() { return btnVoltar; }
    public JMenuItem getBtnDoce() { return btnDoce; }
    public JMenuItem getBtnBolo() { return btnBolo; }
    public JMenuItem getBtnDonut() { return btnDonut; }

    public EncomendaView getEncomendaView() {
        return encomendaAtual;
    }
}