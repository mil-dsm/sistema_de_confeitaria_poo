package Arq;

public class Donut extends Produto {
    private String recheio;
    private String cobertura;
    private boolean confete;

    public Donut(double precoBase, String validade, int quantidade, String recheio, String cobertura, boolean confete) {
        super("Donut", precoBase, validade, quantidade);
        this.recheio = recheio;
        this.cobertura = cobertura;
        this.confete = confete;
    }

    @Override
    public double calcularPrecoFinal() {
        double preco = precoBase;
        if(recheio != null) preco += 3;
        if(recheio != null) preco += 2;
        if(recheio != null) preco += 1.5;

        return preco * quantidade;
    }

    @Override
    public String toString() {
        return "Donut: recheio=" + recheio + ", cobertura=" + cobertura + ", confete=" + confete + "\n" + super.toString();
    }
}
