package Arq;

public class Bolo extends Produto implements Promocionavel {
    private String sabor;
    private String cobertura;
    private char tamanho;
    // static int estoque;

    public Bolo(double precoBase, String validade, int quantidade, String sabor, String cobertura, char tamanho) {
        super("Bolo", precoBase, validade, quantidade);
        this.sabor = sabor;
        this.cobertura = cobertura;
        this.tamanho = tamanho;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
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
        return ("Bolo: [Sabor: " + sabor + ", Cobertura: " + cobertura + ", Tamanho: " + tamanho + "]\n" + super.toString());
    }

}
