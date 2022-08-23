package com.MindHub.homebanking.utilities;

public final class Utility {

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
