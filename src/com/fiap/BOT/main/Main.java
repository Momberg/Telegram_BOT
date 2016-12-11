package com.fiap.BOT.main;

import java.util.ArrayList;
import java.util.List;

import com.fiap.BOT.bean.Comandos;
import com.fiap.BOT.bean.ContaBancaria;
import com.fiap.BOT.bean.Dependentes;
import com.fiap.BOT.bean.TextosComandos;
import com.fiap.BOT.exception.ComandoInvalidoException;
import com.fiap.BOT.util.LeitorComandosUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {

	public static void main(String[] args) {
		String nome = "", sobrenome = "", cpf = "", rg = "", comando = "";
		List<ContaBancaria> listaC = new ArrayList<>();
		ArrayList<Dependentes> dependentes = new ArrayList<>();
		//Código necessário para o funcionamento do BOT.
		TelegramBot bot = TelegramBotAdapter.build("Código pessoal do professor");
		GetUpdatesResponse updatesResponse;
		SendResponse sendResponse;
		BaseResponse baseResponse;
		int m=0;
		ContaBancaria add_denp = null;
		ContaBancaria temp = null;
		Dependentes dependente= null;
		double[] lista;
		double soma;

		while (true){

			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));
			List<Update> updates = updatesResponse.updates();

			for (Update update : updates) {
				m = update.updateId()+1;

				System.out.println("Recebendo mensagem:"+ update.message().text());

				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
				comando = update.message().text();
				
				if(comando.toLowerCase().equals(Comandos.CRIAR_CONTA.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.CRIAR_CONTA_TXT.getTexto()));
				} else if(comando.length() > (Comandos.CRIAR_CONTA.getNome()).length() && comando.substring(0, 11).equals(Comandos.CRIAR_CONTA.getNome())){
					try {
						nome = LeitorComandosUtils.lerComandoCriarContaNome(comando);
						sobrenome = LeitorComandosUtils.lerComandoCriarContaSobrenome(comando);
						cpf = LeitorComandosUtils.lerComandoCriarContaCpf(comando);
						rg = LeitorComandosUtils.lerComandoCriarContaRg(comando);
						ContaBancaria cliente = new ContaBancaria(nome, sobrenome, cpf, rg, 10000);
						listaC.add(cliente);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), sobrenome + TextosComandos.CRIAR_CONTA_SUCESS.getTexto()));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if(comando.toLowerCase().equals(Comandos.VER_CONTA.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),TextosComandos.VER_CONTA_TXT.getTexto()));
				} else if(comando.length() > (Comandos.VER_CONTA.getNome()).length() && comando.substring(0, 9).equals(Comandos.VER_CONTA.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						System.out.println(temp);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Seu nome: " + temp.getNome()
																								+ "\nSeu sobrenome: " + temp.getSobrenome()
																								+ "\nSeu CPF: " + temp.getCpf()
																								+ "\nSeu RG: " + temp.getRg()));
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Seus dependentes na sua conta bancária: "));
						for(Dependentes d : temp.getDependetes()){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Nome: " + d.getNome()
							+ "\nSobrenome: " + d.getSobrenome()
							+ "\nCPF: " + d.getCpf()
							+ "\nRG: " + d.getRg()));
						}
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if (comando.toLowerCase().equals(Comandos.EXTRATO.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.EXTRATO_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.EXTRATO.getNome()).length() && comando.substring(0, 8).equals(Comandos.EXTRATO.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.EXTRATO_SUCESSO.getTexto() + temp.getSaldo()));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				
				else if (comando.toLowerCase().equals(Comandos.TARIFAS.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.TARIFAS_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.TARIFAS.getNome()).length() && comando.substring(0, 8).equals(Comandos.TARIFAS.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						lista = LeitorComandosUtils.getComandoServicos(temp);
						soma =  LeitorComandosUtils.getComandoSomaServicos(temp);
						if(lista != null){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.TARIFAS_SUCESSO_1.getTexto()));
							for(double d : lista){
								sendResponse = bot.execute(new SendMessage(update.message().chat().id(), String.valueOf(d)));
							}
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.TARIFAS_SUCESSO_2.getTexto() + String.valueOf(soma)));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				
				else if (comando.toLowerCase().equals(Comandos.SACAR.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.SACAR_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.SACAR.getNome()).length() && comando.substring(0, 6).equals(Comandos.SACAR.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPFSacar(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						LeitorComandosUtils.lerComandoSacar(temp, comando);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.SACAR_SUCESSO.getTexto() + temp.getSaldovisual()));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if (comando.toLowerCase().equals(Comandos.RETIRADAS.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.RETIRADAS_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.RETIRADAS.getNome()).length() && comando.substring(0, 10).equals(Comandos.RETIRADAS.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						lista = LeitorComandosUtils.getComandoRetiradas(temp);
						soma =  LeitorComandosUtils.getComandoSomaRetiradas(temp);
						if(lista != null){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.RETIRADAS_SUCESSO_1.getTexto()));
							for(double d : lista){
								sendResponse = bot.execute(new SendMessage(update.message().chat().id(), String.valueOf(d)));
							}
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.RETIRADAS_SUCESSO_2.getTexto() + String.valueOf(soma)));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if (comando.toLowerCase().equals(Comandos.DEPOSITAR.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),TextosComandos.DEPOSITAR_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.DEPOSITAR.getNome()).length() && comando.substring(0, 10).equals(Comandos.DEPOSITAR.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPFDepositar(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						LeitorComandosUtils.lerComandoDepositar(temp, comando);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPOSITAR_SUCESSO.getTexto() + temp.getSaldovisual()));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if (comando.toLowerCase().equals(Comandos.DEPOSITOS.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPOSITOS_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.DEPOSITOS.getNome()).length() && comando.substring(0, 10).equals(Comandos.DEPOSITOS.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						lista = LeitorComandosUtils.getComandoDepositos(temp);
						soma =  LeitorComandosUtils.getComandoSomaDepositos(temp);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPOSITOS_SUCESSO_1.getTexto()));
						for(double d : lista){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), String.valueOf(d)));
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPOSITOS_SUCESSO_2.getTexto() + String.valueOf(soma)));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if (comando.toLowerCase().equals(Comandos.EMPRESTIMO.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.EMPRESTIMO_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.EMPRESTIMO.getNome()).length() && comando.substring(0, 11).equals(Comandos.EMPRESTIMO.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPFEmprestimo(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						if(LeitorComandosUtils.lerComandoEmprestimo(temp, comando)){
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.EMPRESTIMO_SUCESSO.getTexto()));
						} else {
							sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.EMPRESTIMO_SEM_SUCESSO.getTexto()));
						}
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if (comando.toLowerCase().equals(Comandos.VER_EMPRESTIMO.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.EMPRESTIMOS_TEXTO.getTexto()));
				} else if (comando.length() > (Comandos.VER_EMPRESTIMO.getNome()).length() && comando.substring(0, 14).equals(Comandos.VER_EMPRESTIMO.getNome())){
					try {
						temp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						if(temp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Seu emprestimo tem o valor de: "
																								+ temp.getEmprestimo()
																								+ "\nDividido em "
																								+ temp.getMeses()
																								+ " meses"));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					} 
				} 
				
				else if (comando.toLowerCase().equals(Comandos.ADICIONAR_DEPENDENTE.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPENDENTE_TEXTO_1.getTexto()));
				} else if(comando.length() > (Comandos.ADICIONAR_DEPENDENTE.getNome()).length() && comando.substring(0, 20).equals(Comandos.ADICIONAR_DEPENDENTE.getNome()) && comando.contains(";")){
					try {
						dependente = LeitorComandosUtils.getDependentesCPF(comando);
						dependentes.add(dependente);
						if(add_denp == null || dependentes == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						add_denp.setDependetes(dependentes);
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), add_denp.getSobrenome() + TextosComandos.DEPENDENTE_SUCESSO_1.getTexto() + dependente.getNome() + TextosComandos.DEPENDENTE_SUCESSO_2.getTexto()));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPENDENTE_ERRO_2.getTexto()));
						e.printStackTrace();
					}
				} else if(comando.length() > (Comandos.ADICIONAR_DEPENDENTE.getNome()).length() && comando.substring(0, 20).equals(Comandos.ADICIONAR_DEPENDENTE.getNome()) && !comando.contains(";")){
					try {
						add_denp = LeitorComandosUtils.getClienteCPF(comando, listaC);
						if(add_denp == null){
							throw new ComandoInvalidoException(TextosComandos.ERRO_NULL.getTexto());
						}
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.DEPENDENTE_TEXTO_2.getTexto()));
					} catch (ComandoInvalidoException e) {
						sendResponse = bot.execute(new SendMessage(update.message().chat().id(), e.getMessage()));
						e.printStackTrace();
					}
				} 
				
				else if(comando.toLowerCase().equals(Comandos.HELP.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.HELP_TEXTO.getTexto()));
				} 
				
				else if(comando.toLowerCase().equals(Comandos.START.getNome())){
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.START_TEXTO.getTexto()));
				} 
				
				else {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(), TextosComandos.COMANDO_NA.getTexto()));
				}
			}

		}

	}

}
