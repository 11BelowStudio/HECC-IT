# (3.2).4: Term 2 - Developing the final version of HECC-IT

During term 2, I made several improvements to the HECCIN' Game (such as adding support for markdown
formatting, conditional statements, and general code cleanup), made some improvements to HECC-IT itself
(mostly back-end, involving the background logic, yet with a few substantial enhancements for user
experience), and created a proper hypertext game using HECC-IT.

It was a rather eventful term, with more than a few setbacks (besides the usual 'a lot of other coursework').
During week 21, [my ethernet cable got eaten so I needed to get a new one](https://cseejira.essex.ac.uk/browse/A301034-124),
and then in week 25, [my desktop's SSD died so I needed to get a replacement drive and reinstall the OS before I could use it again](https://cseejira.essex.ac.uk/browse/A301034-134).
However, due to my constant use of gitlab throughout the whole development process, none of the files for this
project were lost, and I was able to get back to work on this project relatively easily (after a slight delay
waiting for the replacement hardware to arrive and then getting these replacements installed/set up).

## (3.2).4.1: The improvements to the HECCIN' Game

One of the first things I did during term 2 was start making improvements to the HECCIN' Game itself,
such as adding support for markdown formatting and conditional statements. The discussion of these
features, how they were implemented, and how they were utilized in another tech demo game, 
can be read [in this markdown document](./final%20version%20HECCIN'%20Game.md).

The HECC-SPECC for the final iteration of the .hecc language has also been produced,
and can be read in [Documentation for HECC-IT/HECC-SPECC.md](../Documentation%20for%20HECC-IT/HECC-SPECC.md).

Once that was done, I started to work on some improvements to HECC-IT and writing a 'proper' HECCIN'
Game, with both of these tasks being mostly performed concurrently. However, in this report, I shall
start by discussing the further work on HECC-IT.

## (3.2).4.2: The improvements to HECC-IT

HECC-IT itself had quite a few changes made to it, and these changes are discussed, along with
discussion on the testing and final product of HECC-IT, in the [Final version of HECC-IT](./final%20version%20of%20HECC-IT.md)
document.

For some more specific information regarding the HECC-IT codebase, please refer either to the JavaDoc-format
documentation for the codebase (within the [../JavaDocs.zip](../JavaDocs.zip) folder), or, if you would prefer
a very brief overview of what each class does, please refer to the package-level readmes within the [../src](../src)
folder. 

## (3.2).4.3: *Backblast*

With the tool produced, I used it to write the game *Backblast*. The development of *Backblast*, along with the
thought process behind each part of it, and a brief discussion of how the final product failed to meet up to
expectations, is discussed in full within the [Backblast.md](./Backblast.md) document. An overview of the
structure of this game, as viewed by OH-HECC, can also be seen at the end of that document.