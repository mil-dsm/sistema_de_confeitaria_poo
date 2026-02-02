package TO;

public class DoceTO extends ProdutoTO {
    String tipo;

    // Construtor
    public DoceTO() {
        super("DOCE", 1.50);
    }

    // Implementação do método abstrato do calculo do preço do produto personalizado
    @Override
    public double calcularPrecoFinal() {
        double precoFinal = super.precoBase;
        switch (tipo) {
            case "brigadeiro":
                precoFinal += 1;
                break;
            case "trufa":
                precoFinal += 3;
                break;
            case "beijinho":
                precoFinal += 1.5;
                break;
            default:
                break;
        }

        return precoFinal * quantidade;
    }

    // Gets e sets
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Sobrescrita do método abstrato de geração das linhas no arquivo
    @Override
    public String gerarLinhaArquivo() {
        return getCodigo() + ";" + getTipoProduto() + ";" + quantidade + ";" + tipo;
    }

    // Sobrescrita do método abstrato toString() para mostrar na encomenda
    @Override
    public String toString() {
        return (getTipoProduto() + super.toString() + " | Tipo: " + tipo + " | Total: R$" + calcularPrecoFinal());
    }
}