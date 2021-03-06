package Tabuleiro;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Observable;

import Pecas.*;

public class Tabuleiro extends Observable {
	private static Tabuleiro t = null;
	private static Casa _casa[][] = new Casa[8][8];
	private static movimento _jogadas[][] = new movimento[64][64];
	private static boolean _vezBranco;
	private static Posicao _posReiBranco;
	private static Posicao _posReiPreto;
	private static boolean _xeque = false;
	private static Posicao _pXequeAtaque = new Posicao();

	private Tabuleiro() {
		_vezBranco = true;
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
		_posReiPreto = new Posicao(4,0);
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
		_posReiBranco= new Posicao(4,7);
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

		updateJogadas();
	}
	
	private Tabuleiro(BufferedReader arqLeitura) throws IOException {
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
			if(line.contains("false"))
				_vezBranco = false;
			else
				_vezBranco = true;
		}

		if((line = bufferedReader.readLine())!=null) {
			if(line.contains("false"))
				_xeque = false;
			else {
				_xeque = true;
				_pXequeAtaque.x = Integer.parseInt(String.valueOf(line.charAt(5)));
				_pXequeAtaque.y = Integer.parseInt(String.valueOf(line.charAt(7)));
			}
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
					if(time == 'b')
						_posReiBranco= new Posicao(x,y);
					else
						_posReiPreto= new Posicao(x,y);
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
		updateJogadas();
		if(_xeque) {
			if(_casa[_pXequeAtaque.x][_pXequeAtaque.y].peca.time == 'b') {
				xeque(_pXequeAtaque, _posReiPreto);
			}else {
				xeque(_pXequeAtaque, _posReiBranco);
			}
		}
	}
	
	public static Tabuleiro getTabuleiro (){
		if(t== null) {
			t = new Tabuleiro();
		}
		return t;
	}
	
	public static Tabuleiro getTabuleiro (BufferedReader arqLeitura) throws IOException{
		if(t == null) {
			t = new Tabuleiro(arqLeitura);
		}
		return t;
	}
	
	public static Casa[][] getCasas(){
		return _casa;
	}
	
	public static void destroiTabuleiro() {
		t = null;
	}
	
	public static movimento[][] getJogadas (){
		return _jogadas;
	}

	public static void mudarVez() {
		_vezBranco = !_vezBranco;
	}
	
	public static char getVez() {
		if(_vezBranco == true)
			return  'b';
		else
			return 'p';
	}
	
	public static Posicao getPosReiBranco() {
		return _posReiBranco;
	}
	
	public static Posicao getPosReiPreto() {
		return _posReiPreto;
	}
	
	public static boolean estaEmXeque() {
		return _xeque;
	}

	public static void move_peca(Posicao pos, Posicao dest, Casa[][] tab) {
		if(_xeque)
			_xeque=false;
		tab[dest.x][dest.y].peca = tab[pos.x][pos.y].peca;
		tab[pos.x][pos.y].peca = null;
		tab[dest.x][dest.y].peca.pos.x = dest.x;
		tab[dest.x][dest.y].peca.pos.y = dest.y;
		tab[dest.x][dest.y].peca.qtd_mov++;
		updateJogadas();
	}

	public static void updateJogadas () {	
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
							_posReiBranco.set_Pos(x, y);
						else
							_posReiPreto.set_Pos(x, y);
					}
				}
			}
		}

		_jogadas[_posReiBranco.x+8*_posReiBranco.y] = _casa[_posReiBranco.x][_posReiBranco.y].peca.mov_valido(_casa);
		_jogadas[_posReiPreto.x+8*_posReiPreto.y] = _casa[_posReiPreto.x][_posReiPreto.y].peca.mov_valido(_casa);
	}

	public static boolean xeque(Posicao ataque, Posicao rei) {
		boolean temSaida = false;
		int rx, ry;
		char timeRei = _casa[rei.x][rei.y].peca.time;
		_pXequeAtaque = ataque;
		
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
				
				for(int y=0;y<8;y++) {
					for(int x=0;x<8;x++) {
						for(int X=rei.x+rx, Y=rei.y+ry;Y!=ataque.y && X!=ataque.x;X+=rx, Y+=ry) {
							if(!_casa[x][y].vazia() && _casa[x][y].peca.time == timeRei && _jogadas[x+8*y][X+8*Y] == movimento.valido) {
								temSaida = true;
								_jogadas[x+8*y][X+8*Y] = movimento.tiraXeque;
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
		if(temSaida) {
			_xeque = true;
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
		updateJogadas();
	}


	public static void Salvar(BufferedWriter fileWriter) {
		PECA p;
		String aux2="",aux3="";

		try {
			fileWriter.write(String.valueOf(_vezBranco));
			fileWriter.newLine();
			fileWriter.write(String.valueOf(_xeque));
			
			if(_xeque)
				fileWriter.write(" "+_pXequeAtaque.x+" "+_pXequeAtaque.y);
			
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
