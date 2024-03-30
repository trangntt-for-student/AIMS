package isd.aims.main.entity.db;

import java.net.URL;
import java.sql.DriverManager;
import java.util.logging.Logger;
import java.sql.Connection;
import isd.aims.main.utils.Utils;

public class AIMSDB {

	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

    public static Connection getConnection() {
        if (connect != null) return connect;

        try {
			Class.forName("org.sqlite.JDBC");
            String connectionString = "jdbc:sqlite:src/main/resources/isd/aims/main/assets/db/aims.db";
            connect = DriverManager.getConnection(connectionString);
            LOGGER.info("Connect database successfully");
            return connect;
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return connect;
        }
    }


    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}
