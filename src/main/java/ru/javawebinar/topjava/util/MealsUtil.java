package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {
    public static void main(String[] args) {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExcess(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    private static List<MealTo> getFilteredWithExcess(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return mealList.stream()
                .filter(meal -> isBetween(meal.getDateTime(), startTime, endTime))
                .map(meal -> new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        (isExceed(meal.getDateTime(), mealList, caloriesPerDay))) {

                })
                .collect(Collectors.toList());
    }

    private static boolean isExceed(LocalDateTime dateTime, List<Meal> mealList, int caloriesPerDay) {
        Map<Integer, Integer> dayPerCalloriesMap = mealList.stream()
                .filter(meal -> dateTime.getDayOfMonth() == meal.getDateTime().getDayOfMonth())
                .collect(Collectors.groupingBy(meal -> meal.getDateTime().getDayOfMonth(), Collectors.summingInt(Meal::getCalories)));
        return dayPerCalloriesMap.get(dateTime.getDayOfMonth()) > caloriesPerDay;
    }

    private static boolean isBetween(LocalDateTime mealTime, LocalTime startTime, LocalTime endTime) {
        return mealTime.toLocalTime().isAfter(startTime) && mealTime.toLocalTime().isBefore(endTime);
    }
}