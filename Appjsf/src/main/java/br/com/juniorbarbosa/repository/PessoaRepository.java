package br.com.juniorbarbosa.repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.juniorbarbosa.model.PessoaModel;
import br.com.juniorbarbosa.model.UsuarioModel;
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

	/***
	 * Método para consultar pessoas no BD
	 * 
	 * @return um <code>List<PessoaModel></code> com todas as pessoas cadastradas
	 */
	public List<PessoaModel> getPessoas() {
		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();

		entityManager = Uteis.jpaEntityManager();
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>) query.getResultList();

		PessoaModel pessoaModel = null;

		for (PessoaEntity pessoaEntity : pessoasEntity) {
			pessoaModel = new PessoaModel();
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			pessoaModel.setEmail(pessoaEntity.getEmail());
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			pessoaModel.setNome(pessoaEntity.getNome());
			if (pessoaEntity.getOrigemCadastro().equals("X")) {
				pessoaModel.setOrigemCadastro("XML");
			} else {
				pessoaModel.setOrigemCadastro("INPUT");
			}

			if (pessoaEntity.getSexo().equals("M")) {
				pessoaModel.setSexo("Masculino");
			} else {
				pessoaModel.setSexo("Feminino");
			}
			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();

			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
			pessoaModel.setUsuarioModel(usuarioModel);
			pessoasModel.add(pessoaModel);
		}
		return pessoasModel;
	}

	/***
	 * Método responsável por consultar uma pessoa cadastrada pelo código
	 * @param codigo da pessoa
	 * @return um <code>PessoaEntity</code> com o código da pessoa
	 */
	private PessoaEntity getPessoa(int codigo){
		entityManager =  Uteis.jpaEntityManager();
		return entityManager.find(PessoaEntity.class, codigo);
	}
 
	/***
	 * Método responsável por alterar um registro cadastrado no banco de dados
	 * @param pessoaModel a pessoa que vai ser alterada
	 */
	public void alterarRegistro(PessoaModel pessoaModel){
		entityManager =  Uteis.jpaEntityManager();
 
		PessoaEntity pessoaEntity = this.getPessoa(pessoaModel.getCodigo());
		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}
	
	/***
	 * Método responsável por excluir um registro do banco de dados
	 * @param codigo da pessoa que vai ser excluida
	 */
	public void excluirRegistro(int codigo) {
		entityManager =  Uteis.jpaEntityManager();		
		PessoaEntity pessoaEntity = this.getPessoa(codigo);
		entityManager.remove(pessoaEntity);
	}

}
