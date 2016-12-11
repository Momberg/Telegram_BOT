package com.fiap.BOT.bean;

public enum Comandos {

	CRIAR_CONTA("/criarconta", 1),
	VER_CONTA("/verconta", 2),
	HELP("/help", 3),
	EXTRATO("/extrato", 4),
	TARIFAS("/tarifas", 5),
	SACAR("/sacar", 6),
	RETIRADAS("/retiradas", 7),
	DEPOSITOS("/depositos", 8),
	DEPOSITAR("/depositar", 9),
	EMPRESTIMO("/emprestimo", 10),
	VER_EMPRESTIMO("/veremprestimo", 11),
	START("/start", 12),
	ADICIONAR_DEPENDENTE("/adicionardependente", 13);

	String nome;
	int codigo;
	Comandos(String nome, int codigo){
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome(){
		return this.nome;
	}

	public int getCodigo(){
		return this.codigo;
	}
}
