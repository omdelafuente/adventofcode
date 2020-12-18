package com.omdelafuente.adventofcode;

import com.omdelafuente.adventofcode.util.RomanNumber;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {

    static final String PUZZLE_BASE_INPUT_PATH = "input/days/";

    public static void main(String args[]) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Reflections reflections = new Reflections("com.omdelafuente.adventofcode.days");
        final Set<Class<? extends Puzzle>> puzzleClasses = reflections.getSubTypesOf(Puzzle.class);

        Set<Puzzle> puzzles = new HashSet<>();

        for (Class<? extends Puzzle> puzzleClass : puzzleClasses) {
            final String absolutePackageName = puzzleClass.getPackage().getName();
            final String packageName = absolutePackageName.contains(".") ? absolutePackageName.substring(1 + absolutePackageName.lastIndexOf(".")) : absolutePackageName;
            
            final String fileName = PUZZLE_BASE_INPUT_PATH + RomanNumber.fromRoman(packageName.toUpperCase());
            
            final InputStream resource = Main.class.getClassLoader().getResourceAsStream(fileName);
            final List<String> input = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

            puzzles.add(puzzleClass.getDeclaredConstructor(List.class).newInstance(input));
        }

        puzzles.stream()
                .sorted(Comparator.comparing(Puzzle::getDay))
                .forEachOrdered(puzzle -> {
                    System.out.println("Results of day " + puzzle.getDay() + ":");

                    long start = System.nanoTime();
                    try {
                        System.out.println("Results for part 1:" + puzzle.solvePart1() + " in " + elapsedMillis(start) + " millis");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    start = System.nanoTime();
                    try {
                        System.out.println("Results for part 2:" + puzzle.solvePart2() + " in " + elapsedMillis(start) + " millis");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println();
                });
    }

    private static long elapsedMillis(long startNano) {
        return TimeUnit.MILLISECONDS.convert(System.nanoTime() - startNano, TimeUnit.MILLISECONDS);
    }

}
