# src/assets

If you wanted code, you are probably not in the right place.

This folder (and its subfolders) contain assets which are used within HECC-IT, and packaged within the .jar (or the executable),
and used whilst HECC-IT is running.

This is what each of the subfolders contain:

* [Image Assets](/imageAssets/)
    * Holds the images used as the application icons etc. for HECC-IT (and HECC-UP, and OH-HECC).
        * Not critical to the performance of HECC-IT (but they certainly help to make it look a lot less unpolished)
* [Text Assets](/textAssets/)
    * Holds the premade .js and index.html files which are replicated in the output HECCIN' Game produced by HECC-UP.
    * Specifically
        * heccer.js
            * replicated as-is in the output.
            * this is effectively the 'production' version of heccer.js. I probably should minify it at some point.
                * Development on heccer.js should be performed within and tested in the [src-js](../../src-js) folder instead.
        * showdown.min.js
            * replicated as-is in the output.
            * From https://github.com/showdownjs/showdown/blob/6ee6d8c9d92e927f55a5714c51dccad1b598e71f/dist/showdown.min.js
                * Used under the terms of the MIT license (as showdown.js is distributed under
                [that license](https://github.com/showdownjs/showdown/blob/6ee6d8c9d92e927f55a5714c51dccad1b598e71f/LICENSE))
        * index.html
            * replicated almost as-is in the output, but it holds a few placeholder lines which are overwritten with some of the game-specific
            metadata by HECC-UP whilst as outputting it.
    * The other files are not actually used.
