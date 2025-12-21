package View.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.EncomendaView;
import Arq.ManipulaArquivosEncomenda;

public class ListenerBtnFinalizarEncomenda implements ActionListener {
    private EncomendaView view;
    private JTextField tfCpf;
    private ManipulaArquivosEncomenda arq;

    public ListenerBtnFinalizarEncomenda(EncomendaView view, JTextField tfCpf) {
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
        String linha = arq.buscaLinhaPorCpf(nomeArquivo, cpf);

        if(linha == null) {
            JOptionPane.showMessageDialog(view, "Encomenda não encontrada para este CPF.");
        }

        if(linha.endsWith("FINALIZADA")) {
            JOptionPane.showMessageDialog(view, "Encomenda já foi finalizada.");
            return;
        }

        // Confirmação de finalização
        int opc = JOptionPane.showConfirmDialog(view, "Deseja finalizar a encomenda?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if(opc == JOptionPane.NO_OPTION) {
            return;
        }

        // Remove linha antiga
        arq.removerElementoArquivo(nomeArquivo, linha);

        // Escreve nova linha com Status finalizado
        String novaLinha = cpf + ";FINALIZADA";
        
        arq.escreverArquivo(nomeArquivo, novaLinha);
        JOptionPane.showMessageDialog(view, "Encomenda finalizada com sucesso.");
    }
}