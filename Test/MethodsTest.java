import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

class MethodsTest {

    Methods methods = new Methods();

    @Test
    void testCompareDate() {
        String testValidMember = LocalDate.now().minusMonths(5).toString();
        String testInvalidMember = LocalDate.now().minusMonths(15).toString();

        assert(methods.compareDate(testValidMember));
        assert(!methods.compareDate(testInvalidMember));
    }
    @Test
    void testArrayMaker() {
        String testLine = "5306087415, Björn Skifs";

        assert(!methods.arrayMaker(testLine)[0].equals("11111111111"));
        assert(methods.arrayMaker(testLine)[0].equals("5306087415"));
        assert (!methods.arrayMaker(testLine)[1].equals("Lasse Berghagen"));
        assert (methods.arrayMaker(testLine)[1].equals("Björn Skifs"));
        assert (methods.arrayMaker(testLine)[0].length() == 10);
        assert (methods.arrayMaker(testLine)[1].length() == 11);
    }
    @Test
    void testWriter() throws IOException {
        String testRead = "Test";
        methods.writeToFile(testRead,"src/testGymVisitors.txt");
        Path testFile = Paths.get("src/testGymVisitors.txt");
        Scanner reader = new Scanner(testFile);
        String readFirstTestLine = reader.nextLine();

        assert (!readFirstTestLine.equals("Pannkaka"));
        assert (readFirstTestLine.equals("Test"));
    }

    @Test
    void testCustomerFileIteration () {
        String testMatchingMember = "greger ganache";
        String testMatchingNonMember = "bear belle";
        String testNonMatching = "monica zetterlund";
        Path customerInfo = Paths.get("src/customers.txt");

        assert(methods.customerFileIteration(testMatchingMember,customerInfo).equals("Personen du angav är en aktiv medlem, välkommen in och träna."));
        assert(!methods.customerFileIteration(testMatchingMember,customerInfo).equals("Pannkaka"));
        assert(methods.customerFileIteration(testMatchingNonMember, customerInfo).equals("Personen du angav har varit medlem men har inte betalat sin årsavgift."));
        assert(!methods.customerFileIteration(testMatchingNonMember, customerInfo).equals("Lasagne"));
        assert(methods.customerFileIteration(testNonMatching, customerInfo).equals("Personen du angav är inte medlem."));
        assert(!methods.customerFileIteration(testNonMatching, customerInfo).equals("Grönsakssoppa"));
    }

}