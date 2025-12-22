package View.Encomenda.Listeners;

import javax.swing.*;
import View.Encomenda.EncomendaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener para o botão "Voltar" na tela de encomendas.
 * Se houver uma encomenda em andamento, solicita confirmação do usuário para cancelá-la.
 */
public class ListenerBtnVoltar implements ActionListener {
    private EncomendaView view;

    public ListenerBtnVoltar(EncomendaView view) {
        this.view = view;
    }

    @Override
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