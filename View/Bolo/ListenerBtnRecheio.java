package View.Bolo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnRecheio implements ActionListener {

    private BoloView componentePai;
    private boolean recheio;

    public ListenerBtnRecheio(BoloView boloview, boolean recheio) {
        this.componentePai = boloview;
        this.recheio = recheio;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.setOpcaoRecheio(recheio);
        if(componentePai.getOpcaoRecheio()) {
            System.out.println("Recheio adicionado");
        }
        else {
            System.out.println("Recheio não adicionado");
        }
       
    }
}
