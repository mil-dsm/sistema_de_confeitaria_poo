package View.Produto.Bolo.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Bolo.BoloView;
 
//botão voltar pra ProdutoView
public class ListenerBtnVoltarBolo implements ActionListener {
    private BoloView componentePai;

    public ListenerBtnVoltarBolo(BoloView boloView) {
        this.componentePai = boloView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.dispose();
        componentePai.getProdutoView().setVisible(true);
    }
}