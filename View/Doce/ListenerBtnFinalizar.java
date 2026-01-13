
package View.Doce;

import TO.DoceTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ListenerBtnFinalizar implements ActionListener {
    private DoceView componentePai;
    // private ManipulaArquivosProduto arqProduto;
   // private ManipulaArquivosEncomenda arqEncomenda;

    public ListenerBtnFinalizar(DoceView componentePai) {
        this.componentePai = componentePai;
        //this.encomendaAtual = encomendaAtual;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* Verificar se está tudo selecionado */
        if (componentePai.getDoceSelecionado() == null) {
            JOptionPane.showMessageDialog(componentePai, "Por favor, selecione o tipo de doce.", "Erro", JOptionPane.ERROR_MESSAGE);
            
        } else {
            DoceTO doce = new DoceTO();
            doce.setTipo(componentePai.getDoceSelecionado());
            JOptionPane.showMessageDialog(componentePai, "Doce adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // arqProduto.salvarProduto(doce);
            // componentePai.encomendaAtual.adicionarProduto(doce);
            System.out.println("Tipo: " + doce);

        }

       
        
        
        
        
    }
}