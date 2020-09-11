# Treaty of Babel research

## Provisions of the treaty

* Aims (section 1.2)
    * ISBN-like unique ID numbers for story files
    * Standard format for cover art/metadata
    * Web server to provide those for given ID number
    * Command-line tool to identify/extract data for story files in any format
    * Reference software for a format-neutral API for reading story files + removing 'wrappers'
* Other systems/tools besides the initial signatories are 'welcome to join' (section 1.3)



## Requirements for systems

* Provision of metadata/covert art (section 2.1)
    * Metadata (2.1.1)
        * **metadata is required**
        * Minimum
            * Author should be asked to specify
                * Name of the author
                    * Can set this to "Anonymous" if author did not specify.
                * Title of the work
                    * Can set this to "An Interactive Fiction" if author did not specify.
        * Additional metadata
            * Can provide extra metadata if wanted
    * Cover art (2.1.2)
        * **cover art is optional**
        * Requirements for cover art if supported (constraints that must be programmed in)
            * Single square/rectangular JPEG or PNG image
                * No transparency allowed if PNG
            * Each side must be no shorter than 120 pixels
            * Aspect ratio must be between 1:2 or 2:1
        * Guidelines that authors should follow (constraints that don't need to be programmed in, but authors should be aware of)
            * Cover art for new works should be square
                * Rectangles only supported due to legacy box art
            * Ideal dimensions are 960 * 960 px
            * Height/width should not exceed 1200 px
            * Should be designed as a thumbnail
                * Image will be shrunk so the longest side will not exceed 120px when displayed
            * Cover art should be SFW
                * No obligation for works with obscene covers to be archived.
* IFID unique identifier (section 2.2)
    * What it is
        * Each published work of Interactive Fiction will have a unique ID code: IFID
            * like ISBN, but no central authority handing out numbers
        * Design systems will be allowed to generate an IFID for works, but using a standard unique-ID-number algorithm
        * Each IFID must be generated to be **universally unique**
            * Not just unique among projects made by a user or with a certain design system
        * Structure
            * 8-63 characters, digits+capital letters+hyphens.
        * Consistency
            * IFID associated with story, **not** specific story file compiled from the story
                * Re-release with bug fixes: same IFID
            * Porting game from one system to another: new IFID for the port
    * IFIDs for new projects (2.2.1)
        * Design system must assign each new work an IFID.
    * Legacy projects (2.2.2)
        * Executables (2.2.2.9)
            * FORMAT-[MD5 checksum of file]
            * Applicable to Windows executables (MZ-), Linux ELF (ELF-), Java bytecode (JAVA-), Amiga executables (AMIGA-), UNIX scripts (SCRIPT-), MaxOS X binary (MACHO-), and pre-MacOS X mac binary (MAC-) 
    * Projects outside of agreement (2.2.3) 
        * IFID is the MD5 hash code of the story file/executable.
            * hex characters a-f written in uppercase (A-F)
    * **Universally Unique Identifier (UUID) algorithm should be used (RFC 4122)**
        * RFC 4122: https://www.ietf.org/rfc/rfc4122.txt
        * https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html
* 'Babel' tool (section 2.3)
    * Written in ANSI-standard C
    * Supposed to examine a (possibly wrapped) story file, read IFID(s), metadata, and story art.
        * 'Wrapper' in this context
            * Format that can be used natively by one or more interpreters/browsers
            * Include a story file in a format that can exist/be played without the surrounding wrappers
        * Only wrapper currently in use is 'blorb' (not really relevant to the current task at hand)
    * **Code to handle story formats is responsibility of design system producing that format**
    * Contributing to Babel (Section 2.3.2)
        * Must contribute a file containing portable, rights-free C routine to provide babel with support for rule-time format.
            * assume that int32 is 32-bit or large signed integer
    * Babel tool outputs an .iFiction file for the input story file

## Contributing the C routine with support for the runtime format

* Routine used by babel must be in the form: (2.3.2)
    * ```
      int32 SYSTEM_treaty(int32 selector,
         		void *story_file, int32 story_file_extent,
         		void *output, int32 output_extent)
      ```
        * Prefix 'SYSTEM_' would probably need to be replaced with 'html_', considering section (5.5.2) of the treaty
        * Arguments
            * Selector may be one of these constants (state of pointers on entry is given)
                * 
                    | selector value  | *story_file    | *output    |
                    | :---------|:----------- | :-------- |
                    |GET_FORMAT_NAME_SEL |NULL|not NULL|
                    |GET_STORY_FILE_EXTENSION_SEL|NULL| not NULL |
                    |GET_HOME_PAGE_SEL|NULL|not NULL|
                    |GET_FILE_EXTENSIONS_SEL|NULL|not NULL|
                    |CLAIM_STORY_FILE_SEL|not NULL|NULL|
                    |GET_STORY_FILE_METADATA_EXTENT_SEL|not NULL|NULL|
                    |GET_STORY_FILE_COVER_EXTENT_SEL|not NULL|NULL|
                    |GET_STORY_FILE_COVER_FORMAT_SEL|not NULL|NULL|
                    |GET_STORY_FILE_IFID_SEL|not NULL|not NULL|
                    |GET_STORY_FILE_METADATA_SEL|not NULL|not NULL|
                    |GET_STORY_FILE_COVER_SEL|not NULL|not NULL|
            * Where a pointer is NULL, extent is 0
                * Where a pointer is not NULL, it must point to byte-accessible memory, with size of the given extent for it
            * '*story_file' points to complete story file, fully loaded in memory
                * Routine must not write to this
                * Will be the actual story file
            * '*output' points to buffer where text/other data can be written
                * Undefined on entry
                * If text is written there
                    * it should be null-terminated
                    * **do not write beyond extent**
                        * guaranteed to be at least 512 bytes
                    * Encode it with UTF-8 unicode
        * Return value
            * Constant value returned if there's a problem
                *'NO_REPLY_RV'
                    * used if there is no meaningful return value, or if requested data cannot be found in story file (with no indication that story file is broken/invalid)
                    * routine not required to check for validity, only report any problems it hits
                * 'INVALID_STORY_FILE_RV'
                    * Something went wrong, story file broken/invalid.
                * 'UNAVAILABLE_RV'
                    * Support for selector not provided/selector not recognized.
                * 'IMPROPER_USAGE_RV'
                    * Selector believes it has been called with improper parameters
        * What each 'selector' does
            * GET_FORMAT_NAME_SEL
                * Copies design system format into output buffer
                    * Value as defined by <format> (5.5.2)
                * NO_REPLY_RV returned
            * GET_STORY_FILE_EXTENSION_SEL
                * Outputs single best extension for given story file to output buffer
                * Returns length of output string (0 only if it should have no extension)
                    * Or an error value
            * GET_HOME_PAGE_SEL
                * URL for design system's home page entered into output buffer
                * NO_REPLY_RV
            * GET_FILE_EXTENSIONS_SEL
                * Comma-separated list of filename extensions associated with design system (in lowercase) in output buffer
                * NO_REPLY_RV
            * CLAIM_STORY_FILE_SEL
                * NO_REPLY_RV if this is a story file produced by this system
                    * else INVALID_STORY_FILE_SEL
                * only need a casual, superficial, check
            * GET_STORY_FILE_METADATA_EXTENT_SEL
                * return extent (in bytes) of story iFiction record (plus 1)
                    * NO REPLY_RV if no data
                * called immediately before GET_STORY_FILE_METADATA_SEL
            * GET_STORY_FILE_METADATA_SEL
                * Copies story metadata in iFiction format to output buffer, along with zero termination byte
                * returns total number of bytes copied
                    * no metadata: NO_REPLY_RV
                    * buffer too small: IMPROPER_USAGE_RV
            * GET_STORY_FILE_COVER_EXTENT_SEL
                * return extent (in bytes) of cover art image in story file (if there is one) +1
                * NO_REPLY_RV if no cover art
            * GET_STORY_FILE_COVER_SEL
                * like GET_STORY_FILE_METADATA_SEL but the cover art goes in the output buffer
            * GET_STORY_FILE_COVER_FORMAT_SEL
                * JPEG_COVER_FORMAT if cover is JPEG, PNG_COVER_FORMAT if cover is PNG, NO_REPLY_RV if no cover art.
                * No other cover art formats are legal
            * GET_STORY_FILE_IFID_SEL
                * Finds IFID(s), copies them as comma-seperated list into output buffer
                * Returns number if IFIDs in list, or NO_REPLY_RV if no IFID could be found
* How it determines format of story file (2.3.3)
    * If a blorb, blorb executable chunk checks format of real story inside
        * uses that format's CLAIM_STORY_FILE_SEL selector to see if it's legit or not
    * If not a blorb, it treats the filename extension as a clue
        * Goes through all formats, polling each with GET_FILE_EXTENSIONS_SEL, to see which format it could possibly be in
            * Formats with matching extension added to 'likely' list, others added to 'unlikely' list
        * Then offers the story file to each likely format in turn with CLAIM_STORY_FILE_SEL
            * First format to claim the story file is the 'winner'
            * If it's unclaimed by the 'likely' ones, it then polls the 'unlikely' list
                * If it remains unclaimed by the 'unlikely' list, it's format is "unknown"
            * Formats polled in 'popularity' order (formats with most works first)
* Eventually outputs an .iFiction file for the input story.

## Guidelines for 'interpreters' (programs used to run the game) and 'browsers' (programs offering a choice of story files) (section 3)
* Use bibliographic data if available, even if it's just to give windows/saved games sensible titles, or for an 'about this game' option
* If possible,
    * Show a preview of the game based on cover art and bibliographic data before play begins
    * (other guidelines seem very much targeted towards browsers)
* If fetching cover art from IF archive, **the fetched image must be cached**.

## Some of the important metadata used within .iFiction formats  (section 5)

* If stored in a file, it must have the '.iFiction' format
    * Filename should be ```IFID.iFiction``` (replace 'IFID' with the IFID of the work)
* UTF-8 unicode
* ```<?xml version="1.0" encoding="UTF-8"?>
	 <ifindex version="1.0" xmlns="http://babel.ifarchive.org/protocol/iFiction/">
        <!-- Bibliographic data recorded by Inform 7 build 3F47 -->
		 [seqyebce of <story> records]
	 </ifindex>
     ```
* Data on individual stories are within ```<story>...</story>``` tags
    * Mandatory metadata
        * ```<identification>...</identification>``` tags (Defined from design system)
            * ```<ifid>...</ifid>``` The IFID of the work
            * ```<format>...</format>``` The format of the work (one of the following: zcode, glulx, tads2, tads3, hugo, alan, adrift, level9, agt, magscrolls, advsys, html, executable)
        * ```<bibliographic>...</bibliographic>``` tags (defined by author)
            * Mandatory tags
                * ```<title>...</title>``` The title of the work
                * ```<author>...</author>``` Who the author is
            * Optional tags
                * ```<language>...</language>``` what (human) language the work is in
                * ```<headline>...</headline>``` quasi-subtitle for the game
                * ```<firstpublished>...</firstpublished>``` date of first publication: YYYY or YYYY-MM-DD
                * ```<genre>...</genre>``` what genre the author thinks their work is
                * ```<group>...</group>``` Places the work of IF as belonging to that group of works
                * ```<forgiveness>...</forgiveness>``` basically how forgiving it is on the Zarfian scale
                * ```<description>...</description>```Author's outline of the work. Plaintext, but may contain ```<br/>``` tags (no other tags)
                * ```<series>...</series>``` and ```<seriesnumber>...</seriesnumber>```
                    * series shows what 'series' of works this work is from.
                    * seriesnumber shows its position in the series (non-negative integer)
                        * seriesnumber must be accompanied by series
                            * series doesn't need a seriesnumber
    * Optional metadata
        * ```<resources>...</resources>``` used for other files intended to accompany the story/available to any player. made up on 'auxiliary' blocks
                * ```<auxiliary><leafname>```filename```</leafname><description>```description of the resource```</description></auxiliary>```
        * ```<contacts>...</contacts>``` can give a home page and author's contact email. should only be given if they will be indefinitely valid.
            * ```<url>```homepage link```</url>```
            * ```<authoremail>```author's email```</authoremail>```
        * ```<cover>...</cover>``` is mandatory only for stories with a cover picture
            *```<format>...</format>``` format for cover. 'jpg' or 'png'
            * ```<width>...</width>``` and ```<height>...</height>```: width/height in px (positive integer)
            * ```<description>```description of image```</description>```

## What I'd need to do
* If I'm outputting files in a .html format, I could theoretically get away with just entering an IFID tag
    * Call Java.UUID.randomUUID() to obtain it when creating a new project
        * https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html
* Only ensure that the metadata supported by the .iFiction format is supported by my system
* It appears that .html is already supported by the Babel stuff (somewhat), so I probably don't need to create a c subroutine for my stuff (as long as it outputs in .html)
    * Simply need to include the IFID as a comment in the HTML file [3]
        * ```<!-- UUID://IFID goes here// -->```



## Sources etc
* [1] Interactive Fiction Technological Foundation. "The Treaty of Babel". ifarchive.org https://babel.ifarchive.org/ (Accessed Aug. 10, 2020)
* [2] *A Universally Unique IDentifier (UUID) URN Namespace*, RFC 4122, Network Working Group, July 2005 [Online]. Available: https://tools.ietf.org/html/rfc4122
* [3] "IFID - IFWiki". ifwiki.org. http://ifwiki.org/index.php/IFID (Accessed Aug. 10, 2020).