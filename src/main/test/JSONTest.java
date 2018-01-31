
import java.sql.*;

public class JSONTest {

    private static final String URL = "jdbc:mysql://192.168.30.229:3306/tom?useUnicode=true&characterEncoding=UTF-8&useSSL=false";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123456";

    private Connection openDBConnection() {
        try {
            // Load driver and link to driver manager
            Class.forName("org.mariadb.jdbc.MySQLDataSource");
            // Create a connection to the specified connection
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception{
        ResultSet rs = null;
        Connection connection = null;
        Statement stmt = null;

        try {
            JSONTest jsonTest = new JSONTest();
            connection = jsonTest.openDBConnection();
            if (connection == null)
                throw new RuntimeException("Connection creation failed");

            stmt = connection.createStatement();

//            String json = "{\"k1\": null, \"k2\": true}";
//            String queryString = "INSERT INTO test1 VALUES (104 , null, null, null, null, null, null, '"+ json +"')";
//
//            boolean ret = stmt.execute(queryString);
//            System.out.println(ret);

            String queryString = "SELECT t1.meta_json FROM test_json t1 where t1.id=1";
            rs = stmt.executeQuery(queryString);

            while(rs.next()) {
                Object object = rs.getObject(1);
                System.out.println(object);
            }

        } catch (Exception e){
            e.printStackTrace();
        } finally{
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }
    }

}
