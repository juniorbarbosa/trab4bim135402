package br.com.juniorbarbosa.pessoa.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.juniorbarbosa.model.PessoaModel;
import br.com.juniorbarbosa.repository.PessoaRepository;

/**
 * Classe responsável por controlar a consulta de pessoas no BD
 * 
 * @author Joao Carlos Barbosa Junior - 17 de nov de 2016 - 14:53:21
 */
@Named(value = "consultarPessoaCarouselController")
@ViewScoped
public class ConsultarPessoaCarouselController implements Serializable {

	private static final long serialVersionUID = 7565900601564114656L;

	@Inject transient
	private PessoaRepository pessoaRepository;

	@Produces
	private List<PessoaModel> pessoas;

	/**
	 * Obtém uma lista de pessoas
	 * 
	 * @return um <code>List<PessoaModel></code> especificando todas as pessoas cadastradas
	 */
	public List<PessoaModel> getPessoas() {
		return pessoas;
	}

	/***
	 * Método responsável por carregar as pessoas na inicialização da aplicação, que vai retornar as pessoas cadastradas para apresentação
	 */
	@PostConstruct
	private void init() {
		this.pessoas = pessoaRepository.getPessoas();
	}

}