package br.com.juniorbarbosa.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.juniorbarbosa.model.UsuarioModel;

/**
 * Essa classe Filter vai validar se o usuário está logado para acessar outras partes do sistema
 * 
 * @author Joao Carlos Barbosa Junior - 9 de nov de 2016 - 14:36:06
 */
@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {

	/**
	 * Método responsável por destruir o filtro
	 */
	public void destroy() {

	}

	/**
	 * Método que vai validar se existe usuário na sessão para acessar as páginas do sistema, caso não exista é redirecionado a tela de Login
	 * 
	 * @param request a requisição
	 * @param response a resposta
	 * @param chain o filtro
	 * @throws IOException/ServletException caso ocorra alguma falha na utilização do filtro
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession httpSession = ((HttpServletRequest) request).getSession();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (httpServletRequest.getRequestURI().indexOf("index.xhtml") <= -1) {
			UsuarioModel usuarioModel = (UsuarioModel) httpSession.getAttribute("usuarioAutenticado");
			if (usuarioModel == null) {
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.xhtml");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	* Método responsável por iniciar o filtro
	* @param fConfig configuração do filtro
	* @throws ServletException caso ocorra algum problema no Servlet
	*
	*/
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
