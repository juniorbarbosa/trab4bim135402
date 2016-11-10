package br.com.juniorbarbosa.repository;

import java.io.Serializable;

import javax.persistence.Query;

import br.com.juniorbarbosa.model.UsuarioModel;
import br.com.juniorbarbosa.repository.entity.UsuarioEntity;
import br.com.juniorbarbosa.uteis.Uteis;

/**
 * Classe que vai validar os dados dos usuários
 * 
 * @author Joao Carlos Barbosa Junior - 9 de nov de 2016 - 14:25:32
 */
public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = -1188403280498479985L;

	/**
	 * Esse metódo vai validar se o usuário que está tentando logar no sistema está cadastrado
	 * @param usuarioModel o usuário para validação
	 * @return um <code>UsuarioEntity</code> o usuário do BD
	 */
	public UsuarioEntity validaUsuario(UsuarioModel usuarioModel) {
		try {
			Query query = Uteis.jpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
			return (UsuarioEntity) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

}
