package ru.start;

import ru.start.entity.House;
import ru.start.filehandlers.CSVHandler;
import ru.start.filehandlers.XMLHandler;
import ru.start.objectshandler.HouseHandler;
import ru.start.objectsstatistic.HouseStatistic;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;




public class Main {

    private static final Scanner in = new Scanner(System.in);

    private static final int PERCENT_TO_XML = 224;
    private static final int PERCENT_TO_CSV = 416;

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Ввести путь к файлу");
            System.out.println("0 - Выход");

            int choice;
            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода. Пожалуйста, введите корректное значение.");
                in.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> getFilePath();
                case 0 -> System.exit(0);
                default -> System.out.println("Ошибка ввода. Пожалуйста, введите корректное значение.");
            }
        }
    }

    public static void getFilePath() {
        System.out.println("Введите путь к файлу (пример: H:/Directory/filename.xml)");
        Scanner in = new Scanner(System.in);

        String filePath = in.nextLine();
        File file = new File(filePath);

        //Проверка на вес файла, чтобы не вызвать исключение OutOfMemory при обработке
        //~224% нужно больше памяти для обработки файла XML от его веса
        //~416% нужно больше памяти для обработки файла CSV от его веса
        try {
            if (file.exists()) {
                if (filePath.split("\\.")[1].equals("xml")) {
                    if (((file.length() * PERCENT_TO_XML) / 100) > ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax()) {
                        System.out.println("Файл слишком большой");
                        return;
                    }
                    XMLHandler xmlHandler = new XMLHandler();
                    HouseHandler houseHandler = new HouseHandler();
                    List<House> houses = xmlHandler.readFile(file);
                    if (houses.isEmpty()) {
                        return;
                    }
                    HouseStatistic houseStatistic = houseHandler.processHouses(houses);
                    System.out.println(houseStatistic.toString());

                    xmlHandler.clearData();

                } else if (filePath.split("\\.")[1].equals("csv")) {
                    if (((file.length() * PERCENT_TO_CSV) / 100) > ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax()) {
                        System.out.println("Файл слишком большой");
                        return;
                    }
                    CSVHandler csvHandler = new CSVHandler();
                    HouseHandler houseHandler = new HouseHandler();
                    List<House> houses = csvHandler.readFile(file);
                    if (houses.isEmpty()) {
                        return;
                    }
                    HouseStatistic houseStatistic = houseHandler.processHouses(houses);
                    System.out.println(houseStatistic.toString());

                    csvHandler.clearData();


                } else System.out.println("Файл такого расширения не соответствует требованиям.");
            } else {
                System.out.println("Файла не существует.");
            }
        } catch (OutOfMemoryError ex) {
            System.out.println("Файл слишком большой.");
        }

    }
}




