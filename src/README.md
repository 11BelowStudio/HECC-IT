# **Welcome to the source code of HECC-IT!**

Welcome to the inner workings of HECC-IT, where you can see all the things that can and probably will make you shout
'HECC' and other words along those lines!

I have separated the source code into packages to make it relatively easy to tell what thing contains what.

## The things that are not source code but have to be here.

* [META-INF](./META-INF/)
    * This holds the manifest for building HECC-IT as a .jar file.
* [assets](./assets/)
    * This holds
        * The prebaked .html and .js files which are used by HECC-UP when outputting a heccin' game
            * index.html
            * heccer.js
            * showdown.min.js
            * **These are to be treated as the 'production' versions of the files**.
        * The icons and such used by the jframes etc in HECC-IT,
          for the sole purpose of making it look less generic and crappy.

## Sub-packages

* [heccCeptions](./heccCeptions)
    * The custom exceptions used by HECC-IT.
        * ~~the joke is that they're exceptions but they involve hecc, and the 'ex' in 'exceptions' sounds like 'hecc'~~
        * These are thrown when there's a problem with the structure of the games a user is trying to make.
* [hecc_up](./hecc_up)
    * The HECC Ultra Parser.
    * The files in this package are responsible for reading a .hecc file and outputting it as a heccin' game.
        * A lot of the code in here is legacy code (some of it was from back in August 2020)/
* [oh_hecc](./oh_hecc)
    * Optional Help for HECC (and also the main method for HECC-IT is in here)
    * This is the least old part of HECC-IT, still several of months old, and does still need some improvements.
* [utilities](./utilities)
    * some miscellaneous Java files, that are useful, but don't neatly fit into one of the other packages.

If you want to see the **much** more detailed javadoc-style documentation, please go to
[../JavaDocs](../JavaDocs) instead.

If you wanted the really, really, long discussion/rant about the development process of
HECC-IT, please go to [../Technical Documentation](../Technical%20Documentation) instead.