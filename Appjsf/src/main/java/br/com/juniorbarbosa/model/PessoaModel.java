package br.com.juniorbarbosa.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe que vai receber os valores do campo da página de cadastro
 * 
 * @author Joao Carlos Barbosa Junior - 10 de nov de 2016 - 14:48:43
 */
public class PessoaModel implements Serializable {

	private static final long serialVersionUID = -7327523815264705819L;

	private int codigo;
	private String nome;
	private String sexo;
	private LocalDateTime dataCadastro;
	private String email;
	private String endereco;
	private String origemCadastro;
	private UsuarioModel usuarioModel;

	/**
	 * Obtém o código da pessoa
	 * 
	 * @return um <code>Integer</code> especificando o código
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Da uma identificação a pessoa
	 * 
	 * @param codigo o código/id da pessoa
	 * 
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtém o nome da pessoa
	 * 
	 * @return um <code>String</code> especificando o nome da pessoa
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Da um nome a pessoa
	 * 
	 * @param nome o nome da pessoa
	 * 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o sexo da pessoa
	 * 
	 * @return um <code>String</code> especificando o sexo da pessoa
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * Da um sexo/genero a pessoa
	 * 
	 * @param sexo o sexo/genero da pessoa
	 * 
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * Obtém a data de cadastro da pessoa
	 * 
	 * @return um <code>LocalDateTime</code> especificando a data de cadastro da pessoa
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * Da a data de cadastro da pessoa
	 * 
	 * @param dataCadastro a data de cadastro da pessoa
	 * 
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Obtém o email da pessoa
	 * 
	 * @return um <code>String</code> especificando o email da pessoa
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Da um email a pessoa
	 * 
	 * @param email um email da pessoa
	 * 
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtém o endereco da pessoa
	 * 
	 * @return um <code>String</code> especificando o endereco da pessoa
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Da um endereço a pessoa
	 * 
	 * @param endereco um endereco da pessoa
	 * 
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Obtém a origem do cadastro da pessoa
	 * 
	 * @return um <code>String</code> especificando a origem do cadastro da pessoa
	 */
	public String getOrigemCadastro() {
		return origemCadastro;
	}

	/**
	 * Da a origem do cadastro
	 * 
	 * @param origemCadastro como foi cadastrada a pessoa
	 * 
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	/**
	 * Obtém o usuario que cadastrou a pessoa
	 * 
	 * @return um <code>UsuarioModel</code> especificando o usuario que cadastrou a pessoa
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Da o usuario que cadastrou a pessoa
	 *  
	 * @param usuarioModel usuario que cadastrou a pessoa
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
}
