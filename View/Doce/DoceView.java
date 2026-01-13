package View.Doce;


import java.awt.BorderLayout;
import javax.swing.*;
public class DoceView extends JFrame {

    //Arquivos
    //implementar

    //botão que retorna pro botão menu de produto
     private JButton btnVoltar;
     //tipo de doce selecionado
     private String doceSelecionado = null;

    private JButton btnFinalizarBr; 
    private JButton btnFinalizarTr;
    private JButton btnFinalizarBeij;


    //escolher tipo do doce (brigadeiro, trufa, beijinho)
    JMenu menuOpcoes = new JMenu("Tipo");
    JMenuItem itemBrigadeiro = new JMenuItem("Brigadeiro");
    JMenuItem itemTrufa = new JMenuItem("Trufa");
    JMenuItem itemBeijinho = new JMenuItem("Beijinho");
    


    //quantidade de doce
    JTextField txtNumero = new JTextField(10);
   

    public DoceView() {
        setTitle("Personalização de Doces"); // procurar titulo melhor
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //Layout
        setLayout(new BorderLayout());
        
        

         setLayout(null);

         // Botão Voltar
        btnVoltar = new JButton("Voltar");
        add(btnVoltar);
        
        btnVoltar.setBounds(20, 400, 100, 30);
        add(btnVoltar);
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);
        // Menu Escolher Tipo do Doce
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuOpcoes);

        //itens do menu
        menuOpcoes.add(itemBrigadeiro);
        menuOpcoes.add(itemTrufa);
        menuOpcoes.add(itemBeijinho);
        // Campo de texto para quantidade do doce
        txtNumero.setBounds(150, 50, 100, 30);
        //JMenuItem brigadeiro
        ListenerItemBrigadeiro l4 = new ListenerItemBrigadeiro(this);
        itemBrigadeiro.addActionListener(l4);
        //JMenuItem trufa
        ListenerItemTrufa l5 = new ListenerItemTrufa(this);
        itemTrufa.addActionListener(l5);
        //JMenuItem beijinho
        ListenerItemBeijinho l6 = new ListenerItemBeijinho(this);
        itemBeijinho.addActionListener(l6);


    //Btn Finalizar
         JButton btnFinalizarBr = new JButton("Finalizar");
        btnFinalizarBr.setBounds(150, 100, 100, 30);
        add(btnFinalizarBr);
        ListenerBtnFinalizar l2 = new ListenerBtnFinalizar(this);
        btnFinalizarBr.addActionListener(l2);

        
        add(txtNumero);
        setJMenuBar(menuBar);
        setVisible(true);
    }
    //getters
    public JButton getBtnVoltar() {
    return btnVoltar;
}

public JMenuItem getItemBrigadeiro() {
    return itemBrigadeiro;
}

public JMenuItem getItemTrufa() {
    return itemTrufa;
}

public JMenuItem getItemBeijinho() {
    return itemBeijinho;
}

public JTextField getTxtNumero() {
    return txtNumero;
}
    
    public void setDoceSelecionado(String doce) {
        this.doceSelecionado = doce;
    }

    public String getDoceSelecionado() {
        return doceSelecionado;
    }



    public static void main(String[] args) {
        new DoceView();
    }


   

}
