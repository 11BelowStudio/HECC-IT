# The Improvements to HECC-IT

Over term 2, I made several improvements to HECC-IT, with these changes being a mix of 'fully planned
out and documented' improvements, and some being 'realizations of a better way of doing things whilst
working on something else'.

## 1.1: The Planned Stuff

I didn't really document much on Jira that I explicitly set out to improve with HECC-IT itself, in fact,
the vast majority of the improvements that I made on it were 'spur of the moment' changes, made whilst
I was in the process of doing something else.

These documented changes were as follows:

* Incorporating HECC-UP and OH-HECC into one program
    * The 'I' in HECC-IT is supposed to stand for 'integrated', but having OH-HECC and HECC-UP as
      separate executable .jar files was not very 'integrated', so I wanted to make both of them
      part of a single executable, meaning I would need to work out some way of launching one with
      the other.
      
    * I chose to make the existing OH-HECC program into the 'main' HECC-IT program, which HECC-UP would
      become a part of.
      
    * The first thing I did was refactor HECC-UP.
      
        * Instead of the `HeccUpGUI` class having a single no-argument constructor which sets up a
          JFrame and the GUI, I rewrote it so the 'main' constructor for HECC-UP would take a JFrame as
          an argument, repurposing that JFrame for HECC-UP's GUI, just like the `OhHeccNetworkFrame`
          class (replacing the existing no-argument constructor with one that just calls this JFrame
          constructor, passing a new JFrame to it).
        
        * I rewrote the parts of HECC-UP that stored references to file locations as `File` objects to
          refer to them via `Path` objects instead, because OH-HECC (and the main menu of HECC-IT)
          used `Path` objects instead of `File`s (due to `File` being a legacy class), and I wanted to
          be able to pass the `Path`s directly into HECC-UP, and have them immediately interoperable.
          
            * I ran the HECC-UP component by itself after making these changes, and it still worked as
              expected with the `Path`s.
              
        * I created two more constructors for the `HeccUpGUI` class.
            * The first one would take a `JFrame` and a `Path` as an argument.
              
                * This would be called if HECC-UP was to be opened by the main menu of HECC-IT.
                * It would call the 'main' constructor of it with that `JFrame`, to set up the GUI.
                * The `Path` passed to it would be a `Path` to the .hecc file that the user would want
                  to export, so, this constructor would then call the `selectedAHeccFile` method (which
                  the logic that happens after selecting a .hecc file was moved to) in order to register
                  this `Path` as the selected .hecc file.
                * This constructor would then get the actual name of the .hecc file (from the `Path`)
                  as a String, and resolve a new `Path` which would be a subfolder of the directory
                  holding the .hecc file, with this subfolder having the same name as the .hecc file.
                  For example, if the hecc file was at `X://bruh/game.hecc`, the new Path would be
                  `X://bruh/game/`. This subdirectory Path would be defined as the chosen export
                  location for the game, via the `selectedAnOutputFolder` method (which the logic
                  that happens after selecting an export folder was moved to).
                  
                * The reason why this constructor pre-selects the .hecc file and the output directory
                  was so the author would not need to waste time selecting both of them individually,
                  as the OH-HECC code already selects/keeps track of a .hecc file. If an author
                  wants to, HECC-UP will still allow them to manually select a .hecc file/export
                  directory automatically. However, if they just want to export it and get it done,
                  they can do that.
                  
            * The second one would take a `Path` and a `Runnable` as an argument.
    
                * This would be called by the 'Export with HECC-UP' button in OH-HECC (which would
                  replace the 'Save and Quit' button).
                  
                * It would call the aforementioned `Path`+`JFrame` constructor of HECC-UP, passing the
                  given `Path` as the Path argument, and a new `JFrame` as the JFrame argument.
                  
                * It would then construct and add a `addWindowListener` to the `JFrame`, which would,
                  upon the `windowClosed` event, run the `Runnable` which was given to this constructor.
                  
                    * The passed `Runnable` would be a reference to the `editingWindowClosed` method
                      in the `PassageModel`, which is used to notify the `PassageModel` that a
                      currently-open editing window has been closed, allowing it to re-request focus,
                      and set the `activity` value back to `CurrentActivity.DOING_NOTHING`, allowing
                      the author to interact with the model again.
                      
                    * Basically, it's the same thing that the `PassageModel` passes to the passage/metadata
                      editor windows.
                      
    * I then reworked the `OhHeccRunner` and `ChooseFile` classes slightly, to turn `ChooseFile` into a
      main menu for 'HECC-IT'.
        * `OhHeccRunner` was renamed to `HeccItRunner`.
            * I also added another method to `HeccItRunner`, that method being
              `boolean openAndHeccUpFileAtLocation(Path heccFilePath)`.
              
            * This method would be given a `Path` to a .hecc file, and construct a `HeccUpGUI`
              using the `JFrame`+`Path` constructor (passing the given `Path` as one argument
              and the`HeccItRunner`'s JFrame as the other argument, so `ChooseFile` could be
              replaced with HECC-UP, within that frame, just like OH-HECC does it).
              
            * I also replaced the 'OH-HECC' text in the `JFrame` with 'HECC-IT'.
              
        * These are the changes I made to `ChooseFile`
              
            * I added a third parameter to `ChooseFile`'s constructor, of type `Predicate<Path>`,
              which would be used to pass a reference to `openAndHeccUpFileAtLocation` to
              `ChooseFile`.
              
            * The 'open existing hecc file' panel had another button added to it, which (again)
              would only be visible after selecting a .hecc file, but would open HECC-UP for that
              .hecc file instead.
              
            * The top text in the JPanel was changed to say 'HECC-IT' instead, to make it
              obvious that this was 'HECC-IT'.
                
            * I also removed the middle bar thing from the GUI, so I could get the 'create new
              hecc file' and 'open existing hecc file' areas of the GUI to be the same horizontal
              size as each other.
              
            * This is what the updated `ChooseFile` looks like:
            
                * ![choosefile not chosen](./final/hecc-it/menu.PNG)
    
                * ![choosefile chosen](./final/hecc-it/menu%20chosen.PNG)
              
    * Finally, I refactored OH-HECC's `PassageModel` to include the option to launch HECC-UP.
    
        * The 'save and quit' button was renamed to be an 'export with OH-HECC' button instead.
    
        * When pressed, it would attempt to save the .hecc file, and would then launch HECC-UP,
          using the `Path` + `Runnable` constructor (giving it the aforementioned `this::editingWindowClosed`
          method reference). Basically, it would operate the same way as the editor windows would.
          
        * However, there was a problem. It was still possible for OH-HECC to produce .hecc code which
          wasn't entirely valid (such as having passages with deleted links, or empty passages), which,
          upon being opened by HECC-UP, would result in it complaining, and the user getting frustrated
          at not being able to export their game. So, I needed to add some additional validation
          into OH-HECC.
          
        * I decided to adapt the routine used for saving the .hecc file to add an extra step: it would
          save the .hecc file as normal, but there would be an extra method in the `GameDataObject` which
          would save another .hecc file, with a `_lastValidVersion.hecc` suffix in the file name. This
          'lastValidVersion' would be the most recent version of the .hecc file which passed some automatic
          validation. If it fails validation, the most up-to-date .hecc file would still have been saved,
          but the 'lastValidVersion' will not be overwritten, and the user would be informed. The validation
          method will also throw a `HeccCeption` if it failed validation, allowing the problem to be shown
          to the author. HECC-UP would only be openable if the validation routine succeeded.
          
        * As part of the validation routine, it would first verify that there were passages, and that
          it actually had a start passage. Then, it would go through each of the passages in the
          passageMap, and would look at the status `PassageStatus` enum of each of them, throwing an
          appropriate `HeccCeption` if the status was not `NORMAL` or an `END_NODE`.
          
        * I realized that I didn't actually have any way of checking whether or not the content of the
          editable passage was empty, nor did I have any way of warning the user about this. So, I added
          another status to the `PassageStatus` enum; `EMPTY_CONTENT`. The method that would update
          the status of a passage would be updated to first check if the passage was entirely whitespace,
          assigning the `EMPTY_CONTENT` status if this was the case. I also updated the recolouring code
          for the `PassageObject` within OH-HECC to make the `PassageObject` turn dark orange if it had
          an `EMPTY_CONTENT` status, allowing the user to easily see it at a glance.

