import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Path customerInfo = Paths.get("src/customers.txt");

        Methods methods = new Methods();

        methods.customerFileIteration(JOptionPane.showInputDialog(null, "Skriv in ett namn eller personnummer:"), customerInfo);
    }

}
