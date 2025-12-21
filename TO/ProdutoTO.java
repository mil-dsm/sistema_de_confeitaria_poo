package TO;

//classe abstrata pai que extende todos os produtos da confeitaria

public abstract class ProdutoTO {
    protected String nome;
    protected double precoBase;
    protected String validade;
    protected int quantidade;

    //método construtor
    public ProdutoTO(String nome, double precoBase, String validade, int quantidade) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.quantidade = quantidade;
        this.validade = validade;
    }

    //gets e setters
    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public String getValidade() {
        return validade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    //Método ABSTRATO  que calcula o preço final de cada produto a partir da personalização e atributos de cada um.
    public abstract double calcularPrecoFinal();

    // Método base toString para exibir informações do produto
    @Override
    public String toString() {
        return "Nome: " + nome + "\nPreço Base: " + precoBase + "\nValidade: " + validade + "\nQuantidade: " + quantidade;
    }
}