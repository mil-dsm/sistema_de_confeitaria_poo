package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosEncomenda;
import View.Encomenda.EncomendaView;

/** 
 * Classe que implementa o listener para o botão de finalizar encomenda.
 * Ao clicar no botão, verifica se há uma encomenda aberta para o CPF fornecido.
 * Se houver, solicita confirmação para finalizar a encomenda.
 * Se confirmado, atualiza o status da encomenda para "FINALIZADA" no arquivo
 * e desabilita os botões relacionados à encomenda.
 * Se não houver encomenda aberta, exibe uma mensagem informando o usuário.
 */
public class ListenerBtnFinalizarEncomenda implements ActionListener {
    private EncomendaView view;
    private JTextField tfCpf;
    private ManipulaArquivosEncomenda arq;

    public ListenerBtnFinalizarEncomenda(EncomendaView view, JTextField tfCpf) {
        this.view = view;
        this.tfCpf = tfCpf;
        this.arq = new ManipulaArquivosEncomenda();
    }

    @Override
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
            view.desabilitarBotoesEncomenda();
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