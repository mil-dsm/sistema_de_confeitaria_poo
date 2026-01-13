package View.Produto.Bolo;

import View.Produto.Bolo.Listeners.*;
import View.Produto.ProdutoView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;


public class BoloView extends JFrame {
    // Atributos para a sincronização
    private ProdutoView paginaProduto;

    //botão voltar pro menu de produtos
    private JButton btnVoltar;

    private String tamanhoEscolhido;
    private boolean OpcaoRecheio;
    private String OpcaoCobertura;

    //popups
    private JPopupMenu popupTamanho;
    private JPopupMenu popupRecheio;
    private JPopupMenu popupCobertura;

    private JButton btnFinalizar;

    private JButton btnTamanho;
    private JButton btnRecheio;
    private JButton btnCobertura;
    
    public BoloView(ProdutoView paginaProduto) {
        setTitle("Personalização de Bolos"); 
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        /* Inicialização de variaveis */
        this.paginaProduto = paginaProduto;

        setLayout(new BorderLayout(10,10));

        // Popups
        popupTamanho = new JPopupMenu();
        popupRecheio = new JPopupMenu();
        popupCobertura = new JPopupMenu();

        //voltar e finalizar
        JPanel painelInferior = new JPanel(new BorderLayout());
        // Botão Voltar
        JPanel painelVoltar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelInferior.add(painelVoltar, BorderLayout.WEST);
        btnVoltar = new JButton("Voltar");
       // btnVoltar.setBounds(20, 400, 100, 30);
        ListenerBtnVoltarBolo l1 = new ListenerBtnVoltarBolo(this);
        btnVoltar.addActionListener(l1);
        painelVoltar.add(btnVoltar);

        // botões de personalização
        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new BoxLayout(painelOpcoes, BoxLayout.Y_AXIS));
        btnTamanho = new JButton("Tamanho ▼");
        btnRecheio = new JButton("Recheio ▼");
        btnCobertura = new JButton("Cobertura ▼");

        Dimension tamanhoBotao = new Dimension(120, 28);
        Dimension recheioBotao = new Dimension(120, 28);
        Dimension coberturaBotao = new Dimension(120, 28);

        btnTamanho.setPreferredSize(tamanhoBotao);
        btnRecheio.setPreferredSize(recheioBotao);
        btnCobertura.setPreferredSize(coberturaBotao);

        painelOpcoes.add(btnTamanho);
        painelOpcoes.add(btnRecheio);
        painelOpcoes.add(btnCobertura);

        add(painelOpcoes, BorderLayout.CENTER);

        

        //popup tamanho
        ListenerPopupTamanho lpt = new ListenerPopupTamanho(btnTamanho, popupTamanho);
        btnTamanho.addActionListener(lpt);

        //popup recheio
        ListenerPopupRecheio lpr = new ListenerPopupRecheio(btnRecheio, popupRecheio);
        btnRecheio.addActionListener(lpr);

        //popup cobertura
        ListenerPopupCobertura lpc = new ListenerPopupCobertura(btnCobertura, popupCobertura);
        btnCobertura.addActionListener(lpc);

        //sub botões tamanho
        JMenuItem pequeno = new JMenuItem("Pequeno");
        JMenuItem medio   = new JMenuItem("Médio");
        JMenuItem grande  = new JMenuItem("Grande");

        pequeno.addActionListener(new ListenerBtnTamanho(this, "Pequeno"));
        medio.addActionListener(new ListenerBtnTamanho(this, "Médio"));
        grande.addActionListener(new ListenerBtnTamanho(this, "Grande"));
        //sub botões recheio
        JMenuItem sim = new JMenuItem("Sim");
        JMenuItem nao = new JMenuItem("Não");

        sim.addActionListener(new ListenerBtnRecheio(this, true));
        nao.addActionListener(new ListenerBtnRecheio(this, false));

        //sub botões cobertura
        JMenuItem chocolateCobertura = new JMenuItem("Chocolate");
        JMenuItem cremeCobertura     = new JMenuItem("Creme");
        
        chocolateCobertura.addActionListener(new ListenerBtnCobertura(this, "Chocolate"));
        cremeCobertura.addActionListener(new ListenerBtnCobertura(this, "Creme"));

        //finalizar
        JPanel painelFinalizar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnFinalizar = new JButton("Finalizar");
        painelFinalizar.add(btnFinalizar);
        //btnFinalizar.setBounds(200, 400, 100, 30);
        ListenerBtnFinalizar l5 = new ListenerBtnFinalizar(this);
        btnFinalizar.addActionListener(l5);
         painelInferior.add(painelFinalizar, BorderLayout.EAST);
         add(painelInferior, BorderLayout.SOUTH);

        // // Posicionando os botões de personalização
        // btnTamanho.setBounds(20, 50, 100, 30);
        // btnRecheio.setBounds(20, 100, 100, 30);
        // btnCobertura.setBounds(20, 150, 100, 30);
        


        popupTamanho.add(pequeno);
        popupTamanho.add(medio);
        popupTamanho.add(grande);

        popupCobertura.add(chocolateCobertura);
        popupCobertura.add(cremeCobertura);
        
        popupRecheio.add(sim);
        popupRecheio.add(nao);

        painelOpcoes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setVisible(true);
    }

    public void setTamanhoEscolhido(String tamanho) {
       this.tamanhoEscolhido = tamanho;
    }

    public void setOpcaoRecheio(boolean recheio) {
        this.OpcaoRecheio = recheio;
    }
    
   public void setOpcaoCobertura(String cobertura) {
        this.OpcaoCobertura = cobertura;
    }

     public char getTamanhoEscolhido() {
        switch (tamanhoEscolhido) {
            case "Pequeno":
                return 'P';
            case "Médio":
                return 'M';
            case "Grande":
                return 'G';
            default:
                return '\0'; // Retorna um caractere nulo para valores inválidos
        }
    }

    public boolean getOpcaoRecheio() {
        return OpcaoRecheio;
    }
      
    public String getOpcaoCobertura() {
        return OpcaoCobertura;
    }

    public ProdutoView getProdutoView() {
        return paginaProduto;
    }
}
