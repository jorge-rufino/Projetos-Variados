package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class AlterandoDados {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ?"
					+ "WHERE DepartmentId = ?"
					);
			st.setDouble(1, 200.0);	//Valor que será aumentado no salario
			st.setInt(2, 2);		//ID do Departamento (Vendedores deste departamento terão o aumento)
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Update! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnections();
		}
	}

}
