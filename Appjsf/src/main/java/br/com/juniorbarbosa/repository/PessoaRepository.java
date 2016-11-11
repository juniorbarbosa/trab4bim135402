package br.com.juniorbarbosa.repository;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.juniorbarbosa.model.PessoaModel;
import br.com.juniorbarbosa.repository.entity.PessoaEntity;
import br.com.juniorbarbosa.repository.entity.UsuarioEntity;
import br.com.juniorbarbosa.uteis.Uteis;

/**
 * Classe responsável por persistir a entidade PessoaEntity no BD
 * 
 * @author Joao Carlos Barbosa Junior - 10 de nov de 2016 - 14:59:01
 */
public class PessoaRepository implements Serializable {

	private static final long serialVersionUID = 4456078351249693771L;

	@Inject
	private PessoaEntity pessoaEntity;

	private EntityManager entityManager;

	/***
	 * Método responsável por salvar uma nova pessoa no BD
	 * 
	 * @param pessoaModel pessoa que vai ser salva
	 */
	public void salvarNovoRegistro(PessoaModel pessoaModel) {
		entityManager = Uteis.jpaEntityManager();

		pessoaEntity = new PessoaEntity();
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo());

		pessoaEntity.setUsuarioEntity(usuarioEntity);

		entityManager.persist(pessoaEntity);
	}

}
