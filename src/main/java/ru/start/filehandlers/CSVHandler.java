package ru.start.filehandlers;

import ru.start.entity.House;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//При условии, что шаблон будет определён первой строчкой, как в примере
public class CSVHandler implements FileHandler {
    private final List<House> houses = new ArrayList<>();

    public CSVHandler() {
    }

    @Override
    public void clearData() {
        houses.clear();
    }

    @Override
    public List<House> readFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String headerLine = bufferedReader.readLine();

            if (!headerLine.equals("\"city\";\"street\";\"house\";\"floor\"")) {
                System.out.println("Ошибка заголовка");
                return houses;
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(";");

                try {
                    House house = new House(
                            values[0].replaceAll("\"", ""),
                            values[1].replaceAll("\"", ""),
                            Short.parseShort(values[2]),
                            Short.parseShort(values[3])
                    );

                    houses.add(house);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Отсутствующее значение атрибута");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("Недопустимое значение атрибута");
                    continue;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return houses;
    }
}
