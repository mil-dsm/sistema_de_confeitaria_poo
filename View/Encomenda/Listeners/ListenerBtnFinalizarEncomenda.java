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
    private EncomendaView componentePai;
    private JTextField tfCpf;
    private ManipulaArquivosEncomenda encomendaArq;

    public ListenerBtnFinalizarEncomenda(EncomendaView componentePai, JTextField tfCpf) {
        this.componentePai = componentePai;
        this.tfCpf = tfCpf;
        this.encomendaArq = new ManipulaArquivosEncomenda();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cpf = tfCpf.getText().trim();
        if(cpf.isEmpty()) {
            JOptionPane.showMessageDialog(componentePai, "Informe o CPF.");
            return;
        }

        String linha = encomendaArq.buscaLinhaPorCpf(cpf);

        if(linha == null) {
            JOptionPane.showMessageDialog(componentePai, "Encomenda não encontrada para este CPF.");
        }

        if(linha.endsWith("FINALIZADA")) {
            JOptionPane.showMessageDialog(componentePai, "Encomenda já foi finalizada.");
            componentePai.desabilitarBotoesEncomenda();
            return;
        }

        // Confirmação de finalização
        int opc = JOptionPane.showConfirmDialog(componentePai, "Deseja finalizar a encomenda?", "Confirmação", JOptionPane.YES_NO_OPTION);
        
        if(opc == JOptionPane.NO_OPTION) {
            return;
        }

        // Remove linha antiga
        encomendaArq.removerElementoArquivo(linha);

        // Escreve nova linha com Status finalizado
        String novaLinha = cpf + ";FINALIZADA";
        
        encomendaArq.escreverArquivo(novaLinha);
        componentePai.desabilitarBotoesEncomenda();
        JOptionPane.showMessageDialog(componentePai, "Encomenda finalizada com sucesso.");
    }
}