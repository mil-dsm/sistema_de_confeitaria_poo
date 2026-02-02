package TO;

import java.util.ArrayList;

public class EncomendaTO implements EntregavelTO {
    private ClienteTO cliente;
    private String tipoEntrega; // "retirada" ou "delivery"
    private String data;
    private ArrayList<ProdutoTO> produtos;
    
    // Construtor com tratamento de exceção
    public EncomendaTO(ClienteTO cliente, String tipoEntrega) {
        if(cliente == null) {
            throw new IllegalArgumentException("Cliente inválido.");
        }
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        data = "";
        this.produtos = new ArrayList<>();
    }
    
    // Método que retorna o cliente vinculado a encomenda
    public ClienteTO getCliente() {
        return cliente;
    }
    
    // Método que define o cliente vinculado a encomenda
    public void setCliente(ClienteTO cliente) {
        this.cliente = cliente;
    }

    // Método que retorna o tipo da entrega
    public String getTipoEntrega() {
        return tipoEntrega;
    }

    // Método que define o tipo da entrega
    public void setTipoEntrega(String tipo) {
        this.tipoEntrega = tipo;
    }

    // Método que retorna a data de finalização da encomenda
    public String getData() {
        return data;
    }

    // Método que define a data de finalização da encomenda
    public void setData(String data) {
        this.data = data;
    }

    // Método que retorna os produtos dentro da encomenda como ArrayList
    public ArrayList<ProdutoTO> getListaProdutos() {
        return produtos;
    }

    // Método que adiciona um produto personalizado à encomenda (ArrayList)
    // Trata exceções
    public void adicionarProduto(ProdutoTO p) {
        // Verifica erros
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido");
        }
        // Verifica se o produto já existe
        for(ProdutoTO prod : produtos) {
            if(prod.getCodigo() == p.getCodigo()) {
                prod.setQuantidade(prod.getQuantidade() + p.getQuantidade());
                return;
            }
        }
        produtos.add(p);
    }

    // Método sobrecarregado que adiciona um quantidade específica para aumentar em determinado
    // produto da encomenda. Aplicável apenas a produtos que já existem na encomenda.
    public void adicionarProduto(ProdutoTO p, int sum) {
        // Verifica erros
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }
        // Verifica existência e aplica
        for(ProdutoTO prod : produtos) {
            if(p.getCodigo() == prod.getCodigo()) {
                prod.setQuantidade(prod.getQuantidade() + sum);
                return;
            }
        }
    }

    // Método que remove um produto inteiro da encomenda
    public void removerProduto(ProdutoTO p) {
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido");
        }
        for(int i = 0; i < produtos.size(); i++) {
            if(produtos.get(i).getCodigo() == p.getCodigo()) {
                produtos.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Produto não está na encomenda");
    }
    
    // Método sobrecarregado que remove uma quantidade específica de item da encomenda
    // Verifica exceções e manipula o ArrayList
    public void removerProduto(ProdutoTO p, int qtdDif) {
        if(p == null || qtdDif <= 0) {
            throw new IllegalArgumentException("Dados inválidos");
        }
        for(int i = 0; i < produtos.size(); i++) {
            ProdutoTO prod = produtos.get(i);
            if(prod.getCodigo() == p.getCodigo()) {
                int novaQtd = prod.getQuantidade() - qtdDif;
                if(novaQtd <= 0) {
                    produtos.remove(i);
                } else {
                    prod.setQuantidade(novaQtd);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Produto não está na encomenda");
    }
    
    // Método que altera a quantidade de determinado produto
    public void alterarQuantidade(ProdutoTO p, int novaQtd) {
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }
        if(novaQtd <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        for(ProdutoTO prod : produtos) {
            if(p.getCodigo() == prod.getCodigo()) {
                prod.setQuantidade(novaQtd);
                return;
            }
        }
        throw new IllegalArgumentException("Item não existe na encomenda.");
    }

    // Método implementado da interface que calcula o valor do frete de acordo com o tipo de entrega
    @Override
    public double calcularFrete() {
        if(getTipoEntrega().equals("delivery")) {
            return cliente.estimarDistancia() * 2;
        }
        return 0;
    }

    // Método que calcula o valor total da compra, ou seja, a soma de todos os preços e o frete
    // Método atribui o resultado ao atributo valorTotal
    public double calcularValorTotal(boolean incluirFrete) {
        double total = 0;
        for(ProdutoTO p : produtos) {
            total += p.calcularPrecoFinal();
        }
        if(incluirFrete && getTipoEntrega().equals("delivery")) {
            total += calcularFrete();
        }
        return total;
    }
    
    // Método que calcua o subtotal, sem frete adicionado
    public double calcularSubTotal() {
        return calcularValorTotal(false);
    }

    // Método que lista os produtos da encomenda atualmente
    public void listarprodutos() {
        for(ProdutoTO p : produtos) {
            System.out.println("Produto: " + p.getTipoProduto() + " | Quantidade: " + p.getQuantidade());
        }
    }

    // Sobreescrita do método toString() para mostrar no histórico de pedidos
    @Override
    public String toString() {
        return "Data: " + data + " | Entrega: " + tipoEntrega + " | Itens: " + produtos.size() + " | Total: R$ " + String.format("%.2f", calcularValorTotal(true));
    }
}