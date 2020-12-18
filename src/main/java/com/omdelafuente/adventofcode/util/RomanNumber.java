package com.omdelafuente.adventofcode.util;

import java.util.TreeMap;

public class RomanNumber {

    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static String toRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number - l);
    }

    public static Integer fromRoman(String roman) {
        if (roman.isBlank()) return 0;

        return map.entrySet().stream()
                .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                .filter(entry -> roman.startsWith(entry.getValue()))
                .findAny()
                .map(entry -> entry.getKey() + fromRoman(roman.substring(entry.getValue().length(), roman.length())))
                .orElseThrow(IllegalArgumentException::new);
    }

    public static void main(String args[]) {
        System.out.println(RomanNumber.fromRoman(RomanNumber.toRoman(122043)));
    }

}
