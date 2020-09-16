# Notes

* Abstract
    * 'set of authoring steps'
        * 'Ideation'
        * 'Training/Support'
        * 'Planning'
        * 'Visualising/Structuring'
        * 'Writing'
        * 'Editing'
        * 'Compiling/Testing'
        * 'Publishing'
    * 'For challenges we identified 18 codes under 5 themes, falling into 3 phases of development'
        * 'Pre-production'
            * 'User/Tool Misalignment'
            * 'Documentation'
        * 'Production'
            * 'Complexity'
            * 'Programming Environment'
        * 'Post-production'
            * 'Longer-term issues related to the narrative's Lifecycle'
    * 'the authoring problem... is rooted in the common misalignment between'
        * 'the authors' expectations and the tools capabilities'
        * 'the fundamental tension between expressivity and complexity'
        * 'invisibility of the edges of the process to researchers and tool builders'
    * 'a less monolithic view of authoring would allow designers to create more focused tools and address issues specifically at the places in which they occur'

* Authoring steps
    * Ideation
        * 'the stage of creative thinking'
        * 'the birth of ideas'
        * 'the coming together of story components to build the narrative foundation of a story'
    * Training/Support
        * 'guiding the author on how to use the authoring tool via examples, guides, or tutorials'
    * Planning
        * 'Sketching out the plot(s) of a story, creating characters, drafting events, and making notes'
    * Visualising/Structuring
        * 'Graphically creating, studying, and revising the structure of a story'
            * '(meaning the relationships between events, characters, chapters or scenes)'
            * 'granting an overview of the whole'
    * Writing
        * 'Inputting content that is part of the narrative presented directly to the reader (typically the text)'
            * Not 'any specialised language of the tool'
    * Editing
        * 'Revising, augmenting, and changing the content and structure of the story'
            * 'embedding media in the text, changing stylesheets, keeping a revision record, or updating the structures or relationships between nodes'
    * Compiling/Testing
        * 'Checking that the design is complete and error free'
            * 'without any loose ends or empty nodes'
        * 'in the case of a tool with its own vocabulary'
            * 'syntactical errors or any other coding faults'
    * Publishing
        * 'exporting a story format and making it available'
        * 'distributing it through a venue where readers will be able to access and read it'

