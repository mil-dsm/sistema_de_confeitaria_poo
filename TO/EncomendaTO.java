package TO;

import java.util.ArrayList;

public class EncomendaTO implements EntregavelTO {
    private ClienteTO cliente;
    private String tipoEntrega; // "retirada" ou "delivery"
    private String data;
    private ArrayList<ProdutoTO> produtos;
    
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    // Método que retorna o produto
    public ArrayList<ProdutoTO> getListaProdutos() {
        return produtos;
    }

    // Método que adiciona um produto personalizado à encomenda
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
    // produto da encomenda aplicável apenas a produtos que já existem na encomenda.
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
        // Verifica erros
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido");
        }
        // Verifica se existe e apaga
        for(ProdutoTO prod : produtos) {
            if(prod.getCodigo() == p.getCodigo()) {
                produtos.remove(prod);
                return;
            }
        }
        // Se não existir
        throw new IllegalArgumentException("Produto não está na encomenda");
    }
    
    // Método sobrecarregado que remove uma quantidade específica de item da encomenda
    public void removerProduto(ProdutoTO p, int qtdDif) {
        if(p == null || qtdDif<= 0) {
            throw new IllegalArgumentException("Dados inválidos");
        }

        for(ProdutoTO prod : produtos) {
            if(prod.getCodigo() == p.getCodigo()) {
                int novaQtd = prod.getQuantidade() - qtdDif;
                if(novaQtd <= 0) {
                    produtos.remove(prod);
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
        // Verifica erros
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }
        if(novaQtd <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        // Verifica existência e aplica
        for(ProdutoTO prod : produtos) {
            if(p.getCodigo() == prod.getCodigo()) {
                prod.setQuantidade(novaQtd);
                return;
            }
        }
        // Lança erro
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
    
    public double calcularSubTotal() {
        return calcularValorTotal(false);
    }

    // Método que lista os produtos da encomenda atualmente
    public void listarprodutos() {
        for(ProdutoTO p : produtos) {
            System.out.println("Produto: " + p.getNome() + " | Quantidade: " + p.getQuantidade());
        }
    }

    @Override
    public String toString() {
        return "Data: " + data + " | Entrega: " + tipoEntrega + " | Itens: " + produtos.size() + " | Total: R$ " + String.format("%.2f", calcularValorTotal(true));
    }
}