package controll;

import java.util.ArrayList;
import java.util.Scanner;

import model.Pasta;
import model.PastaDAO;

public class ControlePasta {

	private PastaDAO pastaDAO = new PastaDAO();
	private Scanner scanner = new Scanner(System.in);
	
	public void criarPasta(int idPastaPai){
		String nomepasta = "";
		Pasta pasta = new Pasta();
		
		System.out.println("Entre com o nome da pasta:");
		nomepasta = scanner.next();
		
		//trata possíveis entradas invalidas
		 if(nomepasta.equalsIgnoreCase("root")){
			 //o nome da pasta não pode ser root
			 System.out.println("Não é possivel criar uma pasta com esse nome.");
		 }else if (this.pastaExiste(idPastaPai,nomepasta)){
			 //se o nome da pasta ja existe dentro do diretório 
			 System.out.println("Essa pasta já existe!");
		 }else{
			 //cria a nova pasta
			 pasta.setNome(nomepasta);
			 pasta.setIdPastaPai(idPastaPai);
			 pastaDAO.cadastrar(pasta);
		 }
	}
	
	public void deletarPasta(int idPastaPai){
		String nomePasta;
		
		System.out.println("Entre com o nome da pasta que será deletada:");
		nomePasta = scanner.next();
		
		if(pastaExiste(idPastaPai, nomePasta)){
			//se a pasta existe então delete-a
			pastaDAO.deletar(nomePasta, idPastaPai);
		}else{
			System.out.println("Pasta não encontrada!");
		}
	}
	
	public void renomearPasta(int idPastaPai){
		String antigoNomePasta;
		
		System.out.println("Entre com o nome da pasta que deseja renomear:");
		antigoNomePasta = scanner.next();
		
		if(pastaExiste(idPastaPai, antigoNomePasta)){
			//se a pasta existir então
			String novoNomePasta;
			System.out.println("Entre com o novo nome da pasta:");
			novoNomePasta = scanner.next();
			//chama o metodo editar do objeto pastaDAO
			pastaDAO.editar(antigoNomePasta, idPastaPai ,novoNomePasta);
			System.out.println("O nome da pasta foi alterado de " + antigoNomePasta + " para " + novoNomePasta);
		}else{
			System.out.println("Pasta não encontrada.");
		}
	}
	
	public int selecioarPasta(int idPastaPai){
		int idPasta;
		String nomePasta;
		Pasta pasta = new Pasta();
		
		System.out.println("Entre com o nome da pasta que deseja acessar:");
		nomePasta = scanner.next();
		
		if(pastaExiste(idPastaPai, nomePasta)){
			//se a pasta existe então busque ela no banco e retorne sua id
			pasta = pastaDAO.selecionar(nomePasta, idPastaPai);
			idPasta = pasta.getId();
			return idPasta;
		}else{
			System.out.println("Pasta não encontrada!");
			return -1;
		}
	}
		
	public String getNomePastaAtual(int idPastaAtual){
		String nomePasta = "";
		ArrayList<Pasta> listaPasta = new ArrayList<>();
		listaPasta = pastaDAO.listar();
		
		//encontra e retorna a pasta que contem o id
		for (Pasta pasta : listaPasta) {
			if(pasta.getId() == idPastaAtual){
				nomePasta = pasta.getNome();
				break;
			}
		}
		return nomePasta;
	}
	
	public int sairPasta (int idPastaAtual){
		int idPastaAnterior = -1;
		ArrayList<Pasta> listaPasta = new ArrayList<>();
		listaPasta = pastaDAO.listar();
		
		//varre o a lista de pastas para encontrar o id da pasta anterior
		for (Pasta pasta : listaPasta) {
			if(pasta.getId() == idPastaAtual){
				idPastaAnterior = pasta.getIdPastaPai();
				break;
			}
		}
		
		return idPastaAnterior;
	}
	
	public String mostrarPastasInternas(int idPastaAtual){
		
		String listaPastaInternas = "";					//string contendo a lista de pastas contidas na pasta atual
		ArrayList<Pasta> listaPasta = new ArrayList<>();
		listaPasta = pastaDAO.listar();
		
		//varre a lista vinda da classe PastaDAO e salva os nomes das pastas na string LiistaPastasInternas
		for (Pasta pasta : listaPasta) {
			if(pasta.getIdPastaPai() == idPastaAtual && !pasta.getNome().equals("root")){
				listaPastaInternas += "	-> " + pasta.getNome() + "\n";
			}
		}
		return listaPastaInternas;
	}
	
	private boolean pastaExiste (int idPastaPai, String nomePasta){
		boolean existePasta = false;
		ArrayList<Pasta> listaPasta = new ArrayList<>();
		listaPasta = pastaDAO.listar();
		
		for (Pasta pasta : listaPasta) {
			if(pasta.getIdPastaPai() == idPastaPai && pasta.getNome().equalsIgnoreCase(nomePasta)){
				//se a pasta tiver o mesmo nome e estiver no mesmo diretório então retorne true
				existePasta = true;
			}
		}
		
		return existePasta;
	}
	
	
}
