package view;

import java.util.Scanner;

import controll.ControlePasta;

public class Main {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean condicaoSair = true;//condição para sair do menu
		int opcao;					//opção do menu
		int idPastaAtual = 1;		//id da pasta atual que estamos trabalhando (1 é o id do root)
		String pastaAtual;			//nome da pasta atual que estamos trabalhando
		String subPastas;			//string contendo a lista de pasta que esta contida dentro da pasta atual
		ControlePasta controlePasta = new ControlePasta();

		while (condicaoSair) {
			
			//imprime a pasta atual e suas sub pastas
			System.out.println("====================================          ====================================");
			pastaAtual = controlePasta.getNomePastaAtual(idPastaAtual);
			System.out.println("Pasta atual: " + pastaAtual);
			subPastas = controlePasta.mostrarPastasInternas(idPastaAtual);
			System.out.println(subPastas);
			
			//menu de opções
			System.out.println("Entre com uma opcao: " + "\n1 Criar nova pasta" + "\n2 Deletar pasta" +
					"\n3 Entrar na pasta" + "\n4 Sair da pasta" + "\n5 Renomear pasta");
			opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1:
				controlePasta.criarPasta(idPastaAtual);	//id da pasta atual será o idPastaPai para a pasta que será criada
				break;
			case 2:
				controlePasta.deletarPasta(idPastaAtual);//id da pasta atual será o idPastaPai para apasta que será deletada
				break;
			case 3:
				int idPastaSelecionada;
				idPastaSelecionada = controlePasta.selecioarPasta(idPastaAtual);
				if(idPastaSelecionada != -1){
					idPastaAtual = idPastaSelecionada;
				}
				break;
			case 4:
				int idPastaAnterior;
				idPastaAnterior = controlePasta.sairPasta(idPastaAtual);
				if(idPastaAnterior != -1){
					idPastaAtual = idPastaAnterior;
				}
				break;
			case 5:
				controlePasta.renomearPasta(idPastaAtual);//id da pasta atual será o idPastaPai para apasta que será renomeada
				break;

			default:
				System.out.println("Valor invalido");
				break;
			}
		}
	}

}
