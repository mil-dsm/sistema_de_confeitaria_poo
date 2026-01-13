package View.Donut;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnConfete implements ActionListener {

    private DonutView componentePai;
    private boolean  confete;

    public ListenerBtnConfete(DonutView donutview, boolean confete) {
        this.componentePai = donutview;
        this.confete = confete;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.setOpcaoConfete(confete);
        if(componentePai.getOpcaoConfete()) {
            System.out.println("Confete adicionado");
        }
        else {
             System.out.println("Confete não adicionado");
        }
    }
}
