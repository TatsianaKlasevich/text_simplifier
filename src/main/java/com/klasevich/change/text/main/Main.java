package com.klasevich.change.text.main;

import com.klasevich.change.text.reader.TextReader;
import com.klasevich.change.text.TextSimplifier;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TextReader textReader = new TextReader();
        List<String> lines = textReader.readFromFile("data/text.txt");
        TextSimplifier textSimplifier = new TextSimplifier();
        textSimplifier.changeText(lines);
    }

}
