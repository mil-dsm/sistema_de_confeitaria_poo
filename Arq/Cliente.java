package Arq;

public class Cliente {
    private String nome;
    private String endereco;

    public class Cliente(String nome, String endereco) {
        nome=this.nome;
        endereco=this.endereco;
    }

    public double estimarDistancia(this.endereco){
        switch(endereco){
            case "Alto São Francisco"
                return 1.0;
                break;

            case "Campo Velho"
                return 2.0;
                break;

            case "Centro"
                return 0.5;
                break;

            case "Campo Novo"
                return 3.5;
                break;

            case "Herval"
                return 1.5;
                break;

            case "Carrascal"
                return 4.0;
                break;
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

    @Override
    public String toString() {
        return "Cliente: " + nome + ", Endereço: " + endereco;
    }
}