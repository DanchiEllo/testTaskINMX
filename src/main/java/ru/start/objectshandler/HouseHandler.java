package ru.start.objectshandler;

import ru.start.entity.House;
import ru.start.objectsstatistic.HouseStatistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HouseHandler {
    public HouseHandler() {
    }

    /**
     *
     * @param houses Список объектов(House)
     * @return Дубликаты, Города с подсчитанным количеством домов по этажам
     */
    public HouseStatistic processHouses(List<House> houses) {
        Map<House, Integer> houseCounts = new HashMap<>();
        Map<String, Map<Short, Long>> houseCountByCityAndFloor;

        for (House house : houses) {
            houseCounts.put(house, houseCounts.getOrDefault(house, 0) + 1);
        }

        Map<House, Integer> duplicateHouseCounts = new HashMap<>();

        for (Map.Entry<House, Integer> entry : houseCounts.entrySet()) {
            if (entry.getValue() > 1) {
                duplicateHouseCounts.put(entry.getKey(), entry.getValue());
            }
        }
        houseCounts.clear();

        //Подсчёт этажей происходит без дубликатов!
        houseCountByCityAndFloor = houses.stream()
                .distinct()
                .collect(Collectors.groupingBy(
                        House::getCity,
                        Collectors.groupingBy(
                                House::getFloor,
                                Collectors.counting()
                        )
                ));

        return new HouseStatistic(duplicateHouseCounts, houseCountByCityAndFloor);
    }


}
