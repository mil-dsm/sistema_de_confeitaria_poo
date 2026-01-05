package View.MenuInicial.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Encomenda.EncomendaView;
import View.MenuInicial.MenuInicialView;

/**
 * Listener para o botão de encomenda na tela inicial da loja.
 * Ao ser acionado, abre a tela de encomenda ao criar uma instância de EncomendaView.
 */
public class ListenerBtnEncomenda implements ActionListener {
    MenuInicialView componentePai;
    
    public ListenerBtnEncomenda(MenuInicialView componentePai) {
        this.componentePai = componentePai;
    }

    public void actionPerformed(ActionEvent e) {
        new EncomendaView();
    }
}