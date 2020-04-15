package ru.netology.model;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {}
    public static class Registration {
        private Registration() {}
        public static TestUser generateByFaker(String locale) {
            Faker faker = new Faker(new Locale(locale));
            String[] cities = {"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Элиста",
                    "Черкесск", "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск",
                    "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита",
                    "Петропавловск-Камчатский", "Краснодар", "Красноярск", "Пермь", "Владивосток", "Ставрополь",
                    "Хабаровск", "Благовещенск"};
            Random rand = new Random();

            return new TestUser(faker.name().name(),
                    faker.phoneNumber().phoneNumber(),
                    cities[rand.nextInt(cities.length)]
            );
        }
    }
}
