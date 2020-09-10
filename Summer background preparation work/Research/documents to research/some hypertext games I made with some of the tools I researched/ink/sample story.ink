// ---- Sample Story ----
// Converted from original inklewriter URL:
// https://www.inklewriter.com/stories/15016
# title: Sample Story
# author: Anonymous
// -----------------------------

VAR thatsodd = 0
VAR aVar = false
VAR bVar = false

-> onceUponATime


==== onceUponATime ====
Once upon a time...
  + deez
        -> bruh 
  + nuts
        -> alsoBruh 
  + lmao gottem
        -> eecksDee 

= bruh
{ b and not a:
    bruh
}
    -> END

= alsoBruh
also bruh
  + a
        -> a 
  + b
        -> b 

= eecksDee
eecks dee
  ~ thatsodd = thatsodd + 1
  + waoh
        -> woah 

= a
a
  ~ aVar = true
  + C
        -> cDeezNuts 

= woah
woah
    -> alsoBruh

= b
b
  ~ bVar = true
  + C
        -> cDeezNuts 

= cDeezNuts
c deez nuts
  + {aVar} if A
        -> youPickedAEarlie 
  + {bVar} if B
        -> youPickedBEarlie 

= youPickedAEarlie
you picked A earlier
  + how
        -> magic 

= youPickedBEarlie
you picked B earlier
  + how
        -> magic 

= magic
magic.
just like this: {~clear|cloudy|dark|very far away} <>
  + ok then
        -> gladYouLikeIt 
  + i refuse
        -> sure 

= gladYouLikeIt
glad you like it.
  + yes
        -> bruh 
  + no
        -> wack 

= sure
sure? <>
    -> bruh

==== wack ====
wack
  + oh noes
        -> ohYes 

= ohYes
oh yes
[number:thatsodd]
  + {thatsodd == 0} thatsodd is zero
        -> yesItIsZero 
  + {thatsodd == 1} thatsodd is one
        -> yeahItsOne 

= yesItIsZero
yes, it is zero.
    -> END

= yeahItsOne
yeah its one
    -> END