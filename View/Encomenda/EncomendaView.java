package View.Encomenda;

import java.util.ArrayList;
import javax.swing.*;
import Arq.*;
import TO.ClienteTO;
import TO.EncomendaTO;
import View.Encomenda.Listeners.*;

public class EncomendaView extends JFrame {
    // Encomenda a ser trabalhada e cliente vinculado
    private ClienteTO clienteAtual;
    private EncomendaTO encomendaAtual;
    // Arquivos
    private ManipulaArquivosCliente arqCliente;
    private ManipulaArquivosEncomenda arqEncomenda;
    private ManipulaArquivosProdutos arqProduto;
    // Botão de retorna para o menu inicial
    private JButton btnVoltar;
    // Adicionar o CPF do cliente CADASTRADO
    private JLabel lbCpf;
    private JTextField tfCpf;
    // Botão de buscar/criar encomenda
    private JButton btnBuscarCriarEncomenda;
    // Seleção do tipo da entrega
    private JLabel lbTipoEntrega;
    private JRadioButton rbEntrega;
    private JRadioButton rbRetirada;
    // Área de mostrar os produtos da encomenda
    private JTextArea taAreaProdutos;
    // Informações sobre o frete
    private JLabel lbValorFrete;
    private JTextField tfValorFrete;
    // Informações sobre o total
    private JLabel lbTotal;
    private JTextField tfTotal;
    // Botão de adicionar produto
    private JButton btnAdicionarProduto;
    // Botão de remover produto
    private JButton btnRemoverProduto;
    // Botão de finalizar a encomenda
    private JButton btnFinalizarEncomenda;

    public EncomendaView() {
        setTitle("Encomenda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 450);

        encomendaAtual = new EncomendaTO(clienteAtual, getTipoEntrega());
        clienteAtual = new ClienteTO();
        arqCliente = new ManipulaArquivosCliente();
        arqEncomenda = new ManipulaArquivosEncomenda();
        arqProduto = new ManipulaArquivosProdutos();

        JPanel panel = new JPanel();
        
        // Listener para o botão Voltar
        btnVoltar = new JButton("Voltar");
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);

        // Adicionar CPF
        lbCpf = new JLabel("CPF");
        tfCpf = new JTextField(15);

        // Listener para o botão de Buscar / Criar Encomenda
        btnBuscarCriarEncomenda = new JButton("Buscar / Criar Encomenda");
        ListenerBtnBuscarCriarEncomenda l2 = new ListenerBtnBuscarCriarEncomenda(this, tfCpf);
        btnBuscarCriarEncomenda.addActionListener(l2);

        // Selecionar o tipo de entrega
        lbTipoEntrega = new JLabel("Tipo de entrega");
        rbEntrega = new JRadioButton("Entrega");
        rbRetirada = new JRadioButton("Retirada na loja");

        // Mostrar os produtos da encomenda
        taAreaProdutos = new JTextArea(10, 25);
        taAreaProdutos.setEditable(false);
    
        // Mostrar o valor total do frete
        lbValorFrete = new JLabel("Valor do frete");
        tfValorFrete = new JTextField(10);
        tfValorFrete.setEditable(false);

        // Mostrar valor final da compra
        lbTotal = new JLabel("Total");
        tfTotal = new JTextField(10);
        tfTotal.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(taAreaProdutos);

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
        btnFinalizarEncomenda = new JButton("Finalizar Encomenda");
        btnFinalizarEncomenda.setEnabled(false);
        ListenerBtnFinalizarEncomenda l5 = new ListenerBtnFinalizarEncomenda(this, tfCpf);
        btnFinalizarEncomenda.addActionListener(l5);
        
        panel.add(scrollPane);
        panel.add(btnVoltar);
        panel.add(lbCpf);
        panel.add(tfCpf);
        panel.add(btnBuscarCriarEncomenda);
        panel.add(lbTipoEntrega);
        panel.add(rbEntrega);
        panel.add(rbRetirada);
        panel.add(taAreaProdutos);
        panel.add(lbValorFrete);
        panel.add(tfValorFrete);
        panel.add(lbTotal);
        panel.add(tfTotal);
        panel.add(btnAdicionarProduto);
        panel.add(btnRemoverProduto);
        panel.add(btnFinalizarEncomenda);

        add(panel);
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

    
    // Método que retorna qual o tipo de entrega selecionado
    public String getTipoEntrega() {
        if(rbEntrega.isSelected() == true) return "delivery";
        else return "retirada";
    }
    
    // Método que atualiza a área que mostra os produtos da encomenda
    // A cada vez que adiciona ou remove um produto, essa área deve ser atualizada
    public void atualizarAreaProdutos(ArrayList<String> codigos) {
        taAreaProdutos.setText("");

        for(String cod : codigos) {
            int codigo = Integer.parseInt(cod);
            String produto = arqProduto.getProdutoPorCodigo(codigo);
            taAreaProdutos.append(produto + "\n");
        }
    }

    // Método que atualiza o valor do frete a partir da opção escolhida.
    // Cria uma variável temporária que gerencia o frete a partir do endereço do cliente.
    public void atualizarFrete() {
        tfValorFrete.setText("");
        clienteAtual.setNome(arqCliente.getNome(getCpfCliente()));
        clienteAtual.setCpf(getCpfCliente());
        clienteAtual.setEndereco(arqCliente.getEndereco(getCpfCliente()));
        tfValorFrete.setText(String.valueOf(clienteAtual.estimarDistancia()));
    }
    
    // Método que atualiza o valor do total da compra
    // Acontece a chamada do método que calcula o novo valor total e soma do frete
    public void atualizarValorTotal(ArrayList<String> produtos) {
        tfTotal.setText("");
    }
    
    // Método que adiciona um produto à encomenda a partir do seu código único
    // Utiliza método de adicionar o elemento a encomenda aberta, e o método de
    // atualizar a página inicial da encomenda.
    public void adicionarProduto(int codigoProduto) {
        String cpf = getCpfCliente();
        arqEncomenda.adicionaProdutoEncomenda(cpf, codigoProduto);
        ArrayList<String> produtos = arqEncomenda.getProdutosEncomenda(cpf);
        atualizarAreaProdutos(produtos);
        atualizarValorTotal(produtos);
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
}