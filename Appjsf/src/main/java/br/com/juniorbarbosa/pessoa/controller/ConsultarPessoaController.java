package br.com.juniorbarbosa.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.juniorbarbosa.model.PessoaModel;
import br.com.juniorbarbosa.repository.PessoaRepository;

/**
 * Classe responsável por controlar as consultas de pessoas no BD
 * 
 * @author Joao Carlos Barbosa Junior - 11 de nov de 2016 - 21:43:10
 */
@Named(value = "consultarPessoaController")
@ViewScoped
public class ConsultarPessoaController implements Serializable {

	private static final long serialVersionUID = -4719722443381381129L;

	@Inject	transient 
	private PessoaModel pessoaModel;

	@Produces
	private List<PessoaModel> pessoas;

	@Inject	transient 
	private PessoaRepository pessoaRepository;

	/**
	 * Obtém uma lista de pessoas
	 * 
	 * @return um <code>List<PessoaModel></code> especificando as pessoas
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/**
	 * Adiciona as pessoas na lista
	 * 
	 * @param pessoas a lista de pessoas
	 */
	public void setPessoas(List<PessoaModel> pessoas) {
		this.pessoas = pessoas;
	}

	/**
	 * Obtém uma pessoas
	 * 
	 * @return um <code>PessoaModel</code> especificando a pessoa
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Da uma valor a pessoa para controle
	 * 
	 * @param pessoaModel uma pessoa
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/***
	 * Método responsavel por carregar as pessoas na inicializaçao da aplicação, que vai retornar as pessoas cadastradas
	 */
	@PostConstruct
	public void init() {
		this.pessoas = pessoaRepository.getPessoas();
	}
	
	/***
	 * Método que vai carregar as informações de uma pessoa para ser editada
	 * 
	 * @param pessoaModel a pessoa selecionada para edição
	 */
	public void editar(PessoaModel pessoaModel){
		pessoaModel.setSexo(pessoaModel.getSexo().substring(0, 1));
		this.pessoaModel = pessoaModel;
	}
 
	/***
	 * Método que atualiza o registro que foi alterado
	 */
	public void alterarRegistro(){
		this.pessoaRepository.alterarRegistro(this.pessoaModel);	
		this.init();
	}

}