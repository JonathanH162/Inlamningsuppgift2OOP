import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;
public class Methods {
    public String customerFileIteration(String input, Path readFile) {

        try (Scanner reader = new Scanner(readFile)) {
            while (reader.hasNext()) {
                String readFirstLine = reader.nextLine();
                String readSecondLine = reader.nextLine();

                if (input == null) {
                    return "Du avslutade programmet.";
                }
                else if (input.trim().equalsIgnoreCase(arrayMaker(readFirstLine)[0].trim()) || input.trim().equalsIgnoreCase(arrayMaker(readFirstLine)[1].trim())) {
                    if(compareDate(readSecondLine)) {
                        writeToFile(readFirstLine,"src/gymVisitors.txt");
                        return "Personen du angav är en aktiv medlem, välkommen in och träna.";
                    }
                    else {
                        return "Personen du angav har varit medlem men har inte betalat sin årsavgift.";
                    }
                }
                else if (input.isBlank()) {
                    return "Input-raden får ej lämnas tom.";
                }
            }
            return "Personen du angav är inte medlem.";
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public boolean compareDate(String readSecondLine) {
        try {
            Period timeCompare = Period.between(LocalDate.parse(readSecondLine), LocalDate.now());
            if (timeCompare.toTotalMonths() < 12 ){
                return true;
            }
            else if (timeCompare.toTotalMonths() > 12 ) {
                return false;
            }
        }
        catch (DateTimeParseException dtpe) {
            JOptionPane.showMessageDialog(null, "Datumets format i kund-dokumentet stämmer inte.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String[] arrayMaker(String readFirstLine) {
        return readFirstLine.split(", ");
    }
    public void writeToFile (String readFile, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("\n" + readFile + ", " + LocalDate.now());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
