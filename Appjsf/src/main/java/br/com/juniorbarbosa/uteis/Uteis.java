package br.com.juniorbarbosa.uteis;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe responsável por ter todos os métodos que pode ser usado em todo o sistema
 * 
 * @author Joao Carlos Barbosa Junior - 9 de nov de 2016 - 14:13:44
 */
public class Uteis {

	/**
	 * Método responsável por recuperar o EntityManager criado no JPAFilter
	 * 
	 * @return um um <code>EntityManager</code> recuperado do JPAFilter
	 */
	public static EntityManager jpaEntityManager() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	/**
	 * Método que vai apresentar uma mensagem de alerta ao usuário
	 * 
	 * @param mensagem é o texto que vai ser apresentado ao usuário quando a mensagem for apresentada
	 */
	public static void mensagemAlerta(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	/**
	 * Método que vai apresentar uma mensagem de atenção ao usuário
	 * 
	 * @param mensagem é o texto que vai ser apresentado ao usuário quando a mensagem for apresentada
	 */
	public static void mensagemAtencao(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}

	/**
	 * Método que vai apresentar uma mensagem de informação ao usuário
	 * 
	 * @param mensagem é o texto que vai ser apresentado ao usuário quando a mensagem for apresentada
	 */
	public static void mensagemInfo(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

}
