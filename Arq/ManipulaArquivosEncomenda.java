package Arq;

import java.io.*;
import java.util.ArrayList;

public class ManipulaArquivosEncomenda {
    public String diretorio = "dados/";

    public boolean escreverArquivo(String nomeArquivo, String texto) {
        File arq = new File(diretorio + nomeArquivo);

        try(BufferedWriter gravadorBuff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arq, true)))) {
            gravadorBuff.write(texto);
            gravadorBuff.newLine();
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> retornarConteudoArquivo(String nomeArquivo) {
        File arq = new File(diretorio + nomeArquivo);
        ArrayList<String> alArquivo = new ArrayList<>();

        if(arq.isFile() && arq.canRead()) {
            try(BufferedReader leitorBuff = new BufferedReader(new InputStreamReader(new FileInputStream(arq)))) {
                String conteudo;
                while ((conteudo = leitorBuff.readLine()) != null) {
                    alArquivo.add(conteudo);
                }
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println("Arquivo aberto ou inexistente");
        }

        return alArquivo;
    }

    public String buscaLinhaPorCpf(String nomeArquivo, String cpf) {
        ArrayList<String> linhas = retornarConteudoArquivo(nomeArquivo);
        for(String linha : linhas) {
            if(linha.startsWith(cpf + ";")) {
                return linha;
            }
        }
        return null;
    }

    public boolean removerElementoArquivo(String nomeArquivo, String valorRemocao) {
        ArrayList<String> alArquivo = retornarConteudoArquivo(nomeArquivo);

        boolean confirmacao = alArquivo.remove(valorRemocao);

        if(confirmacao) {
            File arq = new File(diretorio + nomeArquivo);
            try(BufferedWriter gravadorBuff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arq)))) {
                for (String valor : alArquivo) {
                    gravadorBuff.write(valor);
                    gravadorBuff.newLine();
                }
                return true;
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return false;
    }

    public boolean buscarElementoArquivo(String nomeArquivo, String valorBusca) {
        ArrayList<String> alArquivo = retornarConteudoArquivo(nomeArquivo);
        for(String linha : alArquivo) {
            if(linha.startsWith(valorBusca + ";")) {
                return true;
            }
        }
        return false;
    }

    public boolean imprimirArquivo(String nomeArquivo) {
        ArrayList<String> alArquivo = retornarConteudoArquivo(nomeArquivo);
        if(alArquivo.isEmpty()) {
            System.out.println("O arquivo está vazio");
            return false;
        }
        for(String linha : alArquivo) {
            System.out.println(linha);
        }
        return true;
    }
}
