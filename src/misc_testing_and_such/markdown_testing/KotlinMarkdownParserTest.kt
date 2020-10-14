package misc_testing_and_such.markdown_testing

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.*
import java.util.*

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
                                Header(level = 1, content = Text(text = "Header 1"))
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
                                Header(level = 1, content = Text(text = "Header 1")),
                                Text(text = "Text 1")
                        )
                )
        )
    }

}

/**
 * This is a sealed Token class (essentially an abstract parent class for other Token subclasses)
 */
sealed class Token(){
    abstract fun toHtml(): String
}

/**
 * A data class for a header text token
 * @property level the header level (1-6)
 * @property content the content of the header tag
 */
data class Header(val level: Int, val content: Token): Token(){
    override fun toHtml(): String = "<h$level>"+ content.toHtml()+"</h$level>"
}

/**
 * A token for raw text stuff
 * @propertytext literally just the raw text
 */
data class Text(val text: String): Token(){
    override fun toHtml(): String = escapeWithLineBreaks(text)
}

/**
 * A token for the HECC passage links
 * @property linkedPassage: the string identifier for the passage being linked
 * @property linkContent: the text for the passage (as a Token)
 */
data class PassageLink(val linkedPassage: String, val linkContent: Token): Token(){
    override fun toHtml(): String =
            "<a class 'passageLink' onClick = 'theHeccer.goToPassage(\\\\\"$linkedPassage\\\\\")'>"+linkContent.toHtml()+"</a>"
}

/**
 * A token for image links
 * @property imgSrc: the source URI for the image
 * @property altText: the alternative text for the image (as a Text token)
 */
data class ImageLink(val imgSrc: String, val altText: Text): Token(){
    override fun toHtml(): String =
            "<img src = '$imgSrc' alt = '"+altText.toHtml()+"'>"
}

/**
 * a token for URLs
 * @param url: the link URL
 * @param content: the link content (as Token)
 */
data class URL(val url: String, val content: Text): Token(){
    override fun toHtml(): String =
            "<a href='$url'>"+content.toHtml()+"</a>"
}

/**
 * a token for bold text
 *
 * @property content: the stuff that's supposed to be bold
 */
data class Bold(val content: Token): Token(){
    override fun toHtml(): String =
            "<strong>"+content.toHtml()+"</strong>"
}

/**
 * a token for italic text
 *
 * @property content: the stuff that's supposed to be italicised
 */
data class Italic(val content: Token): Token(){
    override fun toHtml(): String =
            "<em>"+content.toHtml()+"</em>"
}

/**
 * a token for blockquotes
 *
 * @property contents: the tokens which are supposed to be in the blockquotes
 */
data class Blockquote(val contents: MutableList<Token>): Token(){
    override fun toHtml(): String{
        var output = StringJoiner("\n");
        output.add("<blockquote>")
        contents.forEach{
            token ->
                output.add(token.toHtml())
        }
        output.add("</blockquote>")
        return output.toString()
    }
}

/**
 * a token for ordered lists
 *
 * @property elements: the elements in this list
 */
data class OrderedList(val elements: MutableList<ListElement>): Token(){
    override fun toHtml(): String {
        val output = StringJoiner("\n")
        output.add("<ol>")
        elements.forEach{
            element ->
                output.add(element.toHtml())
        }
        output.add("</ol>")
        return output.toString()
    }
}

/**
 * a token for unordered lists
 *
 * @property elements: the elements in this list
 */
data class UnorderedList(val elements: MutableList<ListElement>): Token(){
    override fun toHtml(): String {
        val output = StringJoiner("\n")
        output.add("<ul>")
        elements.forEach{
            element ->
            output.add(element.toHtml())
        }
        output.add("</ul>")
        return output.toString()
    }
}

/**
 * A token for items from ordered/unordered lists
 *
 * @property content: the content of this list item
 */
data class ListElement(val content: Token): Token(){
    override fun toHtml(): String =
            "<li>"+content.toHtml()+"</li>"
}

/**
 * A code html element token
 *
 * @property text: the textual content of this element
 */
data class Code(val text: Text): Token(){
    override fun toHtml(): String =
            "<code>"+text.toHtml()+"</code>"
}

/**
 * A token that exists for the sole purpose of being a horizontal rule element
 */
class HorizontalRule(): Token(){
    override fun toHtml(): String = "<hr>"
}

/**
 * a token for paragraph elements
 *
 * @property contents: all the HTML elements that this paragraph consists of
 */
data class Paragraph(val contents: MutableList<Token>): Token(){
    override fun toHtml(): String{
        val output = StringJoiner("\n")
        output.add("<p>")
        contents.forEach{
            token -> output.add(token.toHtml())
        }
        output.add("</p>")
        return output.toString()
    }
}

/**
 * a top level token (containing all the paragraphs, blockquotes, headers, header rows, etc)
 *
 * @property contents: all the top level html elements
 */
data class TopLevelToken(val contents: MutableList<Token>): Token(){
    override fun toHtml(): String{
        val output = StringJoiner("\n")
        contents.forEach{
            token -> output.add(token.toHtml())
        }
        return output.toString()
    }
}

public fun escapeCharacters(rawText: String): String{
    var escaped = rawText.replace("&","&amp") //ampersands escaped
    escaped = escaped.replace("<","&lt") //< escaped
    escaped = escaped.replace(">","&rt") //> escaped
    escaped = escaped.replace("\"","&quot") //speechmark escaped
    escaped = escaped.replace("'","&#39") //quotation mark escaped
    return escaped
}

fun escapeWithLineBreaks(rawText: String): String{
    val escaped = escapeCharacters(rawText)
    //two spaces followed by newline: replaced with html linebreak (and a newline for readability)
    return escaped.replace("  \\R", "<br>\n")
}

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
                Header(level = 1, content = Text(line.trimMargin("# ")))
            line.startsWith("## ") ->
                Header(level = 2, content = Text(line.trimMargin("## ")))
            else ->
                Text(text = line)
        }
    }
}

/**
 * This function is intended to break the list down further into individual markdown elements,
 *  due to the existing 'split at newlines' implementation not exactly doing what I need it to do
 */
private fun markdownSplit(markdownText: String): List<String>{
    var tokenList = mutableListOf<String>()
    var mdText = markdownText.trim() //trimming leading/trailing whitespace
    return tokenList
}

const val hrRegex = "^\\s?*-{3}\\s*?$"
const val headerRegex = "^#{1,6} \\R*?$"
const val directPassageRegex = "\\[\\[.*?\\]\\]"
const val indirectPassageRegex = "\\[\\[.*?\\|.*?\\]\\]"
const val paragraphRegex = "(\\r)*?\\r{2}"

private fun splitPreservingDelimiter(splitThis: String, regexp: String): List<String>{
    val splitRegex = Regex("(?<=$regexp)|(?=$regexp)", RegexOption.MULTILINE)
    return splitThis.split(splitRegex)
}

//TODO: tbh I might be better off finding a premade markdown formatting thing to do this work for me