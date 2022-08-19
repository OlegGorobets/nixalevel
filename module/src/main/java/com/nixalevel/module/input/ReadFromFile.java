package com.nixalevel.module.input;

import com.nixalevel.module.exception.InvalidColumnHeadersException;
import com.nixalevel.module.exception.InvalidLineReadException;
import com.nixalevel.module.exception.InvalidProductException;
import com.nixalevel.module.model.product.Product;
import com.nixalevel.module.model.product.ScreenType;
import com.nixalevel.module.model.product.Telephone;
import com.nixalevel.module.model.product.Television;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadFromFile {
    private static final String DELIMITER = ",";
    private static final ClassLoader CLASS_LOADER = Thread.currentThread().getContextClassLoader();
    private static final InputStream INPUT_STREAM = CLASS_LOADER.getResourceAsStream("product list.csv");

    public static List<Product> createProductList() {
        List<Product> productList = new ArrayList<>();
        for (String[] strings : readFile()) {
            List<String> stringList = Arrays.stream(strings).toList();
            switch (stringList.get(0)) {
                case "Telephone" -> productList.add(createTelephoneProductWithDefault(stringList));
                case "Television" -> productList.add(createTelevisionProductWithDefault(stringList));
                case "default" ->
                        productList.add(new Television(stringList.get(1), Double.parseDouble(stringList.get(3)),
                                ScreenType.valueOf(stringList.get(4)), stringList.get(5), new BigDecimal(stringList.get(6))));
                default -> throw new InvalidProductException("The product is missing.");
            }
        }
        return productList;
    }

    private static Television createTelevisionProductWithDefault(List<String> stringList) {
        if (stringList.get(3).equals("default")) {
            return new Television(stringList.get(1), 0,
                    ScreenType.valueOf(stringList.get(4)), stringList.get(5), new BigDecimal(stringList.get(6)));
        } else if (stringList.get(4).equals("default")) {
            return new Television(stringList.get(1), Double.parseDouble(stringList.get(3)),
                    ScreenType.LED, stringList.get(5), new BigDecimal(stringList.get(6)));
        } else if (stringList.get(6).equals("default")) {
            return new Television(stringList.get(1), Double.parseDouble(stringList.get(3)),
                    ScreenType.LED, stringList.get(5), BigDecimal.ZERO);
        } else {
            return new Television(stringList.get(1), Double.parseDouble(stringList.get(3)),
                    ScreenType.valueOf(stringList.get(4)), stringList.get(5), new BigDecimal(stringList.get(6)));
        }
    }

    private static Telephone createTelephoneProductWithDefault(List<String> stringList) {
        if (stringList.get(4).equals("default")) {
           return new Telephone(stringList.get(1), stringList.get(2), ScreenType.QLED,
                    new BigDecimal(stringList.get(6)));
        } else if (stringList.get(6).equals("default")) {
            return new Telephone(stringList.get(1), stringList.get(2), ScreenType.valueOf(stringList.get(4)),
                    BigDecimal.ZERO);
        } else {
            return new Telephone(stringList.get(1), stringList.get(2), ScreenType.valueOf(stringList.get(4)),
                    new BigDecimal(stringList.get(6)));
        }
    }

    private static List<String[]> readFile() {
        List<String[]> readFile = new ArrayList<>();
        if (ReadFromFile.INPUT_STREAM != null) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(ReadFromFile.INPUT_STREAM);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                int[] titleSubsequence = alignColumnSequence(bufferedReader.readLine().split(DELIMITER));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    readFile.add(parsingLine(line, titleSubsequence));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return readFile;
    }

    private static String[] parsingLine(String line, int[] titleSubsequence) {
        String[] split = line.split(DELIMITER);
        String[] row = new String[titleSubsequence.length];
        for (int i = 0; i < titleSubsequence.length; i++) {
            try {
                if (split[titleSubsequence[i]].equals("")) {
                    throw new InvalidLineReadException("An invalid line was read.");
                } else {
                    row[i] = split[titleSubsequence[i]];
                }
            } catch (InvalidLineReadException e) {
                e.printStackTrace();
                row[i] = "default";
            }
        }
        return row;
    }

    private static int[] alignColumnSequence(String[] title) {
        int[] titleSubsequence = new int[title.length];
        for (int i = 0; i < title.length; i++) {
            switch (title[i]) {
                case "type" -> titleSubsequence[0] = i;
                case "series" -> titleSubsequence[1] = i;
                case "model" -> titleSubsequence[2] = i;
                case "diagonal" -> titleSubsequence[3] = i;
                case "screen type" -> titleSubsequence[4] = i;
                case "country" -> titleSubsequence[5] = i;
                case "price" -> titleSubsequence[6] = i;
                default -> throw new InvalidColumnHeadersException("Invalid column headers.");
            }
        }
        return titleSubsequence;
    }
}
