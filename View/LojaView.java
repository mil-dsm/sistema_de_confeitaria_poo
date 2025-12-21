package View;

import javax.swing.*;

public class LojaView extends JFrame {
    public LojaView() {
        setTitle("Loja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lbNome = new JLabel("Nome");
        JTextField tfNome = new JTextField(15);

        JLabel lbCPF = new JLabel("CPF");
        JTextField tfCPF = new JTextField(15);

        JLabel lbEndereco = new JLabel("Endereco");
        JTextField tfEndereco = new JTextField(15);

        JButton btnCadastrar = new JButton("Cadastrar");

        JPanel painel = new JPanel();
        painel.add(lbNome);
        painel.add(tfNome);

        painel.add(lbCPF);
        painel.add(tfCPF);

        painel.add(lbEndereco);
        painel.add(tfEndereco);

        painel.add(btnCadastrar);
        
        add(painel);
        setVisible(true);
    }
}