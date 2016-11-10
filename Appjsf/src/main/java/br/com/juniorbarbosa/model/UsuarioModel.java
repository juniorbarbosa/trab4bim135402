package br.com.juniorbarbosa.model;

import java.io.Serializable;

/**
 * Classe que vai ser usada pelo Managed Beans para receber os dados que são informados nas páginas
 * 
 * @author Joao Carlos Barbosa Junior - 9 de nov de 2016 - 14:23:04
 */
public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = -4283653903070617397L;

	private String codigo;
	private String usuario;
	private String senha;

	/**
	 * Obtém o código do usuário
	 * 
	 * @return um <code>String</code> especificando o código
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Da uma identificação ao cliente que vai ser persistido no BD
	 * 
	 * @param codigo o código/id do usuário
	 * 
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o usuário
	 * 
	 * @return um <code>String</code> especificando o usuário
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Da um login ao usuário que vai ser usado para acesso ao sistema
	 * 
	 * @param usuario é o login do usuário
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Obtém a senha do usuário
	 * 
	 * @return um <code>String</code> especificando o senha do usuário
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Da uma senha ao usuário que vai ser utilizada para acesso ao sistema
	 * 
	 * @param senha a senha de acesso ao sistema
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
