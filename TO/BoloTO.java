package TO;

public class BoloTO extends ProdutoTO {
    private boolean  recheio;
    private String cobertura;
    private char tamanho;

    // Construtor
    public BoloTO() {
        super("BOLO", 30.0);
    }

    // Gets e sets
    public boolean  getRecheio() {
        return recheio;
    }

    public void setRecheio(boolean recheio) {
        this.recheio = recheio;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public char getTamanho() {
        return tamanho;
    }

    public void setTamanho(char tamanho) {
        this.tamanho = tamanho;
    }

    // Implementação do método abstrato do calculo do preço do produto personalizado
    @Override
    public double calcularPrecoFinal() {
        double precoFinal = super.precoBase;

        switch (tamanho) {
            case 'P':
                precoFinal += 0;
                break;
            case 'M':
                precoFinal += 15;
                break;
            case 'G':
                precoFinal += 35;
                break;
        }

        //personalizavel
        if (recheio == true) {
            precoFinal += 10.0;
        }

        return (precoFinal);
    }
    
    // Sobrescrita do método abstrato de geração das linhas no arquivo
    @Override
    public String gerarLinhaArquivo() {
        return getCodigo() + ";" + getTipoProduto() + ";" + quantidade + ";" + recheio + ";" + cobertura + ";" + tamanho;
    }
    
    // Sobrescrita do método abstrato toString() para mostrar na encomenda
    @Override
    public String toString() {
        return (getTipoProduto() + super.toString() + " | Recheio: " + (recheio == true ? "sim" : "não") + " | Cobertura: " + cobertura + " | Tamanho: " + tamanho + " | Total: R$" + calcularPrecoFinal());
    }
}