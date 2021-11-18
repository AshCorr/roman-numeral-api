package rocks.ashleigh.roman.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

public class RomanNumeralGeneratorServiceTest {
    public RomanNumeralGeneratorService out;

    @Before
    public void before() {
        out = new RomanNumeralGeneratorService();
    }

    @Test
    public void test_generate_singleDigits() {
        Assert.assertEquals("I", out.generate(1));
        Assert.assertEquals("II", out.generate(2));
        Assert.assertEquals("III", out.generate(3));
        Assert.assertEquals("IV", out.generate(4));
        Assert.assertEquals("V", out.generate(5));
        Assert.assertEquals("VI", out.generate(6));
        Assert.assertEquals("VII", out.generate(7));
        Assert.assertEquals("VIII", out.generate(8));
        Assert.assertEquals("IX", out.generate(9));
    }

    @Test
    public void test_generate_complex() {
        Assert.assertEquals("XCIX", out.generate(99));
        Assert.assertEquals("CMXCIX", out.generate(999));
        Assert.assertEquals("MMMCMXCIX", out.generate(3999));
    }

    @Test
    public void test_generate_random() {
        Assert.assertEquals("MCLIV", out.generate(1154));
        Assert.assertEquals("MMMCDXCI", out.generate(3491));
        Assert.assertEquals("MMDCCCXXV", out.generate(2825));
        Assert.assertEquals("CDXV", out.generate(415));
        Assert.assertEquals("MMCMLXXII", out.generate(2972));
        Assert.assertEquals("MMMCCXCVII", out.generate(3297));
        Assert.assertEquals("MDCCXLV", out.generate(1745));
        Assert.assertEquals("CDLXXVI", out.generate(476));
        Assert.assertEquals("MCML", out.generate(1950));
        Assert.assertEquals("XLVIII", out.generate(48));
    }

    @Test
    public void test_generate_outOfRangeNumbers() {
        Assert.assertThrows(ResponseStatusException.class, () -> out.generate(0));
        Assert.assertThrows(ResponseStatusException.class, () -> out.generate(4000));
    }
}
