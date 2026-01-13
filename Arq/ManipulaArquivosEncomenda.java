package Arq;

import java.io.*;
import java.util.ArrayList;
import TO.*;

/**
 * Estrutura: cpf;status;entrega;data;valorTotal;codigos
 * EX: 12345678900;FECHADA;delivery;2026-01-10;85.50;1,3,5,...
 */
public class ManipulaArquivosEncomenda {
    public String diretorio;
    public String nomeArquivo;
    public File arq;
    public ManipulaArquivosCliente arqCliente;
    public ManipulaArquivosProduto arqProduto;

    public ManipulaArquivosEncomenda() {
        diretorio = "dados/";
        nomeArquivo = "encomendas.txt";
        arq = new File(diretorio+nomeArquivo);
        arqCliente = new ManipulaArquivosCliente();
        arqProduto = new ManipulaArquivosProduto();
    }

    // Método que escreve em uma linha do arquivo
    public boolean escreverArquivo(String texto) {
        try(BufferedWriter gravadorBuff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arq, true)))) {
            gravadorBuff.write(texto);
            gravadorBuff.newLine();
            return true;
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return false;
    }

    // Método que retorna um ArrayList com todo o conteudo do arquivo
    public ArrayList<String> retornarConteudoArquivo() {
        
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
    
    // Remove uma linha inteira do arquivo e retorna um boolean como confirmação
    public boolean removerElementoArquivo(String valorRemocao) {
        ArrayList<String> alArquivo = retornarConteudoArquivo();
        
        boolean confirmacao = alArquivo.remove(valorRemocao);
        
        if(confirmacao) {
            
            try(BufferedWriter gravadorBuff = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(arq)))) {
                for(String valor : alArquivo) {
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
    
    // Imprime todos os elementos do arquivo e retorna true. Caso não seja possível, retorna false
    public boolean imprimirArquivo() {
        ArrayList<String> alArquivo = retornarConteudoArquivo();
        if(alArquivo.isEmpty()) {
            System.out.println("O arquivo está vazio");
            return false;
        }
        for(String linha : alArquivo) {
            System.out.println(linha);
        }
        return true;
    }

    // Método para buscar se um elemento existe no arquivo
    public boolean buscarElementoArquivo(String valorBusca) {
        ArrayList<String> alArquivo = retornarConteudoArquivo();
        for(String linha : alArquivo) {
            if(linha.startsWith(valorBusca + ";")) {
                return true;
            }
        }
        return false;
    }

    /* ========== Métodos extras para a manipulação do arquivo encomendas.txt ==========*/

    // Metodo para buscar uma linha específica por CPF e ver se já existe um encomenda naquele CPF
    public String buscaEncomendaAbertaPorCpf(String cpf) {
        ArrayList<String> linhas = retornarConteudoArquivo();
        for(String linha : linhas) {
            String[] partes = linha.split(";");
            if(partes.length > 1 &&
            partes[0].equals(cpf) &&
            partes[1].equals("ABERTA")) {
                return linha;
            }
        }
            return null;
    }
    
    // Método para verificar se existe uma encomenda aberta para um determinado CPF
    public boolean existeEncomendaAberta(String cpf) {
        ArrayList<String> linhas = retornarConteudoArquivo();
    
        for(String linha : linhas) {
            String[] partes = linha.split(";");
            if(partes[0].equals(cpf) && partes[1].equals("ABERTA")) {
                return true;
            }
        }
        return false;
    }
    
    // Busca todos os produtos adicionados a uma encomenda e retorna um ArrayList com os produtos
    public ArrayList<String> getProdutosEncomenda(String cpf) {
        ArrayList<String> linhas = retornarConteudoArquivo();
        ArrayList<String> produtos = new ArrayList<>();

        for(String linha : linhas) {
            String[] partes = linha.split(";");

            if(partes[0].equals(cpf) && partes[1].equals("ABERTA")) {
                if(partes.length > 5) {
                    String[] itens = partes[5].split(",");
                    for(String p : itens) {
                        produtos.add(p);
                    }
                }
                break;
            }
        }
        return produtos;
    }

    // Adiciona um produto na encomenda a partir do nome do arquivo, cpf do cliente e código do produto criado
    public boolean adicionaProdutoEncomenda(String cpf, int codigoProduto) {
        
        // Verifica se existe alguma encomenda aberta naquele CPF
        if(!existeEncomendaAberta(cpf)) {
            return false;
        }

        ArrayList<String> linhas = retornarConteudoArquivo();

        for(int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            String[] partes = linha.split(";");

            if(partes[0].equals(cpf) && partes[1].equals("ABERTA")) {
                String produtos = (partes.length > 5) ? partes[5] : "";
                if(!produtos.isEmpty())
                    produtos += "," + codigoProduto;
                else
                    produtos = String.valueOf(codigoProduto);
                String novaLinha =
                    partes[0] + ";" + // cpf
                    partes[1] + ";" + // status
                    partes[2] + ";" + // tipoEntrega
                    partes[3] + ";" + // data (vazia)
                    partes[4] + ";" + // valor (vazio)
                    produtos;
                linhas.set(i, novaLinha);
            }
        }

        // Reescreve o arquivo
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arq))) {
            for(String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Adiciona um produto na encomenda a partir do nome do arquivo, cpf do cliente e código do produto criado
    public boolean removeProdutoEncomenda(String cpf, int codigo) {
        ArrayList<String> linhas = retornarConteudoArquivo();

        for(int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            String[] partes = linha.split(";");

            if(partes[0].equals(cpf) && partes[1].equals("ABERTA")) {

                if(partes.length < 6) return false;

                String[] itens = partes[5].split(",");
                ArrayList<String> novosItens = new ArrayList<>();

                for(String item : itens) {
                    if(!item.equals(String.valueOf(codigo))) {
                        novosItens.add(item);
                    }
                }

                // Reconstrói a linha corretamente
                String novaLinha =
                    partes[0] + ";" + // cpf
                    partes[1] + ";" + // status
                    partes[2] + ";" + // tipoEntrega
                    partes[3] + ";" + // data
                    partes[4];        // valor

                if(!novosItens.isEmpty()) {
                    novaLinha += ";" + String.join(",", novosItens);
                }

                linhas.set(i, novaLinha);
                break;
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq))) {
            for(String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Método que recebe o cpf do cliente, produra sua encomenda no arquivo e pega os códigos dos produtos
    // dentro da encomenda. Com isso, é passar para procurar o valor de cada produto ao método do arquivos
    // de produtos e somar a uma variável externa.
    public double calcularTotalProdutos(String cpf) {
        ArrayList<String> codigos = getProdutosEncomenda(cpf);
        double total = 0.0;
        for(String cod : codigos) {
            double preco = arqProduto.getPrecoProdutoPorCodigo(Integer.parseInt(cod));
            total += preco;
        }
        return total;
    }

    public boolean finalizarEncomenda(String cpf, EncomendaTO encomenda) {
        ArrayList<String> linhas = retornarConteudoArquivo();

        for(int i = 0; i < linhas.size(); i++) {
            String[] partes = linhas.get(i).split(";");

            if(partes[0].equals(cpf) && partes[1].equals("ABERTA")) {

                String data = java.time.LocalDate.now().toString();
                double valorFinal = encomenda.calcularValorTotal(true);
                String produtos = (partes.length > 5) ? partes[5] : "";

                String novaLinha =
                    cpf + ";FECHADA;" +
                    encomenda.getTipoEntrega() + ";" +
                    data + ";" +
                    valorFinal + ";" +
                    produtos;

                linhas.set(i, novaLinha);
                break;
            }
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arq))) {
            for(String l : linhas) {
                bw.write(l);
                bw.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Retorna todas as encomendas FECHADAS de um CPF
    public ArrayList<EncomendaTO> getHistoricoPorCpf(String cpf) {
        ArrayList<EncomendaTO> historico = new ArrayList<>();
        ArrayList<String> linhas = retornarConteudoArquivo();

        for(String linha : linhas) {
            String[] partes = linha.split(";");
            if(partes[0].equals(cpf) && partes[1].equals("FECHADA")) {
                ClienteTO cliente = new ClienteTO(cpf, arqCliente.buscarNomePorCpf(cpf), arqCliente.buscarEnderecoPorCpf(cpf));
                String tipo = partes[2];
                String data = partes[3];
                double valor = Double.parseDouble(partes[4]);
                EncomendaTO encomenda = new EncomendaTO(cliente, tipo);
                encomenda.setData(data);
                if(partes.length > 5) {
                    for(String cod : partes[5].split(",")) {
                        ProdutoTO p = arqProduto.getProdutoPorCodigo(Integer.parseInt(cod));
                        if(p != null) encomenda.adicionarProduto(p);
                    }
                }
                historico.add(encomenda);
            }
        }
        return historico;
    }
}