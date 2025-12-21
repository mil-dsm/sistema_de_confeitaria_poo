package View.EncomendaView.Listeners;

import javax.swing.*;

import Arq.ManipulaArquivosEncomenda;
import View.EncomendaView.EncomendaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnBuscarCriarEncomenda implements ActionListener {
    private EncomendaView view;
    private JTextField tfCpf;
    private ManipulaArquivosEncomenda arq;

    public ListenerBtnBuscarCriarEncomenda(EncomendaView view, JTextField tfCpf) {
        this.view = view;
        this.tfCpf = tfCpf;
        this.arq = new ManipulaArquivosEncomenda();
    }

    public void actionPerformed(ActionEvent e) {
        String cpf = tfCpf.getText().trim();

        if(cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Informe o CPF.");
            return;
        }

        String nomeArquivo = "encomendas.txt";

        boolean existe = arq.buscarElementoArquivo(nomeArquivo, cpf);

        if(existe) {
            JOptionPane.showMessageDialog(view, "Encomenda já cadastrada para este CPF.");
        }
        else {
            String novaLinha = cpf + ";ABERTA";
            arq.escreverArquivo(nomeArquivo, novaLinha);
            JOptionPane.showMessageDialog(view, "Encomenda criada com sucesso.");
        }
    }
}