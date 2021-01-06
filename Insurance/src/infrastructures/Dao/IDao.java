package infrastructures.Dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
	//CRUD
	 public void insert(T obj) throws SQLException;

	 public void update(T obj) throws SQLException;

	 public void delete(T obj) throws SQLException;
	 
	 public List<T> getByID(String id);
	 
	 public List<T> getAll();

}
