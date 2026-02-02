package TO;
/* Classe abstrata pai que extende todos os produtos da confeitaria */

public abstract class ProdutoTO {
    protected String tipoProduto;
    protected double precoBase;
    protected int quantidade;
    public static int proximoCodigo = 0; // Inicializa em 0 e incrementa conforme cria novos produtos
    private int codigoUnico;

    //método construtor
    public ProdutoTO(String tipoProduto, double precoBase) {
        this.tipoProduto = tipoProduto;
        this.precoBase = precoBase;
        codigoUnico = proximoCodigo;
        proximoCodigo++;
    }

    //gets e setters
    public String getTipoProduto() {
        return tipoProduto;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
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

    // Método base toString para exibir informações do produto, utilizado na EncomendaView
    @Override
    public String toString() {
        return " | Qtd: " + quantidade + " | Unit: R$ " + precoBase + " | Total: R$ " + calcularPrecoFinal();
    }
}