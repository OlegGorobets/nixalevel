package com.nixalevel.lesson10.utility;

import com.nixalevel.lesson10.model.Auto;
import com.nixalevel.lesson10.model.AutoManufacturer;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFromFile {
    private static final Pattern PATTERN = Pattern.compile(">+([A-Za-z0-9-: ]*).|\": \"\\b([A-Za-z0-9-: ]*).");

    public Auto readFileAndCreateAuto(String filePath) throws ParseException {
        return createAuto(readAndParsing(filePath));
    }

    public Auto createAuto(List<String> listAuto) throws ParseException {
        List<String> engine = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        engine.add("volume: " + listAuto.get(6));
        engine.add("brand: " + listAuto.get(7));
        return new Auto(listAuto.get(0), AutoManufacturer.valueOf(listAuto.get(1)), new BigDecimal(listAuto.get(2)),
                listAuto.get(3), sdf.parse(listAuto.get(4)), Integer.parseInt(listAuto.get(5)), engine);
    }

    public List<String> readAndParsing(String filePath) {
        Path path = Paths.get(filePath);
        List<String> parsingList = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = PATTERN.matcher(line);
                if (matcher.find()) {
                    if (matcher.group(1) != null) {
                        parsingList.add(matcher.group(1));
                    } else {
                        parsingList.add(matcher.group(2));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parsingList;
    }
}
