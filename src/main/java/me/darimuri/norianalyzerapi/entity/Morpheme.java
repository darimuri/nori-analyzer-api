package me.darimuri.norianalyzerapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Morpheme {
    private String tag;
    private String surfaceForm;    
}
