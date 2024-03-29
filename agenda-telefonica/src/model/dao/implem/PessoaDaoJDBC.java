package model.dao.implem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PreparedStatement st = null;
		
		try {
			
			st = connection.prepareStatement("INSERT INTO pessoa (nome, apelido, sexo, email, data_cadastro, idCategoria, idEndereco) "
					+ "VALUES (?,?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getApelido());
			st.setString(3, obj.getSexo());
			st.setString(4, obj.getEmail());
			st.setDate(5, new java.sql.Date(obj.getData_cadastro().getTime()));
			st.setInt(6, obj.getCategoria().getId());
			st.setInt(7, obj.getEndereco().getId());
			
			int rowAffect = st.executeUpdate();
			
			if(rowAffect > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro ao cadastrar nova pessoa no banco!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Pessoa obj) {
		PreparedStatement st = null;
		
		try {
			
			st = connection.prepareStatement("UPDATE pessoa set nome=?, apelido=?, sexo=?, email=?, data_cadastro=?, "
					+ "idCategoria=?, idEndereco=? WHERE id = ?;");
			st.setString(1, obj.getNome());
			st.setString(2, obj.getApelido());
			st.setString(3, obj.getSexo());
			st.setString(4, obj.getEmail());
			st.setDate(5, new java.sql.Date(obj.getData_cadastro().getTime()));
			st.setInt(6, obj.getCategoria().getId());
			st.setInt(7, obj.getEndereco().getId());
			st.setInt(8, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		 try {
			
			 st = connection.prepareStatement("DELETE from Pessoa WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
			 st.setInt(1, id);
			 
			 //Define que o commit n�o ser� executado automaticamente
			 connection.setAutoCommit(false);
			 
			 Pessoa pessoa = findById(id);
			 
			 if(pessoa == null) {
				 throw new DbException("Id n�o existe!");
			 }
			 
			 List<Telefone> telefones = DaoFactory.createTelefoneDao().findByPessoa(pessoa);
			
			 if (telefones.size() > 0) {
				 for (Telefone telefone : telefones) {
					 DaoFactory.createTelefoneDao().deleteById(telefone.getId());
				}
			 }
			 
			int rowAffect =st.executeUpdate();
			 
			 if (rowAffect == 0) {
				 throw new DbException("Id a ser deletado n�o existe!");
			 }
			 
			 //Faz o commit no banco somente se todas as opera��es tiverem sucesso
			 connection.commit();
			 
			 //Se capturar alguam excecao, faz o rollback
		} catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		}finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Pessoa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {

			st = connection.prepareStatement("SELECT * from Pessoa WHERE id = ? ");
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

			st = connection.prepareStatement("SELECT * from Pessoa"); 
					
					
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
		
		//Se a Pessoa tiver Telefones, adiciona cada um em Pessoa
		if (listTelefone.size() > 0) {
			listTelefone.stream().forEach(telefone -> obj.addTelefone(telefone));
		}
				
		return obj;
	}

	@Override
	public void salvaPessoaEnderecoTelefone(Pessoa pessoa, Telefone telefone) {
		try {
			
			connection.setAutoCommit(false);
			
			if (pessoa.getEndereco() != null) {
				DaoFactory.createEnderecoDAO().insert(pessoa.getEndereco());
			}
			
			insert(pessoa);
			
			telefone.setPessoa(pessoa);
			
			DaoFactory.createTelefoneDao().insert(telefone);
			
			connection.commit();
			
		}catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} 
			catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} 		
	}
}

