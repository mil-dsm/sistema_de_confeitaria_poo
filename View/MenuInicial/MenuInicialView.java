package View.MenuInicial;

import javax.swing.*;
import View.MenuInicial.Listeners.*;
import java.awt.*;

public class MenuInicialView extends JFrame {

    public MenuInicialView() {
        setTitle("Menu Inicial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(319, 223);
        setLocationRelativeTo(null);

        JButton btnCadastrar = new JButton("Cadastro");
        btnCadastrar.addActionListener(new ListenerBtnCadastrar(this));

        JButton btnEncomenda = new JButton("Encomenda");
        btnEncomenda.addActionListener(new ListenerBtnEncomenda(this));

        JButton btnHistoricoDePedidos = new JButton("Historico de Pedidos");
        btnHistoricoDePedidos.addActionListener(new ListenerBtnHistoricoDePedidos());

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(new ListenerBtnSair());

        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 0, 15));
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEncomenda);
        painelBotoes.add(btnHistoricoDePedidos);
        painelBotoes.add(btnSair);

        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.add(painelBotoes);

        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }
}