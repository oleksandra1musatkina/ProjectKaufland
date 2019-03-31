package sk.itsovy.projectKaufland.main;

import sk.itsovy.projectKaufland.exception.BillException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws BillException, ParserConfigurationException, TransformerException, SQLException, IOException {
        Application application = new Application();
        application.example();
        Internet internet = new Internet();
        try {
            internet.getUSDRate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
