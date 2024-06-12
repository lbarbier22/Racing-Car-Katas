package tddmicroexercises.textconvertor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HtmlTextConverterTest {

    @Test
    public void foo() throws IOException {
        HtmlTextConverter converter = new HtmlTextConverter("src/test/ressources/foo");
        String html = converter.convertToHtml();
        System.out.println(html);
        //assertEquals("fixme", converter.getFilename());
    }

}
