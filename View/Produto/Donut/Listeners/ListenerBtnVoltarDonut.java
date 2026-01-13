package View.Produto.Donut.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Donut.DonutView;
 
//botão voltar pra ProdutoView
public class ListenerBtnVoltarDonut implements ActionListener {
    private DonutView componentePai;

    public ListenerBtnVoltarDonut(DonutView donutView) {
        this.componentePai = donutView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.dispose();
        componentePai.getProdutoView().setVisible(true);
    }
}