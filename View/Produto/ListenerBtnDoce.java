package View.Produto;
import View.Doce.DoceView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnDoce implements ActionListener {
    private ProdutoView componentePai;
   // private EncomendaView EncomendaAtual;

    public ListenerBtnDoce(ProdutoView produtoView) { //EncomendaView encomendaAtual
        this.componentePai = produtoView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
       new DoceView();
       componentePai.dispose();
    }
}
