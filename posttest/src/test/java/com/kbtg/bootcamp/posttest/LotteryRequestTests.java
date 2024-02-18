package com.kbtg.bootcamp.posttest;

import com.kbtg.bootcamp.posttest.lottery.LotteryRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class LotteryRequestTests {
    private static Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) validatorFactory.getValidator();
    }

    @Test
    @DisplayName("ticker should be string and length equal 6")
    void ticketShouldBeStringAndLengthEqual6() {
        validateRequest(createRequest("123456", 80, 1));
    }

    @Test
    @DisplayName("price should be positive")
    void priceShouldBePositive() {
        validateRequest(createRequest("123456", 80, 1));
    }

    @Test
    @DisplayName("amount should be positive")
    void amountShouldBePositive() {
        validateRequest(createRequest("123456", 80, 1));
    }

    private LotteryRequest createRequest(String ticket, int price, int amount) {
        return new LotteryRequest(ticket, price, amount);
    }

    private void validateRequest(LotteryRequest request) {
        var violations = validator.validate(request);
        assertThat(violations).isEmpty();
    }


    @Test
    @DisplayName("amount invalid should return 1 violations")
    void amountNegativeInvalid() {
        validateRequest(new LotteryRequest("123456",80, -1), "Amount can't negative number.");
    }

    @Test
    @DisplayName("price invalid negative should return 1 violations")
    void priceNegativeInvalid() {
        validateRequest(new LotteryRequest("123456",-1, 1), "Price can't negative number.");
    }

    @Test
    @DisplayName("ticket invalid less than should return 1 violations")
    void ticketLengthLessThan6Invalid() {
        validateRequest(new LotteryRequest("",80, 1), "Ticket can't less than 6.");
    }

    @Test
    @DisplayName("ticket invalid more than should return 1 violations")
    void ticketLengthMoreThan6Invalid() {
        validateRequest(new LotteryRequest("1234567",80, 1), "Ticket can't more than 6.");
    }

    @Test
    @DisplayName("amount  can't be null should return 3 violations")
    void amountCantBeNull() {
        validateRequest(new LotteryRequest("123456",80, null), "Amount can't be null.");
    }

    @Test
    @DisplayName("price can't be null should return 3 violations")
    void priceCantBeNull() {
        validateRequest(new LotteryRequest("123456",null, 1), "Price can't be null.");
    }

    @Test
    @DisplayName("ticket can't be null should return 3 violations")
    void amountAndPriceAndTicketCantBeNull() {
        validateRequest(new LotteryRequest(null,80, 1), "Ticket can't be null.");
    }

    private void validateRequest(LotteryRequest request, String expectedMessage) {
        var violations = validator.validate(request);

        assertThat(violations).hasSize(1);
        Iterator<ConstraintViolation<LotteryRequest>> iterator = violations.iterator();
        assertThat(iterator.next().getMessage()).isEqualTo(expectedMessage);
    }

}
