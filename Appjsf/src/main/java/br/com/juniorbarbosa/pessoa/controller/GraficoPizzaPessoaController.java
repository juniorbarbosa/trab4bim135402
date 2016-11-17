package br.com.juniorbarbosa.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.juniorbarbosa.repository.PessoaRepository;

/**
 * Classe responsável por gerar e apresentar o gráfico do tipo pizza ao usuário
 * 
 * @author Joao Carlos Barbosa Junior - 17 de nov de 2016 - 16:15:31
 */
@Named(value = "graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;

	private PieChartModel pieChartModel;

	/**
	 * Obtém o PieChartModel do primeFaces
	 * 
	 * @return um <code>PieChartModel</code> especificando o gráfico
	 */
	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	/***
	 * Método responsável por carregar as pessoas após a construção da aplicação
	 */
	@PostConstruct
	public void init() {
		this.pieChartModel = new PieChartModel();
		this.montaGrafico();
	}

	/***
	 * Método responsável por montar o gráfico pizza na página
	 */
	private void montaGrafico() {
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.getOrigemPessoa();
		hashtableRegistros.forEach((chave, valor) -> {
			pieChartModel.set(chave, valor);
		});
		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");
	}
}
