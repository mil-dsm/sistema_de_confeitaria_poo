package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosEncomenda;
import View.Encomenda.EncomendaView;
import View.Produto.ProdutoView;

/**
 * Listener do Botão "Adicionar Produto", que procura pelo CPF do cliente que está editando a encomenda,
 * verifica se tem uma encomenda aberta e, caso tenha, adiciona o código do produto que foi personalizado
 * no arquivo encomendas.txt, e atualiza a área que mostra os produtos.
 * Durante a personalização do produto no ProdutoView, o código do produto será tranferido para esse código
 * para ser adicionado a encomenda.
 */
public class ListenerBtnAdicionarProduto implements ActionListener {
    private EncomendaView componentePai;
    private ManipulaArquivosEncomenda encomendaArq;

    public ListenerBtnAdicionarProduto(EncomendaView componentePai) {
        this.componentePai = componentePai;
        encomendaArq = new ManipulaArquivosEncomenda();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(componentePai.getEncomendaAtual() == null) {
            JOptionPane.showMessageDialog(componentePai, "Crie ou busque uma encomenda primeiro.");
            return;
        }

        String cpf = componentePai.getCpf();

        // Verifica se tem encomenda aberta no CPF do cliente que está editando sua encomenda
        String linha = encomendaArq.buscaEncomendaPorCpf(cpf);
        if(linha == null || linha.contains(";ABERTA") == false) {
            JOptionPane.showMessageDialog(componentePai, "Não existe encomenda aberta nesse CPF.");
        } 
        else {
            new ProdutoView(componentePai);
            componentePai.setVisible(false);
        }
    }
}