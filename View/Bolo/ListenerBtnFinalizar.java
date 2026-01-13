package View.Bolo;

import TO.BoloTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ListenerBtnFinalizar implements ActionListener {
    private BoloView componentePai;
    // private ManipulaArquivosProduto arqProduto;
    // private ManipulaArquivosEncomenda arqEncomenda;

    public ListenerBtnFinalizar(BoloView componentePai) {
        this.componentePai = componentePai;
        //this.encomendaAtual = encomendaAtual;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* Verificar se está tudo selecionado */
        if (componentePai.getTamanhoEscolhido() == '\0' && componentePai.getOpcaoCobertura() == null) {
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
        

      
        //arqProduto.salvarProduto(bolo);
        //componentePai.encomendaAtual.adicionarProduto(bolo);

        
    }
}




        
        
        
        