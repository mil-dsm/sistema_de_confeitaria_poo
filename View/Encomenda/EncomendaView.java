package View.Encomenda;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.*;
import Arq.*;
import TO.ClienteTO;
import TO.EncomendaTO;
import TO.ProdutoTO;
import View.Encomenda.Listeners.*;

public class EncomendaView extends JFrame {
    // Encomenda a ser trabalhada e cliente vinculado
    private ClienteTO clienteAtual;
    private EncomendaTO encomendaAtual;
    // Arquivos
    private ManipulaArquivosCliente arqCliente;
    private ManipulaArquivosEncomenda arqEncomenda;
    private ManipulaArquivosProdutos arqProduto;
    // Adicionar o CPF do cliente CADASTRADO
    private JLabel lbCpf;
    private JTextField tfCpf;
    // Seleção do tipo da entrega
    private JLabel lbTipoEntrega;
    private JRadioButton rbDelivery;
    private JRadioButton rbRetirada;
    // Área de mostrar os produtos da encomenda
    private JList<ProdutoTO> listaProdutos;
    private DefaultListModel<ProdutoTO> modelProdutos;
    // Informações sobre o frete
    private JLabel lbValorFrete;
    private JTextField tfValorFrete;
    // Informações sobre o total
    private JLabel lbTotal;
    private JTextField tfTotal;
    // Botões
    private JButton btnVoltar;
    private JButton btnBuscarCriarEncomenda;
    private JButton btnAdicionarProduto;
    private JButton btnRemoverProduto;
    private JButton btnFinalizarEncomenda;
    
    public EncomendaView() {
        setTitle("Encomenda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        
        /* Inicialização dos atributos */
        arqCliente = new ManipulaArquivosCliente();
        arqEncomenda = new ManipulaArquivosEncomenda();
        arqProduto = new ManipulaArquivosProdutos();
        clienteAtual = null;
        encomendaAtual = null;
        
        /* Painel CPF + Buscar */
        // Inicializa labels e TextField
        lbCpf = new JLabel("CPF");
        tfCpf = new JTextField(15);
        //Cria painel
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        // Adiciona retorno        
        btnVoltar = new JButton("Voltar");
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);
        // Adiciona pesquisa
        btnBuscarCriarEncomenda = new JButton("Buscar / Criar Encomenda");
        ListenerBtnBuscarCriarEncomenda l2 = new ListenerBtnBuscarCriarEncomenda(this, tfCpf);
        btnBuscarCriarEncomenda.addActionListener(l2);
        // Agrupa em um painel
        JPanel painelTopo = new JPanel();
        painelTopo.add(lbCpf);
        painelTopo.add(tfCpf);
        painelTopo.add(btnBuscarCriarEncomenda);
        painelTopo.add(btnVoltar);
        
        /* Painel tipo entrega */
        // Inicializa os labels e radio butons
        lbTipoEntrega = new JLabel("Tipo de entrega:");
        rbDelivery = new JRadioButton("Delivery");
        rbRetirada = new JRadioButton("Retirada na loja");
        // Cria o grupo
        ButtonGroup grupoEntrega = new ButtonGroup();
        grupoEntrega.add(rbDelivery);
        grupoEntrega.add(rbRetirada);
        rbRetirada.setSelected(true);
        // Agrupa em um painel
        JPanel painelEntrega = new JPanel();
        painelEntrega.add(lbTipoEntrega);
        painelEntrega.add(rbDelivery);
        painelEntrega.add(rbRetirada);
        // Atualiza o tipo de entrega toda vez que seleciona algo diferente
        rbDelivery.addActionListener(e -> atualizarTipoEntrega());
        rbRetirada.addActionListener(e -> atualizarTipoEntrega());

        /* Painel de visualização dos produtos */
        modelProdutos = new DefaultListModel<>();
        listaProdutos = new JList<>(modelProdutos);
        listaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Cria Scroll
        JScrollPane scrollProdutos = new JScrollPane(listaProdutos);
        scrollProdutos.setBorder(BorderFactory.createTitledBorder("Produtos na encomenda"));

        /* Painel de valores */
        // Mostrar o valor total do frete
        lbValorFrete = new JLabel("Valor do frete");
        tfValorFrete = new JTextField(10);
        tfValorFrete.setEditable(false);
        // Mostrar valor final da compra
        lbTotal = new JLabel("Total");
        tfTotal = new JTextField(10);
        tfTotal.setEditable(false);
        // Agrupa em um painel
        JPanel painelValores = new JPanel();
        painelValores.add(lbValorFrete);
        painelValores.add(tfValorFrete);
        painelValores.add(lbTotal);
        painelValores.add(tfTotal);

        /* Painel dos botões */
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
        // Agrupa em um painel
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnAdicionarProduto);
        painelBotoes.add(btnRemoverProduto);
        painelBotoes.add(btnFinalizarEncomenda);

