package ru.start.objectsstatistic;

import ru.start.entity.House;

import java.util.Map;

public class HouseStatistic {
    private final Map<House, Integer> houseCounts;

    private final Map<String, Map<Short, Long>> houseCountByCityAndFloor;

    public HouseStatistic(Map<House, Integer> houseCounts, Map<String, Map<Short, Long>> houseCountByCityAndFloor) {
        this.houseCounts = houseCounts;
        this.houseCountByCityAndFloor = houseCountByCityAndFloor;
    }

    /**
     * @return Готовая статистика(String)
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Дубликаты: \n");
        for (Map.Entry<House, Integer> record : houseCounts.entrySet()) {
            stringBuilder
                    .append(record.getKey())
                    .append(", повторяется раз: ")
                    .append(record.getValue())
                    .append("\n");

        }
        stringBuilder.append("\nКол-во домов(1, 2, 3, 4, 5 этажных) в каждом городе: \n");
        for (Map.Entry<String, Map<Short, Long>> record : houseCountByCityAndFloor.entrySet()) {
            stringBuilder
                    .append(record.getKey())
                    .append(": ")
                    .append(record.getValue())
                    .append("\n");
        }

        return  stringBuilder.toString();
    }
}
