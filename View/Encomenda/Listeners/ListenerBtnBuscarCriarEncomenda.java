package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosCliente;
import View.Encomenda.EncomendaView;

/**
 * A tela apenas mostra uma encomenda por vez, só é possível criar uma nova encomenda 
 * após finalizar a anterior.
 * Quando o botão é clicado, verifica se já existe uma encomenda aberta para o CPF
 * fornecido.
 * Se existir, exibe uma mensagem informando que a encomenda já está cadastrada
 * Quando não existir, cria uma nova encomenda e exibe uma mensagem de sucesso
 */
public class ListenerBtnBuscarCriarEncomenda implements ActionListener {
    private EncomendaView componentePai;
    private JTextField tfCpf;
    private ManipulaArquivosEncomenda arqEncomenda;
    private ManipulaArquivosCliente arqCliente;

    public ListenerBtnBuscarCriarEncomenda(EncomendaView componentePai, JTextField tfCpf) {
        this.componentePai = componentePai;
        this.tfCpf = tfCpf;
        arqCliente = new ManipulaArquivosCliente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cpf = tfCpf.getText().trim();

        // 1. Verificar campos preenchidos
        if(cpf.isEmpty()) {
            JOptionPane.showMessageDialog(componentePai, "Informe o CPF.");
            return;
        }

        // 2. Verificar se o cliente está cadastrado
        if(!arqCliente.verificarCPF(cpf)) {
            JOptionPane.showMessageDialog(componentePai, "Cliente não cadastrado.");
            return;
        }

        // 3. Criar encomenda
        componentePai.buscarOuCriarEncomenda(cpf);
    }
}