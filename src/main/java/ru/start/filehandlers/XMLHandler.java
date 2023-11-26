package ru.start.filehandlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.start.entity.House;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler implements FileHandler {
    private final List<House> houses = new ArrayList<>();

    public XMLHandler() {
    }

    @Override
    public void clearData() {
        houses.clear();
    }


    @Override
    public List<House> readFile(File file) {

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, this);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Произошла ошибка при обработке файла: " + e.getMessage());
        }

        return houses;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("item")) {
            String city = attributes.getValue("city");
            String street = attributes.getValue("street");
            String houseStr = attributes.getValue("house");
            String floorStr = attributes.getValue("floor");

            if (city == null || street == null || houseStr == null || floorStr == null) {
                System.out.println("Отсутствующее значение атрибута для тега 'item'");
                return;
            }

            short house;
            short floor;
            try {
                house = Short.parseShort(houseStr);
                floor = Short.parseShort(floorStr);
            } catch (NumberFormatException e) {
                System.out.println("Недопустимое значение атрибута для тега 'item'");
                return;
            }

            houses.add(new House(city, street, house, floor));

        }
    }
}