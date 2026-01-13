package View.Doce;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
