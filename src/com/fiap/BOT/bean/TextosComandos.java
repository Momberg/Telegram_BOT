package com.fiap.BOT.bean;

public enum TextosComandos {
	CRIAR_CONTA_TXT("Você escolheu a opção para criação de conta ! \n"
			+ "Para criação da conta digitar o seguinte comando: \n"
			+ "/criarconta Nome;Sobrenome;CPF;RG", 1),
	CRIAR_CONTA_ERRO("Comando inválido, \n"
			+"favor digitar o comando conforme solicitado ! \n"
			+ "/criarconta Nome;Sobrenome;CPF;RG", 2),
	CRIAR_CONTA_SUCESS(" seu registro foi criado com sucesso", 3),
	VER_CONTA_TXT("Você escolheu a opção para visualização de conta ! \n"
			+ "Para visualização da sua conta favor escreva o seguinte comando: \n"
			+ "/verconta CPF", 4),
	VER_CONTA_ERRO_COMANDO("Comando inválido, \n"
			+"favor digitar o comando conforme solicitado ! \n"
			+ "/verconta CPF", 5),
	HELP_TEXTO("Lista de comandos: \n"
			+"/verconta \n"
			+"/help \n"
			+"/extrato \n"
			+"/tarifas \n"
			+"/sacar \n"
			+"/retiradas \n"
			+"/depositos \n"
			+"/depositar \n"
			+"/criarconta \n"
			+"/emprestimo \n"
			+"/veremprestimo \n"
			+"/adicionardependente \n"
			+"/start", 6),
	START_TEXTO("Bem vindo ! \n"
			+ "Lista de comandos: \n"
			+"/verconta \n"
			+"/help \n"
			+"/extrato \n"
			+"/tarifas \n"
			+"/sacar \n"
			+"/retiradas \n"
			+"/depositos \n"
			+"/depositar \n"
			+"/criarconta \n"
			+"/emprestimo \n"
			+"/veremprestimo \n"
			+"/adicionardependente \n"
			+"/start", 7),
	EXTRATO_TEXTO("Você escolheu a opção para visualização de extrato bancário ! \n"
			+ "Para visualização do seu extrato favor escreva o seguinte comando: \n"
			+ "/extrato CPF", 8),
	EXTRATO_SUCESSO("Seu saldo é de: ", 9),
	EXTRATO_ERRO_COMANDO("Comando inválido, \n"
			+"favor digitar o comando conforme solicitado ! \n"
			+ "/extrato CPF", 10),
	ERRO_NULL("Esse registro não existe !", 11),
	TARIFAS_TEXTO("Você escolheu a opção para visualização de tarifas da sua conta ! \n"
			+ "Para visualização do seu extrato favor escreva o seguinte comando: \n"
			+ "/tarifas CPF", 12),
	TARIFAS_SUCESSO_1("Valores dos serviços: ", 13),
	TARIFAS_SUCESSO_2("Valor total dos serviços: ", 14),
	SACAR_TEXTO("Você escolheu a opção para sacar dinheiro da sua conta ! \n"
			+ "Para sacar favor escreva o seguinte comando: \n"
			+ "/sacar CPF;valor", 15),
	SACAR_SUCESSO("Seu saldo é de: ", 16),
	RETIRADAS_TEXTO("Você escolheu a opção para visualizar os saques realizados na sua conta ! \n"
			+ "Para visualizar os saques favor escreva o seguinte comando: \n"
			+ "/retiradas CPF", 17),
	RETIRADAS_SUCESSO_1("Valores das retiradas: ", 18),
	RETIRADAS_SUCESSO_2("Valor total das retiradas: ", 19),
	DEPOSITAR_TEXTO("Você escolheu a opção para realizar um deposito na sua conta ! \n"
			+ "Para depositar favor escreva o seguinte comando: \n"
			+ "/depositar CPF;valor", 20),
	DEPOSITAR_SUCESSO("Seu saldo é de: ", 21),
	DEPOSITOS_TEXTO("Você escolheu a opção para visualizar os depositos realizados na sua conta ! \n"
			+ "Para visualizar os depositos favor escreva o seguinte comando: \n"
			+ "/depositos CPF", 22),
	DEPOSITOS_SUCESSO_1("Valores das depositos: ", 23),
	DEPOSITOS_SUCESSO_2("Valor total das depositos: ", 24),
	EMPRESTIMOS_TEXTO("Você escolheu a opção para visualizar um emprestimo na sua conta ! \n"
			+ "Para visualizar o emprestimo favor escreva o seguinte comando: \n"
			+ "/veremprestimo CPF", 25),
	EMPRESTIMO_SUCESSO("Seu emprestimo foi aprovado !", 26),
	EMPRESTIMO_SEM_SUCESSO("Seu emprestimo não foi aprovado !", 27),
	DEPENDENTE_TEXTO_1("Você escolheu a opção para adicionar um dependente na sua conta ! \n"
			+ "Para adicionar o dependente favor escreva o seguinte comando: \n"
			+ "/adicionardependente Seu CPF", 28),
	DEPENDENTE_TEXTO_2("Para adicionar o dependente escreva novamente o comando mas com os parametros a seguir: \n"
			+ "/adicionardependente Nome do dependente;Sobrenome do dependente;CPF do dependente;RG do dependente", 29),
	DEPENDENTE_ERRO_1("Comando inválido, \n"
			+"favor digitar o comando conforme solicitado ! \n"
			+ "/adicionardependente Seu CPF", 30),
	DEPENDENTE_ERRO_2("Comando inválido, \n"
			+"favor digitar o comando conforme solicitado ! \n"
			+ "/adicionardependente Nome do dependente;Sobrenome do dependente;CPF do dependente;RG do dependente", 31),
	COMANDO_NA("Não entendi......", 32),
	SACAR_ERRO("Comando inválido ! \n"
			+ "Para sacar favor escreva o seguinte comando: \n"
			+ "/sacar CPF;valor", 33),
	DEPOSITAR_ERRO("Comando inválido ! \n"
			+ "Para depositar favor escreva o seguinte comando: \n"
			+ "/depositar CPF;valor", 34),
	TARIFAS_ERRO("Comando inválido ! \n"
			+ "Para visualização do seu extrato favor escreva o seguinte comando: \n"
			+ "/tarifas CPF", 35),
	RETIRADAS_ERRO("Comando inválido ! \n"
			+ "Para visualizar os saques favor escreva o seguinte comando: \n"
			+ "/retiradas CPF", 36),
	DEPOSITOS_ERRO("Comando inválido ! \n"
			+ "Para visualizar os depositos favor escreva o seguinte comando: \n"
			+ "/depositos CPF", 37),
	DEPENDENTE_SUCESSO_1(" seu dependente ", 38),
	DEPENDENTE_SUCESSO_2(" foi adicionado com sucesso !", 39),
	EMPRESTIMOS_ERRO("Comando inválido ! \n"
			+ "Para visualizar o emprestimo favor escreva o seguinte comando: \n"
			+ "/veremprestimo CPF", 40),
	EMPRESTIMO_TEXTO("Você escolheu a opção para realizar um emprestimo na sua conta ! \n"
			+ "Para fazer o emprestimo favor escreva o seguinte comando: \n"
			+ "/emprestimo CPF;valor do emprestimo;meses de parcelamento", 41),
	EMPRESTIMO_ERRO("Comando inválido ! \n"
			+ "Para fazer o emprestimo favor escreva o seguinte comando: \n"
			+ "/emprestimo CPF;valor do emprestimo;meses de parcelamento", 42),
	EMPRESTIMO_ERRO_VALOR("Favor digitar apenas números nos campos de 'valor' e 'meses' do comando: \n"
			+ "/emprestimo CPF;valor do emprestimo;meses de parcelamento", 43),
	SACAR_ERRO_VALOR("Favor digitar apenas números no campo de 'valor' do comando: \n"
			+ "/sacar CPF;valor", 44),
	DEPOSITAR_ERRO_VALOR("Favor digitar apenas números no campo de 'valor' do comando: \n"
			+ "/depositar CPF;valor", 45);
	
	
	String texto;
	int codigo;
	TextosComandos(String texto, int codigo){
		this.texto = texto;
		this.codigo = codigo;
	}

	public String getTexto(){
		return this.texto;
	}

	public int getCodigo(){
		return this.codigo;
	}

}
