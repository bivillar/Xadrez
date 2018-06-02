package Interface;
import Tabuleiro.*;
import Pecas.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
//import java.awt.event.*;
//import java.awt.color.*;
//import java.io.*;
//import javax.swing.*;
//import javax.imageio.*;
import java.io.File;


public class DesenhoTabuleiro extends JPanel{
	Casa tabuleiro[][];
	Image imgPb, imgTb, imgCb, imgBb, imgQb, imgRb;
	Image imgPp, imgTp, imgCp, imgBp, imgQp, imgRp;
	
	
	public DesenhoTabuleiro(Casa tab[][]) {
		tabuleiro=tab;
		try {
			imgPb = ImageIO.read(new File("Imagens/Pecas_1/peaoB.png"));
			imgPp = ImageIO.read(new File("Imagens/Pecas_1/peaoP.png"));
			imgTb = ImageIO.read(new File("Imagens/Pecas_1/torreB.png"));
			imgTp = ImageIO.read(new File("Imagens/Pecas_1/torreP.png"));
			imgCb = ImageIO.read(new File("Imagens/Pecas_1/cavaloB.png"));
			imgCp = ImageIO.read(new File("Imagens/Pecas_1/cavaloP.png"));
			imgBb = ImageIO.read(new File("Imagens/Pecas_1/bispoB.png"));
			imgBp = ImageIO.read(new File("Imagens/Pecas_1/bispoP.png"));
			imgQb = ImageIO.read(new File("Imagens/Pecas_1/rainhaB.png"));
			imgQp = ImageIO.read(new File("Imagens/Pecas_1/rainhaP.png"));
			imgRb = ImageIO.read(new File("Imagens/Pecas_1/reiB.png"));
			imgRp = ImageIO.read(new File("Imagens/Pecas_1/reiP.png"));
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
	}
	
	
	public void paintComponent (Graphics g){
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		int tam=70;
		int lado = 65;
		Color BROWN = new Color (139,69,19);
		Color BEIGE = new Color (245,222,179);
		PECA p;
		
		
		
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				Rectangle2D ret=new Rectangle2D.Double(tam*i,tam*j,tam,tam);
				if (tabuleiro[i][j].cor =='b') {
					g2d.setPaint(BEIGE);
				}
				else {
					g2d.setPaint(BROWN);
				}
				g2d.fill(ret);
				//DESENHA PECA
				
				p=tabuleiro[i][j].peca;
				
				if (p!= null) {
					switch(p.time) {
					case 'b':
						switch (p.tipo) {
						case peao:
							g2d.drawImage(imgPb,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case torre:
							g2d.drawImage(imgTb,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case bispo:
							g2d.drawImage(imgBb,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case cavalo:
							g2d.drawImage(imgCb,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case rainha:
							g2d.drawImage(imgQb,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case rei:
							g2d.drawImage(imgRb,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						}
						break;
					case 'p':
						switch (p.tipo) {
						case peao:
							g2d.drawImage(imgPp,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case torre:
							g2d.drawImage(imgTp,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case bispo:
							g2d.drawImage(imgBp,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case cavalo:
							g2d.drawImage(imgCp,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case rainha:
							g2d.drawImage(imgQp,tam*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						case rei:
							g2d.drawImage(imgRp,(tam)*p.pos.x,tam*p.pos.y,lado,lado,null);
							break;
						}
						break;
					}
				}
			}
		}
	}

}
