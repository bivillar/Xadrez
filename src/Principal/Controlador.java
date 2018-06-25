package Principal;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import Tabuleiro.*;
import Pecas.*;

import javax.swing.*;

import Interface.Window;

public class Controlador implements MouseListener{
	private Casa _tabuleiro[][];
	private movimento _jogadas[][];
	private Casa _origem = null;
	private Casa _destino = null;
	private Posicao _pos = new Posicao (0,0);
	private Posicao _dest  = new Posicao (0,0);
	private boolean xequeMate = false;
	private JPopupMenu popupmenu;
	private JPopupMenu popupmenuPromo; 

	public static Casa get_casa (Posicao pos, Casa[][] tab) { //recebe uma posicao da tela e retorna a casa que estao dentro dela, se houver
		//int x,y;

		if(pos.x>560 || pos.y>580) {
			return null;
		}
		else {
			pos.x = (int) pos.x/70;
			pos.y = (int) (pos.y-23)/70;
			return tab[pos.x][pos.y];
		}
	}

	/*retorna a peca q ta no caminho de outra ou null se nao tiver nenhuma*/
	public Casa caminho (Posicao pos, Posicao dest, Casa[][] tab) { 

		if(pos.x==dest.x) { //mesmo x
			if(pos.y<dest.y) { //destino ta mais alto
				for(int i=1; i<= dest.y - pos.y ;i++) 
					if (!tab[pos.x][pos.y+i].vazia()) 
						return tab[pos.x][pos.y+i];

			}else {
				for(int i=1; i<= pos.y - dest.y ;i++) //destino ta mais baixo
					if (!tab[pos.x][pos.y-i].vazia()) 
						return tab[pos.x][pos.y-i];
			}

		}else if(pos.y==dest.y) { //mesmo y

			if(pos.x<dest.x) {
				for(int i=1; i<= dest.x-pos.x ;i++) //destino mais a direita
					if (!tab[pos.x+i][pos.y].vazia()) 
						return tab[pos.x+i][pos.y];

			} else {
				for(int i=1; i<= pos.x-dest.x;i++) //destino mais a esquerda
					if (!tab[pos.x-i][pos.y].vazia()) 
						return tab[pos.x-i][pos.y];
			}

		}else if(Math.abs(dest.x-pos.x) == Math.abs(dest.y-pos.y)) { // diagonal

			if(dest.x>pos.x) {		//direita
				if(dest.y>pos.y) {		//em cima
					for(int i=1; i<= dest.x-pos.x;i++) 
						if (!tab[pos.x+i][pos.y+i].vazia()) 
							return tab[pos.x+i][pos.y+i];
				}else { 					//embaixo
					for(int i=1; i<= dest.x-pos.x;i++) 
						if (!tab[pos.x+i][pos.y-i].vazia()) 
							return tab[pos.x+i][pos.y-i];
				}
			} else{ 					//esquerda
				if(dest.y>pos.y) {		//em cima
					for(int i=1; i<= dest.y-pos.y; i++) 
						if (!tab[pos.x-i][pos.y+i].vazia()) 
							return tab[pos.x-i][pos.y+i];
				}else {					//embaixo
					for(int i=1; i<= dest.y-pos.y; i++) 
						if (!tab[pos.x-i][pos.y-i].vazia()) 
							return tab[pos.x-i][pos.y-i];
				}
			}
		}else { //cavalo
			if(!tab[dest.x][dest.y].vazia())
				return tab[dest.x][dest.y];
			else
				return null;
		}

		return null;		
	}

	public void verificaCaminhosLivres (Casa origem) {
		int i,j;


		for(i=0;i<8;i++) {
			for(j=0;j<8;j++) {
				if(_jogadas[_pos.x+8*_pos.y][i+8*j] == movimento.valido) {
					_tabuleiro[i][j].movT='v';
				}
				else if(_jogadas[_pos.x+8*_pos.y][i+8*j] == movimento.ataque ) {
					_tabuleiro[i][j].movT='a';
				}
				else{
					_tabuleiro[i][j].movT='0';
				}
			}
		}
		_tabuleiro[origem.peca.pos.x][origem.peca.pos.y].movT='p'; //indica que é a posição do próprio peão
		//obsTab.notify();
		Window.tab.repaint();

	}

