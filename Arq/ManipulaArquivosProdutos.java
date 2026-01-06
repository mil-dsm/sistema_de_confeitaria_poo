package Arq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import TO.ProdutoTO;

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

	// Método que recebe o produto criado e adiciona ao arquivo produtos.txt
    public boolean salvarProduto(ProdutoTO produto);

    // Método que recebe o produto que quer remover e remove sua linha no arquivo produtos.txt
    public boolean removerProduto(int codigo);

    // Método que retorna a linha inteira do produto a partir do código
    public String getProdutoPorCodigo(int codigo);

    // Método que retorna apenas o preço do produto a partir do código
    public double getPrecoProdutoPorCodigo(int codigo);
}