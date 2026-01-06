package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosEncomenda;
import Arq.ManipulaArquivosProduto; // Adicionar implementação
import TO.EncomendaTO;
import View.Encomenda.EncomendaView;

/* Listener para o botão btnRemoverProduto 
 * Ao receber qual a tela em que está trabalhando (componentePai), seu objetivo é receber o código do
 * produto que o cliente quer remover e remover da encomenda, retirando do produtos.txt e encomendas.txt
 */
public class ListenerBtnRemoverProduto implements ActionListener {
    private EncomendaView componentePai;
    private ManipulaArquivosEncomenda encomendaArq;
    //private ManipulaArquivosProdutos produtoArq;

    public ListenerBtnRemoverProduto(EncomendaView componentePai) {
        this.componentePai = componentePai;
        encomendaArq = new ManipulaArquivosEncomenda();
        //produtoArq = new ManipulaArquivosProdutos();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Implementação
    }
}