package View.Cliente.Listeners;

import TO.ClienteTO;
import Arq.ManipulaArquivosCliente;
import View.Cliente.ClienteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnBuscarCliente implements ActionListener {

    private ClienteView view;
    private ManipulaArquivosCliente sistema;

    public ListenerBtnBuscarCliente(ClienteView view) {
        this.view = view;
        this.sistema = new ManipulaArquivosCliente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cpf = view.txtCpf.getText();

        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Informe o CPF!");
            return;
        }

        if (!sistema.cpfCadastrado(cpf)) {
            JOptionPane.showMessageDialog(view, "Cliente não encontrado!");
            return;
        }

        String nome = sistema.buscarNomePorCpf(cpf);
        String endereco = sistema.buscarEnderecoPorCpf(cpf);

        ClienteTO cliente = new ClienteTO(cpf, nome, endereco);

        view.lblResultado.setText(
            "Cliente: " + nome +
            " | Bairro: " + endereco +
            " | Distância: " + cliente.estimarDistancia() + " km"
        );

        view.txtNome.setText(nome);
        view.cbEndereco.setSelectedItem(endereco);
    }
}