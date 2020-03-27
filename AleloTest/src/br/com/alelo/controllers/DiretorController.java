package br.com.alelo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.alelo.interfaces.I_Crud;
import br.com.alelo.modelo.Diretor;
import br.com.alelo.repositorio.FuncoesGlobais;

/**
 * Controller do Formulário de Cadastro de Diretor.
 * @author henrique.paes
 * @version 1.0.0
 * @since 27/03/2020
 */

public class DiretorController implements I_Crud {
	private static String valorLido = "";
	private static FuncoesGlobais fg = new FuncoesGlobais();
	public static ArrayList<Diretor> listaDiretores = new ArrayList<Diretor>();
	
	public void MenuDiretor() {
		FuncoesGlobais fg = new FuncoesGlobais();
		
		do {
			Scanner scr = new Scanner(System.in);
			valorLido = "";

			System.out.println("\n\n\n*** MENU DIRETOR ***\n\n");
			System.out.println("Por favor, digite a opção desejada no menu abaixo:\n\n");
			
			System.out.println("Cadastrar ........................ 1");
			System.out.println("Pesquisar ........................ 2");
			System.out.println("Excluir   ........................ 3");
			System.out.println("Listar    ........................ 4");
			System.out.println("Retornar ao Menu Principal ....... 0\n");
										
			valorLido = fg.efetuaLeituraDeMenu("4");
			
			switch (valorLido) {
				case "1":
					Cadastro();
					break;
				case "2":
					Pesquisar();
					break;
				case "3":
					Excluir();
					break;
				case "4":
					Listar();
					break;
			}
		} while (!valorLido.equals("0"));		
	}

