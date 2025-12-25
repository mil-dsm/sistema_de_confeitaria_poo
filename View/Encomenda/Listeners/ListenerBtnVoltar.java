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
    private EncomendaView componentePai;

    public ListenerBtnVoltar(EncomendaView componentePai) {
        this.componentePai = componentePai;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(componentePai.getEncomendaAtual() != null) {
            int opc = JOptionPane.showConfirmDialog(componentePai, "Encomenda em andamento. Deseja cancelar?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if(opc == JOptionPane.NO_OPTION) {
                return;
            }
        }
        componentePai.setEncomendaAtual(null);
        componentePai.dispose();
    }
}