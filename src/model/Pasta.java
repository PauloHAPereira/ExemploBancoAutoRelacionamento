package model;

public class Pasta {
	private int id;			//id da pasta
	private int idPastaPai;	//pai da pasta atual, se ele for o root ele é o proprio pai
	private String nome;	//nome da pasta
	
	//construtor gnerico
	public Pasta (){
		
	}

	//getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPastaPai() {
		return idPastaPai;
	}

	public void setIdPastaPai(int idPastaPai) {
		this.idPastaPai = idPastaPai;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
