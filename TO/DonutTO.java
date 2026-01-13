package TO;

public class DonutTO extends ProdutoTO {
    private String recheio;
    private String cobertura;
    private boolean confete;

    public DonutTO() {
        super("DONUT", 5);
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
        if("Nutella".equals(recheio)) preco += 2;
        else if("Nenhuma".equals(recheio)) preco += 0;
        else if("Ninho".equals(recheio)) preco += 1.5;

        return preco * quantidade;
    }

    @Override
    public String gerarLinhaArquivo() {
        return getCodigo() + ";DONUT;" + nome + ";" + precoBase + ";" + quantidade + ";" +
            recheio + ";" + cobertura + ";" + confete;
    }

    @Override
    public String toString() {
        return super.toString() + " | Recheio: " + recheio + " | Cobertura: " + cobertura + " | Confete: " + confete;
    }
}