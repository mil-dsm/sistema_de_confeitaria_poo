package View.Historico.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Historico.HistoricoView;

/**
 * Listener para o botão "Voltar" na tela do Menu Inicial.
 * Recebe a tela do histórico(componentePai) e age sobre ela.
 */
public class ListenerBtnVoltar implements ActionListener {
    private HistoricoView componentePai;

    public ListenerBtnVoltar(HistoricoView componentePai) {
        this.componentePai = componentePai;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.dispose();
    }
}