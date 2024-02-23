package com.kbtg.bootcamp.posttest;
import com.kbtg.bootcamp.posttest.lottery.LotteryRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LotteryRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testValidLotteryRequest() {
        LotteryRequest lotteryRequest = new LotteryRequest("123456", 100, 5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidTicketLessThan6() {
        LotteryRequest lotteryRequest = new LotteryRequest("12345", 100, 5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Ticket can't less than 6.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidTicketMoreThan6() {
        LotteryRequest lotteryRequest = new LotteryRequest("1234567", 100, 5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Ticket can't more than 6.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidTicketNull() {
        LotteryRequest lotteryRequest = new LotteryRequest(null, 100, 5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Ticket can't be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPriceNegative() {
        LotteryRequest lotteryRequest = new LotteryRequest("123456", -100, 5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Price can't negative number.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidPriceNull() {
        LotteryRequest lotteryRequest = new LotteryRequest("123456", null, 5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Price can't be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidAmountNegative() {
        LotteryRequest lotteryRequest = new LotteryRequest("123456", 100, -5);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Amount can't negative number.", violations.iterator().next().getMessage());
    }

    @Test
    public void testInvalidAmountNull() {
        LotteryRequest lotteryRequest = new LotteryRequest("123456", 100, null);
        Set<ConstraintViolation<LotteryRequest>> violations = validator.validate(lotteryRequest);
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Amount can't be null.", violations.iterator().next().getMessage());
    }
}
