import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

class MethodsTest {
    @Test
    void testCustomerFileIterationInput() {
        String input = "ted gärdestad";
        String testReadingFromFile = "93485629205, Ted Gärdestad";
        String[] firstLineArray = testReadingFromFile.split(", ");

        assert (!input.equalsIgnoreCase(firstLineArray[0]));
        assert (input.equalsIgnoreCase(firstLineArray[1]));
    }
    @Test
    void testCompareDate() {
        String testDate = "2021-10-20";
        Period timeCompare = Period.between(LocalDate.parse(testDate), LocalDate.now());

        assert(timeCompare.toTotalMonths() != 5);
        assert(timeCompare.toTotalMonths() == 12);
        assert(timeCompare.getYears() != 10);
        assert(timeCompare.getYears() == 1);
    }
    @Test
    void testScannerAndArraySplit() throws IOException {
        Path customerInfo = Paths.get("src/customers.txt");
        Scanner reader = new Scanner(customerInfo);
        String readFirstLine = reader.nextLine();
        String[] firstLineArray = readFirstLine.split(", ");

        assert (!readFirstLine.equals("8959203030, Jocke Berg"));
        assert (readFirstLine.equals("7703021234, Alhambra Aromes"));
        assert (!firstLineArray[0].equals("735728943"));
        assert (firstLineArray[0].equals("7703021234"));
        assert (!firstLineArray[1].equals("Björn Skifs"));
        assert (firstLineArray[1].equals("Alhambra Aromes"));
    }
    @Test
    void testWriter() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/testGymVisitors.txt", false));
        writer.write("Test");
        writer.close();
        Path testFile = Paths.get("src/testGymVisitors.txt");
        Scanner reader = new Scanner(testFile);
        String readFirstTestLine = reader.nextLine();

        assert (!readFirstTestLine.equals("Pannkaka"));
        assert (readFirstTestLine.equals("Test"));
    }
}