package com.healthcare;

import com.healthcare.config.RandCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandCodeTest {

    @Test
    void generateFixedLengthNum() {
    }

    @Test
    void main() {
        RandCode r=new RandCode();
        System.out.println(r.generateFixedLengthNum(6));
    }
}