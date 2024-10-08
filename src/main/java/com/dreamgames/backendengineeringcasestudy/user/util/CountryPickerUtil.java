package com.dreamgames.backendengineeringcasestudy.user.util;

import com.dreamgames.backendengineeringcasestudy.user.enums.EnumCountry;

import java.util.Random;

public class CountryPickerUtil {
    private static final Random random = new Random();

    public static EnumCountry getRandomCountry() {
        int numCountries = EnumCountry.values().length;
        EnumCountry[] countries = EnumCountry.values();
        int randomIndex = random.nextInt(numCountries);

        return countries[randomIndex];
    }

}
