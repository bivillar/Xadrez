package Pecas;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class PECA {
	public char time;
	public Posicao pos;
	public tPecas tipo;
	Image img;
	
	public PECA(char Time, Posicao Pos, tPecas Tipo) {
		time = Time;
		pos = Pos;
		tipo = Tipo;
	}

	public boolean mov_valido(Posicao dest)  {
		return true;
	}
	
	public void setImage(String end) {
		try {
			System.out.println(end);
			img = ImageIO.read(new File(end));
			
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	public Image getImg() {	
		return img;
	}
}
