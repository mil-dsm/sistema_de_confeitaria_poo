package Arq;

public class Cliente {
    private String nome;
    private String endereco;

    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    // Podemos aplicar uma exceção no default, dizendo que não há entregas para tal endereço.
    public double estimarDistancia(){
        switch(endereco){
            case "Alto São Francisco":
                return 1.0;
            case "Campo Velho":
                return 2.0;
            case "Centro":
                return 0.5;
            case "Campo Novo":
                return 3.5;
            case "Herval":
                return 1.5;
            case "Carrascal":
                return 4.0;
            default:
                return 0.0;
        }
    } 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String toString() {
        return "Cliente: " + nome + ", Endereço: " + endereco;
    }
}