package com.omdelafuente.adventofcode.days.i;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ReportRepairTest {

    private ReportRepair sut;

    @Before
    public void setUp() {
        final String fileName = "input/days/1";

        final InputStream resource = getClass().getClassLoader().getResourceAsStream(fileName);
        final List<String> input = new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines().collect(Collectors.toList());

        sut = new ReportRepair(input);
    }

    @Test
    public void shouldSolvePart1() throws IOException, URISyntaxException {
        final String result = sut.solvePart1();

        assertThat(result).isEqualTo("514579");
    }

    @Test
    public void shouldSolvePart2() throws IOException, URISyntaxException {
        final String result = sut.solvePart2();

        assertThat(result).isEqualTo("241861950");
    }

}