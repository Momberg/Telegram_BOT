package com.fiap.BOT.util;

import java.util.ArrayList;
import java.util.List;

import com.fiap.BOT.bean.Comandos;
import com.fiap.BOT.bean.ContaBancaria;
import com.fiap.BOT.bean.Dependentes;
import com.fiap.BOT.bean.TextosComandos;
import com.fiap.BOT.exception.ComandoInvalidoException;
import com.pengrad.telegrambot.request.SendMessage;

public final class LeitorComandosUtils {

	private LeitorComandosUtils() {

	}

	public static String lerComandoCriarContaNome(String comando) throws ComandoInvalidoException{
		String nome;
		String reg[] = comando.split(";");
		if(reg.length < 4){
			throw new ComandoInvalidoException(TextosComandos.CRIAR_CONTA_ERRO.getTexto());
		}
		nome = reg[0].substring(12, reg[0].length());
		return nome;
	}

	public static String lerComandoCriarContaSobrenome(String comando) throws ComandoInvalidoException{
		String sobrenome;
		String reg[] = comando.split(";");
		if(reg.length < 4){
			throw new ComandoInvalidoException(TextosComandos.CRIAR_CONTA_ERRO.getTexto());
		}
		sobrenome = reg[1];
		return sobrenome;
	}

	public static String lerComandoCriarContaCpf(String comando) throws ComandoInvalidoException{
		String cpf;
		String reg[] = comando.split(";");
		if(reg.length < 4){
			throw new ComandoInvalidoException(TextosComandos.CRIAR_CONTA_ERRO.getTexto());
		}
		cpf = reg[2];
		return cpf;
	}

	public static String lerComandoCriarContaRg(String comando) throws ComandoInvalidoException{
		String rg;
		String reg[] = comando.split(";");
		if(reg.length < 4){
			throw new ComandoInvalidoException(TextosComandos.CRIAR_CONTA_ERRO.getTexto());
		}
		rg = reg[3];
		return rg;
	}

	public static ContaBancaria getClienteCPF(String comando, List<ContaBancaria> clientes) throws ComandoInvalidoException{
		ContaBancaria cliente = null;
		String cpf;
		String reg[] = comando.split(" ");
		if(reg.length < 2){
			if(reg[0].contains(Comandos.VER_CONTA.getNome())){
				throw new ComandoInvalidoException(TextosComandos.VER_CONTA_ERRO_COMANDO.getTexto());
			}
			if(reg[0].contains(Comandos.EXTRATO.getNome())){
				throw new ComandoInvalidoException(TextosComandos.EXTRATO_ERRO_COMANDO.getTexto());
			}
			if(reg[0].contains(Comandos.ADICIONAR_DEPENDENTE.getNome())){
				throw new ComandoInvalidoException(TextosComandos.DEPENDENTE_ERRO_1.getTexto());
			}
			if(reg[0].contains(Comandos.TARIFAS.getNome())){
				throw new ComandoInvalidoException(TextosComandos.TARIFAS_ERRO.getTexto());
			}
			if(reg[0].contains(Comandos.RETIRADAS.getNome())){
				throw new ComandoInvalidoException(TextosComandos.RETIRADAS_ERRO.getTexto());
			}
			if(reg[0].contains(Comandos.DEPOSITOS.getNome())){
				throw new ComandoInvalidoException(TextosComandos.DEPOSITOS_ERRO.getTexto());
			}
			if(reg[0].contains(Comandos.VER_EMPRESTIMO.getNome())){
				throw new ComandoInvalidoException(TextosComandos.EMPRESTIMOS_ERRO.getTexto());
			}
		}
		cpf = reg[1];
		System.out.println(cpf);
		for(ContaBancaria c : clientes){
			if(c.getCpf().equals(cpf)){
				cliente = c;
			}
		}
		return cliente;
	}

	public static Dependentes getDependentesCPF(String comando) throws ComandoInvalidoException{
		String cpf, nome, sobrenome, rg;
		String reg[] = comando.split(";");
		if(reg.length < 4){
			if(reg[0].contains(Comandos.ADICIONAR_DEPENDENTE.getNome())){
				throw new ComandoInvalidoException(TextosComandos.DEPENDENTE_ERRO_2.getTexto());
			}
		}
		nome = reg[0].substring(21, reg[0].length());
		sobrenome = reg[1];
		cpf = reg[2];
		rg = reg[3];
		Dependentes dependente = new Dependentes(nome, sobrenome, cpf, rg, 5000);
		return dependente;
	}

	public static double[] getComandoServicos(ContaBancaria conta) throws ComandoInvalidoException{
		int i = 0;
		ArrayList<String> temp = conta.getServicos();
		double[] lista = new double[temp.size()];
		for(String s : temp){
			lista[i] = Double.parseDouble(s);
			System.out.println(s);
			i++;
		}
		return lista;
	}

	public static double getComandoSomaServicos(ContaBancaria conta) throws ComandoInvalidoException{
		int i = 0;
		double soma = 0;
		ArrayList<String> temp = conta.getServicos();
		double[] lista = new double[temp.size()];
		for(String s : temp){
			lista[i] = Double.parseDouble(s);
			i++;
		}
		for(double d : lista){
			soma += d;
		}
		return soma;
	}

