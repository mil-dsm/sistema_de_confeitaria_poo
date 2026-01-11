//precisa testar os métodos
package Arq;


import TO.BoloTO;
import TO.DoceTO;
import TO.DonutTO;
import TO.ProdutoTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ManipulaArquivosProdutos {
	public String diretorio;
	public String nomeArquivo;
    public File arq;

	public ManipulaArquivosProdutos() {
		diretorio = "dados/";
		nomeArquivo = "produtos.txt";
		arq = new File(diretorio+nomeArquivo);
	}

	public boolean escreverArquivo(String texto) {
		try {
			FileOutputStream gravador = new FileOutputStream(arq, true); // O segundo parâmetro indica se mantém o conteúdo que já está no arquivo (true) ou não (sem parâmetro)

			OutputStreamWriter conversor = new OutputStreamWriter(gravador);
			BufferedWriter gravadorBuff = new BufferedWriter(conversor);

			gravadorBuff.write(texto);
			gravadorBuff.newLine();

			gravadorBuff.close();
			gravador.close();

			return(true);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return(false);		    
	}

	public ArrayList retornarConteudoArquivo() {
		ArrayList<String> alArquivo = new ArrayList<String>();

		if (arq.isFile() && arq.canRead()) {
			try {
				FileInputStream leitor = new FileInputStream(arq);

				InputStreamReader conversor = new InputStreamReader(leitor);
				BufferedReader leitorBuff = new BufferedReader(conversor);

				String conteudo = leitorBuff.readLine();
				while (conteudo != null) {
					alArquivo.add(conteudo); 
					conteudo = leitorBuff.readLine();
				}

				leitorBuff.close(); 
				leitor.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		} else { 
			System.out.println("Arquivo aberto ou inexistente");
		}

		return(alArquivo);

	}

	public boolean imprimirArquivo() {
		if (arq.isFile() && arq.canRead()) {
			try {
				FileInputStream leitor = new FileInputStream(arq);

				InputStreamReader conversor = new InputStreamReader(leitor);
				BufferedReader leitorBuff = new BufferedReader(conversor);

				String conteudo = leitorBuff.readLine();
				if(conteudo==null) System.out.println("O arquivo está vazio");
				while (conteudo != null) {
					System.out.println(conteudo); 
					conteudo = leitorBuff.readLine();
				}

				leitorBuff.close(); 
				leitor.close();
				return(true);
			} catch (IOException ioe) {
				ioe.printStackTrace();
				return(false);
			}
		} else { 
			System.out.println("Arquivo aberto ou inexistente");
			return(false);
		}

	}

	public boolean removerElementoArquivo(String valorRemocao) {
		ArrayList<String> alArquivo = retornarConteudoArquivo();

		boolean confirmacao = alArquivo.remove(valorRemocao);

		if(confirmacao) {
			try {
				FileOutputStream gravador = new FileOutputStream(arq); // O segundo parâmetro indica se mantém o conteúdo que já está no arquivo (true) ou não (sem parâmetro)

				OutputStreamWriter conversor = new OutputStreamWriter(gravador);
				BufferedWriter gravadorBuff = new BufferedWriter(conversor);

				for(String valor:alArquivo){
					gravadorBuff.write(valor);
					gravadorBuff.newLine();
				}

				gravadorBuff.close();
				gravador.close();

				return(true);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		return(false);				
	}
	
	public boolean buscarElementoArquivo(String valorBusca) {
		ArrayList<String> alArquivo = retornarConteudoArquivo();
		boolean confirmacao = alArquivo.contains(valorBusca);
		return(confirmacao);				
	}

	// TODO: Método que recebe o produto criado e adiciona ao arquivo produtos.txt
    public boolean salvarProduto(ProdutoTO produto) {
		return escreverArquivo(produto.gerarLinhaArquivo());
	}

    // TODO: Método que recebe o produto que quer remover e remove sua linha no arquivo produtos.txt
    public boolean removerProduto(int codigo) {
		ArrayList<String> alArquivo = retornarConteudoArquivo();
		boolean removido = false;
		for (String linha : alArquivo) {
			String[] dados = linha.split(";");
			int codArquivo = Integer.parseInt(dados[0]);
			if (codArquivo == codigo) {
				removerElementoArquivo(linha);
				removido = true;
				break;
			}
		}
		return removido;
	}

    // TODO: Método que retorna o produto a partir do código
// 	Primeiro passo, identificar o produto pelo código que foi pensado, primeiro método: Se encontrar, chama o próximo método
// Segundo passo, esse método vai receber do anterior o vetor de String, que vai ter a divisão de acordo com o split(";") das variáveis do produto. Ele vai pegar o indice da String que fala dos atributos em específico e define qual o tipo de produto: se começar com o atributo "tamanho=", é bolo, se "tipo=" é doce, se "recheio=" é donut (OU pode fazer pelo nome, como "BOLO", "DONUT", "DOCE", por exemplo)
// Terceiro passo, quando identifica o tipo de produto, chama o método específico daquele produto e passa o vetor de string que foi criado pela divisão do split(";")
		public ProdutoTO getProdutoPorCodigo(int codigo) {
    	ArrayList<String> alArquivo = retornarConteudoArquivo();
		
		

    	for (String linha : alArquivo) {
        String[] dados = linha.split(";");	// divide em ";"

        int codArquivo = Integer.parseInt(dados[0]);	// Pega o código do arquivo

            // doce
            if (TipoProduto.equals("Doce")) {	
                String nome = dados[1];	
                double precoBase = Double.parseDouble(dados[2]);
                int quantidade = Integer.parseInt(dados[3]);

                

                DoceTO doce = new DoceTO(nome, precoBase);
                doce.setQuantidade(quantidade);
        
                doce.setCodigo(codArquivo);

                return doce;
            }

            // donut
            else if (codArquivo == codigo) {
                String nome = dados[1];
                double precoBase = Double.parseDouble(dados[2]);
                int quantidade = Integer.parseInt(dados[3]);
                String recheio = dados[4];
                String cobertura = dados[5];
                boolean confete = Boolean.parseBoolean(dados[6]);

                DonutTO donut = new DonutTO(nome, precoBase);
                donut.setQuantidade(quantidade);
                donut.setRecheio(recheio);
                donut.setCobertura(cobertura);
                donut.setConfete(confete);
                donut.setCodigo(codArquivo);

                return donut;
            }

            // bolo
            else if (codArquivo == codigo) {
                String nome = dados[1];
                double precoBase = Double.parseDouble(dados[2]);
                int quantidade = Integer.parseInt(dados[3]);
                String recheio = dados[4];
                String cobertura = dados[5];
                char tamanho = dados[6].charAt(0);

                BoloTO bolo = new BoloTO(nome, precoBase);
                bolo.setQuantidade(quantidade);
                bolo.setRecheio(recheio);
                bolo.setCobertura(cobertura);
                bolo.setTamanho(tamanho);
                bolo.setCodigo(codArquivo);

                return bolo;
            }
        
    }

    return null; 
}

//TO DO: retorna Donut de acordo com o código
DonutTO getDonutPorCodigo(int codigo) {
	//implementar
	return null;
}

//TO DO: retorna Bolo de acordo com o código
BoloTO getBoloPorCodigo(int codigo) {
	//implementar
	return null;
}

//TO DO: retorna Doce de acordo com o código
DoceTO getDocePorCodigo(int codigo) {
	//implementar
	return null;
}

	

	
    // TODO: Método que retorna apenas o preço do produto a partir do código
    public double getPrecoProdutoPorCodigo(int codigo) {
		ProdutoTO produto = getProdutoPorCodigo(codigo);
		if (produto != null) {
			return produto.calcularPrecoFinal();
		}
			return -1.0;
	}
}