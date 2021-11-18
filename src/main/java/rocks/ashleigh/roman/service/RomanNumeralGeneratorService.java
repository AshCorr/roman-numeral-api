package rocks.ashleigh.roman.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import rocks.ashleigh.roman.RomanNumeralGenerator;

import java.util.Objects;

@Service
public class RomanNumeralGeneratorService implements RomanNumeralGenerator {

    private static final String[] UNIQUE_ROMAN_DIGITS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] UNIQUE_ROMAN_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    @Override
    public String generate(int number) {
        if (number < 1 || number > 3999)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Expected a number between 1 and 3999 (Inclusive)");

        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < UNIQUE_ROMAN_DIGITS.length; i++) {
            if (number == 0)
                break; //Slight optimization here, just allows the algorithm to early return if it encounters numbers like 3000

            while (number >= UNIQUE_ROMAN_VALUES[i]) {
                sb.append(UNIQUE_ROMAN_DIGITS[i]);
                number -= UNIQUE_ROMAN_VALUES[i];
            }
        }

        return sb.toString();
    }
}
