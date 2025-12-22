package View.Encomenda.Listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Arq.ManipulaArquivosEncomenda;
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
    private EncomendaView view;
    private JTextField tfCpf;
    private ManipulaArquivosEncomenda arqEncomenda;
    private ManipulaArquivosCliente arqCliente;

    public ListenerBtnBuscarCriarEncomenda(EncomendaView view, JTextField tfCpf) {
        this.view = view;
        this.tfCpf = tfCpf;
        this.arqEncomenda = new ManipulaArquivosEncomenda();
        this.arqCliente = new ManipulaArquivosCliente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cpf = tfCpf.getText().trim();

        if(cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Informe o CPF.");
            return;
        }

        if (!arqCliente.clienteExiste(cpf)) {
            JOptionPane.showMessageDialog(view, "Cliente não cadastrado.");
            return;
        }

        String nomeArquivo = "encomendas.txt";
        boolean existeAberta = arqEncomenda.existeEncomendaAberta(nomeArquivo, cpf);
        if(existeAberta) {
            JOptionPane.showMessageDialog(view, "Já existe uma encomenda em aberto para este CPF.");
            view.habilitarBotoesEncomenda();
        } else {
            String novaLinha = cpf + ";ABERTA";
            arqEncomenda.escreverArquivo(nomeArquivo, novaLinha);
            JOptionPane.showMessageDialog(view, "Encomenda criada com sucesso.");
            view.habilitarBotoesEncomenda();
        }
    }
}