* The problems
    * Pre-production
        * User/tool misalignment
            * 'Conceptual misalignment'
                * 'misalignment between what the authors wanted to achieve, and what the tool could actually do'
                    * Need to make it clear what the tool can and can't do
            * 'Expertise misalignment'
                * 'in some cases users were clearly attempting to do things that the tool was not designed for'
                    * Need to make it clear what the tool was not designed for
            * 'Ontological misalignment'
                * 'vocabulary and structures in the tool did not match the mental model of the user'
                    * Need to ensure the vocab/structures make sense
            * 'Workflow misalignment'
                * 'tool required the author to work in an order that they were uncomfortable with'
                    * Should allow the author to have some flexibility in their work order
        * Documentation
            * 'The known unknowns'
                * 'authors knew what they wanted to do, were aware that the tool could do it, but could not figure out how to make it happen'
                    * Ensure that I clearly document all the functionality that my tool does have, and how to do it.
            * 'The unknown unknowns'
                * 'they did not know, and found it hard to discover, whether the tool had a particular capability or not'
                    * Ensure that it's clear what the tool is/isn't able to do
    * Production
        * Complexity
            * 'Content tracking'
                * 'internal consistency of the text'
                    * Add some method for users to take notes?
            * 'Variable tracking'
                * 'external consistency of the state machine around the text', 'hard to track variables across nodes'
                    * Could potentially alleviate this by requiring variables to be declared in advance, so it's harder to lose track of them?
            * 'Scalability'
                * 'where the sheer size of the text (and any behaviour specifications) simply become overwhelming', 'actually testing them and getting them to work was a bit difficult'
                    * Not sure how to address complaints regarding the size of the text
                    * Testing scalability could be addressed by allowing the user to change the start node to whichever node they want to use instead
            * 'Versioning'
                * 'maintain working versions of a story as it develops, and guard against any potential loss of work'
                    * Not sure how to achieve this
                        * Maybe allow git integration within OH-HECC?
        * Programming Environment
            * 'Lack of programmable environment'
                * 'tools that we have now are a little bit more fragmented... you have to step out of the tool to do scripting... you have to have several tools'
                    * Maybe should try to integrate HECC-UP directly into OH-HECC, so users don't need to use 'several tools'
                    * Also ensure that all the options supported by HECC can be done via OH-HECC instead.
            * 'Separation of content and behaviour'
                * 'complicates editing and testing when things such as spelling errors and machine language errors are not differentiated'
                    * Could try to work out a way of clearly differentiating between code and outputs within the syntax of the nodes
                        * Maybe OH-HECC would need to use regexes to style the node content appropriately to distinguish between code and not code?
            * 'Lack of debugging tools (testing)'
                * Nothing to 'clearly state what a programmable error may be when a story doesn't compile', lack of 'good tools to check if there are problems with your code'
                    * HECC-UP currently throws exceptions when there's an error or something in the HECC code whilst it's compiling the HECC code, but that's about it so far.
                        * Some form of validation for the links, passages, passage content and such is present in the HECC-UP prototype, however.
    * Post-production
        * Lifecycle
            * 'Profitability'
                * Distribution issues mean 'a resulting problem with profitability from their work', 'challenge seems to be getting any money out of it'
                    * Unsure how I can address these issues, as I don't have the power nor the money to address this problem head-on
                        * However, seeing as the output HECCIN Game is a compiled version of the input HECC code, I guess that, if someone doesn't wish to publish the source HECC code for their HECCIN Game, they can keep it, so it'll be more annoying for someone to reverse-engineer their HECCIN Game
            * 'Platform support'
                * 'publishing platforms not being able to fully support the functionality of a story'
                    * Need to work out what exactly the minimum requirements for a browser are to run a HECCIN Game (what javascript version is needed etc)
            * 'Maintenance'
                * 'the difficulty of making updates to a story after publication'
                    * It isn't possible to change the existing HECCIN Game after it's been compiled, however, you can use (and edit) the HECC code to produce an updated version of the HECCIN Game.
            * 'Curation'
                * 'lack of spaces that could provide curation for all these works for longevity and accessibility'
                    * I know I don't have the resources to set up a curation playform myself
                    * However, by ensuring that a HECCIN Game is compliant with the Treaty of Babel, it could be archived on the Interactive Fiction Database (IFDB)
            * 'Distribution'
                * 'lack of venues for distribution', 'it's a bit difficult for the end user... people are becoming totally adverse to doing other work other than clicking on a link and getting the game to play'
                    * Unsure how I can solve the distribution problem with my tool, as I don't think I have the resources or the sanity to set up a distribution platform myself.
                    * However, a HECCIN Game is playable via browser, entirely clientside, so there isn't any sort of work needed from the player to get the game to play
                        * So it should be easily uploadable to and playable on itch.io, at the very least

