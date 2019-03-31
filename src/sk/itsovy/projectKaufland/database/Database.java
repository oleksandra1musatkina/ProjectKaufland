package sk.itsovy.projectKaufland.database;

import sk.itsovy.projectKaufland.bill.Bill;
import sk.itsovy.projectKaufland.items.Item;
import sk.itsovy.projectKaufland.items.PiecesInterface;
import sk.itsovy.projectKaufland.items.drink.DraftInterface;
import sk.itsovy.projectKaufland.items.food.Fruit;
import sk.itsovy.projectKaufland.main.Globals;

import java.sql.*;

public class Database {

    private static Database database = new Database();

    private Database() {

    }

    public static Database getInstance() {
        return database;
    }

    private Connection getConnection() {
        Connection connection;
        try {
            Class.forName(Globals.MYSQL_JDBC_DRIVER);
            System.out.println("Driver loaded!");
            connection = DriverManager.getConnection(Globals.URL, Globals.USERNAME, Globals.PASSWORD);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewBill(Bill bill) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement sqlPreparedStatement = null;
        PreparedStatement secondSqlStatement = null;
        try {
            connection.setAutoCommit(false);

            sqlPreparedStatement = connection.prepareStatement("INSERT INTO Bill (TotalPrice, Date) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            sqlPreparedStatement.setDouble(1, bill.getFinalPrice());
            java.text.SimpleDateFormat simpleDateFormat =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = simpleDateFormat.format(bill.getDatetime());
//            sqlPreparedStatement.setDate(2, new java.sql.Date(bill.getDatetime().getTime()));
            sqlPreparedStatement.setString(2, currentTime);

            sqlPreparedStatement.execute();

            int key = 0;
            ResultSet rs = sqlPreparedStatement.getGeneratedKeys();
            if (rs.next()) {
                key = rs.getInt(1);
            }
            if (key == 0) {
                throw new SQLException("wrong id");
            }

            for (Item item : bill.getItems()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO BillItems (BillID, Name,Price, Count, Unit) VALUES(?,?,?,?,?)");
                statement.setLong(1, key);
                statement.setString(2, item.getName());
                statement.setDouble(3, item.getPrice());
                if (item instanceof PiecesInterface) {
                    statement.setDouble(4, ((PiecesInterface) item).getAmount());
                    statement.setString(5, "pcs");
                } else if (item instanceof Fruit) {
                    statement.setDouble(4, ((Fruit) item).getWeight());
                    statement.setString(5, "g");
                } else if (item instanceof DraftInterface) {
                    statement.setDouble(4, ((DraftInterface) item).getVolume());
                    statement.setString(5, "l");
                }
                statement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            if (sqlPreparedStatement != null) {
                sqlPreparedStatement.close();
            }
            if (secondSqlStatement != null) {
                secondSqlStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}
