package View.Encomenda;

import java.awt.*;
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
    private ManipulaArquivosProduto arqProduto;
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        
        /* Inicialização dos atributos */
        arqCliente = new ManipulaArquivosCliente();
        arqEncomenda = new ManipulaArquivosEncomenda();
        arqProduto = new ManipulaArquivosProduto();
        clienteAtual = null;
        encomendaAtual = null;
        
        /* Cria o painel  */
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));

        /* Painel CPF + Buscar */
        JPanel painelTopo = new JPanel();
        // Adiciona retorno        
        btnVoltar = new JButton("Voltar");
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);
        // Inicializa labels e TextField
        lbCpf = new JLabel("CPF");
        tfCpf = new JTextField(15);
        // Adiciona pesquisa
        btnBuscarCriarEncomenda = new JButton("Buscar / Criar Encomenda");
        ListenerBtnBuscarCriarEncomenda l2 = new ListenerBtnBuscarCriarEncomenda(this, tfCpf);
        btnBuscarCriarEncomenda.addActionListener(l2);
        // Agrupa em um painel
        painelTopo.add(btnVoltar);
        painelTopo.add(lbCpf);
        painelTopo.add(tfCpf);
        painelTopo.add(btnBuscarCriarEncomenda);
        
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
        // TODO: Ajustar tamanho da área
        JScrollPane scrollProdutos = new JScrollPane(listaProdutos);
        scrollProdutos.setBorder(BorderFactory.createTitledBorder("Produtos na encomenda"));
        scrollProdutos.setPreferredSize(new Dimension(400, 200));

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
        painelBotoes.setLayout(new GridLayout(3, 1, 0, 10));

        /* Montagem */
        //Painel da direita (Valores + botões)
        /* Painel da Direita (Valores + Botões) */
        JPanel painelDireita = new JPanel(new BorderLayout(5, 10));
        painelDireita.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelDireita.add(painelValores, BorderLayout.NORTH);
        JPanel conteinerBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel gridBotoes = new JPanel(new GridLayout(3, 1, 0, 10));
        gridBotoes.add(btnAdicionarProduto);
        gridBotoes.add(btnRemoverProduto);
        gridBotoes.add(btnFinalizarEncomenda);
        conteinerBotoes.add(gridBotoes);
        painelDireita.add(conteinerBotoes, BorderLayout.CENTER);
        //Painel da esquerda (Tipo de entrega + lista)
        JPanel painelCentro = new JPanel(new BorderLayout(5, 5));
        painelCentro.add(painelEntrega, BorderLayout.NORTH);
        painelCentro.add(scrollProdutos, BorderLayout.CENTER);
        // Montagem final
        panelPrincipal.add(painelTopo, BorderLayout.NORTH);
        panelPrincipal.add(painelCentro, BorderLayout.CENTER);
        panelPrincipal.add(painelDireita, BorderLayout.EAST);
        
        add(panelPrincipal);
        setVisible(true);
    }

    /* Métodos de funcionamento */
    
    // Busca / criação de uma encomenda
    public void buscarOuCriarEncomenda(String cpf) {
        // Limpa os estados anteriores para evitar lixo de memória
        modelProdutos.clear();
        encomendaAtual = null;
        clienteAtual = null;

        // Atualiza o cliente
        if(!arqCliente.cpfCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            return;
        }

        // Cria cliente
        clienteAtual = new ClienteTO(arqCliente.buscarNomePorCpf(cpf), arqCliente.buscarEnderecoPorCpf(cpf), cpf);

        // Verifica a existência do cliente novamente
        if(clienteAtual == null) {
            JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            return;
        }

        // Verifica o arquivo encomendas.txt
        boolean confirmacao = arqEncomenda.existeEncomendaAberta(cpf);
        if(!confirmacao) {
            arqEncomenda.escreverArquivo(cpf + ";ABERTA");
            JOptionPane.showMessageDialog(this, "Encomenda criada com sucesso.");
        } else {
            JOptionPane.showMessageDialog(this, "Encomenda aberta encontrada.");
        }
        encomendaAtual = new EncomendaTO(clienteAtual, getTipoEntrega());

        // Carrega produtos existentes
        ArrayList<String> codigos = arqEncomenda.getProdutosEncomenda(cpf);
        for(String cod : codigos) {
            int codigo = Integer.parseInt(cod);
            ProdutoTO produto = arqProduto.getProdutoPorCodigo(codigo);
            if(produto != null) {
                encomendaAtual.adicionarProduto(produto);
            }
        }

        atualizarListaProdutos();
        atualizarFrete();
        atualizarValorTotal();
        
        btnAdicionarProduto.setEnabled(true);
        btnRemoverProduto.setEnabled(!encomendaAtual.getListaProdutos().isEmpty());
        btnFinalizarEncomenda.setEnabled(!encomendaAtual.getListaProdutos().isEmpty());
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
            JOptionPane.showMessageDialog(this, "Selecione um pedido da encomenda que você deseja remover.");
            return;
        }

        int codigo = produto.getCodigo();

        // Remove da encomenda e do arquivo
        encomendaAtual.removerProduto(produto);
        arqEncomenda.removeProdutoEncomenda(getCpf(), codigo);

        // Atualiza UI
        atualizarListaProdutos();
        atualizarValorTotal();

        if(encomendaAtual.getListaProdutos().isEmpty()) {
            btnRemoverProduto.setEnabled(false);
            btnFinalizarEncomenda.setEnabled(false);
        }
    }

    // Finalização de uma encomenda
    public void finalizarEncomenda() {
        if(encomendaAtual == null) return;
        boolean sucesso = arqEncomenda.finalizarEncomenda(getCpf(), encomendaAtual);
        if(sucesso) {
            JOptionPane.showMessageDialog(this, "Encomenda finalizada com sucesso!");
            encomendaAtual = null;
            modelProdutos.clear();
            atualizaEstadoBotoes();
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível finalizar a encomenda.");
        }
    }

    /* Métodos de atualização */

    // Método que atualiza a área que mostra os produtos da encomenda
    // A cada vez que adiciona ou remove um produto, essa área deve ser atualizada
    public void atualizarListaProdutos() {
        modelProdutos.clear();
        if(encomendaAtual == null) return;
        for(ProdutoTO p : encomendaAtual.getListaProdutos()) {
            modelProdutos.addElement(p);
        }
    }
    
    // Método que atualiza o tipo de entrega
    public void atualizarTipoEntrega() {
        if(encomendaAtual != null) {
            encomendaAtual.setTipoEntrega(getTipoEntrega());
            atualizarFrete();
            atualizarValorTotal();
        }
    }
    
    // Método que atualiza o valor do frete a partir da opção escolhida
    // Cria uma variável temporária que gerencia o frete a partir do endereço do cliente
    public void atualizarFrete() {
        if(encomendaAtual == null) return;
        tfValorFrete.setText(String.valueOf(encomendaAtual.calcularFrete()));
    }
    
    // Método que atualiza o valor do total da compra
    // Acontece a chamada do método que calcula o novo valor total e soma do frete
    public void atualizarValorTotal() {
        if(encomendaAtual == null) return;
        tfTotal.setText(String.valueOf(encomendaAtual.calcularValorTotal(true)));
    }

    // Método que atualiza o estado dos botões da encomenda de acordo com seu estado atual
    public void atualizaEstadoBotoes() {
        // Verifica se existe uma encomenda ativa e carregada na memória
        boolean temEncomenda = (encomendaAtual != null);
        // Verifica se a lista de produtos da encomenda NÃO está vazia
        boolean temItens = temEncomenda && !encomendaAtual.getListaProdutos().isEmpty();
        // Verifica se o usuário selecionou algum item na JList (necessário para remover)
        boolean itemSelecionado = listaProdutos.getSelectedIndex() != -1;
        // Aplicação dos estados
        btnAdicionarProduto.setEnabled(temEncomenda);
        btnRemoverProduto.setEnabled(temItens && itemSelecionado);
        btnFinalizarEncomenda.setEnabled(temItens);
    }

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
}