	public static ContaBancaria lerComandoSacar(ContaBancaria conta, String comando) throws ComandoInvalidoException{
		String valor;
		String reg[] = comando.split(";");
		if(reg.length < 2){
			throw new ComandoInvalidoException(TextosComandos.SACAR_ERRO.getTexto());
		}
		valor = reg[1];
		if(valor.matches(".*[a-zA-Z]+.*")){
			throw new ComandoInvalidoException(TextosComandos.SACAR_ERRO_VALOR.getTexto());
		}
		conta.sacar(Double.parseDouble(valor));
		return conta;
	}

	public static ContaBancaria getClienteCPFSacar(String comando, List<ContaBancaria> clientes) throws ComandoInvalidoException{
		ContaBancaria cliente = null;
		String cpf;
		String reg[] = comando.split(";");
		if(reg.length < 2){
			throw new ComandoInvalidoException(TextosComandos.SACAR_ERRO.getTexto());
		}
		cpf = reg[0].substring(7, reg[0].length());
		for(ContaBancaria c : clientes){
			if(c.getCpf().equals(cpf)){
				cliente = c;
			}
		}
		return cliente;
	}

	public static double[] getComandoRetiradas(ContaBancaria conta) throws ComandoInvalidoException{
		int i = 0;
		ArrayList<String> temp = conta.getRetiradas();
		double[] lista = new double[temp.size()];
		for(String s : temp){
			lista[i] = Double.parseDouble(s);
			System.out.println(s);
			i++;
		}
		return lista;
	}

	public static double getComandoSomaRetiradas(ContaBancaria conta) throws ComandoInvalidoException{
		int i = 0;
		double soma = 0;
		ArrayList<String> temp = conta.getRetiradas();
		double[] lista = new double[temp.size()];
		for(String s : temp){
			lista[i] = Double.parseDouble(s);
			i++;
		}
		for(double d : lista){
			soma += d;
		}
		return soma;
	}

	public static double[] getComandoDepositos(ContaBancaria conta) throws ComandoInvalidoException{
		int i = 0;
		ArrayList<String> temp = conta.getDepositos();
		double[] lista = new double[temp.size()];
		for(String s : temp){
			lista[i] = Double.parseDouble(s);
			System.out.println(s);
			i++;
		}
		return lista;
	}

	public static double getComandoSomaDepositos(ContaBancaria conta) throws ComandoInvalidoException{
		int i = 0;
		double soma = 0;
		ArrayList<String> temp = conta.getDepositos();
		double[] lista = new double[temp.size()];
		for(String s : temp){
			lista[i] = Double.parseDouble(s);
			i++;
		}
		for(double d : lista){
			soma += d;
		}
		return soma;
	}

	public static ContaBancaria lerComandoDepositar(ContaBancaria conta, String comando) throws ComandoInvalidoException{
		String valor;
		String reg[] = comando.split(";");
		if(reg.length < 2){
			throw new ComandoInvalidoException(TextosComandos.DEPOSITAR_ERRO.getTexto());
		}
		valor = reg[1];
		if(valor.matches(".*[a-zA-Z]+.*")){
			throw new ComandoInvalidoException(TextosComandos.DEPOSITAR_ERRO_VALOR.getTexto());
		}
		conta.depositar(Double.parseDouble(valor));
		return conta;
	}

	public static ContaBancaria getClienteCPFDepositar(String comando, List<ContaBancaria> clientes) throws ComandoInvalidoException{
		ContaBancaria cliente = null;
		String cpf;
		String reg[] = comando.split(";");
		if(reg.length < 2){
			throw new ComandoInvalidoException(TextosComandos.DEPOSITAR_ERRO.getTexto());
		}
		cpf = reg[0].substring(11, reg[0].length());
		for(ContaBancaria c : clientes){
			if(c.getCpf().equals(cpf)){
				cliente = c;
			}
		}
		return cliente;
	}

	public static boolean lerComandoEmprestimo(ContaBancaria conta, String comando) throws ComandoInvalidoException{
		String valor, meses;
		String reg[] = comando.split(";");
		valor = reg[1];
		meses = reg[2];
		if(valor.matches(".*[a-zA-Z]+.*") || meses.matches(".*[a-zA-Z]+.*")){
			throw new ComandoInvalidoException(TextosComandos.EMPRESTIMO_ERRO_VALOR.getTexto());
		}
		if(conta.emprestimo(Double.parseDouble(valor), Integer.parseInt(meses))){
			return true;
		} else {
			return false;
		}
	}

	public static ContaBancaria getClienteCPFEmprestimo(String comando, List<ContaBancaria> clientes) throws ComandoInvalidoException{
		ContaBancaria cliente = null;
		String cpf;
		String reg[] = comando.split(";");
		if(reg.length < 2){
			throw new ComandoInvalidoException(TextosComandos.EMPRESTIMO_ERRO.getTexto());
		}
		cpf = reg[0].substring(12, reg[0].length());
		for(ContaBancaria c : clientes){
			if(c.getCpf().equals(cpf)){
				cliente = c;
			}
		}
		return cliente;
	}
}