	public void repaintTabuleiro() {
		for (int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				_tabuleiro[x][y].movT='0';
			}
		}

		//obsTab.notify();
		Window.tab.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent c) {	
		popupmenu = new JPopupMenu();
		JMenuItem item;
		popupmenu.add(item = new JMenuItem("Salvar"));

		item.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
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
					}
					catch (Exception ex) {
						// Error writing game file
						ex.printStackTrace();
					}
				} 
			}  
		});  

		popupmenu.add(item = new JMenuItem("Novo Jogo"));
		item.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) { 
				Tabuleiro.destroiTabuleiro();
				Main.janelaJogo.novo();
			}  
		});  


		if(SwingUtilities.isRightMouseButton(c)) {
			popupmenu.show(Main.janelaJogo , c.getX(), c.getY());  
		}

		//se for pra subir direto, comentar linhas aqui em cima e descomentar as abaixo

		//		if(SwingUtilities.isRightMouseButton(c)) {
		//			final JFileChooser fc = new JFileChooser();
		//			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		//			int retrival = fc.showSaveDialog(null);
		//			if (retrival == JFileChooser.APPROVE_OPTION) {
		//				try {
		//					FileWriter w = new FileWriter(fc.getSelectedFile() + ".txt", false);
		//					BufferedWriter fw = new BufferedWriter(w);
		//					// COLOCAR O QUE SALVAR 
		//					Tabuleiro.Salvar(fw);
		//					fw.close();
		//				}
		//				catch (Exception ex) {
		//					// Error writing game file
		//					ex.printStackTrace();
		//				}
		//			} 
		//		}

		_tabuleiro = Tabuleiro.getTabuleiro();
		_jogadas = Tabuleiro.getJogadas();


		if(_origem == null) { // nao selecionaram quem vai atacar ate agora
			_pos.set_Pos(c.getX(), c.getY());
			_origem = get_casa(_pos,_tabuleiro);

			if(_origem != null && _origem.vazia()) { //nao tem peca
				_origem = null;
			}else if(_origem != null && Tabuleiro.getVez() != _origem.peca.time) {
				_origem = null;
			}
			else {
				verificaCaminhosLivres(_origem);
			}

		}
		else { //vamos ao ataque
			_dest.set_Pos(c.getX(), c.getY());
			_destino = get_casa(_dest,_tabuleiro);

			if(_origem == _destino || _destino == null) {
				_origem = null;
				repaintTabuleiro();

			}else if(!_destino.vazia() && _destino.peca.time == _origem.peca.time) {
				if(_destino.peca.tipo == tPecas.torre && _origem.peca.tipo == tPecas.rei) {
					if(_jogadas[_pos.x+8*_pos.y][_dest.x+8*_dest.y] == movimento.valido && !Tabuleiro.estaEmXeque()) {
						//ROQUE

						Posicao nRei = new Posicao();
						Posicao nTorre = new Posicao();
						int fat = 1;

						if(_pos.x> _dest.x) {
							fat = -1;
						}

						nRei.set_Pos(_pos.x+2*fat,_dest.y);
						nTorre.set_Pos(nRei.x-1*fat,_dest.y);

						Tabuleiro.move_peca(_pos,nRei,_tabuleiro); // bota o rei duas casas mais perto da torre
						Tabuleiro.move_peca(_dest,nTorre,_tabuleiro);  //bota a torre na casa do lado do rei


						Tabuleiro.mudarVez();

					}

					_origem = null;
					repaintTabuleiro();

				}
				else {
					_pos.set_Pos(_dest.x,_dest.y);
					_origem = _destino;
					verificaCaminhosLivres(_origem);
				}
			} else {
				if(_jogadas[_pos.x+8*_pos.y][_dest.x+8*_dest.y]== movimento.valido ||  _jogadas[_pos.x+8*_pos.y][_dest.x+8*_dest.y]== movimento.ataque) {
					Tabuleiro.move_peca(_pos,_dest,_tabuleiro);

					if(!(_destino.peca.tipo != tPecas.peao || (_destino.peca.time == 'b' && _destino.peca.pos.y!=0) || (_destino.peca.time == 'p' && _destino.peca.pos.y!=7))) {
						promoPeaoJpop(_destino); //PROMOCAO PEAO
						popupmenuPromo.show(Main.janelaJogo, c.getX(), c.getY()); 
					}
					Tabuleiro.mudarVez();	
					verifica_xeque();
				}
				repaintTabuleiro();
				_origem = null;
			}
			_destino = null;
		}
		if(xequeMate || Tabuleiro.estaEmXeque()) {
			xequeMate = false;
		}else {
			if(congelamento(Tabuleiro.getVez())) {
				JOptionPane.showMessageDialog(Main.janelaJogo,
						"CONGELAMENTO!!\nNÃO HÁ MAIS JOGADAS VÁLIDAS PARA O TIME DA VEZ",
						"Aviso",
						JOptionPane.WARNING_MESSAGE);
				Tabuleiro.destroiTabuleiro();
				Main.janelaJogo.novo();
			}
		}
	}

	private void verifica_xeque() {
		Posicao reis[]= {Tabuleiro.getPosReiBranco(),Tabuleiro.getPosReiPreto()}; 
		String time[]= {"PRETO", "BRANCO"};
		boolean vez[]= {false, false};

		if(Tabuleiro.getVez() == 'b') {
			vez[0] = true;
		}else {
			vez[1] = true;
		}

		for(int i=0;i<2;i++) {
			if(!xequeMate) {
				for(int y=0;y<8;y++) {
					for(int x=0;x<8;x++) {
						if(_jogadas[x+8*y][reis[i].x+reis[i].y*8] == movimento.ataque || _jogadas[x+8*y][reis[i].x+reis[i].y*8] == movimento.ataque_valido) {
							//XEQUE OU XEQUE MATE
							if(vez[i] && Tabuleiro.xeque(new Posicao(x,y),reis[i])) {
								JOptionPane.showMessageDialog(Main.janelaJogo,
										"XEQUE PELO TIME " + time[i],
										"Aviso",
										JOptionPane.WARNING_MESSAGE);
							}else {
								xequeMate = true;
								JOptionPane.showMessageDialog(Main.janelaJogo,
										"XEQUE-MATE!!\nVITORIA DO TIME " + time[i],
										"Aviso",
										JOptionPane.WARNING_MESSAGE);
								Tabuleiro.destroiTabuleiro();
								Main.janelaJogo.novo();
							}
						}
					}
				}
			}
		}
	}

	private boolean congelamento(char time) {
		for(int x=0;x<8;x++) {
			for(int y=0;y<8;y++) {
				if(!_tabuleiro[x][y].vazia() && _tabuleiro[x][y].peca.time == time) {
					for(int k=0;k<64;k++) {
						if(_jogadas[x+8*y][k] == movimento.valido || _jogadas[x+8*y][k] == movimento.ataque)
							return false;
					}
				}
			}
		}
		return true;
	}

	private void promoPeaoJpop (Casa c){ //tentei fazer com Jpop q é como ele quer mas n consegui :(
		popupmenuPromo= new JPopupMenu(); 
		JMenuItem item;

		popupmenuPromo.add(item = new JMenuItem("Rainha"));
		item.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
				Tabuleiro.promovePeao(c.peca.pos,"Rainha");
				repaintTabuleiro();
				verifica_xeque();
			}  
		});

		popupmenuPromo.add(item = new JMenuItem("Torre"));
		item.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
				Tabuleiro.promovePeao(c.peca.pos,"Torre");
				repaintTabuleiro();
				verifica_xeque();
			}  
		});

		popupmenuPromo.add(item = new JMenuItem("Bispo"));
		item.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
				Tabuleiro.promovePeao(c.peca.pos,"Bispo");
				repaintTabuleiro();
				verifica_xeque();
			}  
		});

		popupmenuPromo.add(item = new JMenuItem("Cavalo"));
		item.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {              
				Tabuleiro.promovePeao(c.peca.pos,"Cavalo");
				repaintTabuleiro();
				verifica_xeque();
			}  
		});
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
