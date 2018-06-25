package Interface;

import Principal.Facade;
import Pecas.Posicao;

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
	private static final long serialVersionUID = 1L;
	Image imgPb, imgTb, imgCb, imgBb, imgQb, imgRb;
	Image imgPp, imgTp, imgCp, imgBp, imgQp, imgRp;
	Facade _facade;
	Posicao pos = new Posicao();
	
	public DesenhoTabuleiro(Facade facade) {
		_facade = facade;
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
		int t = 10;
		Color BROWN = new Color (139,69,19);
		Color BEIGE = new Color (245,222,179);
		Color YEL = new Color(255,255,100);
		Color RED = new Color(214,0,0);
		Color BLUE = new Color(0,116,224);
		Color c = null;
		BasicStroke espRet= new BasicStroke(t-3);

		
		
		for(int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				Rectangle2D ret=new Rectangle2D.Double(tam*i,tam*j,tam,tam);
				Rectangle2D ret2=new Rectangle2D.Double(tam*i+t/2,tam*j+t/2,tam-t,tam-t);
				pos.set_Pos(i, j);
				
				switch (_facade.CasaGetCor(pos)) {
					case 'g':
						c=Color.GRAY;
						break;
					case 'b':
						c=BEIGE;
						break;
					case 'p':
						c=BROWN;
						break;
				}
				
				g2d.setPaint(c);
				g2d.fill(ret);
				
				if (_facade.CasaGetMovT(pos) != '0'){
					g2d.setStroke(espRet);
					if(_facade.CasaGetMovT(pos) == 'v')
						g2d.setColor(YEL);
					else if (_facade.CasaGetMovT(pos) == 'a')
						g2d.setColor(RED);
					else
						g2d.setColor(BLUE);
					g2d.draw(ret2);	
				}
				
				//DESENHA PECA
				
				if (_facade.CasaNaoVazia(pos)) {
					switch(_facade.PecaGetTime(pos)) {
					case 'b':
						switch (_facade.PecaGetTipo(pos)) {
						case peao:
							g2d.drawImage(imgPb,tam*i,tam*j,lado,lado,null);
							break;
						case torre:
							g2d.drawImage(imgTb,tam*i,tam*j,lado,lado,null);
							break;
						case bispo:
							g2d.drawImage(imgBb,tam*i,tam*j,lado,lado,null);
							break;
						case cavalo:
							g2d.drawImage(imgCb,tam*i,tam*j,lado,lado,null);
							break;
						case rainha:
							g2d.drawImage(imgQb,tam*i,tam*j,lado,lado,null);
							break;
						case rei:
							g2d.drawImage(imgRb,tam*i,tam*j,lado,lado,null);
							break;
						}
						break;
					case 'p':
						switch (_facade.PecaGetTipo(pos)) {
						case peao:
							g2d.drawImage(imgPp,tam*i,tam*j,lado,lado,null);
							break;
						case torre:
							g2d.drawImage(imgTp,tam*i,tam*j,lado,lado,null);
							break;
						case bispo:
							g2d.drawImage(imgBp,tam*i,tam*j,lado,lado,null);
							break;
						case cavalo:
							g2d.drawImage(imgCp,tam*i,tam*j,lado,lado,null);
							break;
						case rainha:
							g2d.drawImage(imgQp,tam*i,tam*j,lado,lado,null);
							break;
						case rei:
							g2d.drawImage(imgRp,(tam)*i,tam*j,lado,lado,null);
							break;
						}
						break;
					}
				}
			}
		}
	}

}
