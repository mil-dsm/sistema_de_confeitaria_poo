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

    // Método sobrecarregado que adiciona uma determinada quantidade ao produto selecionado
    // Parametros: Produto p como a key dentro de um HashMap
    public void adicionarProduto(ProdutoTO p, int qtd) {
        if(p == null) {
            throw new IllegalArgumentException("Produto inválido.");
        }
        if(qtd <= 0) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }
        for(ProdutoTO prod : produtos) {
            if(p.getCodigo() == prod.getCodigo()) {
                prod.setQuantidade(prod.getQuantidade() + qtd);
                return;
            }
        }
        p.setQuantidade(qtd);
        produtos.add(p);
    }

    // Método sobrecarregado que adiciona mais um de quantidade ao produto selecionado
    // Parametros: Produto p como a key dentro de um HashMap
    public void adicionarProduto(ProdutoTO p) {
        adicionarProduto(p, 1);
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

    // Método sobrecarregado que remove uma determidada qtd total de um determinado item
    public void removerProduto(ProdutoTO p, int qtd) {
        if(p == null || qtd <= 0) {
            throw new IllegalArgumentException("Dados inválidos.");
        }
        for(ProdutoTO prod : produtos) {
            if(p.getCodigo() == prod.getCodigo()) {
                if(prod.getQuantidade() - qtd <= 0) produtos.remove(prod);
                else prod.setQuantidade(prod.getQuantidade() - qtd);
                return;
            }
        }
        throw new IllegalArgumentException("Produto não está na encomenda.");
    }

    // Método sobrecarregado que remove um da quantidade total de um determinado item
    public void removerProduto(ProdutoTO p) {
        removerProduto(p, 1);
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
            System.out.println("Produto: " + p.getNome() + ", Quantidade: " + p.getQuantidade());
        }
    }
}