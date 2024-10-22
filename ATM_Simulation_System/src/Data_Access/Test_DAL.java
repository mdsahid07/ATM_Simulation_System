package Data_Access;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class Test_DAL implements Dao {


    public Test_DAL() {}

    @Override
    public String getSql() {
        return "SELECT * from User";
    }
    @Override
    public void unpackResultSet(ResultSet rs) throws SQLException {
        while(rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
//            allAddresses.add(new Address(rs.getString("street"),
//                    rs.getString("city"),
//                    rs.getString("state"),
//                    rs.getString("zip")));
        }
    }
    @Override
    public List<?> getResults() {
//        return allAddresses;
        return null;
    }
    public static void main(String[] args) {
        Test_DAL dal = new Test_DAL();

        DataAccess da = DataAccessFactory.getDataAccess();
        try {
            da.read(dal);
            System.out.println(dal.getResults());
            //display addresses
        } catch(SQLException e) {
            //handle
        }

    }
}
