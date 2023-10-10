package application.database;

import java.sql.Connection;
import java.sql.DriverManager;
public class Database {

    // create connection
    public static Connection getConnection() {
        Connection con = null;
        try {
            con = (Connection) DriverManager.getConnection(Config.URL , Config.username , Config.password);
            if (con != null) {
                System.out.println("Connect success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void Disconnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
