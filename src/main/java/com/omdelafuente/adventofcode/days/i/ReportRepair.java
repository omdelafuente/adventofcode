package com.omdelafuente.adventofcode.days.i;

import com.omdelafuente.adventofcode.Puzzle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ReportRepair extends Puzzle {

    public ReportRepair(List<String> input) {
        super(input);
    }

    @Override
    public Integer getDay() {
        return 1;
    }

    @Override
    public String solvePart1() {
        final List<String> input = this.getInput();
        for (int i = 0; i < input.size(); i++) {
            for (int j = i; j < input.size(); j++) {
                final Integer n1 = Integer.valueOf(input.get(i));
                final Integer n2 = Integer.valueOf(input.get(j));
                if (n1 + n2 == 2020) {
                    return String.valueOf(n1 * n2);
                }
            }
        }
        throw new IllegalStateException("Part 1 didn't solve");
    }

    @Override
    public String solvePart2() {
        return null;
    }

}
