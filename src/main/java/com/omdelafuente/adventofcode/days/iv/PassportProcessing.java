package com.omdelafuente.adventofcode.days.iv;

import com.omdelafuente.adventofcode.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class PassportProcessing extends Puzzle {

    private static final Map<String, Predicate<String>> valid = Map.of(
            "byr", Pattern.compile("^(19[2-9][0-9]|200[0-2])$").asMatchPredicate(),
            "iyr", Pattern.compile("^(201[0-9]|2020)$").asMatchPredicate(),
            "eyr", Pattern.compile("^(202[0-9]|2030)$").asMatchPredicate(),
            "hgt", Pattern.compile("^((1([5-8][0-9]|9[0-3])cm)|((59|6[0-9]|7[0-6])in))$").asMatchPredicate(),
            "hcl", Pattern.compile("^(#[0-9a-f]{6})$").asMatchPredicate(),
            "ecl", Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$").asMatchPredicate(),
            "pid", Pattern.compile("^([0-9]{9})$").asMatchPredicate()
    );

    public PassportProcessing(List<String> input) {
        super(input);
    }

    @Override
    public Integer getDay() {
        return 4;
    }

    @Override
    public String solvePart1() {
        return String.valueOf(validPassports(this::isPresent));
    }

    @Override
    public String solvePart2() {
        return String.valueOf(validPassports(this::isValid));
    }

    private boolean isPresent(String passport) {
        return valid.keySet().stream().allMatch(passport::contains);
    }

    private boolean isValid(String passport) {
        return isPresent(passport) && Arrays.stream(passport.split(" ")).map(s -> s.split(":")).allMatch(field -> {
            if (!valid.containsKey(field[0])) return true;
            return valid.get(field[0]).test(field[1]);
        });
    }

    private long validPassports(Predicate<String> predicate) {
        return getPassports().stream().filter(predicate).count();
    }

    private List<String> getPassports() {
        final List<String> input = getInput();

        List<String> passports = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : input) {
            if (s.isBlank()) {
                passports.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                continue;
            }
            stringBuilder.append(s).append(" ");
        }
        passports.add(stringBuilder.toString());

        return passports;
    }

}
