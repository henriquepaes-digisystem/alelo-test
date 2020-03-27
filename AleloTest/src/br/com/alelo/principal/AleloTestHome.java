package br.com.alelo.principal;

import java.util.ArrayList;

import br.com.alelo.controllers.DiretorController;
import br.com.alelo.controllers.FilmeController;
import br.com.alelo.modelo.Filme;
import br.com.alelo.repositorio.FuncoesGlobais;

/**
 * Aplicativo desenvolvido para atender a Avaliação de Teste da Alelo
 * <h2>Funcionalidade do programa</h2>
 * <p>Uma empresa de filmes, contratou um sistema da sua empresa - considerando que existe
 * a necessidade de cadastrar diretor (nome, nascimento) e filmes (nome, ano de lançamento)
 * construa um sistema utilizando arraylist para atender esta necessidade e no final, construa
 * um codigo de automação (utilizar selenium, JUnit e ChromeDriver) que busque no google o diretor
 * e o filme e retorne a quantidade aproximada de resultados para a busca.
 * </p>
 * @author henrique.paes@digisystem.com.br
 * @version 1.0.0
 * @since 27/03/2020
 */

public class AleloTestHome {
	
	private static String valorLido = "";
	
	public static ArrayList<Filme> listaFilmes = new ArrayList<Filme>();
	
	public static void main(String[] args) {	
		DiretorController dc = new DiretorController();
		FilmeController fc = new FilmeController();
		FuncoesGlobais fg = new FuncoesGlobais();
		
		do {
			valorLido = "";
			
			System.out.println("\n\n*** LOCADORA SYSTEM ***\n\n");
						
			System.out.println("Cadastrar Diretor ...................... 1");
			System.out.println("Cadastrar Filme ........................ 2");
			System.out.println("Sair do Sistema ........................ 0\n");
				
			valorLido = fg.efetuaLeituraDeMenu("2");
			
			switch (valorLido) {
				case "1":
					dc.MenuDiretor();
					break;
				case "2":
					fc.MenuFilme();
			}
		} while (!valorLido.equals("0"));
		
		System.out.println("\n\nObrigado por usar nosso Sistema. Tenha um Bom dia !!");
	}

}