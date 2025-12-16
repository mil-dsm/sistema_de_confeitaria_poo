package Arq;

import java.util.HashMap;

import Cliente;

public class Encomenda implements Entregavel {
    private Cliente cliente;
    private String tipoEntrega; // "retirada" ou "delivery"
    private double valorTotal;
    private HashMap<Produto, Integer> itens; // chave = produto, valor = quantidade

    public Encomenda(Cliente cliente) {
        this.cliente = cliente;
        this.tipoEntrega = null;
        this.valorTotal = calcularValorTotal();
        this.itens = new HashMap<>();
    }

    // Metódo para definir o tidoEntrega
    public void setTipoEntrega(String tipo) {
        this.tipoEntrega = tipo;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    // Método sobrecarregado que adiciona uma determinada quantidade ao produto selecionado
    // Parametros: Produto p como a key dentro de um HashMap
    public boolean adicionarProduto(Produto p, int qtd) {
        try {
            if(p == null) {
                throw new IllegalArgumentException("Produto inválido.");
            }
            if(qtd <= 0) {
                throw new IllegalArgumentException("Quantidade inválida.");
            }

            itens.put(p, itens.getOrDefault(p, 0) + qtd);
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método sobrecarregado que adiciona mais um de quantidade ao produto selecionado
    // Parametros: Produto p como a key dentro de um HashMap
    public boolean adicionarProduto(Produto p) {
        return adicionarProduto(p, 1);
    }

    public boolean alterarQuantidade(Produto p, int novaQtd) {
        try {
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
            
            itens.put(p, itens.getOrDefault(p, novaQtd));
            return true;
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Método sobrecarregado que remove uma determidada qtd total de um determinado item
    public boolean removerProduto(Produto p, int qtd) {
        if(!itens.containsKey(p)) { // Returns true if this map maps one or more keys to the specified value.
            System.out.println("Produto não está na encomenda.");
            return false;
        }
        int qtdAtual = itens.get(p);
        if(qtd <= 0) {
            System.out.println("Quantidade inválida.\n");
        }
        if(qtdAtual - qtd <= 0) {
            itens.remove(p);
        } else {
            itens.put(p, qtdAtual - qtd);
        }
        return true;
    }

    // Método sobrecarregado que remove um da quantidade total de um determinado item
    public boolean removerProduto(Produto p) {
        return removerProduto(p, 1);
    }

    @Override
    public double calcularFrete() {
        if(getTipoEntrega().equals("delivery")) {
            return cliente.estimarDistancia() * 2;
        }
        return 0;
    }

    // Método que calcula o valor total da compra, ou seja, a soma de todos os preços e o frete
    public double calcularValorTotal() {
        double valorTotal = 0;
        for(Produto p : itens.keySet()) {
            valorTotal += (p.precoBase * itens.get(p));
        }
        valorTotal += calcularFrete();
        return valorTotal;
    }

    // Método que lista os itens da encomenda atualmente
    public void listarItens() {
        for(Produto p : itens.keySet()) {
            System.out.println("Produto: " p.getNome() + ", Quantidade: " + itens.get(p));
        }
    }
}