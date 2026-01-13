package View.Produto.Doce.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import TO.DoceTO;
import View.Produto.Doce.DoceView;
import Arq.ManipulaArquivosProduto;

public class ListenerBtnFinalizar implements ActionListener {
    private DoceView componentePai;
    private ManipulaArquivosProduto arqProduto;

    public ListenerBtnFinalizar(DoceView componentePai) {
        this.componentePai = componentePai;
        arqProduto = new ManipulaArquivosProduto();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* Verificar se está tudo selecionado */
        if (componentePai.getDoceSelecionado() == null) {
            JOptionPane.showMessageDialog(componentePai, "Por favor, selecione o tipo de doce.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            int quantidade;
            try {
                quantidade = Integer.parseInt(componentePai.getTxtNumero());
                if (quantidade <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    componentePai,
                    "Informe uma quantidade válida (número inteiro positivo).",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            DoceTO doce = new DoceTO();
            doce.setTipo(componentePai.getDoceSelecionado());
            doce.setQuantidade(quantidade);

            arqProduto.salvarProduto(doce);
            componentePai.getProdutoView().getEncomendaView().adicionarProduto(doce);
            JOptionPane.showMessageDialog(componentePai, "Doce adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            componentePai.dispose();
            componentePai.getProdutoView().setVisible(true);
        }
    }
}