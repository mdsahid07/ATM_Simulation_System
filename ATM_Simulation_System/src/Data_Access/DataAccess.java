package Data_Access;

import java.sql.SQLException;

public interface DataAccess {
	void read(Dao dao) throws SQLException;
	void write(Dao dao) throws SQLException;
	
}
