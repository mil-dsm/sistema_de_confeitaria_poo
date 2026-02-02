package TO;

public class DonutTO extends ProdutoTO {
    private String cobertura;
    private boolean confete;

    // Construtor
    public DonutTO() {
        super("DONUT", 5.0);
    }

    // Gets e sets
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

    // Implementação do método abstrato do calculo do preço do produto personalizado
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

    // Sobrescrita do método abstrato de geração das linhas no arquivo
    @Override
    public String gerarLinhaArquivo() {
        return getCodigo() +  ";" + getTipoProduto() + ";" + quantidade + ";" + cobertura + ";" + confete;
    }

    // Sobrescrita do método abstrato toString() para mostrar na encomenda
    @Override
    public String toString() {
        return (getTipoProduto() + super.toString() + " | Cobertura: " + cobertura + " | Confete: " + confete + " | Total: " + calcularPrecoFinal());
    }
}