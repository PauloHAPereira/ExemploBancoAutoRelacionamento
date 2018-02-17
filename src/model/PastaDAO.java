package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PastaDAO {
	
	private Connection conexao;
	private PreparedStatement preparedStatement;
	private ResultSet resultado;
	ConexaoDB conexaoDB = new ConexaoDB();
	
	public void cadastrar(Pasta pasta){
		
		try {
			//abrir uma conexão
			conexao = conexaoDB.conectar();
			//passa uma sql
			//preparedStatement = conexao.prepareStatement("INSERT INTO PASTAS.PASTA (NOME,IDPAI) VALUES (?,?)");
			preparedStatement = conexao.prepareStatement("INSERT INTO PASTA (PASTA,IDPAI) VALUES (?,?)");
			//adiciona os valores no codigo sql, subistituindo os simbolos ?
			preparedStatement.setString	(1, pasta.getNome());
			preparedStatement.setInt	(2, pasta.getIdPastaPai());
			//executa a query sql
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//fecha a conexão
			conexaoDB.desconectar(conexao, preparedStatement, resultado);
		}
	}
	
	public void deletar(String quem, int idPastaPai){
		try {
			//abrir uma conexão
			conexao = conexaoDB.conectar();
			//passa uma sql
			//preparedStatement = conexao.prepareStatement("DELETE FROM PASTAS.PASTA WHERE NOME=? AND IDPAI=?");
			preparedStatement = conexao.prepareStatement("DELETE FROM PASTA WHERE PASTA=? AND IDPAI=?");
			//adiciona os valores no codigo sql, subistituindo os simbolos ?
			preparedStatement.setString	(1, quem);
			preparedStatement.setInt	(2, idPastaPai);
			//executa a query sql
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//fechar a conexão
			conexaoDB.desconectar(conexao, preparedStatement, resultado);
		}
	}
	
	public Pasta selecionar (String nomePasta, int idPastaPai){
		Pasta pasta = new Pasta();
		try {
			//abrir uma conexão
			conexao = conexaoDB.conectar();
			//Adiciona uma sql
			//preparedStatement = conexao.prepareStatement("SELECT * FROM PASTAS.PASTA WHERE NOME=? AND IDPAI=?");
			preparedStatement = conexao.prepareStatement("SELECT * FROM PASTA WHERE PASTA=? AND IDPAI=?");
			//adiciona os valores no codigo sql, subistituindo os simbolos ?
			preparedStatement.setString(1, nomePasta);
			preparedStatement.setInt(2, idPastaPai);
			//executa a query sql e guarda seu resultado
			resultado = preparedStatement.executeQuery();
			while(resultado.next()){
				//enquanto houver pastas dentro do resultado faça
				pasta.setId		(resultado.getInt("idpasta"));
				pasta.setIdPastaPai	(resultado.getInt("idPai"));
				pasta.setNome	(resultado.getString("nome"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//fechar a conexão
			conexaoDB.desconectar(conexao, preparedStatement, resultado);
		}
		return pasta;
	}
	
	public void editar(String nomeAntigo, int idPai , String novoNome){
		
		try {
			//abrir a conexão
			conexao = conexaoDB.conectar();
			//Adiciona uma sql
			//preparedStatement = conexao.prepareStatement("UPDATE PASTAS.PASTA SET NOME=? WHERE NOME=? AND IDPAI=?");
			preparedStatement = conexao.prepareStatement("UPDATE PASTA SET PASTA=? WHERE PASTA=? AND IDPAI=?");
			//adiciona os valores no codigo sql, subistituindo os simbolos ?
			preparedStatement.setString	(1, novoNome);
			preparedStatement.setString	(2, nomeAntigo);
			preparedStatement.setInt	(3, idPai);
			//executa a query sql
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//fecha a conexão
			conexaoDB.desconectar(conexao, preparedStatement, resultado);
		}
		
	}
	
	public ArrayList<Pasta> listar(){
		//array de pastas para receber o resultado do banco
		ArrayList<Pasta> listaPasta = new ArrayList<>();
		try {
			//abrir a conexão
			conexao = conexaoDB.conectar();
			//Adiciona uma sql
			//preparedStatement = conexao.prepareStatement("SELECT * FROM PASTAS.PASTA");
			preparedStatement = conexao.prepareStatement("SELECT * FROM PASTA");
			//executa a query sql e guarda seu resultado
			resultado = preparedStatement.executeQuery();
			while(resultado.next()){
				//enquanto houver pastas dentro do resultado faça
				Pasta pasta = new Pasta();
				//adiciona os valores do banco ao objeto pasta
				pasta.setId		(resultado.getInt("idpasta"));
				//pasta.setNome	(resultado.getString("nome"));
				pasta.setNome	(resultado.getString("pasta"));
				pasta.setIdPastaPai	(resultado.getInt("idPai"));
				//adiciona o objeto pasta a lista de pastas
				listaPasta.add(pasta);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//fecha a conexão
			conexaoDB.desconectar(conexao, preparedStatement, resultado);
		}
		return listaPasta;
	}
}
