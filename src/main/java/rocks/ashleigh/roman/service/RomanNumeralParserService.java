package rocks.ashleigh.roman.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rocks.ashleigh.roman.RomanNumeralParser;

import java.util.HashMap;
import java.util.Map;

@Service
public class RomanNumeralParserService implements RomanNumeralParser {
    private static final Map<Character, Integer> ROMAN_NUMERALS = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};


    // Currently does not check if a roman numeral is VALID.
    // For example this method can parse a numeral like IC (99) just fine even though by roman numeral rules this should be represented as XCIX
    @Override
    public int parse(String numeral) {
        char[] chars = numeral.toCharArray();

        int largest = 0;
        int sum = 0;

        for (int i = chars.length-1; i >= 0; i--) {
            int value = ROMAN_NUMERALS.getOrDefault(chars[i], -1);

            if (value == -1)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unexpected numeral: " + chars[i]);

            if (value < largest) {
                sum -= value;
            } else {
                sum += value;
                largest = value;
            }
        }

        return sum;
    }
}
