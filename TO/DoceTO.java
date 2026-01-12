package TO;

public class DoceTO extends ProdutoTO {
    String tipo;

    public DoceTO(String nome, double precoBase) {
        super(nome, precoBase);
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
        return getCodigo() + ";DOCE;" + nome + ";" + precoBase + ";" + quantidade + ";" + tipo;
    }

    //Sobrescrita de toString: seu toString() + super.toString()
    @Override
    public String toString() {
        return (super.toString() + " | Tipo: " + tipo);
    }
}