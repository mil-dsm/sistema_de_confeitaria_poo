package View.Produto.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Doce.DoceView;
import View.Produto.ProdutoView;

// Finalizado
public class ListenerBtnDoce implements ActionListener {
    private ProdutoView componentePai;

    public ListenerBtnDoce(ProdutoView componentePai) {
        this.componentePai = componentePai;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        new DoceView(componentePai);
        componentePai.setVisible(false);
    }
}