package model.dao.implem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.DaoFactory;
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

			if (rs.next()) {
				return instanciaTelefone(rs, DaoFactory.createPessoaDao().findById(rs.getInt("idPessoa")));
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
	public List<Telefone> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT * from Telefone");

			rs = st.executeQuery();

			List<Telefone> list = new ArrayList<>();

			Map<Integer, Pessoa> mapPessoa = new HashMap<>();
			
			while (rs.next()) {
				
				Pessoa pessoa = mapPessoa.get(rs.getInt("idPessoa"));
				
				if(pessoa == null) {
					pessoa = DaoFactory.createPessoaDao().findById(rs.getInt("idPessoa"));
					mapPessoa.put(rs.getInt("idPessoa"), pessoa);
				}
				
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

	@Override
	public List<Telefone> findByPessoa(Pessoa pessoa) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT T.*, P.nome, P.apelido, P.cpf, P.sexo, "
					+ "P.email, P.data_cadastro, P.idCategoria, P.idEndereco "
					+ "FROM telefone T "
					+ "INNER JOIN pessoa P on T.idPessoa = P.id WHERE idPessoa = ?");
			st.setInt(1, pessoa.getId());

			rs = st.executeQuery();
			
			List<Telefone> list = new ArrayList<>();
			
			while (rs.next()) {				
				list.add(instanciaTelefone(rs,instanciaPessoa(rs)));
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
		obj.setPessoa(pessoa);
		
		return obj;
	}
	
	private Pessoa instanciaPessoa(ResultSet rs) throws SQLException {
		Pessoa obj = new Pessoa();
		obj.setId(rs.getInt("idPessoa"));
		obj.setNome(rs.getString("nome"));
		obj.setApelido(rs.getString("apelido"));
		obj.setEmail(rs.getString("email"));
		obj.setCpf(rs.getString("cpf"));
		obj.setSexo(rs.getString("sexo"));
		obj.setData_cadastro(rs.getDate("data_cadastro"));
		obj.setCategoria(DaoFactory.createCategoriaDAO().findById(rs.getInt("idCategoria")));
		obj.setEndereco(DaoFactory.createEnderecoDAO().findById(rs.getInt("idEndereco")));
		
		return obj;
	}
}
