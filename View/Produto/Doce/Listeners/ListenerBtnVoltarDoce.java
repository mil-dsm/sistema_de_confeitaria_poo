package View.Produto.Doce.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Doce.DoceView;
 
// Finalizado
//botão voltar pra ProdutoView
public class ListenerBtnVoltarDoce implements ActionListener {
    private DoceView componentePai;

    public ListenerBtnVoltarDoce(DoceView doceView) {
        this.componentePai = doceView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.dispose();
        componentePai.getProdutoView().setVisible(true);
    }
}