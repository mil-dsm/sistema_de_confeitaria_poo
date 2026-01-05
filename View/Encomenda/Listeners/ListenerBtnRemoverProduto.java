package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosEncomenda;
import Arq.ManipulaArquivosProdutos;
import TO.EncomendaTO;

/* Listener para o botão btnRemoverProduto 
 * Ao receber qual a tela em que está trabalhando (componentePai), seu objetivo é receber o código do
 * produto que o cliente quer remover e remover da encomenda, retirando do produtos.txt e encomendas.txt
 */
public class ListenerBtnRemoverProduto implements ActionListener {
    private EncomendaTO componentePai;
    ManipulaArquivosEncomenda encomendaArq;
    ManipulaArquivosProdutos produtoArq; 

    public ListenerBtnRemoverProduto(EncomendaTO componentePai) {
        this.componentePai = componentePai;
        encomendaArq = new ManipulaArquivosEncomenda();
        produtoArq = new ManipulaArquivosProdutos();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Implementação
    }
}