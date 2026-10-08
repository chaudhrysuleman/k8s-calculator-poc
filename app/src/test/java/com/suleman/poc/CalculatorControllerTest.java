package com.suleman.poc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorControllerTest {

    @Autowired
    private CalculatorController controller;

    @Test
    void testHealthEndpointReturnsUp() {
        Map<String, Object> health = controller.health();
        assertNotNull(health);
        assertEquals("UP", health.get("status"));
        assertNotNull(health.get("podName"));
        assertNotNull(health.get("timestamp"));
    }

    @Test
    void testAddition() {
        Map<String, Object> req = Map.of("num1", 10.0, "num2", 5.0, "operator", "+");
        Map<String, Object> res = controller.calculate(req);
        assertEquals(15.0, res.get("result"));
    }

    @Test
    void testDivisionByZeroThrowsException() {
        Map<String, Object> req = Map.of("num1", 10.0, "num2", 0.0, "operator", "/");
        assertThrows(IllegalArgumentException.class, () -> controller.calculate(req));
    }
}
