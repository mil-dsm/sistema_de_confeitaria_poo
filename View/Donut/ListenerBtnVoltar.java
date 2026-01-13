package View.Donut;

import View.Produto.ProdutoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
//botão voltar pra ProdutoView
public class ListenerBtnVoltar implements ActionListener {
    private DonutView componentePai;
    private ProdutoView ProdutoAtual;

    public ListenerBtnVoltar(DonutView donutView) {
        this.componentePai = donutView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            componentePai.dispose();
            new ProdutoView();
     }
    }

