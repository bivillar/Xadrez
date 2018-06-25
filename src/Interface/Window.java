package Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Tabuleiro.*;

public class Window extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public final int LARG_DEFAULT=560;
	public final int ALT_DEFAULT=580;
	public static DesenhoTabuleiro tab;
	private static JPanel panel;
	private static Tabuleiro t;
	private JButton novo,carregar;
	private JLabel l;
	private Image img;
	private Casa[][] tabuleiro;
	public JPopupMenu popupmenu;
	final JFrame f= new JFrame("PopupMenu");  

	public Window() {
		t.addObserver(Principal.Controlador.obsTab);
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

		
		panel.setPreferredSize(new Dimension(100, 100));
		panel.add(novo);
		panel.add(carregar);
		getContentPane().add(panel, BorderLayout.CENTER);

	}

	public void novo(){
		getContentPane().remove(tab);
		panel.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if("novo".equals(e.getActionCommand())) {
			panel.setVisible(false);
			t = Tabuleiro.getTabuleiro();
			tab = new DesenhoTabuleiro(Tabuleiro.getCasas());
			getContentPane().add(tab);
			

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
					t = Tabuleiro.getTabuleiro(fr);
					tab = new DesenhoTabuleiro(Tabuleiro.getCasas());
					getContentPane().add(tab);

					fr.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}


}
