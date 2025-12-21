package View.EncomendaView;

import javax.swing.*;

import TO.EncomendaTO;
import View.EncomendaView.Listeners.*;

public class EncomendaView extends JFrame {
    // A tela apenas mostra uma encomenda por vez
    // Só é possível criar uma nova encomenda após finalizar a anterior
    private EncomendaTO encomendaAtual;
    private JTextField tfCpf;
    private JButton btnAdicionarProduto;
    private JButton btnRemoverProduto;
    private JButton btnFinalizarEncomenda;

    public EncomendaView() {
        setTitle("Encomenda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450);

        JPanel painel = new JPanel();

        JLabel lbCpf = new JLabel("CPF Cliente");
        tfCpf = new JTextField(15);

        // Listener para o botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);

        // Listener para o botão de Buscar / Criar Encomenda
        JButton btnBuscarCriarEncomenda = new JButton("Buscar / Criar Encomenda");
        ListenerBtnBuscarCriarEncomenda l2 = new ListenerBtnBuscarCriarEncomenda(this, tfCpf);
        btnBuscarCriarEncomenda.addActionListener(l2);

        // Listener para o botão Adicionar Produto
        btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.setEnabled(false);
        ListenerBtnAdicionarProduto l3 = new ListenerBtnAdicionarProduto(this);
        btnAdicionarProduto.addActionListener(l3);

        // Listener para o botão Remover Produto
        btnRemoverProduto = new JButton("Remover Produto");
        btnRemoverProduto.setEnabled(false);
        ListenerBtnRemoverProduto l4 = new ListenerBtnRemoverProduto(this);
        btnRemoverProduto.addActionListener(l4);

        // Listener para o botão Finalizar Encomenda
        JButton btnFinalizarEncomenda = new JButton("Finalizar Encomenda");
        btnFinalizarEncomenda.setEnabled(false);
        ListenerBtnFinalizarEncomenda l5 = new ListenerBtnFinalizarEncomenda(this, tfCpf);
        btnFinalizarEncomenda.addActionListener(l5);
        
        painel.add(btnVoltar);
        
        painel.add(lbCpf);
        painel.add(tfCpf);
        painel.add(btnBuscarCriarEncomenda);
        painel.add(btnAdicionarProduto);
        painel.add(btnRemoverProduto);
        painel.add(btnFinalizarEncomenda);

        add(painel);
        setVisible(true);
    }

    public EncomendaTO getEncomendaAtual() {
        return encomendaAtual;
    }

    public void setEncomendaAtual(EncomendaTO encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
    }

    public String getCpfCliente() {
        return tfCpf.getText();
    }

    public void habilitarBotoesEncomenda() {
        btnAdicionarProduto.setEnabled(true);
        btnRemoverProduto.setEnabled(true);
        btnFinalizarEncomenda.setEnabled(true);
    }

    public void desabilitarBotoesEncomenda() {
        btnAdicionarProduto.setEnabled(false);
        btnRemoverProduto.setEnabled(false);
        btnFinalizarEncomenda.setEnabled(false);
    }
}