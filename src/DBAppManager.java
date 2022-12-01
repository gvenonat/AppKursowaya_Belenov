import java.sql.*;

public class DBAppManager{
    private static DBAppManager instance;
//    DataSource dataSource;
    final String user = "postgres";
    final String password = "12345";
    final String url = "jdbc:postgresql://localhost:5432/AIS_UPTP";
    private DBAppManager() throws SQLException {
    }

    public static DBAppManager getInstance() {
        if (instance == null) {
            try {
                instance = new DBAppManager();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

//    protected final <T> T doInConnectionBlock(Object o, String sql, String... columnNames) {
//        Statement statement = (Statement) o;
//        try (Connection connection =  DriverManager.getConnection(url, user, password)){
//            PreparedStatement preparedStatement = columnNames.length > 0 ? connection.prepareStatement(sql, columnNames) : connection.prepareStatement(sql);
//            return (T) statement.executeQuery(preparedStatement.toString());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

        public ResultSet getTariffsData() throws SQLException {
        String sql = "SELECT * FROM TARIFFS";

        try (Connection connection =  DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            final ResultSet resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

/*    public long saveSaleData(Integer id_discount,
                             Integer id_tariffs) throws SQLException {
        String sql = ""

        try (Connection connection =  DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = null;
            statement.setInt(1, id_discount);
            statement.setInt(2, id_tariffs);
            PreparedStatement preparedStatement = columnNames.length > 0 ? connection.prepareStatement(sql, columnNames) : connection.prepareStatement(sql);
            final ResultSet resultSet = statement.executeQuery();
            return ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
