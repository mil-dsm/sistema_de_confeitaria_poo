package TO;

public class DonutTO extends ProdutoTO {
    private String recheio;
    private String cobertura;
    private boolean confete;

    public DonutTO(String nome, double precoBase) {
        super(nome, precoBase);
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

    public boolean getConfete() {
        return confete;
    }
    
    public void setConfete(boolean confete) {
        this.confete = confete;
    }

    @Override
    public double calcularPrecoFinal() {
        double preco = precoBase;
        if(recheio == "Nenhuma") preco += 0;
        if(recheio == "Nutella") preco += 2;
        if(recheio != "Ninho") preco += 1.5;

        return preco * quantidade;
    }

    @Override
    public String toString() {
        return "Donut: recheio=" + recheio + ", cobertura=" + cobertura + ", confete=" + confete + "\n" + super.toString();
    }
}