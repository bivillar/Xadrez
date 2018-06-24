package Tabuleiro;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;

import Pecas.*;

public class Tabuleiro {
	private static Casa _casa[][] = new Casa[8][8];
	private static movimento _jogadas[][] = new movimento[64][64];
	public static boolean vezBranco = true;
	public static Posicao posReiBranco;
	public static Posicao posReiPreto;

	public Tabuleiro () {
		inicia();
	}

	public Tabuleiro (BufferedReader arqLeitura) throws IOException {
		inicia(arqLeitura);
	}

	public static Casa[][] get_Tabuleiro (){
		return _casa;
	}

	public static movimento[][] get_Jogadas (){
		return _jogadas;
	}

	public static void inicia() {
		vezBranco = true;
		for (int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(y%2 == 0) {
					_casa[x][y] = new Casa('b', null); 
					_casa[++x][y] = new Casa('p', null); 
				}
				else {
					_casa[x][y] = new Casa('p', null); 
					_casa[++x][y] = new Casa('b', null); 
				}
			}
		}

		_casa[0][0].peca = (Torre) new Torre('p',new Posicao());		
		_casa[1][0].peca = (Cavalo) new Cavalo('p',new Posicao(1,0));
		_casa[2][0].peca = (Bispo) new Bispo('p',new Posicao(2,0));
		_casa[3][0].peca = (Rainha) new Rainha('p',new Posicao(3,0));
		_casa[4][0].peca = (Rei) new Rei('p',new Posicao(4,0));
		posReiPreto = new Posicao(4,0);
		_casa[5][0].peca = (Bispo) new Bispo('p',new Posicao(5,0));
		_casa[6][0].peca = (Cavalo) new Cavalo('p',new Posicao(6,0));
		_casa[7][0].peca = (Torre) new Torre('p',new Posicao(7,0));

		_casa[0][1].peca = (Peao) new Peao('p',new Posicao(0,1));
		_casa[1][1].peca = (Peao) new Peao('p',new Posicao(1,1));
		_casa[2][1].peca = (Peao) new Peao('p',new Posicao(2,1));
		_casa[3][1].peca = (Peao) new Peao('p',new Posicao(3,1));
		_casa[4][1].peca = (Peao) new Peao('p',new Posicao(4,1));
		_casa[5][1].peca = (Peao) new Peao('p',new Posicao(5,1));
		_casa[6][1].peca = (Peao) new Peao('p',new Posicao(6,1));
		_casa[7][1].peca = (Peao) new Peao('p',new Posicao(7,1));

		_casa[0][7].peca = (Torre) new Torre('b',new Posicao(0,7));
		_casa[1][7].peca = (Cavalo) new Cavalo('b',new Posicao(1,7));
		_casa[2][7].peca = (Bispo) new Bispo('b',new Posicao(2,7));
		_casa[3][7].peca = (Rainha) new Rainha('b',new Posicao(3,7));
		_casa[4][7].peca = (Rei) new Rei('b',new Posicao(4,7));
		posReiBranco= new Posicao(4,7);
		_casa[5][7].peca = (Bispo) new Bispo('b',new Posicao(5,7));
		_casa[6][7].peca = (Cavalo) new Cavalo('b',new Posicao(6,7));
		_casa[7][7].peca = (Torre) new Torre('b',new Posicao(7,7));

		_casa[0][6].peca = (Peao) new Peao('b',new Posicao(0,6));
		_casa[1][6].peca = (Peao) new Peao('b',new Posicao(1,6));
		_casa[2][6].peca = (Peao) new Peao('b',new Posicao(2,6));
		_casa[3][6].peca = (Peao) new Peao('b',new Posicao(3,6));
		_casa[4][6].peca = (Peao) new Peao('b',new Posicao(4,6));
		_casa[5][6].peca = (Peao) new Peao('b',new Posicao(5,6));
		_casa[6][6].peca = (Peao) new Peao('b',new Posicao(6,6));
		_casa[7][6].peca = (Peao) new Peao('b',new Posicao(7,6));	

