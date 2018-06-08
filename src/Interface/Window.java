package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import Principal.Controlador;
import Tabuleiro.*;

public class Window extends JFrame implements ActionListener{

	protected JButton novo, carregar;
    public final int LARG_DEFAULT=560;
    public final int ALT_DEFAULT=582;
    public DesenhoTabuleiro tab;
    
    public Window(Casa t[][]) {
        
        tab = new DesenhoTabuleiro(t);
        
//        JPanel panel = new JPanel();
        
//        novo = new JButton("Novo Jogo");
//        novo.setVerticalTextPosition(AbstractButton.CENTER);
//        novo.setHorizontalTextPosition(AbstractButton.LEADING);
//        novo.setBounds(100, 100, 100, 100);
//        novo.setActionCommand("novo");
//        
//        carregar = new JButton("Carregar Jogo");
//        carregar.setVerticalTextPosition(AbstractButton.CENTER);
//        carregar.setHorizontalTextPosition(AbstractButton.CENTER);
//        carregar.setBounds(100, 100, 100, 100);
//        carregar.setActionCommand("carregar");
//        
//        novo.addActionListener(this);
//        carregar.addActionListener(this);
        
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
        
//        panel.setPreferredSize(new Dimension(100, 100));
//        panel.add(novo);
//        panel.add(carregar);
//        getContentPane().add(panel, BorderLayout.CENTER);

        
//        	JButton novo, carregar;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
//		if("novo".equals(e.getActionCommand())) {
//			getContentPane().add(tab);
//		}else {
//			//TODO carregar
//		}
//		
//		
	}
    
    
}