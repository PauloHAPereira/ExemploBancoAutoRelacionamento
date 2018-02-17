package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoDB {
	
	//cria uma conexao com o banco
	public Connection conectar() throws Exception{
		Class.forName(Constants.DRIVER);
		Connection conexao = DriverManager.getConnection(Constants.BD_URL, 
				Constants.BD_NOME_USUARIO, Constants.BD_SENHA);
		return conexao;
	}
	
	//fecha a conexao com o banco
	public void desconectar (Connection conexao, PreparedStatement pStm, ResultSet resultado){
		if(conexao != null && pStm != null && resultado != null){
			try {
				conexao.close();
				pStm.close();
				resultado.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
