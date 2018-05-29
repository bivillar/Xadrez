import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class aux extends JFrame {
	
	public final int LARG_DEFAULT=400;
	public final int ALT_DEFAULT=300;
	public aux() {
		Toolkit tk=Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int sl=screenSize.width;
		int sa=screenSize.height;
		int x=sl/2-LARG_DEFAULT/2;
		int y=sa/2-ALT_DEFAULT/2;
		setBounds(x,y,LARG_DEFAULT,ALT_DEFAULT);
		getContentPane().setBackground(Color.gray);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	public void paintComponent (Graphics g){
		
		super.paintComponents(g);
		Graphics2D g2d = (Graphics2D) g;
		
		String end;
		Image img=null;
		
		//end="Imagens/Pecas_1/peaoB.gif";
		end="peaoB.gif";
		try {
			img = ImageIO.read(new File(end));
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		g2d.drawImage(img,70,70,null);

		
	}
		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		aux f=new aux();
		f.setTitle("Aux");
		f.setVisible(true);
		
		
	}
}




