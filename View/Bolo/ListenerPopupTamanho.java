
package View.Bolo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

public class ListenerPopupTamanho implements ActionListener {

    private JButton botao;
    private JPopupMenu popup;

    public ListenerPopupTamanho(JButton botao, JPopupMenu popup) {
        this.botao = botao;
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        popup.show(botao, 0, botao.getHeight());
    }
}
