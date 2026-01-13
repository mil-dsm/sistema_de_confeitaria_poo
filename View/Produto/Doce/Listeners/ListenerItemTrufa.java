package View.Produto.Doce.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Doce.DoceView;

public class ListenerItemTrufa implements ActionListener {
    private DoceView componentePai;

    public ListenerItemTrufa(DoceView doceView) {
        this.componentePai = doceView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
          componentePai.setDoceSelecionado("trufa");
    }
}