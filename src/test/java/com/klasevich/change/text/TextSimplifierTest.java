package com.klasevich.change.text;

import com.klasevich.change.text.reader.TextReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextSimplifierTest {

    TextReader textReader = new TextReader();
    TextSimplifier simplifier = new TextSimplifier();
    List<String> expected;
    List<String> lines;

    @BeforeEach
    void setUp() {
        lines = textReader.readFromFile("data/text.txt");
        expected = new ArrayList<>();
        expected.add("kakao, kofi and apl");
        expected.add("sukses");
    }

    @Test
    void changeText() {
        List<String> actual = simplifier.changeText(lines);
        assertEquals(expected, actual);
    }
}