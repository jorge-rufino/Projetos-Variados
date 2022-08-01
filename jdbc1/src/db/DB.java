package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
//	Classe que cria e fecha a conexao com o banco de dados
	private static Connection conn = null;
	
//	Metodo que cria a conexao
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();		//Carrega as propriedades
				String url = props.getProperty("dburl");	//"dburl" é o mesmo no nome que está no arquivo "db.properties"
				conn = DriverManager.getConnection(url, props);	//Cria a conexao
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
//	Metodo que fecha a conexao
	public static void closeConnections() {
		if(conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement (Statement st) {
		if (st != null) {
			try {
				st.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Carrega os dados do arquivo de configuraçao do banco
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);		//Carrega os dados e guarda na variavel "props"
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());	//Excecao personalizada
		}
	}
}
