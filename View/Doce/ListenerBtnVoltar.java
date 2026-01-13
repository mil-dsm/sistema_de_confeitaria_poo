package View.Doce;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Produto.ProdutoView;
 
//botão voltar pra ProdutoView
public class ListenerBtnVoltar implements ActionListener {
    private DoceView componentePai;
    private ProdutoView ProdutoAtual;

    public ListenerBtnVoltar(DoceView doceView) {
        this.componentePai = doceView;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
            componentePai.dispose();
            new ProdutoView();
    }


    }

