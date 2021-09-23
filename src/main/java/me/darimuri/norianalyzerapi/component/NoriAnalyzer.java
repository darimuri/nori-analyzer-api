package me.darimuri.norianalyzerapi.component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.apache.lucene.analysis.ko.KoreanPartOfSpeechStopFilter;
import org.apache.lucene.analysis.ko.KoreanTokenizer.DecompoundMode;
import org.apache.lucene.analysis.ko.POS.Tag;
import org.apache.lucene.analysis.ko.dict.UserDictionary;
import org.apache.lucene.analysis.ko.tokenattributes.PartOfSpeechAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
public class NoriAnalyzer {
    
    KoreanAnalyzer analyzer;

    public NoriAnalyzer() throws IOException {
        InputStream in = new FileInputStream("src/main/resources/userdict.txt");
        Reader reader = new InputStreamReader(in, "UTF-8");
        UserDictionary userDict = UserDictionary.open(reader);
        analyzer = new KoreanAnalyzer(userDict, DecompoundMode.NONE, KoreanPartOfSpeechStopFilter.DEFAULT_STOP_TAGS,
                false);

        try {
            reader.close();
        } catch (Exception e) {
        }

        try {
            in.close();
        } catch (Exception e) {
        }
    }

    public String[] Tokenize(String text) throws IOException {
        TokenStream ts = analyzer.tokenStream("string", text);
        CharTermAttribute ctAttr = ts.addAttribute(CharTermAttribute.class);
        PartOfSpeechAttribute posAttr = ts.addAttribute(PartOfSpeechAttribute.class);

        ts.reset();

        List<String> tokens = new ArrayList<>();

        while (ts.incrementToken()) {
            String token = ctAttr.toString();
            Tag leftPOS = posAttr.getLeftPOS();
            Tag rightPOS = posAttr.getRightPOS();

            if (leftPOS == Tag.NNG && rightPOS == Tag.NNG) {
                tokens.add(token);
            } else if (leftPOS == Tag.NNP && rightPOS == Tag.NNP) {
                tokens.add(token);
            } else if (leftPOS == Tag.VA && rightPOS == Tag.VA) {
                tokens.add(token);
            } else if (leftPOS == Tag.VV && rightPOS == Tag.VV) {
                tokens.add(token);
            } else if (leftPOS == Tag.VV && rightPOS == Tag.E) {
                tokens.add(token);
            } else if (leftPOS == Tag.SN && rightPOS == Tag.SN) {
                tokens.add(token);
            } else if (leftPOS == Tag.SL && rightPOS == Tag.SL) {
                tokens.add(token);
            } else if (leftPOS == Tag.NR && rightPOS == Tag.NR) {
                tokens.add(token);
            }
        }

        ts.end();
        ts.close();

        return tokens.toArray(new String[tokens.size()]);
    }
}
