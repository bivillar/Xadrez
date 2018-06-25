package Principal;

import java.io.BufferedReader;
import java.io.IOException;

import Tabuleiro.*;
import Pecas.tPecas;
import Pecas.Posicao;

public class Facade{
	private static Tabuleiro _tab;
	private static Casa[][] _casa;
	
	public void criaTabuleiro() {
		_tab = Tabuleiro.getTabuleiro();
		_tab.addObserver(Principal.Controlador.obsTab);
	}
	public void criaTabuleiro(BufferedReader arqLeitura) throws IOException {
		_tab = Tabuleiro.getTabuleiro(arqLeitura);
		_tab.addObserver(Principal.Controlador.obsTab);
	}
	public Casa[][] tabuleiro(){
		_tab = Tabuleiro.getTabuleiro();
		_tab.addObserver(Principal.Controlador.obsTab);
		return Tabuleiro.getCasas();
	}
	
	public Casa[][] tabuleiro(BufferedReader arqLeitura) throws IOException{
		_tab = Tabuleiro.getTabuleiro(arqLeitura);
		_tab.addObserver(Principal.Controlador.obsTab);
		return Tabuleiro.getCasas();
	}
	
	public Tabuleiro getTabuleiro() {
		return _tab;
	}
	
	public char CasaGetMovT(Posicao pos) {
		_casa = Tabuleiro.getCasas();
		return _casa[pos.x][pos.y].movT;
	}
	
	public char CasaGetCor(Posicao pos) {
		_casa = Tabuleiro.getCasas();
		return _casa[pos.x][pos.y].cor;
	}
	
	public boolean CasaNaoVazia(Posicao pos) {
		return !_casa[pos.x][pos.y].vazia();
	}
	
	public char PecaGetTime(Posicao pos) {
		return _casa[pos.x][pos.y].peca.time;
	}
	public tPecas PecaGetTipo(Posicao pos) {
		return _casa[pos.x][pos.y].peca.tipo;
	}
}

