* What this means for my tool
    * My tool should have
        * Very clear documentation (pre-production issues)
            * What HECC can do
                * What HECC is designed to do
                * If HECC can do it
                    * Make it clear that HECC can do it
                    * Explain how to do it
            * What HECC cannot do
                * What HECC is not designed to do
                * If there is something that HECC cannot do, which a user may expect to be able to do
                    * Point out that HECC cannot do it
                    * Maybe explain a workaround, **but explain that it's not going to end well**
            * Vocabulary for HECC-IT should make sense
                * Users shouldn't have to make sense of unclear syntax
                * Stick to a particular glossary of terms
        * Functionality for the tools
            * Content Tracking
                * Allow users to make notes of some description within their HECC code
                    * Potentially allowing a string of 'notes' metadata for a passage to be declared after the end of a passage's content?
                        * Would pretty much give an explicit start/end for the passage text contents
                        * This metadata would be preserved and usable by OH-HECC, but ignored by HECC-UP.
            * Variable Tracking
                * Potentially require variables and such to be explicitly declared at the start of the HECC file, and these definitions would be used by HECC-UP to validate the HECC code, and would be passed (along with other metadata) to the HECCER by HECCED.js
                    * 'Metadata' object definitely could clean up some relatively untidy bits of HECC-UP/HECCER
                    * Also, gamestates could keep track of the state of all declared variables at each state of the game, so it's easier to keep track of all of them
                        * No digging through the gamestatestack to obtain the value of a variable, as it'll be right there on the current gamestate
            * Testing (scalability/workflow misalignment)
                * I guess having the option to change the given starting node (even by editing HECCED.js in a compiled HECCIN Game) would help with testing somewhat
                * However, I guess that having to compile the HECC code to produce a new version of HECCED.js every time might get uncomfortable fast
                    * But there's probably nothing to stop OH-HECC from letting a user view the HECCED contents of a particular passage (especially if HECC-UP and OH-HECC share the Passage object), to copy and paste into getHecced() in HECCED.js to test something quickly.
            * Version control
                * I theoretically could include git integration within OH-HECC (version control for the .hecc file)
                    * but that would definitely have to be a stretch goal
                    * users could back it up on git themselves in the meantime I guess
            * Programmable environment (integration)
                * Bundle OH-HECC and HECC-UP into the same tool, so users don't need to use multiple programs (as the two components will be part of the same program)
                    * Users who just want to use HECC-UP still can do that
                    * Ensure that OH-HECC can be used to do anything that the HECC language is capable of doing
                    * I guess that the parsing process of OH-HECC and HECC-UP could be shared
                        * Constructing passage objects from the .hecc file
                            * HECC-UP then parses the passage objects into HECCED data, and exports the HECCIN Game
                            * OH-HECC then allows the users to edit the passage objects via a GUI, saving the new state of the passage objects in .hecc format
                        * The Passage object already has some functionality that could be useful for OH-HECC already.
            * Separation of content and behaviour
                * Not sure about this one
                    * If I have no special scripting stuff, there won't be any content and behaviour to seperate, y'know?
                    * Should aim to make it explicitly clear what stuff is 'written content' and what stuff is 'code stuff'
                        * Maybe OH-HECC could have regexes to identify code stuff/not code stuff, and format the two differently?
            * Debugging tools
                * HECC-UP currently complains about big problems with the .hecc code it's given
                * Perhaps OH-HECC should have safeguards to prevent users from entering any invalid .hecc code?
                    * Replace certain special characters in the user input with escaped versions?
                * I guess that an option to export/run a HECCIN Game in 'debug' mode (with a visual, interactive, representation of the gamestatestack might be useful)
        * Post-production functionality
            * Curation
                * Make it somewhat 'curatable' by ensuring compliance with the Treaty of Babel
            * Maintenance
                * Authors can use HECC-UP to produce updated versions of their game.
            * Platform support
                * Work out what exactly the minimum system requirements that are needed for the HECCER/HECCED to run are, and then update the 'game broke' message for index.html to explicitly state these minimum requirements.
            * Distribution
                * Making the HECCIN Game .html/.js (on browser, 100% clientside): easy to host (no serverside processing), easy to play (just need to click on the link to start playing and you're playing it)
                    * But I don't have the resources to set up a distribution platform for people's HECCIN Games (assuming anyone's going to use HECC-IT, ofc)
            * Profitability
                * Not sure how I can inherently address this problem authors have.
                    * Sure, it'll be a pain in the arse to reverse-engineer a HECCIN Game into raw .hecc code
                    * But, ofc, that doesn't guarantee profitability or anything like that.
   

# Source

* S. Kitromili, J. Jordan, and D. E. Millard, “What authors think about hypertext authoring”, in *Proceedings of the 31st ACM Conference on Hypertext and Social Media*, 2020.
    * https://dl.acm.org/doi/10.1145/3372923.3404798
