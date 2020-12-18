package com.omdelafuente.adventofcode;

import java.util.List;

public abstract class Puzzle {
    
    private List<String> input;

    public Puzzle(List<String> input) {
        this.input = input;
    }

    public abstract Integer getDay();

    public abstract String solvePart1();

    public abstract String solvePart2();

    public List<String> getInput() {
        return input;
    }

}
