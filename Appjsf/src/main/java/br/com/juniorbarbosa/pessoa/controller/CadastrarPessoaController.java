package br.com.juniorbarbosa.pessoa.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

	private UploadedFile file;

	/**
	 * Obtém o arquivo xml
	 * 
	 * @return um <code>UploadedFile</code> especificando o arquivo xml
	 */
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Indica um arquivo xml
	 * 
	 * @param file o arquivo xml
	 */
	public void setFile(UploadedFile file) {
		this.file = file;
	}

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

	/**
	 * Método que realiza o upload do arquivo xml e salva os registros no BD
	 */
	public void uploadRegistros() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			if (this.file.getFileName().equals("")) {
				Uteis.mensagemAtencao("Nenhum arquivo selecionado!");
				return;
			}
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(this.file.getInputstream());
			Element element = doc.getDocumentElement();
			NodeList nodes = element.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elementPessoa = (Element) node;
					String nome = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
					String sexo = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
					String email = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
					String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

					PessoaModel newPessoaModel = new PessoaModel();
					newPessoaModel.setUsuarioModel(this.usuarioController.getUsuarioSession());
					newPessoaModel.setEmail(email);
					newPessoaModel.setEndereco(endereco);
					newPessoaModel.setNome(nome);
					newPessoaModel.setOrigemCadastro("X");
					newPessoaModel.setSexo(sexo);

					pessoaRepository.salvarNovoRegistro(newPessoaModel);
				}
			}
			Uteis.mensagemInfo("Registros cadastrados com sucesso!");
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
