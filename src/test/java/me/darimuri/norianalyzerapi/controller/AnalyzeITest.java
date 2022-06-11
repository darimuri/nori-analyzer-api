package me.darimuri.norianalyzerapi.controller;

import org.apache.lucene.analysis.ko.POS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import me.darimuri.norianalyzerapi.entity.Morpheme;
import me.darimuri.norianalyzerapi.entity.Token;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnalyzeITest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void postAnalyze() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("text", "가리봉역 鄕歌");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);
        
        ResponseEntity<Token[]> response = template.postForEntity("/analyze", requestEntity, Token[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Token token1 = new Token();
        token1.setToken("가리봉역");
        token1.setType(POS.Type.COMPOUND.name());
        List<Morpheme> morphemes = new ArrayList<>();
        morphemes.add(new Morpheme());
        morphemes.get(0).setSurfaceForm("가리봉");
        morphemes.get(0).setTag(POS.Tag.NNG.description());
        morphemes.add(new Morpheme());
        morphemes.get(1).setSurfaceForm("역");
        morphemes.get(1).setTag(POS.Tag.NNG.description());
        token1.setMorphemes(morphemes);
        
        Token token2 = new Token();
        token2.setToken("향가");
        token2.setType(POS.Type.MORPHEME.name());

        Token[] expected = new Token[]{
            token1,
            token2,
        };
        Token[] actual = response.getBody();
        assertThat(actual).isEqualTo(expected);
    }
}
