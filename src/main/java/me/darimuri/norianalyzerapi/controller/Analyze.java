package me.darimuri.norianalyzerapi.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.darimuri.norianalyzerapi.component.NoriAnalyzer;
import me.darimuri.norianalyzerapi.entity.Token;

@RestController
public class Analyze {
    
    @Autowired
    private NoriAnalyzer noriAnalyzer;

    @PostMapping("/analyze")
	public Token[] hello(@RequestParam(value = "text", required = true) String text) throws IOException {
        return noriAnalyzer.Tokenize(text);
	}
}
