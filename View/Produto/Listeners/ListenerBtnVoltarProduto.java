package View.Produto.Listeners;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.ProdutoView;

// Finalizado
//botão voltar pra EncomendaView
public class ListenerBtnVoltarProduto implements ActionListener {
    private ProdutoView componentePai;

    public ListenerBtnVoltarProduto(ProdutoView componentePai) {
        this.componentePai = componentePai;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        componentePai.dispose(); // fecha ProdutoView
        componentePai.getEncomendaView().setVisible(true); // volta EncomendaView
    }
}
