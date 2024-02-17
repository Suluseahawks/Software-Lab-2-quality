package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinaryAPIController {

    @GetMapping("/calculate")
    public BinaryAPIResult calculate(@RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
                                     @RequestParam(name = "operator", required = false, defaultValue = "") String operator,
                                     @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {
        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        Binary result = calculateResult(number1, operator, number2);

        return new BinaryAPIResult(number1, operator, number2, result);
    }

    private Binary calculateResult(Binary operand1, String operator, Binary operand2) {
        switch (operator) {
            case "+":
                return Binary.add(operand1, operand2);
            case "-":
                return Binary.subtract(operand1, operand2);
            case "*":
                return Binary.multiply(operand1, operand2);
            case "&":
                return Binary.and(operand1, operand2);
            case "|":
                return Binary.or(operand1, operand2);
            case "^":
                return Binary.xor(operand1, operand2);
            default:
                return Binary.add(operand1, operand2); // Default to addition
		// http://localhost:8080/add?operand1=111&operand2=1010
	}
        }
    }
}
