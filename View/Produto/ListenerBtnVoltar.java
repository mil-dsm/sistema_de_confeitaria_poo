package View.Produto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import View.Encomenda.EncomendaView;
 
//botão voltar pra EncomendaView
public class ListenerBtnVoltar implements ActionListener {
    private ProdutoView componentePai;
    // private EncomendaView EncomendaAtual;

    public ListenerBtnVoltar(ProdutoView produtoView) {
        this.componentePai = produtoView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            componentePai.dispose();
            // abrir EncomendaView
            //new EncomendaView();
        
    }


    }

