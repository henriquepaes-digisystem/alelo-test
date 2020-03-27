package br.com.alelo.repositorio;

import java.util.Scanner;

/**
 * Classe responsável por guardar funções de uso geral do sistema.
 * @author henrique.paes@digisystem.com.br
 * @version 1.0.0
 * @since 27/03/2020
 */

public class FuncoesGlobais {
	/**
	 * Rotina responsável por verificar as opções informadas pelo usuário, validando
	 * com a opção escolhida com os valores disponiveis no sistema.
	 * @param vlrTotalMenu Quantidade total do menu
	 * @return O valor da opção escolhida.
	 */
	public String efetuaLeituraDeMenu(String vlrTotalMenu) {
		Scanner scr = new Scanner(System.in);
		String valorLido = "";
		
		do {
			System.out.print("Digite a opção desejada: ");
			valorLido = scr.nextLine();
			
			if (valorLido.substring(0, valorLido.length()).matches("[0-9]*")) {
				if (!valorLido.substring(0, valorLido.length()).matches("[0-"+ vlrTotalMenu +"]*")) 
					System.out.println("Valor informado '"+ valorLido +"' não está contido no menu !");	
				else
					break;
			} else
				System.out.println("Valor informado '"+ valorLido +"' não é válido !");			
			
			System.out.println("Por favor, informe valores contidos no menu ou pressione 0 para sair.\n");
			valorLido = "";
		} while (valorLido != "0");

		return valorLido;
	}
	
	/**
	 * Verifica apenas se o ano informado é ano bissexto.
	 * @param ano Ano informado
	 * @return <strong>Verdadeiro</strong> caso o ano seja bissexto, ou <strong>Falso</strong> caso não seja.
	 */
	public boolean verificaAnoBissexto(int ano) {
		if (ano % 400 != 0 && ano % 4 != 0 && ano % 100 != 0)
			return false;
		
		return true;	
	}
}