package View.Cliente.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.Cliente.ClienteView;

public class ListenerBtnVoltarCliente implements ActionListener {

    private ClienteView view;

    public ListenerBtnVoltarCliente(ClienteView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.dispose();
    }
}