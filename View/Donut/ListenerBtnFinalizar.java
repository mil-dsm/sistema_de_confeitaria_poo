package View.Donut;

import TO.DonutTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class ListenerBtnFinalizar implements ActionListener {
    private DonutView componentePai;
    // private ManipulaArquivosProduto arqProduto;
    // private ManipulaArquivosEncomenda arqEncomenda;

    public ListenerBtnFinalizar(DonutView componentePai) {
        this.componentePai = componentePai;
        //this.encomendaAtual = encomendaAtual;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* Verificar se está tudo selecionado */
        if (componentePai.getOpcaoCobertura() == null) {
            JOptionPane.showMessageDialog(componentePai, "Por favor, selecione a cobertura.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        else {
            JOptionPane.showMessageDialog(componentePai, "Donut adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }


        /* Cria novo produto */

        
        // Chamar métodos que pegam os atributos selecionados...

        DonutTO donut = new DonutTO();
        donut.setCobertura(componentePai.getOpcaoCobertura());
        donut.setConfete(componentePai.getOpcaoConfete());


        

      
        //arqProduto.salvarProduto(bolo);
        //componentePai.encomendaAtual.adicionarProduto(bolo);

        
    }
}




        
        
        
        