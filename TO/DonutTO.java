package TO;

public class DonutTO extends ProdutoTO {
    private String cobertura;
    private boolean confete;

    public DonutTO() {
        super("DONUT", 5.0);
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
        if(confete) {
            preco += 0.50;
        }
        if("Chocolate".equals(cobertura)) {
            preco += 1.0;
        } 
        else if("Morango".equals(cobertura)) {
            preco += 1.50;
        }
        return preco * quantidade;
    }

    @Override
    public String gerarLinhaArquivo() {
        return getCodigo() +  ";" + getTipoProduto() + ";" + quantidade + ";" + cobertura + ";" + confete;
    }

    @Override
    public String toString() {
        return (getTipoProduto() + super.toString() + " | Cobertura: " + cobertura + " | Confete: " + confete + " | Total: " + calcularPrecoFinal());
    }
}