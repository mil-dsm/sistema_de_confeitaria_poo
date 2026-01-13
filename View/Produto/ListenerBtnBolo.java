package View.Produto;
import View.Bolo.BoloView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnBolo implements ActionListener {
    private ProdutoView componentePai;
   // private EncomendaView EncomendaAtual;

    public ListenerBtnBolo(ProdutoView produtoView) { //EncomendaView encomendaAtual
        this.componentePai = produtoView;
         // this.EncomendaAtual = encomendaAtual;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
       new BoloView();
       componentePai.dispose();
    }
}