	@Override
	public void Cadastro() {
		String desejaCadastrar = "S";
		
		do {
			Scanner scr = new Scanner(System.in);
			String nome = "";
			String dtNascimento = "";
			
			System.out.println("\nPor favor, Digite os valores\n");
			
			System.out.print("Nome do Diretor(a): ");
			try {
				nome = scr.nextLine();
			} catch (Exception e) {
				System.out.println("Ocorreu um erro na leitura do Nome. Por favor, tente novamente !");
				continue;
			}
			
			// Validando o nome inserido.
			if (nome.equals("")) {
				System.out.println("Por favor, preencha o campo Nome do Diretor(a)");
				continue;
			}
			
			System.out.print("Data de Nascimento: ");
			dtNascimento = scr.nextLine();
			
			if (dtNascimento.equals("")) {
				System.out.println("Por favor, preencha o campo Data de Nascimento");
				continue;
			}			
			
			// Validando a data de nascimento inserida.
			Calendar dtNascimentoConvertida = Calendar.getInstance();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dtNascimentoConvertida.setTime(sdf.parse(dtNascimento));
				
				if (dtNascimentoConvertida.get(Calendar.MONTH) == 2)
					if (fg.verificaAnoBissexto(dtNascimentoConvertida.get(Calendar.YEAR)) == false) {
						System.out.println("Data informada não existe. Por favor, tente Novamente");
						continue;					
					}
			} catch (ParseException e) {
				System.out.println("Valor informado da data de nascimento inválida.Por favor, tente Novamente");
				continue;
			} catch (Exception e) {
				System.out.println("Ocorreu um erro na leitura da Data de Nascimento. Por favor, tente novamente !");
				continue;				
			}
		
			// Cadastrando o registro informado.
			Diretor diretor = new Diretor();
			diretor.setNome(nome);
			diretor.setDtNascimento(dtNascimentoConvertida);			
			
			listaDiretores.add(diretor);
			
			do {
				System.out.print("Deseja cadastrar mais? S='Sim | N='Nao': ");
				desejaCadastrar = scr.next().toUpperCase();
				
				// Validando o valor informado.
				if (!desejaCadastrar.equals("S") && !desejaCadastrar.equals("N"))
					System.out.println("Valor Informado inválido");			
			} while(!desejaCadastrar.equals("S") && !desejaCadastrar.equals("N"));
		} while (desejaCadastrar.equals("S"));		
	}

	@Override
	public void Pesquisar() {
		String desejaCadastrar = "S";
		
		do {
			Scanner scr = new Scanner(System.in);
			String valor = "";
			
			System.out.println("\nPESQUISAR REGISTRO\n");
			
			if (listaDiretores.isEmpty())
				System.out.println("Não há registros para pesquisar !");
			else {			
				System.out.print("Digite o valor para pesquisar: ");
				try {
					valor = scr.nextLine();
				} catch (Exception e) {
					System.out.println("Ocorreu um erro na leitura. Por favor, tente novamente !");
					continue;
				}
				
				// Validando o nome inserido.
				if (valor.equals("")) {
					System.out.println("Por favor, preencha algo para pesquisar !");
					continue;
				}
				
				// Pesquisando o nome informado.
				boolean registroEncontrado = false;
				ArrayList<Diretor> diretorEncontrado = new ArrayList<Diretor>();
				for (Diretor diretor : listaDiretores) {
					if (diretor.getNome().contains(valor) || diretor.getDtNascimentoFormatado().contains(valor)) {
						registroEncontrado = true;
						diretorEncontrado.add(diretor);
					}
						
				}
				
				if (!registroEncontrado)
					System.out.println("\nO Diretor(a) informado '"+ valor +"' não foi encontrado!\n");
				else {
					System.out.println("\n\n REGISTROS ENCONTRADOS \n");
					
					System.out.println("\n ID | NOME | DATA DE NASCIMENTO");
					int indice = 0;
					for (Diretor diretor : diretorEncontrado) {
						System.out.println("\n"+ indice + " | "+ diretor.getNome() +" | "+ diretor.getDtNascimentoFormatado());
						indice++;					
					}		
				}			
			}
							
			do {
				System.out.print("\nDeseja consultar mais? S='Sim | N='Nao': ");
				desejaCadastrar = scr.next().toUpperCase();
				
				// Validando o valor informado.
				if (!desejaCadastrar.equals("S") && !desejaCadastrar.equals("N"))
					System.out.println("Valor Informado inválido");			
			} while(!desejaCadastrar.equals("S") && !desejaCadastrar.equals("N"));
		} while (desejaCadastrar.equals("S"));
		
	}

	@Override
	public void Excluir() {
		String desejaCadastrar = "S";
		
		do {
			Scanner scr = new Scanner(System.in);			
			
			System.out.println("\nEXCLUIR REGISTRO\n");
			
			if (listaDiretores.isEmpty())
				System.out.println("Não há registros para serem excluidos !");
			else {
				System.out.println("Lista de registros cadastrados\n");
				int maiorValor = 0;	
				
				for (Diretor diretor : listaDiretores) {
					if ((diretor.getNome().length() + 10) > maiorValor)
						maiorValor = diretor.getNome().length() + 10;
				}
				
				for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
					System.out.print("-");
	
				System.out.println("\n ID | NOME | DATA DE NASCIMENTO");
				for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
					System.out.print("-");
				
				// Percorre a lista de diretores cadastrados.
				int indice = 0;
				for (Diretor diretor : listaDiretores) {
					System.out.println("\n"+ indice + " | "+ diretor.getNome() +" | "+ diretor.getDtNascimentoFormatado());
					indice++;
					
					for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
						System.out.print("-");
				}
				
				// Solicita o ID a ser excluido.
				String indiceExcluir = "";
				do {
					System.out.print("\n\nDigite o ID para excluir: ");
					indiceExcluir = scr.nextLine();
					
					if (valorLido.substring(0, valorLido.length()).matches("[0-9]*")) {
						break;
					} else
						System.out.println("Valor informado '"+ indiceExcluir +"' não é válido !");			
					
					System.out.println("Por favor, informe apenas os indices(ID) contidos na tabela.\n");
					valorLido = "";
				} while (valorLido != "0");			
				
				// Remove o Registro da Lista.
				try {
					listaDiretores.remove(Integer.parseInt(indiceExcluir));
					System.out.println("Registro excluido com sucesso !");
				} catch (Exception e) {
					System.out.println("\nDesculpe não foi possível excluir o registro. Por favor, Tente novamente mais tarde !");
				}
			}
			
			do {
				System.out.print("\nDeseja excluir mais? S='Sim | N='Nao': ");
				desejaCadastrar = scr.next().toUpperCase();
				
				// Validando o valor informado.
				if (!desejaCadastrar.equals("S") && !desejaCadastrar.equals("N"))
					System.out.println("Valor Informado inválido");		
				
			} while(!desejaCadastrar.equals("S") && !desejaCadastrar.equals("N"));
		} while (desejaCadastrar.equals("S"));	
	}
	
	@Override
	public void Listar() {
		Scanner scr = new Scanner(System.in);
		int maiorValor = 0;
		
		System.out.println("\n\n LISTA DE DIRETORES CADASTRADOS\n");	
		
		if (listaDiretores.isEmpty())
			System.out.println("Não há registros cadastrados !");
		else {		
			for (Diretor diretor : listaDiretores) {
				if ((diretor.getNome().length() + 10) > maiorValor)
					maiorValor = diretor.getNome().length() + 10;
			}
			
			for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
				System.out.print("-");
	
			System.out.println("\n ID | NOME | DATA DE NASCIMENTO");
			for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
				System.out.print("-");
			
			// Percorre a lista de diretores cadastrados
			int indice = 0;
			for (Diretor diretor : listaDiretores) {
				System.out.println("\n"+ indice + " | "+ diretor.getNome() +" | "+ diretor.getDtNascimentoFormatado());
				indice++;
				
				for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
					System.out.print("-");
			}
		}

		System.out.println("\n\nPressione <ENTER> para sair");
		String valorRecebido = scr.nextLine();
	}	
}