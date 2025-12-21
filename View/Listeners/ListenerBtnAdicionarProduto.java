package View.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TO.*;

public class ListenerBtnAdicionarProduto implements ActionListener {
    JFrame componentePai;
    private EncomendaTO encomendaAtual;

    public ListenerBtnAdicionarProduto(EncomendaTO encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
    }

    public void actionPerformed(ActionEvent event) {
        // Implementação do evento de clique no botão Adicionar Produto
    }
}