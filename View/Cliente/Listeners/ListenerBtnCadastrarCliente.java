package View.Cliente.Listeners;

import TO.ClienteTO;
import Arq.ManipulaArquivosCliente;
import View.Cliente.ClienteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerBtnCadastrarCliente implements ActionListener {

    private ClienteView view;
    private ManipulaArquivosCliente Arq;

    public ListenerBtnCadastrarCliente(ClienteView view) {
        this.view = view;
        this.Arq = new ManipulaArquivosCliente();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cpf = view.txtCpf.getText();
        String nome = view.txtNome.getText();
        String endereco = (String) view.cbEndereco.getSelectedItem();

        if (cpf.isEmpty() || nome.isEmpty() || view.cbEndereco.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(view, "Preencha todos os campos!");
            return;
        }

        if (Arq.cpfCadastrado(cpf)) {
            JOptionPane.showMessageDialog(view, "CPF já cadastrado!");
            return;
        }

        ClienteTO cliente = new ClienteTO(cpf, nome, endereco);
        Arq.salvarCliente(cliente);

        JOptionPane.showMessageDialog(view,
                "Cliente cadastrado com sucesso!\n" +
                "Distância: " + cliente.estimarDistancia() + " km");

        view.txtCpf.setText("");
        view.txtNome.setText("");
        view.cbEndereco.setSelectedIndex(0);
        view.lblResultado.setText("");
    }
}