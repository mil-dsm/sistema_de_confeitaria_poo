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

        if(confete == true){
            return preco += 0.50;
        } 
            
        
        if("Chocolate".equals(cobertura)) {
            return preco += 1.0;

        }
        else if("Morango".equals(cobertura)) {
            return preco += 1.50;
        }  
        

        return preco * quantidade;
    }

    @Override
    public String toString() {
        return "Donut:  " +  "cobertura= " + cobertura + ", confete= " + confete + "\n" + super.toString();
    }

    @Override
    public String gerarLinhaArquivo() {
        return codigoUnico + ";" + nome + ";" + calcularPrecoFinal() + ";" + quantidade + ";" + ";" + cobertura + ";" + confete;
    }
}
