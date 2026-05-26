package com.cfs.bajajchallenge.controller;

import com.cfs.bajajchallenge.dto.BfhlRequest;
import com.cfs.bajajchallenge.dto.BfhlResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
public class BfhlController {

    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@RequestBody BfhlRequest request) {
        BfhlResponse response = new BfhlResponse();

        // Setup Basic Info [cite: 23, 26]
        response.setIs_success(true);
        response.setUser_id("siddharth_navlakhe_06032004");
        response.setEmail("siddharthnavlakhe230824@acropolis.in");
        response.setRoll_number("0828CS231254");

        List<String> inputData = request.getData();
        List<String> evens = new ArrayList<>();
        List<String> odds = new ArrayList<>();
        List<String> alpha = new ArrayList<>();
        List<String> specials = new ArrayList<>();
        int totalSum = 0;
        StringBuilder alphaCollector = new StringBuilder();

        for (String item : inputData) {
            if (item.matches("-?\\d+")) { // Number check [cite: 42]
                int num = Integer.parseInt(item);
                totalSum += num;
                if (num % 2 == 0) evens.add(item);
                else odds.add(item);
            } else if (item.matches("[a-zA-Z]+")) { // Alphabet check [cite: 10]
                alpha.add(item.toUpperCase());
                alphaCollector.append(item);
            } else { // Special Character check [cite: 11]
                specials.add(item);
            }
        }

        // Logic for Alternating Caps Reverse String [cite: 13, 14]
        String reversed = alphaCollector.reverse().toString();
        StringBuilder altResult = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            if (i % 2 == 0) altResult.append(Character.toUpperCase(c));
            else altResult.append(Character.toLowerCase(c));
        }

        response.setEven_numbers(evens);
        response.setOdd_numbers(odds);
        response.setAlphabets(alpha);
        response.setSpecial_characters(specials);
        response.setSum(String.valueOf(totalSum)); // [cite: 12]
        response.setConcat_string(altResult.toString()); // [cite: 13]

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> handleGet() {
        return ResponseEntity.ok().body("{\"operation_code\":1}"); // [cite: 32]
    }
}