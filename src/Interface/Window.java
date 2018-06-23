package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import Principal.Controlador;
import Tabuleiro.*;

public class Window extends JFrame implements ActionListener{

//	protected JButton novo, carregar;
    public final int LARG_DEFAULT=560;
    public final int ALT_DEFAULT=582;
    public DesenhoTabuleiro tab;
    private JPanel panel;
    private JButton novo,carregar;
    private JLabel l;
    private Image img;
    private Casa[][] tabuleiro;
    JMenuBar menuBar;
    JMenuItem menuItem;
    JMenu menu, submenu;
    
    public Window(Casa t[][]) {
    	
    		//Create the menu bar.
        menuBar = new JMenuBar();
        
        //Build the first menu.
        menu = new JMenu("Jogo");
        menu.getAccessibleContext().setAccessibleDescription("Menu");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Salvar");
        menuItem.addActionListener(this);
        menuItem.setActionCommand("salvar");
        menu.add(menuItem);
        
    		tabuleiro=t;
        
        panel = new JPanel();
        try {
        	img = ImageIO.read(new File("Imagens/chess.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(1);
		}
        l=new JLabel("imagem");
        l.setIcon(new ImageIcon(img));
        panel.add(l);
        
        
        novo = new JButton("Novo Jogo");
        novo.setVerticalTextPosition(AbstractButton.CENTER);
        novo.setHorizontalTextPosition(AbstractButton.LEADING);
        novo.setBounds(100, 100, 100, 100);
        novo.setActionCommand("novo");
        
        carregar = new JButton("Carregar Jogo");
        carregar.setVerticalTextPosition(AbstractButton.CENTER);
        carregar.setHorizontalTextPosition(AbstractButton.CENTER);
        carregar.setBounds(100, 100, 100, 100);
        carregar.setActionCommand("carregar");
        
        novo.addActionListener(this);
        carregar.addActionListener(this);
        
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
        //getContentPane().add(tab);
        
        
        panel.setPreferredSize(new Dimension(100, 100));
        panel.add(novo);
        panel.add(carregar);
        getContentPane().add(panel, BorderLayout.CENTER);

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if("novo".equals(e.getActionCommand())) {
			panel.setVisible(false);
			tab= new DesenhoTabuleiro(tabuleiro);
			getContentPane().add(tab);
			this.setJMenuBar(menuBar);
			
		}else if("carregar".equals(e.getActionCommand())){
			//TODO carregar
			
			final JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int retrival = fc.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
		        try {
		        	FileReader r = new FileReader(fc.getSelectedFile() + ".txt");
		        	BufferedReader fr = new BufferedReader(r);
		        	
		        	// LER O ARQUIVO
		        	//Carregar(fr);
		        	//tab= new DesenhoTabuleiro(tabuleiro);
					//getContentPane().add(tab);
		        	
		            fr.close();
		        } catch (Exception ex) {
		        	// Error writing game file
		            ex.printStackTrace();
		        }
			}
		}else if("salvar".equals(e.getActionCommand())) {
			final JFileChooser fc1 = new JFileChooser();
		}	
	}
    
    
}