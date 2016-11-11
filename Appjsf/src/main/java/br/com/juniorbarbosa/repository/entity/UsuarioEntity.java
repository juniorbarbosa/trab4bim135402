package br.com.juniorbarbosa.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe responsável por ser a entidade para persistir a tabela de usuários no BD
 * 
 * @author Joao Carlos Barbosa Junior - 9 de nov de 2016 - 14:15:25
 */
@Table(name = "tb_usuario")
@Entity
@NamedQuery(name = "UsuarioEntity.findUser", query = "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 590333728225128024L;

	@Id
	@GeneratedValue
	@Column(name = "id_usuario")
	private int codigo;

	@Column(name = "ds_login")
	private String usuario;

	@Column(name = "ds_senha")
	private String senha;

	/**
	 * Obtém o código do usuário
	 * 
	 * @return um <code>integer</code> especificando o código
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Da uma identificação ao cliente que vai ser persistido no BD
	 * 
	 * @param codigo o código/id do usuário
	 * 
	 */
	public void setCodigo(int codigo) {
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
