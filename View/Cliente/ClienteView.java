package View.Cliente;

import javax.swing.*;
import View.Cliente.Listeners.*;
import java.awt.*;

public class ClienteView extends JFrame {

    public JTextField txtCpf;
    public JTextField txtNome;
    public JComboBox<String> cbEndereco;

    public JButton btnCadastrar;
    public JButton btnBuscar;
    public JButton btnVoltar;

    public JLabel lblResultado;

    public ClienteView() {

        setTitle("Cadastro de Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(319, 223);
        setLocationRelativeTo(null);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setHorizontalAlignment(SwingConstants.CENTER);
        txtCpf = new JTextField(15);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setHorizontalAlignment(SwingConstants.CENTER);
        txtNome = new JTextField(15);

        JLabel lblEndereco = new JLabel("Bairro:");
        lblEndereco.setHorizontalAlignment(SwingConstants.CENTER);
        cbEndereco = new JComboBox<>(new String[]{
            "Selecione...",
            "Alto São Francisco",
            "Campo Velho",
            "Centro",
            "Campo Novo",
            "Herval",
            "Carrascal"
        });

        btnCadastrar = new JButton("Cadastrar");
        btnBuscar = new JButton("Buscar");

        lblResultado = new JLabel("");

        JPanel painelDados = new JPanel(new GridLayout(3, 2, 5, 5));
        painelDados.add(lblCpf);
        painelDados.add(txtCpf);
        painelDados.add(lblNome);
        painelDados.add(txtNome);
        painelDados.add(lblEndereco);
        painelDados.add(cbEndereco);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        painelBotoes.add(btnCadastrar);
        btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnVoltar);
        btnVoltar.addActionListener(new ListenerBtnVoltarCliente(this));
        painelBotoes.add(btnBuscar);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(lblResultado, BorderLayout.CENTER);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.add(painelDados, BorderLayout.NORTH);
        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        getContentPane().add(painelPrincipal);

        btnCadastrar.addActionListener(new ListenerBtnCadastrarCliente(this));
        btnBuscar.addActionListener(new ListenerBtnBuscarCliente(this));

        setVisible(true);
    }
}