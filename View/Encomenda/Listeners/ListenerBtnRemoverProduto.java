package View.Encomenda.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Encomenda.EncomendaView;

/* Listener para o botão btnRemoverProduto 
 * Ao receber qual a tela em que está trabalhando (componentePai), chama o método
 * de remover o produto que o cliente selecionar. Com isso, remove dentro dos ar-
 * quivos de encomendas.txt e produtos.txt e do objeto EncomendaTO no componentePai.
 */
public class ListenerBtnRemoverProduto implements ActionListener {
    private EncomendaView componentePai;

    public ListenerBtnRemoverProduto(EncomendaView componentePai) {
        this.componentePai = componentePai;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.removerProdutoSelecionado();
    }
}