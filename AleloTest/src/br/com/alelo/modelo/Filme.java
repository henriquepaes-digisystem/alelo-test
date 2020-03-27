package br.com.alelo.modelo;

/**
 * Entidade Filme
 * @author henrique.paes@digisystem.com.br
 * @version 1.0.0
 * @since 27/03/2020
 */

public class Filme {
	private String nome;
	private int anoLancamento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getAnoLancamento() {
		return anoLancamento;
	}
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
	
	@Override
	public String toString() {
		return "Filme [Nome = " + nome + ", Ano de Lançamento = " + anoLancamento + "]";
	}
}