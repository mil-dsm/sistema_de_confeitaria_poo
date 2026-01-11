package View.Historico;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import View.Historico.Listeners.*;
import TO.EncomendaTO;
import Arq.ManipulaArquivosCliente;
import Arq.ManipulaArquivosEncomenda;

public class HistoricoView extends JFrame {
    private JButton btnVoltar;
    private JLabel lbCpf;
    private JTextField tfCpf;
    private JButton btnBuscar;
    private DefaultListModel<EncomendaTO> modelEncomendas;
    private JList<EncomendaTO> listaEncomenda;
    // Arquivos
    ManipulaArquivosCliente arqCliente;
    ManipulaArquivosEncomenda arqEncomenda;

    public HistoricoView() {
        setTitle("Menu Inicial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Inicialização de variáveis */
        arqCliente = new ManipulaArquivosCliente();
        arqEncomenda = new ManipulaArquivosEncomenda();

        /* Painel Principal com BorderLayout */
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /* Painel do topo */
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnVoltar = new JButton("Voltar");
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);

        lbCpf = new JLabel("CPF:");
        tfCpf = new JTextField(15);
        btnBuscar = new JButton("Buscar");
        ListenerBtnBuscarEncomendas l2 = new ListenerBtnBuscarEncomendas(this);
        btnBuscar.addActionListener(l2);

        painelTopo.add(btnVoltar);
        painelTopo.add(new JSeparator(SwingConstants.VERTICAL));
        painelTopo.add(lbCpf);
        painelTopo.add(tfCpf);
        painelTopo.add(btnBuscar);

        /* Painel central (Lista) */
        modelEncomendas = new DefaultListModel<>();
        listaEncomenda = new JList<>(modelEncomendas);
        listaEncomenda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Monta Scroll
        JScrollPane scroll = new JScrollPane(listaEncomenda);
        scroll.setBorder(BorderFactory.createTitledBorder("Encomendas finalizadas"));
        
        /* Montagem */
        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(scroll, BorderLayout.CENTER);

        add(painelPrincipal);
        setVisible(true);
    }

    /* Métodos para atualização */

    // Método que atualiza a área que mostra as encomendas vinculadas ao CPF colocado.
    public void atualizarListaEncomenda() {
        String cpf = getCpf().trim();
        // Verifica CPF e cliente
        if(cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um CPF.");
            return;
        }
        if(!arqCliente.cpfCadastrado(cpf)) {
            JOptionPane.showMessageDialog(this, "Cliente não cadastrado.");
        }

        modelEncomendas.clear();
        // Busca a lista de objetos EncomendaTO no arquivo
        ArrayList<EncomendaTO> historico = arqEncomenda.getHistoricoPorCpf(cpf);

        if(historico.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma encomenda fechada encontrada para este CPF.");
        } else {
            for(EncomendaTO e : historico) {
                modelEncomendas.addElement(e);
            }
        }
    }

    /* Métodos auxiliares */

    // Método que retorna o CPF digitado
    public String getCpf() {
        return tfCpf.getText();
    }
}