package TO;
public class Doce extends ProdutoTO {

    String tipo;

    public Doce(String nome, double precoBase, String validade, int quantidade, String tipo) {
        super(nome, precoBase, validade, quantidade);
        this.tipo = tipo;
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
}
