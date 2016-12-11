package com.fiap.BOT.bean;

import java.util.ArrayList;

public class ContaBancaria {

	protected String nome, sobrenome, cpf, rg;
	protected double valor, saldo, emprestimo;
	protected int meses;
	protected ArrayList<String> lancamento = new ArrayList<>();
	protected ArrayList<String> retirada = new ArrayList<>();
	protected ArrayList<String> servico = new ArrayList<>();
	protected ArrayList<Dependentes> dependetes = new ArrayList<>();

	public ContaBancaria(String nome, String sobrenome, String cpf, String rg, double saldo){
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.rg = rg;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public double getSaldo() {
		saldo -= 1;
		servico.add("-1");
		return saldo;
	}

	public double getSaldovisual() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void depositar(double valor){
		saldo += valor;
		lancamento.add(String.valueOf(valor));
	}

	public void sacar(double valor){
		saldo -= (valor + 2.5);
		retirada.add(String.valueOf(-valor));
		servico.add("-2.5");
	}

	public boolean emprestimo(double valor, int meses){
		if(valor <= (40*saldo) && meses <= 36){
			emprestimo = valor;
			this.meses = meses;
			saldo += emprestimo - (15 + (valor*0.05)/meses);
			servico.add(String.valueOf(-(15 + (valor*0.05)/meses)));
			return true;
		} else {
			return false;
		}
	}

	public double getEmprestimo(){
		return emprestimo;
	}

	public ArrayList<String> getServicos(){
		return servico;
	}

	public ArrayList<String> getRetiradas(){
		return retirada;
	}

	public ArrayList<String> getDepositos(){
		return lancamento;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}

	public ArrayList<Dependentes> getDependetes() {
		return dependetes;
	}

	public void setDependetes(ArrayList<Dependentes> dependetes) {
		this.dependetes = dependetes;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", \n sobrenome=" + sobrenome + ", \n cpf=" + cpf + ", \n rg=" + rg + "]";
	}

}
