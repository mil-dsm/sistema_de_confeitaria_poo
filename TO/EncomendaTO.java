package TO;
import java.util.HashMap;
import java.util.Map;

public class EncomendaTO implements EntregavelTO {
    private ClienteTO cliente;
    private String tipoEntrega; // "retirada" ou "delivery"
    private HashMap<ProdutoTO, Integer> itens; // chave = produto, valor = quantidade
    
    public EncomendaTO(ClienteTO cliente, String tipoEntrega) {
        if(cliente == null) {
            throw new IllegalArgumentException("Cliente inválido.");
        }
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.itens = new HashMap<>();
    }
    
    public ClienteTO getCliente() {
        return cliente;
    }
    
    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipo) {
        this.tipoEntrega = tipo;
    }
    
    public Map<ProdutoTO, Integer> getItens() {
        return itens;
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
        itens.put(p, itens.getOrDefault(p, 0) + qtd);
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

        if(itens.containsKey(p) == false) {
            throw new IllegalArgumentException("Item não existe na encomenda.");
        }
        itens.put(p, novaQtd);
    }

    // Método sobrecarregado que remove uma determidada qtd total de um determinado item
    public void removerProduto(ProdutoTO p, int qtd) {
        if(!itens.containsKey(p)) { // Returns true if this map maps one or more keys to the specified value.
            throw new IllegalArgumentException("Produto não está na encomenda.");
        }
        if(qtd <= 0) {
            throw new IllegalArgumentException("Quantidade inválida.");
        }
        if(itens.get(p) - qtd <= 0) {
            itens.remove(p);
        } else {
            itens.put(p, itens.get(p) - qtd);
        }
    }

    // Método sobrecarregado que remove um da quantidade total de um determinado item
    public void removerProduto(ProdutoTO p) {
        removerProduto(p, 1);
    }

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
        for (ProdutoTO p : itens.keySet()) {
            total += p.calcularPrecoFinal() * itens.get(p);
        }
        if(incluirFrete && getTipoEntrega().equals("delivery")) {
            total += calcularFrete();
        }
        return total;
    }
    
    public double calcularSubTotal() {
        return calcularValorTotal(false);
    }

    // Método que lista os itens da encomenda atualmente
    public void listarItens() {
        for(ProdutoTO p : itens.keySet()) {
            System.out.println("Produto: " + p.getNome() + ", Quantidade: " + itens.get(p));
        }
    }
}