* Idiotproofing the passage content/multiline comment input boxes
    * OH-HECC initially did not prevent an author from writing lines within a passage content/passage
      multiline comment that were passage declaration lines (starting with `::`) and were multiline
      comment declaration lines (consisting of `;;`).
      
    * Because this could lead to invalid .hecc code being produced by OH-HECC, these inputs needed to be
      prevented.
      
    * I rewrote the `setPassageContent` method of the `EditablePassage` to call a new private method,
      called `sanitizeContent`, which would put an `\` before any `::`s or `;;`s at the start of a line
      in the given passage content string, returning the sanitized string. Technically, it puts two `\`s
      before these; the first `\` is there to escape the `\` which is being used to 'escape' the `::`s
      and `;;`s. Both of these `\`s had to be escaped themselves to be put into the replacement
      string in the first place, which is why it looks like it inserts four `\`s in there.
      
    * I unit-tested this by adding a test for this in the [`EditablePassageTest`](../src/oh_hecc/game_parts/passage/EditablePassageTest.java)
      unit-test class, and it worked as expected, escaping the `;;`s and `::`s at the start of lines,
      and leaving any others untouched.
      
* Fixing a problem where all but 1 of the links to a child passage would not update when a child
  passage was moved, but only updating if the parent passage's position was updated.
    * In OH-HECC, when moving a passage, the link objects that point to/from that passage are supposed
      to be updated, such that these passages are still visibly connected. However, I noticed an unusual
      bug where, if a passage had multiple parents, all but one of the parent links would cease to
      update with the position of this passage upon it being moved, but would only update if the parent
      passage was moved.
      
    * For context, when moving a passage, the link objects (the 'links') are updated as follows:
        * All of the current passage's links to other passages are updated.
        * The passages which link to the current passage (having the current passage's UUID in their
          set of linked passages) are found by the `PassageModel`.
        * For each of these parent passages, their `updatePositionOfSpecificLink` method is called,
          with the UUID of the current passage being given as an argument for that method, so the link
          to the passage which is being moved is obtained (as these link objects are held within a map,
          with the UUIDs of the passages they link to being the keys), and that one object is updated.
        * I chose to implement the 'updating other passages' in this way, so I wouldn't be wasting
          computational resources on updating the links which hadn't been changed, which, for a game
          with a lot of links, could get out of hand very quickly.
          
    * After some further investigation, I realized that the links would work as expected until the
      'save' button was pressed, after which they would break, so I would need to investigate the save
      routine to see if that had anything to do with it.
      
    * As mentioned earlier, I used a recursive depth-first strategy in the save routine, keeping track
      of unvisited passages, so I don't end up getting into an infinite loop. As part of this routine,
      the set of linked passage UUIDs for a passage would be obtained, and everything but the
      'unvisited' passage UUIDs would be omitted from this 'linked' set, before going through the
      'linked' set to attempt saving them.
      
    * The problem was that the save routine obtained a shallow copy of the linked UUIDs, not a deep
      copy; the previously-visited passages would be removed from the **actual** linked UUID set for
      a passage, instead of from a copy of it. So, when the `PassageModel` would be looking for the
      parent passages of a given passage later on, it wouldn't notice the parent passages that had
      their link to the currently-moving child omitted, so those links would be left dangling.
      
    * By changing the line of code in the 'save' routine to obtain a copy of each passage's linked
      UUID set (via `new HashSet<>(sp.getLinkedPassageUUIDs())`), this problem was fixed.
      
* Refactoring the `Model` to inherit from `JComponent` instead of from `Canvas`
    * `Canvas` is an `java.awt.*` class, and is a 'heavyweight' GUI component (unlike
      `javax.swing.JComponent`, a 'lightweight' GUI component). Additionally, combining AWT
      and Swing components within a GUI is heavily discouraged. Therefore, I refactored
      the `Model` class to inherit from `JComponent`, a swing component, instead.
      
    * In hindsight, this may not have been a good idea. However, it did mean I could eventually
      omit the `View` class from being used, as I did change the `OhHeccNetworkFrame` to store the
      `View` as a `JComponent` instead. So, as the `View` was showing a single `JComponent`, and as
      it was, itself, being used as a `JComponent`, that made sense.
      

Another bug I documented and dealt with was a problem where the passage content could get parsed
as the line end metadata. I managed to fix it in a rather quick-and-dirty method by splitting the given
'lineEndMetadata' string at the first line break within the constructor for the passage data objects,
and discarding everything in that string which was after that line break. It isn't elegant, but, as
the 'lineEndMetadata' string was only intended for the passage declaration line metadata (not for the
content, which was in a different string), it was sufficient.
      
There was one bug I wanted to get fixed which I didn't manage to document getting fixed. This one
involved the model itself not automatically repaint itself after an editing window was closed.
I eventually managed to fix this in one of the many undocumented improvements, most likely the one
which involved the removal of the redundant `View` object (allowing the `Model`'s `repaint` to call
back to the `repaint` method of the frame holding it).

One improvement I had documented intent to make, which I didn't actually make, was some form of
'passage search' functionality for OH-HECC, allowing the user to navigate to a certain passage of
their choosing. This was left unimplemented mostly because I couldn't decide how to actually present
this option to the user. I could relatively easily use Streams to filter the `objectMap` for passages
that have a given name/given tags, and I could have potentially automatically moved the viewport to
put the currently 'found' passage in the middle of the viewable area. However, I wasn't sure how
to allow the user to make use of this search stuff. I considered opening another JFrame with a text
input field and some JButtons that could be used to navigate through the 'results' found, but, that
solution would have been a bit too unwieldy for an author to want to use it, and the author would
probably be able to find it a bit more efficiently if they were to just look at the .hecc file itself.

## 1.2 The undocumented changes to HECC-IT.

The other improvements to HECC-IT were not explicitly created as issues on Jira, but, instead,
were mostly made as spur-of-the-moment changes, whilst working on another task (with many usability-
related improvements being made during the creation of *Backblast*, and several of the 'I've just
realized how to do this/found a better way of doing this'). Therefore, I have divided this section
into a 'user experience' section, and a 'behind-the-scenes' section.

### 1.2.1: HECC-IT User Experience

* Changed the `done` ('Save and Exit') buttons at the bottom of the `PassageEditorWindow` and
  `MetadataEditorWindow` to save the updated inputs into the `PassageEditingInterface` and
  `MetadataEditingInterface` objects themselves, before closing the window. If any of the inputs fails
  validation, the user will be notified, and that window will not be closed. This change was made
  because I couldn't be bothered to keep individually clicking each 'update *x*' button before I
  closed the editor windows.
  
* Added a red outline to any passages in the `PassageModel` that have a `noreturn` tag. I did this via
  a method in the `PassageEditingInterface` which would return true if it had `noreturn` within its tag
  list, and, if it was true, a boolean attribute of the `PassageObject` would be set to true. This
  would cause a red rectangle (an outline) to be shown around the `PassageObject` in the draw operation.
  This change was made because I was starting to get confused between what was/wasn't a point of no return.
  
* Added some very nice icons for the JFrames within HECC-IT, with variations for HECC-UP (whenever the
  HECC-UP JFrame is displayed) and OH-HECC (used whenever the OH-HECC icon is displayed). This was
  done because I was sick and tired of seeing the generic default JFrame icons all the time.
    * These icons are image files, stored in [src/assets/imageAssets](../src/assets/imageAssets).
    * They are imported via `getResource` so they can be included in the final .jar, 
      and are stored within [`utilities/ImageManager`](../src/utilities/ImageManager.java).
    * Specifically, they are stored in `List<Image>`s, because the `setIconImages` method of the
      `JFrame` class takes a list of `Image`s (and/or subclasses of `Image`s) as an argument, with
      this list consisting of icon images of different sizes, for use in different parts of the `JFrame`.
    * The very small, square, `HECC` icon is for the top-right corner of the `JFrame`s. It's 16px by
      16px, which is the largest possible size for these icons, so, even though it's barely readable,
      it's the best that I could do.
    * The other icons are intended to be used as system tray icons (of 32\*32, 64\*64, and 128\*128
      pixels).
    * The `setIconImages` method is called by `HeccItRunner` with the `HECC_IT_IMAGES` list, to give
      the frame some 'HECC-IT' icons. The `OhHeccNetworkFrame` calls this method again with the
      `OH_HECC_IMAGES` list (for 'OH-HECC' icons), and the `HeccUpGUI` also calls it, with the
      `HECC_UP_IMAGES` list (for 'HECC-UP' icons).
  


### 1.2.2: HECC-IT behind the scenes