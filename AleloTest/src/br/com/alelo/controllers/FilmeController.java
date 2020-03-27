package br.com.alelo.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.alelo.interfaces.I_Crud;
import br.com.alelo.modelo.Filme;
import br.com.alelo.repositorio.FuncoesGlobais;

/**
 * Controller do Formulário de Cadastro de Filme.
 * @author henrique.paes
 * @version 1.0.0
 * @since 27/03/2020
 */

public class FilmeController implements I_Crud {
	private static String valorLido = "";
	public static ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
	
	public void MenuFilme() {
		FuncoesGlobais fg = new FuncoesGlobais();
		
		do {
			Scanner scr = new Scanner(System.in);
			valorLido = "";

			System.out.println("\n\n\n*** MENU FILME ***\n\n");
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
			String nomeFilme = "";
			String anoLancamento = "";
			
			System.out.println("\nPor favor, Digite os valores\n");
			
			System.out.print("Nome do Filme: ");
			try {
				nomeFilme = scr.nextLine();
			} catch (Exception e) {
				System.out.println("Ocorreu um erro na leitura do Nome. Por favor, tente novamente !");
				continue;
			}
			
			// Validando o nome inserido.
			if (nomeFilme.equals("")) {
				System.out.println("Por favor, preencha o campo Nome do Filme");
				continue;
			}			
						
			do {
				System.out.print("Ano de Lançamento: ");
				anoLancamento = scr.nextLine();
				
				if (anoLancamento.equals("")) {
					System.out.println("Por favor, preencha o campo Ano de Lançamento");
					continue;
				}				
				
				// Validando o Ano de Lançamento inserida.
				if (anoLancamento.substring(0, anoLancamento.length()).matches("[0-9]*")) {
					break;
				} else
					System.out.println("Valor informado '"+ anoLancamento +"' não é válido !");			
				
				System.out.println("Por favor, informe o Ano de Lançamento. Exemplo: 2020.\n");
				anoLancamento = "0";
			} while (anoLancamento != "0");
		
			// Cadastrando o registro informado.
			Filme filme = new Filme();
			filme.setNome(nomeFilme);
			filme.setAnoLancamento(Integer.parseInt(anoLancamento));		
			
			listaFilmes.add(filme);
			
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
			
			if (listaFilmes.isEmpty())
				System.out.println("Não há registros para pesquisar !");
			else {			
				System.out.print("Digite o valor para pesquisar: ");
				try {
					valor = scr.nextLine();
				} catch (Exception e) {
					System.out.println("Ocorreu um erro na leitura. Por favor, tente novamente !");
					continue;
				}
				
				// Validando o valor inserido.
				if (valor.equals("")) {
					System.out.println("Por favor, preencha algo para pesquisar !");
					continue;
				}
				
				// Pesquisando o valor informado.
				boolean registroEncontrado = false;
				ArrayList<Filme> filmeEncontrado = new ArrayList<Filme>();
				for (Filme filme : listaFilmes) {
					if (filme.getNome().contains(valor)) {
						registroEncontrado = true;
						filmeEncontrado.add(filme);
					}
					
					if (valor.substring(0, valor.length()).matches("[0-9]*")) {
						if (filme.getAnoLancamento() == Integer.parseInt(valor)) {
							registroEncontrado = true;
							filmeEncontrado.add(filme);							
						}
					}
				}
				
				if (!registroEncontrado)
					System.out.println("\nO Filme informado '"+ valor +"' não foi encontrado!\n");
				else {
					System.out.println("\n\n REGISTROS ENCONTRADOS \n");
					
					System.out.println("\n ID | NOME | ANO DE LANÇAMENTO");
					int indice = 0;
					for (Filme filme : filmeEncontrado) {
						System.out.println("\n"+ indice + " | "+ filme.getNome() +" | "+ filme.getAnoLancamento());
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
			
			if (listaFilmes.isEmpty())
				System.out.println("Não há registros para serem excluidos !");
			else {
				System.out.println("Lista de registros cadastrados\n");
				int maiorValor = 0;	
				
				for (Filme filme : listaFilmes) {
					if ((filme.getNome().length() + 10) > maiorValor)
						maiorValor = filme.getNome().length() + 10;
				}
				
				for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
					System.out.print("-");
	
				System.out.println("\n ID | NOME | ANO DE LANÇAMENTO");
				for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
					System.out.print("-");
				
				// Percorre a lista de filmes cadastrados.
				int indice = 0;
				for (Filme filme : listaFilmes) {
					System.out.println("\n"+ indice + " | "+ filme.getNome() +" | "+ filme.getAnoLancamento());
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
					listaFilmes.remove(Integer.parseInt(indiceExcluir));
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
		
		System.out.println("\n\n LISTA DE FILMES CADASTRADOS\n");	
		
		if (listaFilmes.isEmpty())
			System.out.println("Não há registros cadastrados !");
		else {		
			for (Filme filme : listaFilmes) {
				if ((filme.getNome().length() + 10) > maiorValor)
					maiorValor = filme.getNome().length() + 10;
			}
			
			for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
				System.out.print("-");
	
			System.out.println("\n ID | NOME | ANO DE LANÇAMENTO");
			for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
				System.out.print("-");
			
			// Percorre a lista de filmes cadastrados
			int indice = 0;
			for (Filme filme : listaFilmes) {
				System.out.println("\n"+ indice + " | "+ filme.getNome() +" | "+ filme.getAnoLancamento());
				indice++;
				
				for (int valorInicial = 0; valorInicial <= maiorValor; valorInicial++)
					System.out.print("-");
			}
		}

		System.out.println("\n\nPressione <ENTER> para sair");
		String valorRecebido = scr.nextLine();
	}	
}