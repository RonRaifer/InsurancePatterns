package infrastructures.Dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import infrastructures.Factories.IFactory;
import infrastructures.Logger.Logger;

public abstract class InsuranceDao<T> implements IDao<T> {
	protected Connection _conn;
	protected Logger _logger;
	private String _myType;
	private IFactory<T> _factory;
	private SQLiteDataSource ds = null;
    public InsuranceDao(Logger logger, IFactory<T> factory) {
        super();
        createConnection();
        _logger = logger;
        _factory = factory;
	
    }	
	@Override
	public abstract void insert(T obj) throws SQLException;
	@Override
	public abstract void update(T obj) throws SQLException;
	@Override
	public abstract void delete(T obj) throws SQLException;
	@Override
	public T getByID(String id) {
		return null;
	}
	@Override
	public List<T> getAll() {
		return null;
	}
	
	private void createConnection() {
		File file = new File ("db/test.db");
	    ds = null;

		if(!file.exists()) //check DB existence
		{
			try {
	            ds = new SQLiteDataSource();
	            ds.setUrl("jdbc:sqlite:db/test.db");
			} catch ( Exception e ) {
	            e.printStackTrace();
	            System.exit(0);
	        }
			System.out.println("DB created");
			try {
				_conn = ds.getConnection();
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }
			System.out.println("\nConnection established");
		}
	}
	
}
