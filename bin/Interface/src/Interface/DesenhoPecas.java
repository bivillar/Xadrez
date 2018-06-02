package Interface;
//CARREGA AS IMAGENS 


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Pecas.PECA;
import Pecas.tPecas;
import Tabuleiro.*;

public class DesenhoPecas extends JPanel {
	Image imgPb, imgTb, imgCb, imgBb, imgQb, imgRb;
	Image imgPp, imgTp, imgCp, imgBp, imgQp, imgRp;
	Casa m[][];
	
	public DesenhoPecas() {
		
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
			imgQp = ImageIO.read(new File("Imagens/Pecas_1/rainhaP..png"));
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
		PECA p;
		
		
		
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				//DESENHA PECA
				
				p=m[i][j].peca;
				
				if (p!= null) {
					switch(p.time) {
					case 'b':
						switch (p.tipo) {
						case peao:
							g2d.drawImage(imgPb,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case torre:
							g2d.drawImage(imgTb,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case bispo:
							g2d.drawImage(imgBb,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case cavalo:
							g2d.drawImage(imgCb,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case rainha:
							g2d.drawImage(imgQb,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case rei:
							g2d.drawImage(imgRb,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						}
						break;
					case 'p':
						switch (p.tipo) {
						case peao:
							g2d.drawImage(imgPp,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case torre:
							g2d.drawImage(imgTp,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case bispo:
							g2d.drawImage(imgBp,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case cavalo:
							g2d.drawImage(imgCp,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case rainha:
							g2d.drawImage(imgQp,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						case rei:
							g2d.drawImage(imgRp,tam*p.pos.x,tam*p.pos.y,30,30,null);
							break;
						}
						break;
					}
				}
			}
		}
	}


	
	public void inicializaImagens(Casa m[][]) {
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				switch(i) {
				case 1:
					m[i][j].peca.setImage("Imagens/Pecas_1/peaoB.png");
					break;
				case 6:
					m[i][j].peca.setImage("Imagens/Pecas_1/peaoP.png");
					break;
				case 0:
					if(j==0 || j==7)
						m[i][j].peca.setImage("Imagens/Pecas_1/torreB.png");
					else if(j==1 || j==6)
						m[i][j].peca.setImage("Imagens/Pecas_1/cavaloB.png");
					else if(j==2 || j==5)
						m[i][j].peca.setImage("Imagens/Pecas_1/bispoB.png");
					else if(j==3)
						m[i][j].peca.setImage("Imagens/Pecas_1/rainhaB.png");
					else
						m[i][j].peca.setImage("Imagens/Pecas_1/reiB.png");
					break;
				case 7:
					if(j==0 || j==7)
						m[i][j].peca.setImage("Imagens/Pecas_1/torreP.png");
					else if(j==1 || j==6)
						m[i][j].peca.setImage("Imagens/Pecas_1/cavaloP.png");
					else if(j==2 || j==5)
						m[i][j].peca.setImage("Imagens/Pecas_1/bispoP.png");
					else if(j==3)
						m[i][j].peca.setImage("Imagens/Pecas_1/rainhaP.png");
					else
						m[i][j].peca.setImage("Imagens/Pecas_1/reiP.png");
					break;
				}	
			}
		}

	}
	
	
	
	
	
	
	public void movimento (int origemI, int origemJ, int destinoI, int destinoJ) {
			
	}

}
