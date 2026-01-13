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

/* Estrutura:
 * codigo;tipoProduto;quantidade;atributo1;atributo2;atributo3;...
 */

public class ManipulaArquivosProduto {
	public String diretorio;
	public String nomeArquivo;
    public File arq;

	public ManipulaArquivosProduto() {
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

	public ArrayList<String> retornarConteudoArquivo() {
		ArrayList<String> alArquivo = new ArrayList<String>();

		if(arq.isFile() && arq.canRead()) {
			try {
				FileInputStream leitor = new FileInputStream(arq);

				InputStreamReader conversor = new InputStreamReader(leitor);
				BufferedReader leitorBuff = new BufferedReader(conversor);

				String conteudo = leitorBuff.readLine();
				while(conteudo != null) {
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
		if(arq.isFile() && arq.canRead()) {
			try {
				FileInputStream leitor = new FileInputStream(arq);

				InputStreamReader conversor = new InputStreamReader(leitor);
				BufferedReader leitorBuff = new BufferedReader(conversor);

				String conteudo = leitorBuff.readLine();
				if(conteudo==null) System.out.println("O arquivo está vazio");
				while(conteudo != null) {
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

	// Método que recebe o produto criado e adiciona ao arquivo produtos.txt
    public boolean salvarProduto(ProdutoTO produto) {
		return escreverArquivo(produto.gerarLinhaArquivo());
	}

    // Método que recebe o produto que quer remover e remove sua linha no arquivo produtos.txt
    public boolean removerProduto(int codigo) {
		ArrayList<String> alArquivo = retornarConteudoArquivo();
		boolean removido = false;
		for(String linha : alArquivo) {
			String[] dados = linha.split(";");
			int codArquivo = Integer.parseInt(dados[0]);
			if(codArquivo == codigo) {
				removerElementoArquivo(linha);
				removido = true;
				break;
			}
		}
		return removido;
	}

    // Método que retorna o produto a partir do código
	public ProdutoTO getProdutoPorCodigo(int codigo) {
    	ArrayList<String> alArquivo = retornarConteudoArquivo();

    	for(String linha : alArquivo) {
        	String[] dados = linha.split(";");
        	int codArquivo = Integer.parseInt(dados[0]);
			String tipoProduto = dados[1];
			if (dados.length < 4) return null;

			if(codArquivo == codigo) {
				switch(tipoProduto) {
					case "BOLO":
						/* codigo;tipoProduto;quantidade;recheio;cobertura;tamanho */
						BoloTO bolo = new BoloTO(); // Define tipoProduto e preço automticamente
						bolo.setCodigo(Integer.parseInt(dados[0]));
						ProdutoTO.proximoCodigo = Math.max(ProdutoTO.proximoCodigo, bolo.getCodigo() + 1);
						bolo.setQuantidade(Integer.parseInt(dados[2]));
						bolo.setRecheio(Boolean.parseBoolean(dados[3]));
						bolo.setCobertura(dados[4]);
						bolo.setTamanho(dados[5].charAt(0));
						return bolo;
					case "DONUT":
						/* codigo;tipoProduto;quantidade;recheio;cobertura;confete */
						DonutTO donut = new DonutTO(); // Define tipoProduto e preço automticamente
						donut.setCodigo(Integer.parseInt(dados[0]));
						ProdutoTO.proximoCodigo = Math.max(ProdutoTO.proximoCodigo, donut.getCodigo() + 1);
						donut.setQuantidade(Integer.parseInt(dados[2]));
						// donut.setRecheio(Boolean.parseBoolean(dados[3])); //TODO
						donut.setCobertura(dados[4]);
						donut.setConfete(Boolean.parseBoolean(dados[5]));
						return donut;
					case "DOCE":
						/* codigo;tipoProduto;quantidade;tipo */
						DoceTO doce = new DoceTO(); // Define tipoProduto e preço automticamente
						doce.setCodigo(Integer.parseInt(dados[0]));
						ProdutoTO.proximoCodigo = Math.max(ProdutoTO.proximoCodigo, doce.getCodigo() + 1);
						doce.setQuantidade(Integer.parseInt(dados[2]));
						doce.setTipo(dados[3]);
						return doce;
					default:
						return null;
				}
			}
		}
		return null;
	}
	
    // Método que retorna apenas o preço do produto a partir do código
    public double getPrecoProdutoPorCodigo(int codigo) {
		ProdutoTO produto = getProdutoPorCodigo(codigo);
		if(produto != null) {
			return produto.calcularPrecoFinal();
		}
			return -1.0;
	}

	public int getMaiorCodigo() {
		int maior = 0;
		ArrayList<String> linhas = retornarConteudoArquivo();

		for(String linha : linhas) {
			String[] partes = linha.split(";");

			if(partes.length > 0) {
				int codigo = Integer.parseInt(partes[0]);
				if(codigo > maior) {
					maior = codigo;
				}
			}
		}

		return maior;
	}
}