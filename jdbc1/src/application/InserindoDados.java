package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class InserindoDados {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
				"INSERT INTO SELLER (Name,Email,BirthDate,BaseSalary,DepartmentId) "
				+ "VALUES"
				+"(?,?,?,?,?)",			//Valores serão preenchidos depois
				Statement.RETURN_GENERATED_KEYS); //Faz com que retorne os IDs dos elementos que foram criados na tabela
			
			//Preencher os valores das interrogações
			//Primeiro argumento é a posição da Interrogação, e o segundo é o valor que será passado
			ps.setString(1, "Carl Purple");			//Nome
			ps.setString(2, "carl@gmail.com");		//Email
			ps.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));	//Data de Nascimento
			ps.setDouble(4, 3000.0);				//Salario
			ps.setInt(5, 4);						//Id do Departamento
			
			int rowsAffect = ps.executeUpdate();	//Serve 
			
			if (rowsAffect > 0) {
				ResultSet rs = ps.getGeneratedKeys();	//Pega os IDs dos elementos que foram criados, e retorna um ResultSet
				
				while(rs.next()) {			
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			}	
			else {
				System.out.println("No rows affect");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnections();
		}
	}

}
