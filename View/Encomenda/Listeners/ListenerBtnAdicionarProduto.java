package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosEncomenda;
import View.Encomenda.EncomendaView;
// import View.Produto.ProdutoView;

/**
 * Listener do Botão "Adicionar Produto", que procura pelo CPF do cliente que está editando a encomenda,
 * verifica se tem uma encomenda aberta e, caso tenha, adiciona o código do produto que foi personalizado
 * no arquivo encomendas.txt, e atualiza a área que mostra os produtos.
 * Durante a personalização do produto no ProdutoView, o código do produto será tranferido para esse código
 * para ser adicionado a encomenda.
 */
public class ListenerBtnAdicionarProduto implements ActionListener {
    private EncomendaView encomendaAtual;
    private ManipulaArquivosEncomenda encomendaArq;

    public ListenerBtnAdicionarProduto(EncomendaView encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
        encomendaArq = new ManipulaArquivosEncomenda();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String cpf = encomendaAtual.getCpf();

        // Verifica se tem encomenda aberta no CPF do cliente que está editando sua encomenda
        String linha = encomendaArq.buscaEncomendaPorCpf(cpf);
        if(linha == null || linha.contains(";ABERTA") == false) {
            JOptionPane.showMessageDialog(encomendaAtual, "Não existe encomenda aberta nesse CPF.");
        } 
        else {
            // new ProdutoView(encomendaAtual);
            encomendaAtual.setVisible(false);
        }
    }
}