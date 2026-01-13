package View.Produto.Donut.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Produto.Donut.DonutView;

public class ListenerBtnCobertura implements ActionListener{
    private DonutView componentePai;
    private String cobertura;

    public ListenerBtnCobertura(DonutView donutView, String cobertura) {
        this.componentePai = donutView;
        this.cobertura = cobertura;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.setOpcaoCobertura(cobertura);
        System.out.println("Cobertura escolhida: " + cobertura);
    }
    
}