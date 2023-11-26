package ru.start.filehandlers;

import ru.start.entity.House;

import java.io.File;
import java.util.List;

public interface FileHandler {
    /**
     * @param file файл, который нужно обработать
     * @return Список объектов(HouseJson)
     */
    List<House> readFile(File file);

    /**
     * Очистить список объектов
     */
    void clearData();

}
