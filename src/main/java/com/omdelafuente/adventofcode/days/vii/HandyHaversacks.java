package com.omdelafuente.adventofcode.days.vii;

import com.omdelafuente.adventofcode.Puzzle;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Pattern;

public class HandyHaversacks extends Puzzle {

    private static final Pattern HOLDER_PATTERN = Pattern.compile("^([a-z]+\\s[a-z]+)\\sbags contain");
    private static final Pattern BAG_PATTERN = Pattern.compile("([0-9])\\s([a-z]+\\s[a-z]+)\\sbag");

    private Map<String, Bag> bags;

    public HandyHaversacks(List<String> input) {
        super(input);
        this.bags = new HashMap<>();
    }

    @Override
    public Integer getDay() {
        return 7;
    }

    @Override
    public String solvePart1() {
        getInput().forEach(input -> {
            final String type = HOLDER_PATTERN.matcher(input).results().findFirst().orElseThrow().group(1);

            final Bag parent = bags.computeIfAbsent(type, t -> new Bag());

            BAG_PATTERN.matcher(input).results()
                    .forEach(result -> {
                        final Bag bag = bags.computeIfAbsent(result.group(2), bt -> new Bag());
                        parent.children.put(bag, Integer.parseInt(result.group(1)));
                        bag.parents.add(parent);
                    });
        });

        final Queue<Bag> parents = new ArrayDeque<>(bags.get("shiny gold").parents);
        final Set<Bag> counted = new HashSet<>();
        while (!parents.isEmpty()) {
            final Bag bag = parents.poll();
            if (!counted.add(bag)) {
                continue;
            }
            parents.addAll(bag.parents);
        }

        return String.valueOf(counted.size());
    }

    @Override
    public String solvePart2() {
        getInput().forEach(input -> {
            final String type = HOLDER_PATTERN.matcher(input).results().findFirst().orElseThrow().group(1);

            final Bag parent = bags.computeIfAbsent(type, t -> new Bag());

            BAG_PATTERN.matcher(input).results()
                    .forEach(result -> {
                        final Bag bag = bags.computeIfAbsent(result.group(2), bt -> new Bag());
                        parent.children.put(bag, Integer.parseInt(result.group(1)));
                        bag.parents.add(parent);
                    });
        });

        return String.valueOf(bags.get("shiny gold").innerBags());
    }

    public static class Bag {

        private Set<Bag> parents;
        private Map<Bag, Integer> children;

        public Bag() {
            this.parents = new HashSet<>();
            this.children = new HashMap<>();
        }

        public int innerBags() {
            return children.entrySet().stream().mapToInt(b -> b.getValue() * (b.getKey().innerBags() + 1)).sum();
        }

    }

}
