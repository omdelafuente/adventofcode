package com.omdelafuente.adventofcode.days.vi;

import com.omdelafuente.adventofcode.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomCustoms extends Puzzle {

    public CustomCustoms(List<String> input) {
        super(input);
    }

    @Override
    public Integer getDay() {
        return 6;
    }

    @Override
    public String solvePart1() {
        List<String> answers = getAnswers();

        return String.valueOf(answers.stream()
                .mapToLong(s -> s.replace(" ", "").chars().mapToObj(c -> (char) c).distinct().count())
                .sum());
    }

    @Override
    public String solvePart2() {
        final List<String> answers = getAnswers();

        return String.valueOf(answers.stream()
                .mapToLong(s -> {
                    final List<String> individuals = Arrays.stream(s.split(" ")).collect(Collectors.toList());
                    final List<Character> base = individuals.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());

                    for (String individual : individuals) {
                        base.retainAll(individual.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
                    }
                    return base.size();
                }).sum());
    }

    private List<String> getAnswers() {
        final List<String> input = getInput();

        List<String> questions = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : input) {
            if (s.isBlank()) {
                questions.add(stringBuilder.toString());
                stringBuilder.setLength(0);
                continue;
            }
            stringBuilder.append(s).append(" ");
        }
        questions.add(stringBuilder.toString());
        return questions;
    }

}
