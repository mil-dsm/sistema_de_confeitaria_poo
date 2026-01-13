package View.Produto.Bolo.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import TO.BoloTO;
import View.Produto.Bolo.BoloView;
import Arq.ManipulaArquivosProduto;

public class ListenerBtnFinalizar implements ActionListener {
    private BoloView componentePai;
    private ManipulaArquivosProduto arqProduto;

    public ListenerBtnFinalizar(BoloView componentePai) {
        this.componentePai = componentePai;
        arqProduto = new ManipulaArquivosProduto();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* Verificar se está tudo selecionado */
        if(componentePai.getTamanhoEscolhido() == '\0' && componentePai.getOpcaoCobertura() == null) {
            JOptionPane.showMessageDialog(componentePai, "Por favor, selecione as opções.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(componentePai, "Bolo adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }

        /* Cria novo produto */
        // Chamar métodos que pegam os atributos selecionados...
        BoloTO bolo = new BoloTO();
        bolo.setTamanho(componentePai.getTamanhoEscolhido());
        bolo.setRecheio(componentePai.getOpcaoRecheio());
        bolo.setCobertura(componentePai.getOpcaoCobertura());
        bolo.setQuantidade(1);
      
        arqProduto.salvarProduto(bolo);
        componentePai.getProdutoView().getEncomendaView().adicionarProduto(bolo);
    }
}