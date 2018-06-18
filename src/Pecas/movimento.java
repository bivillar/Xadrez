package Pecas;

public enum movimento {
	invalido,
	valido,
	ataque,
	bloqueado,
	ataque_valido //usado pra apontar lugar onde o peao pode atacar mas nao pode andar, pro rei poder saber que ali ele n pode ir
}
