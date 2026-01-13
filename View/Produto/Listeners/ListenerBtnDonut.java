package View.Produto.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Donut.DonutView;
import View.Produto.ProdutoView;

public class ListenerBtnDonut implements ActionListener {
    private ProdutoView componentePai;

    public ListenerBtnDonut(ProdutoView produtoView) {
        this.componentePai = produtoView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        new DonutView(componentePai);
        componentePai.setVisible(false);
    }
}