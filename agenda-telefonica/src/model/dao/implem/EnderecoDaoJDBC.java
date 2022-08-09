package model.dao.implem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Endereco;
import modelo.dao.EnderecoDao;

public class EnderecoDaoJDBC implements EnderecoDao {

	Connection connection = null;

	public EnderecoDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Endereco obj) {
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement(
					"INSERT INTO endereco (rua, bairro, complemento, cidade) " + "VALUES (?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getRua());
			st.setString(2, obj.getBairro());
			st.setString(3, obj.getComplemento());
			st.setString(4, obj.getCidade());

			int rowAffect = st.executeUpdate();

			if (rowAffect > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado! Sem rows afetadas.");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Endereco obj) {
		PreparedStatement st = null;

		try {
			st = connection.prepareStatement("UPDATE endereco SET rua = ?, bairro = ?, complemento = ?, cidade = ?"
					+ " WHERE id = ?;");
			st.setString(1, obj.getRua());
			st.setString(2, obj.getBairro());
			st.setString(3, obj.getComplemento());
			st.setString(4, obj.getCidade());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null; 
		
		try {
			
			st = connection.prepareStatement("DELETE FROM Endereco WHERE id = ?;");
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException("Erro de Integridade no banco: " + e.getMessage());
		}finally {
			DB.closeStatement(st);			
		}

	}

	@Override
	public Endereco findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT * from Endereco WHERE id = ?");
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				return instanciaEndereco(rs);
			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Endereco> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT * From Endereco");
			rs = st.executeQuery();

			List<Endereco> list = new ArrayList<>();

			while (rs.next()) {
				list.add(instanciaEndereco(rs));
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public Endereco instanciaEndereco(ResultSet rs) throws SQLException {
		Endereco obj = new Endereco();
		obj.setId(rs.getInt("id"));
		obj.setRua(rs.getString("rua"));
		obj.setBairro(rs.getString("bairro"));
		obj.setComplemento(rs.getString("complemento"));
		obj.setCidade(rs.getString("cidade"));

		return obj;
	}

}
