import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.start.entity.House;
import ru.start.filehandlers.CSVHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestCSV implements TestInterface{

//"city";"street";"house";"floor"
//            "Барнаул";"Дальняя улица";56;2
//            "Братск";"Большая Октябрьская улица";65;5
//            "Балаково";"Барыши, местечко";67;2

    @Test
    @Override
    public void testDataCorrect() {
        List<House> housesActual = new ArrayList<>();
        housesActual.add(new House("Барнаул","Дальняя улица", (short) 56, (short) 2));
        housesActual.add(new House("Братск","Большая Октябрьская улица", (short) 65, (short) 5));
        housesActual.add(new House("Балаково","Барыши, местечко", (short) 67, (short) 2));


        CSVHandler csvHandler = new CSVHandler();
        List<House> housesExpected = csvHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.csv"));

        Assertions.assertIterableEquals(housesExpected, housesActual);

    }

    //"city";"street";"house";"floor"
//            "Барнаул";"Дальняя улица";56;2
//            "Братск";"Большая Октябрьская улица";65;
//            "Балаково";"Барыши, местечко";67;2
    @Test
    @Override
    public void stabilityTestMissingAttributeValue() {
        List<House> housesActual = new ArrayList<>();
        housesActual.add(new House("Барнаул","Дальняя улица", (short) 56, (short) 2));
        housesActual.add(new House("Балаково","Барыши, местечко", (short) 67, (short) 2));

        CSVHandler csvHandler = new CSVHandler();
        List<House> housesExpected = csvHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.csv"));

        Assertions.assertIterableEquals(housesExpected, housesActual);

    }

    //"city";"street";"house";"floor"
//            "Барнаул";"Дальняя улица";56;2
//            "Братск";"Большая Октябрьская улица";65;5
//            "Балаково";"Барыши, местечко";67;"spring"
    @Test
    @Override
    public void stabilityTestInvalidAttributeValue() {
        List<House> housesActual = new ArrayList<>();
        housesActual.add(new House("Барнаул","Дальняя улица", (short) 56, (short) 2));
        housesActual.add(new House("Братск","Большая Октябрьская улица", (short) 65, (short) 5));

        CSVHandler csvHandler = new CSVHandler();
        List<House> housesExpected = csvHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.csv"));

        Assertions.assertIterableEquals(housesExpected, housesActual);

    }

    @Override
    public void stabilityTestMissingQuotationMark() {

    }

    @Override
    public void stabilityTestEncodingError() {

    }

    //"city";"street";"ho;"floor"
//            "Барнаул";"Дальняя улица";56;2
//            "Братск";"Большая Октябрьская улица";65;5
//            "Балаково";"Барыши, местечко";67;2
    @Test
    @Override
    public void stabilityTestHeaderError() {
        CSVHandler csvHandler = new CSVHandler();
        List<House> houses = csvHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.csv"));

        Assertions.assertTrue(houses.isEmpty());
    }

}
