# **Welcome to the source code of HECC-IT!**

Welcome to the inner workings of HECC-IT, where you can see all the things that can and probably will make you shout 'HECC'
and other words along those lines!

I have seperated the source code into packages to make it relatively easy to tell what thing contains what.

## The things that are not source code but have to be here.

* [META-INF](./META-INF/)
    * This holds the manifest for building HECC-IT as a .jar file.
* [assets](./assets/)
    * This holds
        * The prebaked .html and .js files which are used by HECC-UP when outputting a heccin' game
        * The icons and such used by the jframes etc in HECC-IT, for the sole purpose of making it look less generic and crappy.

## The things that are source code.

* [heccCeptions](./heccCeptions/)
    * The custom exceptions used by HECC-IT.
        * ~~the joke is that they're exceptions but they involve hecc, and the 'ex' in 'exceptions' sounds like 'hecc'~~
        * These are thrown when there's a problem with the structure of the games a user is trying to make.
* [hecc_up](./hecc_up/)
    * The HECC Ultra Parser.
* [oh_hecc](./oh_hecc/)
    * Optional Help for HECC (and also the main method for HECC-IT is in here)
* [misc_testing_and_such](./oh_hecc/)
    * deprecated, need to yeet it
* [utilities](./utilities/)
    * some miscellaneous Java files, that are useful, but don't neatly fit into one of the other packages.
