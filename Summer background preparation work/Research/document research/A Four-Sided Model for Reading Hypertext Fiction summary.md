
# The important bits

* Abstract
    * 'electronic literature, just like print literature, prefigures different modes of reading'
    * identifies/describes 'Four modes of reading'.

* Identifies that prior exploration about the question of 'how do we read electronic literature or literary hypertext?' has 'two problems':
    * They focus on 'readers with little or no experience of literary hypertext'
        * Doing that 'tells us more about these readers' ignorance on hypertext, and less about any significance on hypertext literature'
    * They don't 'take into account the response structures prefigured in hypertext fiction'
        * 'broad spectrum' of genres in hypertext
            * 'network fiction'
                * Afternoon (Michael Joyce, 1990)
                * From lexia to perplexia (Talan Memmott, 2000)
            * 'axial hypertexts'
                * I am a singer (Megan Heyward, 1997)
                * The Glass snail (Milorad Pavic, 2003)
    
## The modes

### Semantization
* tl;dr
   * 'The reading is done when a meaning is found'

* 'based on predictability, confirmability, and the reader's intentionality'

* Of day, of night (Megan Heyward, 2004)
    * Game split into two parts
        * Day
            * Must read all the screens in this part to move to the next part
            * All sections of this part 'are collected in a single  node with a graphic map and "words that yell"'
            * How it works
                * Presented with 'three links and sections' at the start
                * Reader clicks them, reads them, then will 'get access to several other links and sections'
                * The links 'are organised in such a way so that the reader might recognize a pattern'
                * As the links become available, 'the reader might use the spatial organization of the links to read the information in a particular order'
            * Reader only gets access to part 2 ('night') once all of 'day' has been read/visited.
        * Night
        
* Who this is geared towards
    * 'Achievers'
        * 'Goal-oriented strategy'
    * Semantic reading 'prefigures a search for meaning', so 'construction of meaning is then a goal'
        * 'Of day, of night' essentially 'guides the reader through' this mode of reading, making it 'not easy to stray from'


### Exploration

* tl;dr
    * 'reader is driven and played by chance, and not by confirmation and predictability'
    * Aiming 'to gain experiences through exploring the hypertextual world'

* Facilitated 'when the text encourages or opens up for random exploration and not for one particular way of reading'
    * 'does not recommend one reading path over another'
    * Reader cannot 'predict the consequences' or 'control the narrative'
    * Instead, it 'guides us towards the unsafe, unpredictable route'

* Who this is geared towards
    * 'Explorers'
        * 'favours those players who are oriented towards exploration, and who find pleasure in having the game surprise them'

### Self-reflection

* tl;dr
    * giving 'the reader an opportunity to play a role'

* Facilitated by
    * Allowing the reader to be 'involved in a fictive character, and begins to socialise with this character'
    * Letting the player 'play a role, being someone else or being somewhere else'

* Who this is geared towards
    * 'socialisers'
        * Primary goal of these players 'is to socialise with others through the game's communicative facilities'
        * Socialisers 'must both play a role and activate his own code'
        * The 'socialiser and the self-reflexive reader are thereby involved in role-playing'
    * Enjoyment comes from 'a self-enjoyment in the enjoyment of being someone else or being somewhere else'
        * Reader 'becomes aware of his own codes, his own experiences and expections'
            * This 'turns the reading into a self-reflective mode of reading'


### Absorption

* tl;dr
    * The reader is 'in a condition of confusion'
    * Therefore, all the player can do is 'be absorbed in a play of textual elements'
    * Basically the game overloads the player and makes them have a bit of a meltdown

* Facilitated by
    * the literature 'stages a break up and gives no other option than to slip into an in-between position'
    * Player's 'effort to discover meaning... or play a role... falls apart'

* Who this is geared towards
    * The 'experience and outcome' of absorbtive reading is similar to 'the outcome enforced by Killers'
        * 'Killers sabotage the game', like how absorptive approach is sabotaged by the nature of a hypertext game
    * Characterized 'by the experience of a lack of control, and a lack of knowledge on how to approach the text'
        * Either 'distress and frustration, or pleasure and bliss'

## Conclusions of the paper

* Readers tend to drift between these modes when reading hypertext fiction
* Understanding the modes of reading hypertext is useful for research on the reading of hypertext literature
    * Concerns real and intended readers
    * Helps to identify what causes the confusion in certain readers of hypertext
        * And how to avoid it

## What this means for my project

* The 'game' component
    * Should consider the potential modes of reading it when making it
        * Semantization
            * Is my game going to involve exhaustively reading it, in order to build up a meaning?
            * I guess there are multiple ways of this happening
                * *Of Night, Of Day*: does this in the 'day' section by forcing players to click on every node, adding more nodes, then building up meaning
                * The *Zero Escape* series (written/directed by Kotaro Uchikoshi): Games have branching narratives, but all narrative branches have to be played to ultimately reach the 'true endings' of the games (aforementioned endings would be completely incoherent without viewing the rest of the endings)
        * Exploration
            * Is my game going to basically allow the player to choose a path of their own, not guiding them down a certain path, and allowing a player to be surprised by what they find?
                * Will there be any sort of 'hidden paths' hidden anywhere for an explorer to, well, explore?
                * Is the tool going to have support for implementing that sort of stuff?
        * Self-reflection
            * Is my game going to allow the player to have an opportunity to role-play?
                * Putting player in the situation, addressing the player directly, etc?
        * Absorption
            * Is my game going to fly in the face of all common conventions of story telling and force the player into the absorptive state?
                * Probably not, seeing as the examples given in the paper generally have a very unconventional software structure, and it might be a bit difficult to write the necessary functionality for overwhelming the player into the tool that I will ultimately be using to produce my game
* Tool component
    * Would need to add some functionality to facilitate those modes
        * Semantization/Self Reflection
            * Checking visited nodes (both for prior choices, and whether all are visited or not) could be relevant
                * Checking for whether or not the game's been exhaustively visited
                * Allowing choices to have consequences
        * Exploration
            * RNG/hidden links/changing content
                * Elements of unpredictability/surprises
        * Absorption
            * RNG/changing content/potential to completely overwhelm the player
                * Stuff like that might be helpful for forcing the reader into the absorptive state

# Source

* H. K. Rustad, “A four-sided model for reading hypertext fiction” Hyperrhiz, no. 6, pp. 1–1, 2009.
    * http://hyperrhiz.io/hyperrhiz06/essays/a-four-sided-model-for-reading-hypertext-fiction.html
    * doi:10.20415/hyp/006.e01