package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import entities.Seller;

public class Crud {

	public static void addSeller(String name, String email, String date, double salary, int idDepartment) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DB.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO SELLER (Name,Email,BirthDate,BaseSalary,DepartmentId) " + "VALUES" + "(?,?,?,?,?)", 
					Statement.RETURN_GENERATED_KEYS); // Faz com que retorne os IDs dos elementos que foram criados na tabela

			// Preencher os valores das interrogações
			// Primeiro argumento é a posição da Interrogação, e o segundo é o valor que
			// será passado
			ps.setString(1, name); // Nome
			ps.setString(2, email); // Email
			ps.setDate(3, new java.sql.Date(sdf.parse(date).getTime())); // Data de Nascimento
			ps.setDouble(4, salary); // Salario
			ps.setInt(5, idDepartment); // Id do Departamento

			int rowsAffect = ps.executeUpdate(); 

			if (rowsAffect > 0) {
				ResultSet rs = ps.getGeneratedKeys(); // Pega os IDs dos elementos que foram criados, e retorna um	ResultSet

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affect");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(ps);
//			DB.closeConnections();
		}
	}
	
	public static List<Seller> listaDados() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Seller> sellers =  new ArrayList<>();
		
		try {
			conn = DB.getConnection();

			st = conn.createStatement(); // Cria o Statement para poder fazer operaçoes no banco

			rs = st.executeQuery("select * from department"); // Executa e query e retonar um ResultSet com os dados do
																// banco

			System.out.println("Data Table Department:");
			while (rs.next()) { // "next()" traz a proxima linha ta tabela e retorna "null" quando tiver mais

				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}

			DB.closeResultSet(rs);
			rs = st.executeQuery("select * from seller");

			System.out.println();
			System.out.println("Data Table Seller:");
			
			while (rs.next()) { // "next()" traz a proxima linha ta tabela e retorna "null" quando tiver mais

//				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
				
				sellers.add(new Seller(rs.getInt("Id"),rs.getString("Name"), rs.getString("Email"), 
						rs.getDate("BirthDate"), rs.getDouble("BaseSalary"), rs.getInt("DepartmentId")));
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
//			DB.closeConnections();
		}
		
		return sellers;
	}
}
