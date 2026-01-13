package View.Bolo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnCobertura implements ActionListener{
    private BoloView componentePai;
    private String cobertura;

    public ListenerBtnCobertura(BoloView boloView, String cobertura) {
        this.componentePai = boloView;
        this.cobertura = cobertura;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.setOpcaoCobertura(cobertura);
        System.out.println("Cobertura escolhida: " + cobertura);
    }
    
}
