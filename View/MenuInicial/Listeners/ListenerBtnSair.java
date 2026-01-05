package View.MenuInicial.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Listener que faz o programa parar a execução */
public class ListenerBtnSair implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }   
}