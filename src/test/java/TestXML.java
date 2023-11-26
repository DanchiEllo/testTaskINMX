import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.start.entity.House;
import ru.start.filehandlers.XMLHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class TestXML implements TestInterface {
//    <?xml version="1.0" encoding="utf-8"?>
//<root>
//    <item city="Барнаул" street="Дальняя улица" house="56" floor="2" />
//    <item city="Братск" street="Большая Октябрьская улица" house="65" floor="5" />
//    <item city="Балаково" street="Барыши, местечко" house="67" floor="2" />
//</root>

    @Test
    @Override
    public void testDataCorrect() {
        List<House> housesActual = new ArrayList<>();
        housesActual.add(new House("Барнаул","Дальняя улица", (short) 56, (short) 2));
        housesActual.add(new House("Братск","Большая Октябрьская улица", (short) 65, (short) 5));
        housesActual.add(new House("Балаково","Барыши, местечко", (short) 67, (short) 2));

        XMLHandler xmlHandler = new XMLHandler();
        List<House> housesExpected = xmlHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.xml"));

        Assertions.assertIterableEquals(housesExpected, housesActual);

    }

    //    <?xml version="1.0" encoding="utf-8"?>
//<root>
//    <item city="Барнаул" street="Дальняя улица" house="56" floor="2" />
//    <item citsy="Братск" street="Большая Октябрьская улица" house="65" floor="5" />
//    <item city="Балаково" street="Барыши, местечко" house="67" floor="2" />
//</root>
    @Test
    @Override
    public void stabilityTestMissingAttributeValue() {
        List<House> housesActual = new ArrayList<>();
        housesActual.add(new House("Барнаул","Дальняя улица", (short) 56, (short) 2));
        housesActual.add(new House("Балаково","Барыши, местечко", (short) 67, (short) 2));

        XMLHandler xmlHandler = new XMLHandler();
        List<House> housesExpected = xmlHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.xml"));

        Assertions.assertIterableEquals(housesExpected, housesActual);

    }

    //    <?xml version="1.0" encoding="utf-8"?>
//<root>
//    <item city="Барнаул" street="Дальняя улица" house="56" floor="2" />
//    <item city="Братск" street="Большая Октябрьская улица" house="65" floor="5" />
//    <item city="Балаково" street="Барыши, местечко" house="spring" floor="2" />
//</root>
    @Test
    @Override
    public void stabilityTestInvalidAttributeValue() {
        List<House> housesActual = new ArrayList<>();
        housesActual.add(new House("Барнаул","Дальняя улица", (short) 56, (short) 2));
        housesActual.add(new House("Братск","Большая Октябрьская улица", (short) 65, (short) 5));

        XMLHandler xmlHandler = new XMLHandler();
        List<House> housesExpected = xmlHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.xml"));

        Assertions.assertIterableEquals(housesExpected, housesActual);

    }


    //    <?xml version="1.0" encoding="utf-8"?>
//<root>
//    <item city=Барнаул" street="Дальняя улица" house="56" floor="2" />
//    <item city="Братск" street="Большая Октябрьская улица" house="65" floor="5" />
//    <item city="Балаково" street="Барыши, местечко" house="67" floor="2" />
//</root>
    @Test
    @Override
    public void stabilityTestMissingQuotationMark() {
        XMLHandler xmlHandler = new XMLHandler();
        List<House> houses = xmlHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.xml"));

        Assertions.assertTrue(houses.isEmpty());
    }

    //    <?xml version="1.0" encoding="utf-"?>
//<root>
//    <item city="Барнаул" street="Дальняя улица" house="56" floor="2" />
//    <item city="Братск" street="Большая Октябрьская улица" house="65" floor="5" />
//    <item city="Балаково" street="Барыши, местечко" house="67" floor="2" />
//</root>
    @Test
    @Override
    public void stabilityTestEncodingError() {
        XMLHandler xmlHandler = new XMLHandler();
        List<House> houses = xmlHandler.readFile(new File("C:/Users/ANASTASIYA/IdeaProjects/INFOMAX/task/task/src/main/java/ru/start/dataset/test.xml"));

        Assertions.assertTrue(houses.isEmpty());
    }

    @Override
    public void stabilityTestHeaderError() {

    }

}
