package rocks.ashleigh.roman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rocks.ashleigh.roman.service.RomanNumeralGeneratorService;
import rocks.ashleigh.roman.service.RomanNumeralParserService;

@RestController
public class RomanNumeralController {
    private RomanNumeralGeneratorService romanNumeralGeneratorService;
    private RomanNumeralParserService romanNumeralParserService;

    public RomanNumeralController(RomanNumeralGeneratorService romanNumeralGeneratorService, RomanNumeralParserService romanNumeralParserService) {
        this.romanNumeralGeneratorService = romanNumeralGeneratorService;
        this.romanNumeralParserService = romanNumeralParserService;
    }

    @GetMapping("/numeralize/{decimal}")
    public String toNumeral(@PathVariable int decimal) {
        return romanNumeralGeneratorService.generate(decimal);
    }

    @GetMapping("/decimalize/{numeral}")
    public int toDecimal(@PathVariable String numeral) {
        return romanNumeralParserService.parse(numeral);
    }
}
