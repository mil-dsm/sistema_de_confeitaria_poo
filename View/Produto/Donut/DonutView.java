package View.Produto.Donut;

import View.Produto.Donut.Listeners.*;
import View.Produto.ProdutoView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class DonutView extends JFrame {
    // Atributo de vinculo
    private ProdutoView telaProduto;
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
    // Opções
    private String OpcaoCobertura = null;
    private Boolean OpcaoConfete;

    public DonutView(ProdutoView telaProduto) {
        setTitle("Personalização de Donuts");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Atributo para vinculo
        this.telaProduto = telaProduto;

        JPanel painelInferior = new JPanel(new BorderLayout());
        
        // Botão Voltar
        
        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(20, 400, 100, 30);
        ListenerBtnVoltarDonut l1 = new ListenerBtnVoltarDonut(this);
        btnVoltar.addActionListener(l1);
        painelVoltar.add(btnVoltar);
        painelInferior.add(painelVoltar, BorderLayout.WEST);

        //botão finalizar
        JPanel painelFinalizar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnFinalizar = new JButton("Finalizar");
        btnFinalizar.setBounds(200, 400, 100, 30);
       

        btnFinalizar.addActionListener(new ListenerBtnFinalizar(this));
        painelFinalizar.add(btnFinalizar);
        painelInferior.add(painelFinalizar, BorderLayout.EAST);

        add(painelInferior, BorderLayout.SOUTH);

        // Botões de personalização
        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new BoxLayout(painelOpcoes, BoxLayout.Y_AXIS));
        painelOpcoes.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));

        popupCobertura = new JPopupMenu();
        popupConfete = new JPopupMenu();
        btnCobertura = new JButton("Cobertura ▼");
        btnConfete = new JButton("Confete ▼");

        Dimension ConfeteBotao = new Dimension(120, 28);
        Dimension CoberturaBotao = new Dimension(120, 28);

        btnCobertura.setPreferredSize(CoberturaBotao);
        btnCobertura.setMaximumSize(CoberturaBotao);
        btnCobertura.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        btnConfete.setPreferredSize(ConfeteBotao);
       btnConfete.setMaximumSize(ConfeteBotao);
        btnConfete.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        painelOpcoes.add(btnCobertura);
        painelOpcoes.add(Box.createVerticalStrut(10));
        painelOpcoes.add(btnConfete);

        add(painelOpcoes, BorderLayout.CENTER);
        

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

    public void setOpcaoConfete(Boolean OpcaoConfete) {
        this.OpcaoConfete = OpcaoConfete;
    }

    public Boolean getOpcaoConfete() {
        return OpcaoConfete;
    }

    public ProdutoView getProdutoView() {
        return telaProduto;
    }
}
