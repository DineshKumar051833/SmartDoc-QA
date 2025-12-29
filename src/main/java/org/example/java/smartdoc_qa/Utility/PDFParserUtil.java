package org.example.java.smartdoc_qa.Utility;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import java.io.IOException;
import java.io.InputStream;

public class PDFParserUtil {

    private static final Tika tika = new Tika();

    public static String extractText(InputStream inputStream) throws IOException, TikaException {
        return tika.parseToString(inputStream);
    }
}
