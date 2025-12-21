package TO;

public class BoloTO extends ProdutoTO {
    private String recheio;
    private String cobertura;
    private char tamanho;

    public BoloTO(String nome, double precoBase, String validade, int quantidade, String recheio, String cobertura, char tamanho) {
        super(nome, precoBase, validade, quantidade);
        this.recheio = recheio;
        this.cobertura = cobertura;
        this.tamanho = tamanho;
    }

    public String getRecheio() {
        return recheio;
    }

    public void setRecheio(String recheio) {
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

        if(cobertura != null) {
            precoFinal += 5.0;
        }

        return (precoFinal);
    }

    @Override
    public String toString() {
        return ("Bolo: [Recheio: " + recheio + ", Cobertura: " + cobertura + ", Tamanho: " + tamanho + "]\n" + super.toString());
    }

}
