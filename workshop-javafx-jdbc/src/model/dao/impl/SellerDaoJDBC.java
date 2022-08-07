package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	private Connection conn;
	
	//A classe já tem que instanciar com um Connection que só será fechado no programa principal
	public SellerDaoJDBC(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);	//Faz retornar tb o ID da linha(s) inserida
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDate().getTime()));
			st.setDouble(4, obj.getSalary());			
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffect = st.executeUpdate();	//Executa no banco a query e retorna o numero de linhas afetadas
			
			if(rowsAffect > 0 ) {					//Se numero de linhas for maior que zero, é pq a query foi executada e gerou pelo menos uma linha 
				ResultSet rs =  st.getGeneratedKeys();	//Pega o ID(s) da linha(s) que foi inserida(s) 
				if (rs.next()) {					
					int id = rs.getInt(1);
					obj.setId(id);		//Seta o Id no objeto que foi recebido como argumento no metodo e inserido no banco para ficar atualizado
				}
				//Foi fechado aqui pois a variavel "rs" só existe no escopo do "if"
				DB.closeResultSet(rs); 
			}
			//Se der algum erro e o insert na for feito, dispara esta exceção
			else {	
				throw new DbException("Unexpected error! No rows affected");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
					+ "WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);	//Faz retornar tb o ID da linha(s) inserida
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDate().getTime()));
			st.setDouble(4, obj.getSalary());			
			st.setInt(5, obj.getDepartment().getId());
			st.setInt(6, obj.getId());
			
			st.executeUpdate();	//Executa no banco a query e retorna o numero de linhas afetadas
			System.out.println("Upadate completed!");
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE from seller WHERE id = ?");
			
			st.setInt(1, id);
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new DbException("Delete failed!");
			}
			else {
				System.out.println("Delete success!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);	//Cria um objeto Department				
				Seller obj = instantiateSeller(rs, dep);	//Cria um objeto Seller vinculado ao Department acima
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	//Instancia um novo Seller e passa a obrigação de tratar a exceção para quem for usar o método
	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setSalary(rs.getDouble("BaseSalary"));
		obj.setDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	//Instancia um novo Department e passa a obrigação de tratar a exceção para quem for usar o método
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "					
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
		
			List<Seller> sellers =  new ArrayList<>();
			
			//Este Map vai guardar os objetos Department
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {	
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rs, dep);	//Cria um objeto Seller vinculado ao Department acima
				sellers.add(obj);
			}
			return sellers;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			st.setInt(1, department.getId());
			rs = st.executeQuery();
		
			List<Seller> sellers =  new ArrayList<>();
			
			//Este Map vai guardar os objetos Department
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {	
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rs, dep);	//Cria um objeto Seller vinculado ao Department acima
				sellers.add(obj);
			}
			return sellers;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByFields(String name, String email, Double salary, String depName) {
		PreparedStatement st = null;
		ResultSet rs = null;
				
		Map<String, Object> mapFields = new TreeMap<>();
		
		mapFields.put("s.Name", name);
		mapFields.put("s.Email", email);
		mapFields.put("s.BaseSalary", salary);
		mapFields.put("d.Name", depName);
		
		String condition = "";		
		
		for (String key : mapFields.keySet()) {
			if (mapFields.get(key) != null) {	//Pega somente as keys que nao tiverem valor "null"
				//Vai passar os valores na query de acordo com o tipo dos dados na tabela
				String complemento = "";
				
				if (mapFields.get(key) instanceof String) {	//Se o valor for uma instancia de String					
					complemento += " like '%"+mapFields.get(key)+"%'";
				}
				if (mapFields.get(key) instanceof Double) {	//Se o valor for uma instancia de Double
					complemento += " = "+mapFields.get(key);
				}
				
				if (condition.isEmpty()) {
					condition += key + complemento;
				}
				else {				
					condition += " AND " + key + complemento;
				}
			}
		}
		
		try {
			String query = "SELECT s.*, d.Name as DepName "
					+ "FROM seller s INNER JOIN department d "
					+ "ON s.DepartmentId = d.Id "
					+ "WHERE " + condition
					+ " ORDER BY s.Name";
			
//			System.out.println(query);
			st = conn.prepareStatement(query);
			
			rs = st.executeQuery();
		
			List<Seller> sellers =  new ArrayList<>();
			
			//Este Map vai guardar os objetos Department
			Map<Integer, Department> map = new HashMap<>();
			
			while(rs.next()) {	
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantiateSeller(rs, dep);	//Cria um objeto Seller vinculado ao Department acima
				sellers.add(obj);
			}
			return sellers;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}


