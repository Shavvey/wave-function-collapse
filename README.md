# Wave Function Collapse

## Description

Wave function collapse is a simple algorithm often used in
procedural generation. The idea relies on entropy--which
can be interpreted as the number of possibilities.

Essentially, we start with a tile set, where
each tile can be any of the possible tile types
we define.
We collapse the tile of the least entropy,
propagate the information to neighboring tiles
to constrict their possible types, and then
repeat this process until all the tiles
have been collapsed.

## Compilation

This is a java project, you can just compile each file into
their .class byte code representation.

The class files are also available inside the output directory.
So, you can execute the program using a java JVM using
this command inside the main repo directory:
`java -cp ./output Main`
