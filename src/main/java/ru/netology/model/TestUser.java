package ru.netology.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TestUser {
    private final String name;
    private final String phoneNumber;
    private final String city;

}
