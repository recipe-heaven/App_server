package no.twct.recipeheaven.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * String parser utility class
 */
public class StringParser {

    /**
     * Converts a CSV numbered string to BigInt list.
     * Example string: "1,2,3,4,6"
     *
     * @param csvString csv string
     * @return
     */
    public static List<BigInteger> convertCsvNumberedStringToBigInt(String csvString) {
        List<BigInteger> bigIntList = new ArrayList<BigInteger>();
        try {
            var idsAsString = csvString.split(",");
            for (var id : idsAsString) {
                bigIntList.add(BigInteger.valueOf(Integer.parseInt(id)));
            }
            return bigIntList;
        } catch (NumberFormatException e) {
            bigIntList = new ArrayList<BigInteger>();
        }
        return bigIntList;
    }

}
