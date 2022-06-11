package me.darimuri.norianalyzerapi.entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token {
    private String token;
    private String type;
    private List<Morpheme> morphemes;
}