package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class BuscandoDadosNoBanco {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

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

				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnections();
		}
	}

}
