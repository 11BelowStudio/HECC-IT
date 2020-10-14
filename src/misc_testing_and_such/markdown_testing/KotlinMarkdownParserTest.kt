package misc_testing_and_such.markdown_testing

import org.hamcrest.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Ignore
import org.junit.jupiter.api.*

/**
 *   To start with, I attempted to follow a tutorial to create a simple version of my markdown parser, following
 *   **Dmitry Kandalov, “Minimal markdown parser with Marcin Moskala,” 13-Dec-2019. [Online]. Available: https://www.youtube.com/watch?v=pgL4e0uRliM. [Accessed: 14-Oct-2020].**
 *
 *   The plan is to then turn this into more full implementation by myself later on
 *
 *    As of right now, it's pretty much at the end result of the tutorial (albeit now commented and such).
 *    So I'm now going to make it fully functional.
 *
*/


class MarkdownParserTests {
    // Unit tests for this markdown stuff

    //empty input -> empty output
    @Test fun `empty markdown produces empty html`(){
        val html = parseMarkdownToHtml("")
        assertThat(html, equalTo(""))
    }

    //plaintext -> plaintext
    @Test fun `plaintext produces same html`(){
        val texts = listOf("lorem ipsum", "", "sdtwetwsad awera ta tqsaret", "line 1\nline2")
        for (text in texts) {
            val html = parseMarkdownToHtml(text)
            assertThat(html, equalTo(text))
        }
    }

    //header 1 markdown -> h1 html
    @Test fun `header1 text produces h1 html`(){
        val html = parseMarkdownToHtml("# Header 1")
        assertThat(html, equalTo("<h1>Header 1</h1>"))
    }

    //header 2 markdown -> h2 html
    @Test fun `header2 text produces h2 html`(){
        val html = parseMarkdownToHtml("## Header 2")
        assertThat(html, equalTo("<h2>Header 2</h2>"))
    }

    //testing some header object stuff
    @Test fun `header in markdown converted to header object`(){
        assertThat(
                parseMarkdown("# Header 1"),
                equalTo(
                        listOf<Token>(
                                Header(level = 1, text = "Header 1")
                        )
                )
        )
    }

    //testing some text object stuff
    @Test fun `plaintext in markdown converted to text object`(){
        assertThat(
                parseMarkdown("lorem ipsum"),
                equalTo(
                        listOf<Token>(
                                Text(text = "lorem ipsum")
                        )
                )
        )
    }

    //putting text and a header together
    @Test fun `plaintext and headers in markdown converted to text and header objects`(){
        assertThat(
                parseMarkdown(
                        """
                        |# Header 1
                        |Text 1
                        """.trimMargin()
                ),
                equalTo(
                        listOf<Token>(
                                Header(level = 1, text = "Header 1"),
                                Text(text = "Text 1")
                        )
                )
        )
    }

}

//basically an abstract parent class for all the other types of token data classes which this parser should generate
/**
 * This is a sealed Token class (essentially an abstract parent class for other Token subclasses)
 */
sealed class Token(){
    fun toHtml(): String = when(this){
        is Header ->
            "<h$level>$text</h$level>"
        is Text ->
            this.text
    }
}


/**
 * A data class for a header text token
 * @param level the header level (1-6)
 * @param text the text for the header
 */
data class Header(val level: Int, val text: String): Token()

/**
 * A data class for raw text tokens
 * @param text literally just the raw text
 */
data class Text(val text: String): Token()

/**
 * function which converts a markdown text string into the equivalent HTML text string
 *
 * @param markdownText the raw markdown text to be converted to html
 * @return string with that markdown text as html code
*/
fun parseMarkdownToHtml(markdownText: String): String =
        parseMarkdown(markdownText)
                .joinToString("\n"){
                    token -> token.toHtml()
        }


/*
private fun Token.toHtml(): String = when(this){
    is Header ->
        "<h$level>$text</h$level>"
    is Text ->
        this.text
}*/

/**
 * function which converts markdown text into a list of Token objects
 *
 * @param markdownText The markdown string which is converted into Tokens
 *
 * @return Aforementioned markdown string as a list of tokens
 */
private fun parseMarkdown(markdownText: String): List<Token> {
    return markdownText.split("\n").map{ line ->
        when{
            line.startsWith("# ") ->
                Header(level = 1, text = line.trimMargin("# "))
            line.startsWith("## ") ->
                Header(level = 2, text = line.trimMargin("## "))
            else ->
                Text(text = line)
        }
    }
}