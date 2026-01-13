package View.Doce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerItemBrigadeiro implements ActionListener {
    private DoceView componentePai;

    public ListenerItemBrigadeiro(DoceView doceView) {
        this.componentePai = doceView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
          componentePai.setDoceSelecionado("brigadeiro");
    }
}
