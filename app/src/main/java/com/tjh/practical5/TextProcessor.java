package com.tjh.practical5;

public class TextProcessor {

    public static String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();

        if (trimmed.isEmpty()) {
            return null;
        }

        switch (trimmed.toLowerCase()) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
