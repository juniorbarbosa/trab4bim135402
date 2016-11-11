package br.com.juniorbarbosa.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe responsável por gravar na tabela as pessoas no BD
 * 
 * @author Joao Carlos Barbosa Junior - 10 de nov de 2016 - 14:22:31
 */
@Entity
@Table(name = "tb_pessoa")
public class PessoaEntity implements Serializable {

	private static final long serialVersionUID = 2379308972655117308L;

	@Id
	@GeneratedValue
	@Column(name = "id_pessoa")
	private int codigo;

	@Column(name = "nm_pessoa")
	private String nome;

	@Column(name = "fl_sexo")
	private String sexo;

	@Column(name = "dt_cadastro")
	private LocalDateTime dataCadastro;

	@Column(name = "ds_email")
	private String email;

	@Column(name = "ds_endereco")
	private String endereco;

	@Column(name = "fl_origemCadastro")
	private String origemCadastro;

	@OneToOne
	@JoinColumn(name = "id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

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
	 * @return um <code>UsuarioEntity</code> especificando o usuario que cadastrou a pessoa
	 */
	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	/**
	 * Da o usuario que cadastrou a pessoa
	 *  
	 * @param usuarioEntity usuario que cadastrou a pessoa
	 */
	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

}
