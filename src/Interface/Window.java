package Interface;

import java.awt.*;
import javax.swing.*;
import Principal.Controlador;
import Tabuleiro.*;

public class Window extends JFrame{

    public final int LARG_DEFAULT=560;
    public final int ALT_DEFAULT=582;
    public DesenhoTabuleiro tab;
    private DesenhoPecas p;
    
    public Window(Casa t[][]) {
        
        //p=new DesenhoPecas();
        //p.inicializaImagens(t);
        tab = new DesenhoTabuleiro(t);
        /*
        JButton novo, carregar;
        
        novo=new JButton("Novo Jogo");
        novo.setAlignmentX(Component.CENTER_ALIGNMENT);
        carregar= new JButton("Carregar Jogo");
        carregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        */
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        int sl=screenSize.width;
        int sa=screenSize.height;
        int x=sl/2-LARG_DEFAULT/2;
        int y=sa/2-ALT_DEFAULT/2;
        setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Xadrez");
        getContentPane().add(tab);
        
        
        
    }
    
    
}