        /* Montagem */
        panelPrincipal.add(painelTopo, BorderLayout.NORTH);
        panelPrincipal.add(painelEntrega, BorderLayout.WEST);
        panelPrincipal.add(scrollProdutos, BorderLayout.CENTER);
        panelPrincipal.add(painelValores, BorderLayout.SOUTH);
        panelPrincipal.add(painelBotoes, BorderLayout.EAST);
        
        add(panelPrincipal);
        setVisible(true);
    }

    // Busca / criação de uma encomenda
    public void buscarOuCriarEncomenda(String cpf) {
        // 1. Atualiza o cliente
        clienteAtual = new ClienteTO(cpf, arqCliente.getNome(cpf), arqCliente.getEndereco(cpf));

        // 2. Verifica o arquivo encomendas.txt
        if(arqEncomenda.existeEncomendaAberta(cpf)) {
            JOptionPane.showMessageDialog(this, "Encomenda aberta encontrada.");
            encomendaAtual = new EncomendaTO(clienteAtual, getTipoEntrega());

            ArrayList<String> codigos = arqEncomenda.getProdutosEncomenda(cpf);
            for(String cod : codigos) {
                int codigo = Integer.parseInt(cod);
                ProdutoTO produto = arqProduto.getProdutoPorCodigo(codigo);
                encomendaAtual.adicionarProduto(produto, 0);
            }

            atualizarListaProdutos(arqEncomenda.getProdutosEncomenda(cpf));
        } else {
            arqEncomenda.escreverArquivo(cpf + ";ABERTA");
            encomendaAtual = new EncomendaTO(clienteAtual, getTipoEntrega());
            JOptionPane.showMessageDialog(this, "Encomenda criada com sucesso.");
            this.habilitarBotoesEncomenda();
        }

        habilitarBotoesEncomenda();
        atualizarFrete();
        atualizarValorTotal();
    }

    // Método que adiciona um produto à encomenda a partir do seu código único
    // Utiliza método de adicionar o elemento a encomenda aberta, e o método de
    // atualizar a página inicial da encomenda.
    public void adicionarProduto(ProdutoTO produto) {
        if(produto == null || encomendaAtual == null) return;
        // Adiciona no TO da encomendaAtual
        encomendaAtual.adicionarProduto(produto);
        // Adiciona no arquivo encomendas.txt
        arqEncomenda.adicionaProdutoEncomenda(getCpf(), produto.getCodigo());
        atualizarListaProdutos();
        atualizarValorTotal();
    }

    // Remoção de um produto
    public void removerProdutoSelecionado() {
        ProdutoTO produto = listaProdutos.getSelectedValue();
        if(produto == null) {
            JOptionPane.showMessageDialog(this, "Selecione um pedido da encomenda que você deseja remover.", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Recupera o código do produto selecionado
        int codigo = produto.getCodigo();
        // Remove o produto por objeto e nos arquivos
        encomendaAtual.removerProduto(produto);
        arqEncomenda.removeProdutoEncomenda(getCpf(), codigo);
        arqProduto.removerProduto(codigo);
        // Atualiza áreas de produtos e valores
        atualizarListaProdutos();
        atualizarValorTotal();
    }

    // Finalização de uma encomenda
    public void finalizarEncomenda();

    /* Métodos auxiliares */

    // Metodo que retorna a encomenda que está sendo trabalhada
    public EncomendaTO getEncomendaAtual() {
        return encomendaAtual;
    }
    
    // Metodo que define a encomenda que será trabalhada
    public void setEncomendaAtual(EncomendaTO encomenda) {
        this.encomendaAtual = encomenda;
    }

    // Método que retorna o CPF digitado
    public String getCpf() {
        return tfCpf.getText();
    }

    // Método que retorna qual o tipo de entrega selecionado
    public String getTipoEntrega() {
        return rbDelivery.isSelected() ? "delivery" : "retirada";
    }

    // Método que atualiza a área que mostra os produtos da encomenda
    // A cada vez que adiciona ou remove um produto, essa área deve ser atualizada
    public void atualizarListaProdutos() {
        modelProdutos.clear();
        if(encomendaAtual == null) return;
        for(ProdutoTO p : encomendaAtual.getListaProdutos()) {
            modelProdutos.addElement(p);
        }
    }
    
    // Método que atualiza o valor do frete a partir da opção escolhida
    // Cria uma variável temporária que gerencia o frete a partir do endereço do cliente
    public void atualizarFrete() {
        if(encomendaAtual == null) return;
        tfValorFrete.setText(String.valueOf(encomendaAtual.calcularFrete()));
    }
    
    // Método que atualiza o tipo de entrega
    public void atualizarTipoEntrega() {
        if(encomendaAtual != null) {
            encomendaAtual.setTipoEntrega(getTipoEntrega());
            atualizarFrete();
            atualizarValorTotal();
        }
    }
    
    // Método que atualiza o valor do total da compra
    // Acontece a chamada do método que calcula o novo valor total e soma do frete
    public void atualizarValorTotal() {
        tfTotal.setText(String.valueOf(encomendaAtual.calcularValorTotal(true)));
    }

    // Método que habilita botões de adicionar, remover ou finalizar compra
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