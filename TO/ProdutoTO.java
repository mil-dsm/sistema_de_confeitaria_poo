package TO;
//classe abstrata pai que extende todos os produtos da confeitaria

public abstract class ProdutoTO {
    protected String nome;
    protected double precoBase;
    protected int quantidade;
    public static int proximoCodigo = 0;
    private int codigoUnico;

    //método construtor
    public ProdutoTO(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
        codigoUnico = proximoCodigo;
        proximoCodigo++;
    }

    //gets e setters
    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
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

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public int getCodigo() {
        return codigoUnico;
    }

    public void setCodigo(int codigo) {
        codigoUnico = codigo;
    }
    
    //Método ABSTRATO  que calcula o preço final de cada produto a partir da personalização e atributos de cada um.
    public abstract double calcularPrecoFinal();

    // Método ABSTRATO que formata como os atributos de cada produto aparecera no arquivo produtos.txt
    public abstract String gerarLinhaArquivo();

    // Método base toString para exibir informações do produto
    @Override
    public String toString() {
        return nome + " | Qtd: " + quantidade + " | Unit: R$ " + precoBase + " | Total: R$ " + calcularPrecoFinal();
    }
}