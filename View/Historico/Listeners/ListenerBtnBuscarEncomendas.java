package View.Historico.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.Historico.HistoricoView;

public class ListenerBtnBuscarEncomendas implements ActionListener {
    private HistoricoView componentePai;

    public ListenerBtnBuscarEncomendas(HistoricoView componentePai) {
        this.componentePai = componentePai;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        componentePai.atualizarListaEncomenda();
    }
}