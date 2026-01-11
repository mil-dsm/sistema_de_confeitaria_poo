package TO;
import java.util.ArrayList;

public class EncomendaTO implements EntregavelTO {
    private ClienteTO cliente;
    private String tipoEntrega; // "retirada" ou "delivery"
    private ArrayList<ProdutoTO> produtos;
    
    public EncomendaTO(ClienteTO cliente, String tipoEntrega) {
        if(cliente == null) {
            throw new IllegalArgumentException("Cliente inválido.");
        }
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
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
    
    public ArrayList<ProdutoTO> getListaProdutos() {
        return produtos;
    }

    public void adicionarProduto(ProdutoTO p) {
        if (p == null) throw new IllegalArgumentException("Produto inválido");

        for (ProdutoTO prod : produtos) {
            if (prod.getCodigo() == p.getCodigo()) {
                prod.setQuantidade(prod.getQuantidade() + p.getQuantidade());
                return;
            }
        }
        produtos.add(p);
    }

    public void removerProduto(ProdutoTO p) {
        if (p == null)
            throw new IllegalArgumentException("Produto inválido");

        boolean removido = produtos.removeIf(
            prod -> prod.getCodigo() == p.getCodigo()
        );

        if (!removido)
            throw new IllegalArgumentException("Produto não está na encomenda");
    }
    
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

    // // Método que adiciona um produto a encomenda
    // public void adicionarProduto(ProdutoTO p) {
    //     if(p == null) {
    //         throw new IllegalArgumentException("Produto inválido.");
    //     }
    //     for(ProdutoTO prod : produtos) {
    //         if(p.getCodigo() == prod.getCodigo()) {
    //             prod.setQuantidade(prod.getQuantidade() + p.getQuantidade());
    //             return;
    //         }
    //     }
    //     produtos.add(p);
    // }

    // // Método sobrecarregado que remove um item inteiro da encomenda
    // public void removerProduto(ProdutoTO p) {
        //     if(p == null) 
        //         throw new IllegalArgumentException("Produto inválido");
        
    //     boolean flag = produtos.removeIf(prod -> prod.getCodigo() == p.getCodigo());
    //     if(flag == false)
    //         throw new IllegalArgumentException("Produto não está na encomenda");
    // }

    // // Método sobrecarregado que remove uma determidada qtd total de um determinado item
    // public void removerProduto(ProdutoTO p, int qtd) {
    //     if(p == null || qtd <= 0) {
    //         throw new IllegalArgumentException("Dados inválidos.");
    //     }

    //     for(ProdutoTO prod : produtos) {
    //         if(p.getCodigo() == prod.getCodigo()) {
    //             int novaQtd = prod.getQuantidade() - qtd;
    //             if(novaQtd <= 0) produtos.remove(prod);
    //             else prod.setQuantidade(novaQtd);
    //             return;
    //         }
    //     }
    //     throw new IllegalArgumentException("Produto não está na encomenda.");
    // }

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
            System.out.println("Produto: " + p.getNome() + ", Quantidade: " + p.getQuantidade());
        }
    }
}