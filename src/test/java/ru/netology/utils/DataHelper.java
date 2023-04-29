package ru.netology.utils;

import ru.netology.models.Card;
import ru.netology.models.User;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {
    private DataHelper(){

    }

    public static User getUserWithCards(){
        List<Card> userCards = new ArrayList<>();
        userCards.add(new Card("5559 0000 0000 0001","10 000", 1));
        userCards.add(new Card("5559 0000 0000 0002", "10 000", 2));

        return new User("vasya", "qwerty123", "12345", userCards);
    }
}
