package View.Produto.Doce.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Doce.DoceView;

public class ListenerItemBeijinho implements ActionListener {
  
    private DoceView componentePai;

    public ListenerItemBeijinho(DoceView doceView) {
        this.componentePai = doceView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.setDoceSelecionado("beijinho");
    }
}