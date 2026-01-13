package View.Produto;

import View.Donut.DonutView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ListenerBtnDonut implements ActionListener {
    private ProdutoView componentePai;
    // private EncomendaView EncomendaAtual;

    public ListenerBtnDonut(ProdutoView produtoView) { //EncomendaView encomendaAtual
        this.componentePai = produtoView;
         // this.EncomendaAtual = encomendaAtual;
         }
        @Override
        public void actionPerformed(ActionEvent event) {
           new DonutView();
           componentePai.dispose();
         }

}