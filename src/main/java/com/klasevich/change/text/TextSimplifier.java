package com.klasevich.change.text;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextSimplifier {
    private static final Logger logger = LogManager.getLogger();
    public static final String DELIMITER = " ";
    public static final Pattern WORD_WITH_LAST_E = Pattern.compile("([A_Za-z]+)([Ee])(\\p{Punct}?)");
    public static final Pattern ARTICLE = Pattern.compile("The|the|Th|th|A|a|An|an");

    public List<String> changeText(List<String> lines) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            line = removeC(line);
            line = removeDoubleLetter(line);
            line = removeLastE(line);
            line = removeArticles(line);
            result.add(line);
            logger.info("The result line is {}", line);
        }
        return result;
    }

    private String removeArticles(String line) {
        List<String> words = Arrays.stream(line.split(DELIMITER)).toList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            Matcher matcher = ARTICLE.matcher(words.get(i));
            if (matcher.matches()) {
                continue;
            }
            stringBuilder.append(words.get(i)).append(DELIMITER);
        }
        return stringBuilder.toString().trim();
    }

    private String removeLastE(String line) {
        List<String> words = Arrays.stream(line.split(DELIMITER)).toList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            Matcher matcher = WORD_WITH_LAST_E.matcher(words.get(i));
            if (matcher.matches()) {
                String word = matcher.group(1) + matcher.group(3);
                logger.debug("word in after removing e {}", word);
                stringBuilder.append(word).append(" ");
                continue;
            }
            stringBuilder.append(words.get(i)).append(DELIMITER);
        }
        return stringBuilder.toString().trim();
    }

    private String removeDoubleLetter(String line) {

        String newLine = line.replaceAll("ee", "i");
        newLine = newLine.replaceAll("oo", "u");

        for (char i = 'a'; i <= 'z'; i++) {
            String s = String.valueOf(i);
            newLine = newLine.replaceAll(s + s, s);
        }

        logger.debug("line remove double letter - {}", newLine);
        return newLine;
    }

    private String removeC(String line) {
        String newLine = line.replaceAll("ci", "si");
        newLine = newLine.replaceAll("ce", "se");
        newLine = newLine.replaceAll("ce", "se");
        newLine = newLine.replaceAll("ck", "k");
        newLine = newLine.replaceAll("c", "k");
        logger.debug("line remove c - {}", newLine);
        return newLine;
    }
}
