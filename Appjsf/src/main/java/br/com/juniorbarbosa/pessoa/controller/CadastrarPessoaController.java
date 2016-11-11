package br.com.juniorbarbosa.pessoa.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.juniorbarbosa.model.PessoaModel;
import br.com.juniorbarbosa.repository.PessoaRepository;
import br.com.juniorbarbosa.usuario.controller.UsuarioController;
import br.com.juniorbarbosa.uteis.Uteis;

/**
 * Classe que vai controlar o cadastro de pessoas
 * 
 * @author Joao Carlos Barbosa Junior - 10 de nov de 2016 - 16:47:53
 */
@Named(value = "cadastrarPessoaController")
@RequestScoped
public class CadastrarPessoaController implements Serializable {

	private static final long serialVersionUID = 6433881242093401362L;

	@Inject
	private PessoaModel pessoaModel;

	@Inject
	private UsuarioController usuarioController;

	@Inject
	private PessoaRepository pessoaRepository;

	/**
	 * Obtém o a pessoa
	 * 
	 * @return um <code>PessoaModel</code> especificando o a pessoa
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/**
	 * Da um valor a pessoaModel
	 * 
	 * @param pessoaModel a pessoa
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 * Método para salvar uma nova pessoa via input 
	 */
	public void salvarNovaPessoa() {
		pessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
		pessoaModel.setOrigemCadastro("I");
		pessoaRepository.salvarNovoRegistro(this.pessoaModel);
		this.pessoaModel = null;
		Uteis.mensagemInfo("Pessoa cadastrada com sucesso");
	}

}
