package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Arq.*;
import TO.EncomendaTO;

public class ListenerBtnAdicionarProduto implements ActionListener {
    private EncomendaTO encomendaAtual;

    public ListenerBtnAdicionarProduto(EncomendaTO encomendaAtual) {
        this.encomendaAtual = encomendaAtual;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // Implementação do evento de clique no botão Adicionar Produto
    }
}