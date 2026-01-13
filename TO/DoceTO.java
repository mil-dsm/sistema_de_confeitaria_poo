package TO;

public class DoceTO extends ProdutoTO {
    String tipo;

    public DoceTO() {
        super("DOCE", 1.50);
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String gerarLinhaArquivo() {
        return getCodigo() + ";" + getTipoProduto() + ";" + quantidade + ";" + tipo;
    }

    //Sobrescrita de toString: seu toString() + super.toString()
    @Override
    public String toString() {
        return (getTipoProduto() + super.toString() + " | Tipo: " + tipo + " | Total: R$" + calcularPrecoFinal());
    }
}