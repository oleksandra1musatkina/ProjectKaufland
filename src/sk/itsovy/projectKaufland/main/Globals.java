package sk.itsovy.projectKaufland.main;

public class Globals {
    public static final int MAXITEMS;

    static {
        MAXITEMS = 7;
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public static final String USERNAME = "oleksandra";
    public static final String PASSWORD = "sasa";
    public static final String URL = "jdbc:mysql://localhost:3306/kaufland?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";

}
