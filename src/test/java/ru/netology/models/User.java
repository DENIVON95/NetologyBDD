package ru.netology.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String login;
    private String password;
    private String verificationCode;
    private List<Card> cards;
}
