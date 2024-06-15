# Wave Function Collapse

## Description

Wave function collapse is a simple algorithm often used in
procedural generation. The idea relies on entropy--which
can be interpreted as the number of possibilities.

Essentially, we start with a tile set, where
each tile can be any of the possible tile types
we define.
We collapse the tile of the least entropy,
propogate the information to neighboring tiles
to constrict their possibile types, and then
repeat this process until all the tiles
have been collapsed.

## Compilation

This is a java project, you can just compile each file as onto
their .class byte code representation
