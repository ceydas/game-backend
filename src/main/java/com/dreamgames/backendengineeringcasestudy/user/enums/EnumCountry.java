package com.dreamgames.backendengineeringcasestudy.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum EnumCountry {
    TURKEY("Turkey", "TR"),
    UNITED_STATES("United States", "US"),
    UNITED_KINGDOM("United Kingdom", "GB"),
    FRANCE("France", "FR"),
    GERMANY("Germany", "DE");

    private final String name;
    private final String code;

    EnumCountry(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
    @Override
    public String toString() {
        return this.name;
    }

    // Static method to get a country by name
    public static EnumCountry getByName(String name) {
        for (EnumCountry country : EnumCountry.values()) {
            if (country.name.equalsIgnoreCase(name)) {
                return country;
            }
        }
        throw new IllegalArgumentException("No country found with name: " + name);
    }

    @JsonCreator
    public static EnumCountry fromString(String key) {
        return key == null ? null : EnumCountry.valueOf(key.toUpperCase());
    }
}

