package br.com.alelo.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Entidade Diretor
 * @author henrique.paes@digisystem.com.br
 * @version 1.0.0
 * @since 27/03/2020
 */

public class Diretor {
	private String nome;
	private Calendar dtNascimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Calendar getDtNascimento() {
		return dtNascimento;
	}
	public String getDtNascimentoFormatado() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dtNascimento.getTime());
	}	
	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Diretor [Nome = " + nome + ", Data de Nascimento = " + sdf.format(dtNascimento.getTime()) + "]";
	}
}