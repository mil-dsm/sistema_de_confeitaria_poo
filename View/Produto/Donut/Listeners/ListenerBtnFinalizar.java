package View.Produto.Donut.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import TO.DonutTO;
import View.Produto.Donut.DonutView;
import Arq.ManipulaArquivosProduto;


public class ListenerBtnFinalizar implements ActionListener {
    private DonutView componentePai;
    private ManipulaArquivosProduto arqProduto;

    public ListenerBtnFinalizar(DonutView componentePai) {
        this.componentePai = componentePai;
        arqProduto = new ManipulaArquivosProduto();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* Verificar se está tudo selecionado */
        if(componentePai.getOpcaoCobertura() == null || componentePai.getOpcaoConfete() == null) {
            JOptionPane.showMessageDialog(
                componentePai, "Selecione cobertura ou confete.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "Donut adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Cria novo produto 
        DonutTO donut = new DonutTO();
        donut.setCobertura(componentePai.getOpcaoCobertura());
        donut.setConfete(componentePai.getOpcaoConfete());
        donut.setQuantidade(1);
        // Salvar
        arqProduto.salvarProduto(donut);
        componentePai.getProdutoView().getEncomendaView().adicionarProduto(donut);
    }
}