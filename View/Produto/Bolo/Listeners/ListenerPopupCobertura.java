package View.Produto.Bolo.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

public class ListenerPopupCobertura implements ActionListener {

    private JButton botao;
    private JPopupMenu popup;

    public ListenerPopupCobertura(JButton botao, JPopupMenu popup) {
        this.botao = botao;
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        popup.show(botao, 0, botao.getHeight());
    }
}
