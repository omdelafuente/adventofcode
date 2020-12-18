package com.omdelafuente.adventofcode.days.v;

import com.omdelafuente.adventofcode.Puzzle;

import java.util.List;
import java.util.stream.Collectors;

public class BinaryBoarding extends Puzzle {

    public BinaryBoarding(List<String> input) {
        super(input);
    }

    @Override
    public Integer getDay() {
        return 5;
    }

    @Override
    public String solvePart1() {
        final List<String> input = getInput();
        return input.stream().map(this::getSeat).max(Integer::compareTo).map(Object::toString).orElseThrow();
    }

    @Override
    public String solvePart2() {
        final List<Integer> seats = getInput().stream().map(this::getSeat).sorted(Integer::compareTo).collect(Collectors.toList());

        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i) + 1 != seats.get(i + 1)) {
                return String.valueOf(seats.get(i) + 1);
            }
        }
        throw new IllegalStateException();
    }

    private int getSeat(String in) {
        int lowerRow = 0;
        int upperRow = 128;
        int lowerColumn = 0;
        int upperColumn = 8;
        for (char c : in.toCharArray()) {
            if (c == 'F') {
                upperRow -= (upperRow - lowerRow) / 2;
            } else if (c == 'B') {
                lowerRow += (upperRow - lowerRow) / 2;
            } else if (c == 'L') {
                upperColumn -= (upperColumn - lowerColumn) / 2;
            } else if (c == 'R') {
                lowerColumn += (upperColumn - lowerColumn) / 2;
            }
        }
        return (upperRow - 1) * 8 + (upperColumn - 1);
    }

}
