package View.MenuInicialView;

import javax.swing.*;

public class LojaView extends JFrame {
    public LojaView() {
        setTitle("Loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 200);

        JButton btnCadastrar = new JButton("Cadastro");
        JButton btnEncomenda = new JButton("Encomenda");

        JPanel painel = new JPanel();
        painel.add(btnCadastrar);
        painel.add(btnEncomenda);
        
        add(painel);
        setVisible(true);
    }
}