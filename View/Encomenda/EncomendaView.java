package View.Encomenda;

import java.util.ArrayList;
import javax.swing.*;
import TO.EncomendaTO;
import View.Encomenda.Listeners.*;

public class EncomendaView extends JFrame {
    private EncomendaTO encomendaAtual;
    private JButton btnVoltar;
    private JTextField tfCpf;
    private JButton btnBuscarCriarEncomenda;
    private JTextArea taAreaProdutos;
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
        btnVoltar = new JButton("Voltar");
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);

        // Listener para o botão de Buscar / Criar Encomenda
        btnBuscarCriarEncomenda = new JButton("Buscar / Criar Encomenda");
        ListenerBtnBuscarCriarEncomenda l2 = new ListenerBtnBuscarCriarEncomenda(this, tfCpf);
        btnBuscarCriarEncomenda.addActionListener(l2);

        taAreaProdutos = new JTextArea(10, 25);
        taAreaProdutos.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(taAreaProdutos);

        // Listener para o botão Adicionar Produto
        btnAdicionarProduto = new JButton("Adicionar Produto");
        btnAdicionarProduto.setEnabled(false);
        // ListenerBtnAdicionarProduto l3 = new ListenerBtnAdicionarProduto(this);
        // btnAdicionarProduto.addActionListener(l3);

        // Listener para o botão Remover Produto
        btnRemoverProduto = new JButton("Remover Produto");
        btnRemoverProduto.setEnabled(false);
        // ListenerBtnRemoverProduto l4 = new ListenerBtnRemoverProduto(this);
        // btnRemoverProduto.addActionListener(l4);

        // Listener para o botão Finalizar Encomenda
        btnFinalizarEncomenda = new JButton("Finalizar Encomenda");
        btnFinalizarEncomenda.setEnabled(false);
        ListenerBtnFinalizarEncomenda l5 = new ListenerBtnFinalizarEncomenda(this, tfCpf);
        btnFinalizarEncomenda.addActionListener(l5);
        
        painel.add(btnVoltar);
        
        painel.add(lbCpf);
        painel.add(tfCpf);
        painel.add(btnBuscarCriarEncomenda);
        painel.add(scrollPane);
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

    // Método que habilita os botões relacionados à encomenda quando há uma encomenda ativa
    // e desabilita caso o contrário
    public void desabilitarBotoesEncomenda() {
        btnAdicionarProduto.setEnabled(false);
        btnRemoverProduto.setEnabled(false);
        btnFinalizarEncomenda.setEnabled(false);
    }

    // Método que atualiza a área que mostra os produtos da encomenda
    // A cada vez que adiciona ou remove um produto, essa área deve ser atualizada
    public void atualizarAreaProdutos(ArrayList<String> produtos) {
        taAreaProdutos.setText("");
        for(String produto : produtos) {
            taAreaProdutos.append(produto + "\n");
        }
    }
}