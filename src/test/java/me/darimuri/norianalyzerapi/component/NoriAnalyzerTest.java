package me.darimuri.norianalyzerapi.component;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoriAnalyzerTest {
    
    private NoriAnalyzer cut;

    @BeforeEach
    public void beforeTest() throws IOException {
        cut = new NoriAnalyzer();
    }

    @Test
    public void analyze() throws IOException {
        assertThat(cut.Tokenize("가거도항").length).isEqualTo(1);
        assertThat(cut.Tokenize("지리산").length).isEqualTo(1);
        assertThat(cut.Tokenize("지리산역").length).isEqualTo(1);
    }
}