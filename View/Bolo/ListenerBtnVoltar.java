package View.Bolo;

import View.Produto.ProdutoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
//botão voltar pra ProdutoView
public class ListenerBtnVoltar implements ActionListener {
    private BoloView componentePai;
    private ProdutoView ProdutoAtual;

    public ListenerBtnVoltar(BoloView boloView) {
        this.componentePai = boloView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            componentePai.dispose();
            new ProdutoView();
    }


    }

