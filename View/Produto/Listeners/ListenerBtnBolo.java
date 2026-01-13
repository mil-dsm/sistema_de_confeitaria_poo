package View.Produto.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.ProdutoView;
import View.Produto.Bolo.BoloView;

public class ListenerBtnBolo implements ActionListener {
    private ProdutoView componentePai;

    public ListenerBtnBolo(ProdutoView produtoView) {
        this.componentePai = produtoView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
       new BoloView(componentePai);
       componentePai.setVisible(false);
    }
}