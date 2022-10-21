import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;
public class Methods {
    public void customerFileIteration(String input, Path readFile) {
        String readFirstLine;
        String readSecondLine;

        try (Scanner reader = new Scanner(readFile)) {
            while (reader.hasNext()) {
                readFirstLine = reader.nextLine();
                readSecondLine = reader.nextLine();

                String[] firstLineArray = readFirstLine.split(", ");

                if (input == null) {
                    JOptionPane.showMessageDialog(null,"Du avslutade programmet.");
                    return;
                }
                else if (input.trim().equalsIgnoreCase(firstLineArray[0].trim()) || input.trim().equalsIgnoreCase(firstLineArray[1].trim())) {
                    compareDate(readFirstLine,readSecondLine);
                    return;
                }
                else if (input.isBlank()) {
                    JOptionPane.showMessageDialog(null,"Input-raden får ej lämnas tom.");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Personen du angav är inte medlem.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void compareDate(String readFirstLine, String readSecondLine) {
        try {
            Period timeCompare = Period.between(LocalDate.parse(readSecondLine), LocalDate.now());
            if (timeCompare.toTotalMonths() < 12 ){
                JOptionPane.showMessageDialog(null, "Personen du angav är en aktiv medlem och " +
                        "betalade sin årsavgift för " + timeCompare.toTotalMonths() + " månader sen.");
                writeToFile(readFirstLine);
            }
            else if (timeCompare.toTotalMonths() > 12 ) {
                JOptionPane.showMessageDialog(null, "Personen du angav är en vilande medlem och " +
                        "betalade sin senaste årsavgift för " + timeCompare.toTotalMonths() + " månader sen.");
            }
        }
        catch (DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(null, "Datumets format i kund-dokumentet stämmer inte.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeToFile (String readFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/gymVisitors.txt", true))) {
            writer.write("\n" + readFile + ", " + LocalDate.now());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
