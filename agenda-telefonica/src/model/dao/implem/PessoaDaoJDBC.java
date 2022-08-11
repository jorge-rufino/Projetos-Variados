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
import model.entities.Categoria;
import model.entities.Endereco;
import model.entities.Pessoa;
import model.entities.Telefone;
import modelo.dao.DaoFactory;
import modelo.dao.PessoaDao;

public class PessoaDaoJDBC implements PessoaDao {

	private Connection connection;

	public PessoaDaoJDBC(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void insert(Pessoa obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Pessoa obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pessoa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			st = connection.prepareStatement("SELECT P.*, T.id as idTelefone, T.idPessoa " 
					+ "FROM pessoa P "
					+ "INNER JOIN telefone T " 
					+ "ON P.id = T.idPessoa " 
					+ "WHERE P.id = ? "
					+ "GROUP BY T.idPessoa");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				return instanciaPessoa(
						rs
						,DaoFactory.createEnderecoDAO().findById(rs.getInt("idEndereco"))
						,DaoFactory.createCategoriaDAO().findById(rs.getInt("idCategoria"))
						);
						
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
	public List<Pessoa> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = connection.prepareStatement("SELECT P.*, T.id as idTelefone, T.idPessoa " 
					+ "FROM pessoa P "
					+ "INNER JOIN telefone T " 
					+ "ON P.id = T.idPessoa " 
					+ "GROUP BY T.idPessoa;");
			rs = st.executeQuery();

			List<Pessoa> list = new ArrayList<>();

			Map<Integer, Categoria> mapCategoria = new HashMap<>();
			
			Map<Integer, Endereco> mapEndereco= new HashMap<>();
			
			while (rs.next()) {
				
				Categoria categoria = mapCategoria.get(rs.getInt("idCategoria"));
				
				Endereco endereco = mapEndereco.get(rs.getInt("idEndereco"));
				
				if(categoria == null) {
					categoria = DaoFactory.createCategoriaDAO().findById(rs.getInt("idCategoria"));
					mapCategoria.put(rs.getInt("idCategoria"), categoria);
				}
				
				if (endereco == null) {
					endereco = DaoFactory.createEnderecoDAO().findById(rs.getInt("idEndereco"));
					mapEndereco.put(rs.getInt("idCategoria"), endereco);
				}
				
				Pessoa pessoa = instanciaPessoa(rs, endereco, categoria);
				list.add(pessoa);
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
	public List<Pessoa> findByEndereco(Endereco endereco) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> findByCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pessoa> findByTelefone(Telefone telefone) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pessoa instanciaPessoa(ResultSet rs, Endereco end, Categoria cat) throws SQLException {
		Pessoa obj = new Pessoa();		
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("nome"));
		obj.setApelido(rs.getString("apelido"));
		obj.setEmail(rs.getString("email"));
		obj.setCpf(rs.getString("cpf"));
		obj.setSexo(rs.getString("sexo"));
		obj.setData_cadastro(rs.getDate("data_cadastro"));
		obj.setCategoria(cat);
		obj.setEndereco(end);
		
		//Busca todos os telefones dessa Pessoa
		List<Telefone> listTelefone = DaoFactory.createTelefoneDao().findByPessoa(obj);
		
		//Se a Pessoa tiver Telefones, adiciona cada uma em Pessoa
		if (listTelefone.size() > 0) {
			listTelefone.stream().forEach(telefone -> obj.addTelefone(telefone));
		}
				
		return obj;
	}

}
