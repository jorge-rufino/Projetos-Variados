package model.dao.implem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.entities.Categoria;
import modelo.dao.CategoriaDao;

public class CategoriaDaoJDBC implements CategoriaDao{
	
	Connection connection;
	
	public CategoriaDaoJDBC(Connection connection) {		
		this.connection = connection;
	}

	@Override
	public void insert(Categoria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categoria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM categoria WHERE id = ?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			Categoria obj = new Categoria();
			
			if(rs.next()) {
				instanciaCategoria(rs);
			}
			
			return obj;
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);			
		}
	}

	@Override
	public List<Categoria> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM categoria");			
			rs = st.executeQuery();
			
			List<Categoria> list = new ArrayList<>();
			
			while(rs.next()) {				
				list.add(instanciaCategoria(rs));
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	public Categoria instanciaCategoria (ResultSet rs) throws SQLException {
		Categoria obj = new Categoria();
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("nome"));		
		return obj;
	}

}
