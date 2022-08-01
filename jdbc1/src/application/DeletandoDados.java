package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class DeletandoDados {
	
	public static void main (String args[]) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE from department "					
					+ "WHERE Id = ?"
					);			
			st.setInt(1, 2);		//ID do Departamento a ser deletado
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Deleted! Rows affected: " + rowsAffected);
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnections();
		}
	}

}
