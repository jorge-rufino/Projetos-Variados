package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int rowsAffect = st.executeUpdate();

//Lembrar que o objeto que será inserido na tabela está sem o Id, portanto precisamos recuperar o Id que for gerado, e setar no "objeto"
			if (rowsAffect > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE department set Name = ? where Id = ?");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			int rowsAffect = st.executeUpdate();

			if (rowsAffect > 0) {
				System.out.println("Update success!");
			} else {
				throw new DbException("Unexpected error! No rows affected");
			}

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
			st = conn.prepareStatement("DELETE from department where Id = ?");
			st.setInt(1, id);
			int rowsAffect = st.executeUpdate();
			
			if (rowsAffect > 0) {
				System.out.println("Delete success!");
			}
			else {
				throw new DbException("Delete failed!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT Id as DepartmentId, Name as DepName FROM department " + "WHERE Id = ?");
			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				return instantiateDepartment(rs);
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
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT Id as DepartmentId, Name as DepName FROM department");

			rs = st.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {
				list.add(instantiateDepartment(rs));
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

}
