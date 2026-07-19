package com.suleman.poc;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @PostMapping("/calculate")
    public Map<String, Object> calculate(@RequestBody Map<String, Object> request) {
        double num1 = Double.parseDouble(request.get("num1").toString());
        double num2 = Double.parseDouble(request.get("num2").toString());
        String operator = request.get("operator").toString();
        double result = 0;

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }

        // Hostname represents the Pod Name in Kubernetes
        String podName = System.getenv().getOrDefault("HOSTNAME", "Local-Machine");

        Map<String, Object> response = new HashMap<>();
        response.put("num1", num1);
        response.put("num2", num2);
        response.put("operator", operator);
        response.put("result", result);
        response.put("podName", podName);

        return response;
    }
}
