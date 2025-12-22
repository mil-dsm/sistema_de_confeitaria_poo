package View.MenuInicial.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Encomenda.EncomendaView;

/**
 * Listener para o botão de encomenda na tela inicial da loja.
 * Ao ser acionado, abre a tela de encomenda ao criar uma instância de EncomendaView.
 */
public class ListenerBtnEncomenda implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        new EncomendaView();
    }
}