package View.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.EncomendaView;

// Colta para a tela anterior, mas pode chegar a cancelar a encomenda em andamento
public class ListenerBtnVoltar implements ActionListener {
    private EncomendaView view;

    public ListenerBtnVoltar(EncomendaView view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent event) {
        if(view.getEncomendaAtual() != null) {
            int opc = JOptionPane.showConfirmDialog(view, "Encomenda em andamento. Deseja cancelar?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(opc == JOptionPane.NO_OPTION) {
                return;
            }
        }
        view.setEncomendaAtual(null);
        view.dispose();
    }
}