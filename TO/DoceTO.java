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

    //Sobrescrita de toString: seu toString() + super.toString()
    @Override
    public String toString() {
        return "Doce: " + tipo + "\n" + super.toString();
    }
     @Override
    public String gerarLinhaArquivo() {
        return codigoUnico + ";" + nome + ";" + calcularPrecoFinal() + ";" + quantidade + ";" + tipo;
    }

}
