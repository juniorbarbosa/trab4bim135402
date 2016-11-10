package br.com.juniorbarbosa.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Essa classe/filter vai ser chamado toda vez que for realizada uma requisição para o Faces Servlet
 * É também responsável por gerenciar o EntityManager
 * 
 * @author Joao Carlos Barbosa Junior - 8 de nov de 2016 - 20:13:52
 */
@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {

	private EntityManagerFactory entityManagerFactory;

	private String persistence_unit_name = "unit_app";

	/**
	 * Método responsável pelo fechamento do EntityManager
	 */
	public void destroy() {
		this.entityManagerFactory.close();
	}

	/**
	 * Método responsável por criar o EntityManager e fazer o Filtro
	 * @param request a requisição
	 * @param response a resposta
	 * @param chain o filtro
	 * @throws IOException/ServletException caso ocorra alguma falha na utilização do filtro
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		request.setAttribute("entityManager", entityManager);
		entityManager.getTransaction().begin();
		chain.doFilter(request, response);
		try {
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	/**
	* Método responsável por criar o EntityManagerFactory com os parâmetros definidos no persistence.xml
	* @param fConfig configuração do filtro
	* @throws ServletException caso ocorra algum problema no Servlet
	*
	*/
	public void init(FilterConfig fConfig) throws ServletException {
		this.entityManagerFactory = Persistence.createEntityManagerFactory(this.persistence_unit_name);
	}

}