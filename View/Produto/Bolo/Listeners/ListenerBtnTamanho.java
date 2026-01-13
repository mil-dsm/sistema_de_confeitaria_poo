package View.Produto.Bolo.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Bolo.BoloView;

public class ListenerBtnTamanho implements ActionListener {
    private BoloView componentePai;
    private String tamanhoSelecionado;

    public ListenerBtnTamanho(BoloView boloView, String tamanhoSelecionado) {
        this.componentePai = boloView;
        this.tamanhoSelecionado = tamanhoSelecionado;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
     componentePai.setTamanhoEscolhido(tamanhoSelecionado);
        System.out.println("Tamanho escolhido: " + tamanhoSelecionado);
    }
    
}