package br.com.alelo.repositorio;

import java.util.Scanner;

/**
 * Classe respons�vel por guardar fun��es de uso geral do sistema.
 * @author henrique.paes@digisystem.com.br
 * @version 1.0.0
 * @since 27/03/2020
 */

public class FuncoesGlobais {
	/**
	 * Rotina respons�vel por verificar as op��es informadas pelo usu�rio, validando
	 * com a op��o escolhida com os valores disponiveis no sistema.
	 * @param vlrTotalMenu Quantidade total do menu
	 * @return O valor da op��o escolhida.
	 */
	public String efetuaLeituraDeMenu(String vlrTotalMenu) {
		Scanner scr = new Scanner(System.in);
		String valorLido = "";
		
		do {
			System.out.print("Digite a op��o desejada: ");
			valorLido = scr.nextLine();
			
			if (valorLido.substring(0, valorLido.length()).matches("[0-9]*")) {
				if (!valorLido.substring(0, valorLido.length()).matches("[0-"+ vlrTotalMenu +"]*")) 
					System.out.println("Valor informado '"+ valorLido +"' n�o est� contido no menu !");	
				else
					break;
			} else
				System.out.println("Valor informado '"+ valorLido +"' n�o � v�lido !");			
			
			System.out.println("Por favor, informe valores contidos no menu ou pressione 0 para sair.\n");
			valorLido = "";
		} while (valorLido != "0");

		return valorLido;
	}
	
	/**
	 * Verifica apenas se o ano informado � ano bissexto.
	 * @param ano Ano informado
	 * @return <strong>Verdadeiro</strong> caso o ano seja bissexto, ou <strong>Falso</strong> caso n�o seja.
	 */
	public boolean verificaAnoBissexto(int ano) {
		if (ano % 400 != 0 && ano % 4 != 0 && ano % 100 != 0)
			return false;
		
		return true;	
	}
}