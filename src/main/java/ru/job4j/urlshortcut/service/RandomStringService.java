package ru.job4j.urlshortcut.service;

import net.bytebuddy.utility.RandomString;

public class RandomStringService {
    public static String genRandom() {
        return new RandomString(10).nextString();
    }
}
