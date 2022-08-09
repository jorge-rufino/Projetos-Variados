package model.dao.implem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.TelefoneDao;

public class TelefoneDaoJDBC implements TelefoneDao {

	private Connection connection;

	public TelefoneDaoJDBC(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void insert(Telefone obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Telefone obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Telefone findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT * from Telefone WHERE id = ?");
			st.setInt(1, id);

			rs = st.executeQuery();

//			if (rs.next()) {
//				return instanciaTelefone(rs);
//			}

			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Telefone> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT * from Telefone");

			rs = st.executeQuery();

			List<Telefone> list = new ArrayList<>();

//			while (rs.next()) {
//				list.add(instanciaTelefone(rs));
//			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Telefone> findByPessoa(Pessoa pessoa) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT * from Telefone WHERE idPessoa = ?");
			st.setInt(1, pessoa.getId());

			rs = st.executeQuery();

			List<Telefone> list = new ArrayList<>();

			while (rs.next()) {
				list.add(instanciaTelefone(rs,pessoa));
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public Telefone instanciaTelefone(ResultSet rs, Pessoa pessoa) throws SQLException {
		Telefone obj = new Telefone();
		obj.setId(rs.getInt("id"));
		obj.setDdd(rs.getString("ddd"));
		obj.setNumero(rs.getString("numero"));
		obj.setTipo(rs.getString("tipo"));
		obj.setIdPessoa(pessoa.getId());
		
		return obj;
	}

}
