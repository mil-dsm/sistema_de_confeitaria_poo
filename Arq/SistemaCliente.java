package Arq;

import TO.ClienteTO;
import java.io.*;

public class SistemaCliente {

    private final String ARQUIVO = "Clientes.txt";


    public void salvarCliente(ClienteTO cliente) {
        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(cliente.getCpf() + ";" +
                     cliente.getNome() + ";" +
                     cliente.getEndereco());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Erro ao salvar cliente!");
        }
    }

    public boolean CPFcadastrado(String cpf) {
        try (FileReader fr = new FileReader(ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {

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
}