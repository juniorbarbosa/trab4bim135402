package br.com.juniorbarbosa.pessoa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.juniorbarbosa.model.PessoaModel;
import br.com.juniorbarbosa.repository.PessoaRepository;

/**
 * Classe responsável por exportar os registros do sistema para XML
 * 
 * @author Joao Carlos Barbosa Junior - 17 de nov de 2016 - 16:36:42
 */
@Named(value = "exportarRegistrosXmlController")
@RequestScoped
public class ExportarRegistrosXmlController implements Serializable {

	private static final long serialVersionUID = -5047662630790029416L;

	@Inject transient
	private PessoaRepository pessoaRepository;

	private StreamedContent arquivoDownload;

	/***
	 * Realiza o download do arquivo XML
	 * 
	 * @return um <code>StreamedContent</code> especificando o arquivo de download
	 */
	public StreamedContent getArquivoDownload() {
		this.downlaodArquivoXmlPessoa();
		return arquivoDownload;
	}

	/***
	 * Método responsável por gerar o arquivo XML para exportação, com toda a estrutura necessária para gravar todas as informações de forma correta,
	 * junto com o caminho onde vai ser salvo e o nome do arquivo
	 * 
	 * @return um <code>File</code> especificando o arquivo
	 */
	private File gerarXmlPessoas() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		List<PessoaModel> pessoasModel = pessoaRepository.getPessoas();
		Element elementPessoas = new Element("Pessoas");
		Document documentoPessoas = new Document(elementPessoas);
		pessoasModel.forEach(pessoa -> {
			Element elementPessoa = new Element("Pessoa");
			elementPessoa.addContent(new Element("codigo").setText(pessoa.getCodigo().toString()));
			elementPessoa.addContent(new Element("nome").setText(pessoa.getNome()));
			elementPessoa.addContent(new Element("sexo").setText(pessoa.getSexo()));

			String dataCadastroFormatada = pessoa.getDataCadastro().format(dateTimeFormatter);

			elementPessoa.addContent(new Element("dataCadastro").setText(dataCadastroFormatada));
			elementPessoa.addContent(new Element("email").setText(pessoa.getEmail()));
			elementPessoa.addContent(new Element("endereco").setText(pessoa.getEndereco()));
			elementPessoa.addContent(new Element("origemCadastro").setText(pessoa.getOrigemCadastro()));
			elementPessoa.addContent(new Element("usuarioCadastro").setText(pessoa.getUsuarioModel().getUsuario()));
			elementPessoas.addContent(elementPessoa);
		});
		XMLOutputter xmlGerado = new XMLOutputter();
		try {
			String nomeArquivo = "pessoas_".concat(java.util.UUID.randomUUID().toString()).concat(".xml");
			File arquivo = new File("C:\\TesteDownload\\".concat(nomeArquivo));
			FileWriter fileWriter = new FileWriter(arquivo);
			xmlGerado.output(documentoPessoas, fileWriter);
			return arquivo;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/***
	 * Método responsável por preparar o arquivo para download
	 */
	public void downlaodArquivoXmlPessoa() {
		File arquivoXml = this.gerarXmlPessoas();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(arquivoXml.getPath());
			arquivoDownload = new DefaultStreamedContent(inputStream, "application/xml", arquivoXml.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
