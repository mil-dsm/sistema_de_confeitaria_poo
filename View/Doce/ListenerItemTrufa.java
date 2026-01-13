package View.Doce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