		update_Jogadas();
	}

	public static void inicia(BufferedReader arqLeitura) throws IOException{
		String line = null;
		BufferedReader bufferedReader = new BufferedReader(arqLeitura);
		char time;

		for (int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(y%2 == 0) {
					_casa[x][y] = new Casa('b', null); 
					_casa[++x][y] = new Casa('p', null); 
				}
				else {
					_casa[x][y] = new Casa('p', null); 
					_casa[++x][y] = new Casa('b', null); 
				}
			}
		}

		if((line = bufferedReader.readLine())!=null) {
			if(line == "false")
				vezBranco = false;
			else
				vezBranco = true;
		}

		for(int y=0; (line = bufferedReader.readLine()) != null ;y++) {
			for(int x=0,i=0;i<32;i+=2) {
				if(line.charAt(i)=='P') {
					time = 'p';
				}else {
					time = 'b';
				}
				switch(line.charAt(++i)) {
				case 't':
					_casa[x][y].peca = (Torre) new Torre(time,new Posicao(x,y));	
					break;
				case 'c':
					_casa[x][y].peca = (Cavalo) new Cavalo(time,new Posicao(x,y));
					break;
				case 'b':
					_casa[x][y].peca = (Bispo) new Bispo(time,new Posicao(x,y));
					break;
				case 'q':
					_casa[x][y].peca = (Rainha) new Rainha(time,new Posicao(x,y));
					break;
				case 'k':
					_casa[x][y].peca = (Rei) new Rei(time,new Posicao(x,y));
					break;
				case 'p':
					_casa[x][y].peca = (Peao) new Peao(time,new Posicao(x,y));
					break;
				}
				i++;
				if(!_casa[x][y].vazia()) {
					_casa[x][y].peca.qtd_mov=Integer.parseInt(String.valueOf(line.charAt(i)));
				}
				x++;
			}
		}  
		update_Jogadas();
	}

	public static void move_peca(Posicao pos, Posicao dest, Casa[][] tab) {
		tab[dest.x][dest.y].peca = tab[pos.x][pos.y].peca;
		tab[pos.x][pos.y].peca = null;
		tab[dest.x][dest.y].peca.pos.x = dest.x;
		tab[dest.x][dest.y].peca.pos.y = dest.y;
		tab[dest.x][dest.y].peca.qtd_mov++;
		update_Jogadas();
	}


	public static void update_Jogadas () {	
		int i=0;
		for(int k = 0;k<64;k++)
			for(int t=0;t<64;t++)
				_jogadas[t][k]=movimento.invalido;

		for(int y = 0; y<8;y++) {
			for(int x = 0; x<8;x++ ) {
				if(!_casa[x][y].vazia()) {
					if(_casa[x][y].peca.tipo != tPecas.rei)
						_jogadas[x+8*y] = _casa[x][y].peca.mov_valido(_casa);
					else { //guardo as posicoes dos reis pra nao ter que verificar todo o tabuleiro de novo
						if(_casa[x][y].peca.time == 'b')
							posReiBranco.set_Pos(x, y);
						else
							posReiPreto.set_Pos(x, y);
					}
				}
			}
		}

		_jogadas[posReiBranco.x+8*posReiBranco.y] = _casa[posReiBranco.x][posReiBranco.y].peca.mov_valido(_casa);
		_jogadas[posReiPreto.x+8*posReiPreto.y] = _casa[posReiPreto.x][posReiPreto.y].peca.mov_valido(_casa);
	}

	public static boolean xeque(Posicao ataque, Posicao rei) {
		boolean temSaida = false;
		int rx, ry;
		char timeRei = _casa[rei.x][rei.y].peca.time;
		
		//procurar bloqueio
		if(_casa[ataque.x][ataque.y].peca.tipo != tPecas.cavalo) { //só tem bloqueio se nao é cavalo
			
			if(ataque.x - rei.x == 0) { // mesmo x
				//mesmo x mas tem diferenca de y
				ry = (ataque.y-rei.y)/Math.abs(ataque.y-rei.y);
	
				//pra cada posicao do tabuleiro verificar se a jogada na posicao entre o rei e o ataque é valido
				//ou seja, pra cada posicao do tabuleiro tem q verificar pra cada posicao entre o rei e o ataque
				for(int y=0;y<8;y++) {
					for(int x=0;x<8;x++) {
						for(int Y=rei.y+ry;Y!=ataque.y;Y+=ry) {
							if(!_casa[x][y].vazia() && _casa[x][y].peca.time == timeRei && _jogadas[x+8*y][rei.x+8*Y] == movimento.valido) {
								temSaida = true;
								_jogadas[x+8*y][rei.x+8*Y] = movimento.tiraXeque;
							}
						}
					}
				}
			}
			
			else if(ataque.y - rei.y ==0) { // mesmo y
				//mesmo y mas tem diferenca de x
				rx = (ataque.x-rei.x)/Math.abs(ataque.x-rei.x);
	
				//pra cada posicao do tabuleiro verificar se a jogada na posicao entre o rei e o ataque é valido
				//ou seja, pra cada posicao do tabuleiro tem q verificar pra cada posicao entre o rei e o ataque
				for(int y=0;y<8;y++) {
					for(int x=0;x<8;x++) {
						for(int X=rei.x+rx;X!=ataque.x;X+=rx) {
							if(!_casa[x][y].vazia() && _casa[x][y].peca.time == timeRei && _jogadas[x+8*y][X+8*rei.y] == movimento.valido) {
								temSaida = true;
								_jogadas[x+8*y][X+8*rei.y] = movimento.tiraXeque;
							}
						}
					}
				}
			}
			
			else if(Math.abs(ataque.x-rei.x)== Math.abs(ataque.y-rei.y)) {//diagonal
				rx = (ataque.x-rei.x)/Math.abs(ataque.x-rei.x);
				ry = (ataque.y-rei.y)/Math.abs(ataque.y-rei.y);
				System.out.println("rei: x-"+rei.x+"y-"+rei.y);
				
				for(int y=0;y<8;y++) {
					for(int x=0;x<8;x++) {
						for(int X=rei.x+rx, Y=rei.y+ry;Y!=ataque.y && X!=ataque.x;X+=rx, Y+=ry) {
							if(!_casa[x][y].vazia() && _casa[x][y].peca.time == timeRei && _jogadas[x+8*y][X+8*Y] == movimento.valido) {
								temSaida = true;
								_jogadas[x+8*y][X+8*Y] = movimento.tiraXeque;
								System.out.println("----------------------");
								System.out.println("ajuda: x="+x+" y="+y);
								System.out.println("caminho: x="+X+" y="+Y);
							}
						}
					}
				}
			}	
		}
		
		//procurar ataque
		for(int y=0;y<8;y++) {
			for(int x=0;x<8;x++) {
				if(_jogadas[x+8*y][ataque.x+8*ataque.y] == movimento.ataque || _jogadas[x+8*y][ataque.x+8*ataque.y] == movimento.ataque_valido) {
					temSaida = true;
				}else if(_jogadas[rei.x+rei.y*8][x+8*y] == movimento.valido || _jogadas[rei.x+rei.y*8][x+8*y] == movimento.ataque) {
					temSaida = true;
				}
			}
		}
		
		
		for(int k=0;k<64;k++) {
			for(int t=0;t<64;t++){
				if(t != ataque.x+8*ataque.y && k!= rei.x+8*rei.y && _jogadas[k][t] != movimento.tiraXeque) // so deixa manter as jogadas que tiram o xeque
					_jogadas[k][t] = movimento.invalido;
				else if(_jogadas[k][t] == movimento.tiraXeque)
					_jogadas[k][t] = movimento.valido;
			}	
		}

		return temSaida;
	}

	public static void promovePeao(Posicao pos, String tipo) {
		switch(tipo) {
		case "Rainha":
			_casa[pos.x][pos.y].peca = (Rainha) new Rainha(_casa[pos.x][pos.y].peca.time,pos);
			break;
		case "Torre":
			_casa[pos.x][pos.y].peca = (Torre) new Torre(_casa[pos.x][pos.y].peca.time,pos);
			break;
		case "Bispo":
			_casa[pos.x][pos.y].peca = (Bispo) new Bispo(_casa[pos.x][pos.y].peca.time,pos);
			break;
		case "Cavalo":
			_casa[pos.x][pos.y].peca = (Cavalo) new Cavalo(_casa[pos.x][pos.y].peca.time,pos);
			break;
		}
		update_Jogadas();
	}


	public static void Salvar(BufferedWriter fileWriter) {
		PECA p;
		String aux2="",aux3="";

		try {
			fileWriter.write(String.valueOf(vezBranco));
			fileWriter.newLine();

			for(int i=0; i<8;i++){
				for(int j=0; j<8;j++){
					String aux="";
					p=_casa[j][i].peca;
					aux2="Vz0 ";
					if (p != null){
						switch(p.tipo){
						case peao:
							aux = "p";
							break;
						case torre:
							aux = "t";
							break;
						case cavalo:
							aux = "c";
							break;
						case bispo:
							aux = "b";
							break;
						case rainha:
							aux = "q";
							break;
						case rei:
							aux = "k";
							break;
						}

						if(p.qtd_mov>0)
							aux3="1";
						else
							aux3="0";

						if(p.time=='b') {
							aux2="B"+aux+aux3+' ';
						}
						else {
							aux2="P"+aux+aux3+' ';
						}
					}
					fileWriter.write(aux2);

				}
				fileWriter.newLine();
			}



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void imprime () {
		char aux=' ';
		for(int y=0; y<8;y++){
			for(int x=0; x<8;x++){
				if(_casa[x][y].vazia()) {
					if (_casa[x][y].cor == 'b')
						System.out.print("|---|");
					else
						System.out.print("|+++|");
				}
				else {
					switch(_casa[x][y].peca.tipo){
					case peao:
						aux = 'p';
						break;
					case torre:
						aux = 't';
						break;
					case cavalo:
						aux = 'c';
						break;
					case bispo:
						aux = 'b';
						break;
					case rainha:
						aux = 'a';
						break;
					case rei:
						aux = 'e';
						break;
					}

					if(_casa[x][y].peca.time == 'b')
						aux = Character.toUpperCase(aux);

					if (_casa[x][y].cor == 'b')
						System.out.print("|-"+aux+"-|");
					else
						System.out.print("|+"+aux+"+|");
				}

			}
			System.out.println(" ");
		}

	}
}
