package com.omdelafuente.adventofcode.days.iii;

import com.omdelafuente.adventofcode.Puzzle;

import java.util.List;

public class TobogganTrajectory extends Puzzle {

    public TobogganTrajectory(List<String> input) {
        super(input);
    }

    @Override
    public Integer getDay() {
        return 3;
    }

    @Override
    public String solvePart1() {
        final char[][] grid = getInput().stream().map(String::toCharArray).toArray(char[][]::new);

        return String.valueOf(trees(grid, 3, 1));
    }

    @Override
    public String solvePart2() {
        final char[][] grid = getInput().stream().map(String::toCharArray).toArray(char[][]::new);

        return String.valueOf(trees(grid, 1, 1) * trees(grid, 3, 1) * trees(grid, 5, 1) * trees(grid, 7, 1) * trees(grid, 1, 2));
    }

    private int trees(char[][] grid, int x, int y) {
        int length = grid[0].length;

        int count = 0;
        for (int i = 0; i * y < grid.length; i++) {
            if (grid[i * y][i * x % length] == '#') {
                count++;
            }
        }
        return count;
    }

}
