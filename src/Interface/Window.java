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
import javax.swing.filechooser.FileNameExtensionFilter;

import Principal.Controlador;
import Tabuleiro.*;

public class Window extends JFrame implements ActionListener{

	//	protected JButton novo, carregar;
	public final int LARG_DEFAULT=560;
	public final int ALT_DEFAULT=600;
	public DesenhoTabuleiro tab;
	private JPanel panel;
	private JButton novo,carregar;
	private JLabel l;
	private Image img;
	private Casa[][] tabuleiro;
	JMenuBar menuBar;
	JMenuItem menuItem, menuItem1;
	JMenu menu;
	Tabuleiro t = new Tabuleiro();

	public Window() {

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Opções");
		menu.getAccessibleContext().setAccessibleDescription("Menu");
		menuBar.add(menu);

		menuItem = new JMenuItem("Salvar");
		menuItem.addActionListener(this);
		menuItem.setActionCommand("salvar");
		menu.add(menuItem);

		menuItem1 = new JMenuItem("Novo Jogo");
		menuItem1.addActionListener(this);
		menuItem1.setActionCommand("reiniciar");
		menu.add(menuItem1);


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
		panel.setAlignmentY(CENTER_ALIGNMENT);
		getContentPane().add(panel, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("novo".equals(e.getActionCommand())) {
			panel.setVisible(false);
			Tabuleiro.inicia();
			tabuleiro=Tabuleiro.get_Tabuleiro();
			tab= new DesenhoTabuleiro(tabuleiro);
			getContentPane().add(tab);
			this.setJMenuBar(menuBar);

		}else if("carregar".equals(e.getActionCommand())){

			final JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text document", "txt");
			fc.setFileFilter(filter);
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));

			int retrival = fc.showOpenDialog(fc);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				try {
					FileReader r = new FileReader(fc.getSelectedFile());
					BufferedReader fr = new BufferedReader(r);
					
					panel.setVisible(false);
					Tabuleiro.inicia(fr);
					tabuleiro = Tabuleiro.get_Tabuleiro();
					tab= new DesenhoTabuleiro(tabuleiro);
					getContentPane().add(tab);
					this.setJMenuBar(menuBar);

					fr.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}else if("salvar".equals(e.getActionCommand())) {

			final JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int retrival = fc.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				try {
					FileWriter w = new FileWriter(fc.getSelectedFile() + ".txt", false);
					BufferedWriter fw = new BufferedWriter(w);

					// COLOCAR O QUE SALVAR 
					Tabuleiro.Salvar(fw);

					fw.close();
				} catch (Exception ex) {
					// Error writing game file
					ex.printStackTrace();
				}

			}
		}else if("reiniciar".equals(e.getActionCommand())) {
			getContentPane().remove(tab);
			panel.setVisible(true);
		}	

	}


}