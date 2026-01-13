package View.Donut;

import View.Doce.ListenerPopupConfete;
import javax.swing.*;

public class DonutView extends JFrame {
    //botão voltar pro menu de produtos
    private JButton btnVoltar;
    // Botões
    private JButton btnCobertura;
    private JButton btnConfete;

    // Popups
    private JPopupMenu popupCobertura;
    private JPopupMenu popupConfete;
   //botão finalizar
    private JButton btnFinalizar;

    private String OpcaoCobertura = null;
    private boolean OpcaoConfete;
    

    public DonutView() {
        setTitle("Personalização de Donuts");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(null);

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 400, 100, 30);
        add(btnVoltar);
        ListenerBtnVoltar l1 = new ListenerBtnVoltar(this);
        btnVoltar.addActionListener(l1);

        //botão finalizar
        btnFinalizar = new JButton("Finalizar");
        btnFinalizar.setBounds(200, 400, 100, 30);
        add(btnFinalizar);

        btnFinalizar.addActionListener(new ListenerBtnFinalizar(this));

        // Botões de personalização
        popupCobertura = new JPopupMenu();
        popupConfete = new JPopupMenu();
        btnCobertura = new JButton("Cobertura ▼");
        btnConfete = new JButton("Confete ▼");

        //popup cobertura
        ListenerPopupCobertura lp1 = new ListenerPopupCobertura(btnCobertura, popupCobertura);
        btnCobertura.addActionListener(lp1);
        //popup confete
        ListenerPopupConfete lp2 = new ListenerPopupConfete(btnConfete, popupConfete);
        btnConfete.addActionListener(lp2);
        JMenuItem chocolate = new JMenuItem("Chocolate");
        JMenuItem morango   = new JMenuItem("Morango");

        chocolate.addActionListener(new ListenerBtnCobertura(this, "Chocolate"));
        morango.addActionListener(new ListenerBtnCobertura(this, "Morango"));

        JMenuItem sim = new JMenuItem("Sim");
        JMenuItem nao = new JMenuItem("Não");

        sim.addActionListener(new ListenerBtnConfete(this, true));
        nao.addActionListener(new ListenerBtnConfete(this, false));


        btnCobertura.setBounds(30, 100, 120, 30);
        btnConfete.setBounds(30, 150, 120, 30);

        
        add(btnCobertura);
        add(btnConfete);

        popupCobertura.add(chocolate);
        popupCobertura.add(morango);

        popupConfete.add(sim);
        popupConfete.add(nao);



        setVisible(true);
    }
    //sets e gets
    public void setOpcaoCobertura(String OpcaoCobertura) {
        this.OpcaoCobertura = OpcaoCobertura;
    }
    public String getOpcaoCobertura() {
        return OpcaoCobertura;
    }

    public void setOpcaoConfete(boolean OpcaoConfete) {
        this.OpcaoConfete = OpcaoConfete;
    }
    public boolean getOpcaoConfete() {
        return OpcaoConfete;
    }
    public static void main(String[] args) {
        new DonutView();
    }
    
    
     

}
