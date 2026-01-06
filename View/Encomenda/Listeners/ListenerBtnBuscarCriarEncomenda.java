package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.*;
import TO.ClienteTO;
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
    private ClienteTO clienteAtual;

    public ListenerBtnBuscarCriarEncomenda(EncomendaView componentePai, JTextField tfCpf) {
        this.componentePai = componentePai;
        this.tfCpf = tfCpf;
        arqEncomenda = new ManipulaArquivosEncomenda();
        arqCliente = new ManipulaArquivosCliente();
        clienteAtual = new ClienteTO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cpf = tfCpf.getText().trim();

        if(cpf.isEmpty()) {
            JOptionPane.showMessageDialog(componentePai, "Informe o CPF.");
            return;
        }

        // Verificação se o cliente está cadastrado
        if(!arqCliente.clienteExiste(cpf)) {
            JOptionPane.showMessageDialog(componentePai, "Cliente não cadastrado.");
            return;
        }

        // Diz qual o cliente criado
        clienteAtual.setNome(arqCliente.getNome(cpf));
        clienteAtual.setEndereco(arqCliente.getEndereco(cpf));
        clienteAtual.setCpf(cpf);

        // Verifica o arquivo encomendas.txt
        if(arqEncomenda.existeEncomendaAberta(cpf)) {
            JOptionPane.showMessageDialog(componentePai, "Já existe uma encomenda em aberto para este CPF.");
            componentePai.habilitarBotoesEncomenda();
        } else {
            String novaLinha = cpf + ";ABERTA";
            arqEncomenda.escreverArquivo(novaLinha);
            JOptionPane.showMessageDialog(componentePai, "Encomenda criada com sucesso.");
            componentePai.habilitarBotoesEncomenda();
        }
    }
}