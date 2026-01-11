package Arq;

import TO.ClienteTO;
import java.io.*;

public class ManipulaArquivosCliente {

    private final String ARQUIVO = "Clientes.txt";


    public void salvarCliente(ClienteTO cliente) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {

            bw.write(cliente.getCpf() + ";" +
                     cliente.getNome() + ";" +
                     cliente.getEndereco());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente!");
        }
    }


    public boolean cpfCadastrado(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(cpf)) {
                    return true;
                }
            }

        } catch (IOException e) {
            return false;
        }
        return false;
    }

 
    public String buscarNomePorCpf(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(cpf)) {
                    return dados[1];
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao buscar nome!");
        }
        return null;
    }


    public String buscarEnderecoPorCpf(String cpf) {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equals(cpf)) {
                    return dados[2];
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao buscar endereço!");
        }
        return null;
    }
}