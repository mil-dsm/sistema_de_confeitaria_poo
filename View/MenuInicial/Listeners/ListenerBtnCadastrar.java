package View.MenuInicial.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Cliente.ClienteView;

/**
 * Listener para o botão de cadastro na tela inicial da loja.
 * Ao ser acionado, abre a tela de cadastro de cliente ao criar uma instância de CLienteView.
 */
public class ListenerBtnCadastrar implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        new ClienteView();
    }
}