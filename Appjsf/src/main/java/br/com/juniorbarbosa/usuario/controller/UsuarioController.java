package br.com.juniorbarbosa.usuario.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.juniorbarbosa.model.UsuarioModel;
import br.com.juniorbarbosa.repository.UsuarioRepository;
import br.com.juniorbarbosa.repository.entity.UsuarioEntity;
import br.com.juniorbarbosa.uteis.Uteis;

/**
 * Essa classe é um bean gerenciado pelo CDI, e que vai validar acesso ao sistema e usuário
 * 
 * @author Joao Carlos Barbosa Junior - 9 de nov de 2016 - 14:29:14
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -1224831466501408712L;

	@Inject
	private UsuarioModel usuarioModel;

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private UsuarioEntity usuarioEntity;

	/**
	 * Obtém o usuário da página
	 * 
	 * @return um <code>UsuarioModel</code> especificando o usuário da página
	 */
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	/**
	 * Seta um usuário nas páginas que serão utilizadas
	 * 
	 * @param usuarioModel usuário de controle
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	/**
	 * Método que vai retornar o usuário logado no sistema
	 * 
	 * @return um <code>UsuarioModel</code> especificando o usuário logado no sistema
	 */
	public UsuarioModel getUsuarioSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		return (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	}

	/**
	 * Finaliza a sessão do usuário e redireciona a página de login
	 * 
	 * @return um <code>String</code> especificando o redirecionamento para a tela de login
	 */
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/index.xhtml?faces-redirect=true";
	}

	/**
	 * Método que autentica o usuário para acesso ao sistema
	 * 
	 * @return um <code>String</code> especificando o redirecionamento para a tela principal do sistema
	 */
	public String efetuarLogin() {
		if (StringUtils.isEmpty(usuarioModel.getUsuario()) || StringUtils.isBlank(usuarioModel.getUsuario())) {
			Uteis.mensagemAlerta("Favor informar o login!");
			return null;
		} else if (StringUtils.isEmpty(usuarioModel.getSenha()) || StringUtils.isBlank(usuarioModel.getSenha())) {
			Uteis.mensagemAlerta("Favor informar a senha!");
			return null;
		} else {
			usuarioEntity = usuarioRepository.validaUsuario(usuarioModel);

			if (usuarioEntity != null) {
				usuarioModel.setSenha(null);
				usuarioModel.setCodigo(usuarioEntity.getCodigo());

				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);

				return "sistema/home?faces-redirect=true";
			} else {
				Uteis.mensagemAlerta("Não foi possível efetuar o login com esse usuário e/ou senha!");
				return null;
			}
		}
	}

}
