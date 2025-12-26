package View.MenuInicial;

import javax.swing.*;
import View.MenuInicial.Listeners.ListenerBtnEncomenda;
import View.MenuInicial.Listeners.ListenerBtnSair;

public class MenuInicialView extends JFrame {
    public MenuInicialView() {
        setTitle("Menu Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(350, 450);

        JButton btnCadastrar = new JButton("Cadastro");
        ListenerBtnCadastrar l1 = new ListenerBtnCadastrar(this);
        btnCadastrar.addActionListener(l1);

        JButton btnEncomenda = new JButton("Encomenda");
        ListenerBtnEncomenda l2 = new ListenerBtnEncomenda();
        btnEncomenda.addActionListener(l2);

        JButton btnSair = new JButton("Sair");
        ListenerBtnSair l3 = new ListenerBtnSair();
        btnSair.addActionListener(l3);

        JPanel painel = new JPanel();
        painel.add(btnCadastrar);
        painel.add(btnEncomenda);
        painel.add(btnSair);
        
        add(painel);
        setVisible(true);
    }
}