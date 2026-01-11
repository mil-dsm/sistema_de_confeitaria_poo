package View.MenuInicial.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Historico.HistoricoView;

public class ListenerBtnHistoricoDePedidos implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        new HistoricoView();
    }
}