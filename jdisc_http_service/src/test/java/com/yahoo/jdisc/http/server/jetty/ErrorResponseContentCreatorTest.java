package com.yahoo.jdisc.http.server.jetty;

import org.testng.annotations.Test;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

/**
 * @author bjorncs
 */
public class ErrorResponseContentCreatorTest {

    @Test
    public void response_content_matches_expected_string() {
        String expectedHtml =
                "<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=ISO-8859-1\"/>\n" +
                "<title>Error 200</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h2>HTTP ERROR: 200</h2>\n" +
                "<p>Problem accessing http://foo.bar. Reason:\n" +
                "<pre>    My custom error message</pre></p>\n" +
                "<hr/>\n" +
                "</body>\n" +
                "</html>\n";

        ErrorResponseContentCreator c = new ErrorResponseContentCreator();
        byte[] rawContent = c.createErrorContent(
                "http://foo.bar",
                HttpServletResponse.SC_OK,
                Optional.of("My custom error message"));
        String actualHtml = new String(rawContent, StandardCharsets.ISO_8859_1);
        assertEquals(expectedHtml, actualHtml);
    }

}