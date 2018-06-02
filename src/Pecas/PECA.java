package Pecas;

import Tabuleiro.Casa;
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

	public movimento[] mov_valido(Casa casas[][])  {
		movimento movs[] = new movimento[64];
		for(int i=0;i<64;i++)
			movs[i]=movimento.invalido;
		return movs;
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
