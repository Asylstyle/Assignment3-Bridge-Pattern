# Assignment 3 | Bridge Pattern

Name: Assylzhan Amangeldi
Group: SE-2526
Topic: A - Drawing

Repository:
https://github.com/Asylstyle/Assignment3-Bridge-Pattern

## Role map

| Role | Class | Source |
|---|---|---|
| Abstraction | Shape | src/Shape.java |
| A1 | Circle | src/Circle.java |
| A2 | Square | src/Square.java |
| Implementor | Renderer | src/Renderer.java |
| I1 | VectorRenderer | src/VectorRenderer.java |
| I2 | RasterRenderer | src/RasterRenderer.java |
| I3 | AsciiRenderer | src/AsciiRenderer.java |
| Client | Main | src/Main.java |

## Bridge locations

Bridge reference:
src/Shape.java -> Renderer implementation

execute():
src/Shape.java
src/Circle.java
src/Square.java

setImplementation(...):
src/Shape.java

Runtime switch T5:
src/Main.java -> checkRuntimeSwitch()

## Build

javac --release 17 -encoding UTF-8 -d out "@sources.txt"

## Run

java -cp out Main --demo

## Expected results

T1: VECTOR circle radius=2

T2: RASTER circle radius=2

T3: VECTOR square side=3

T4: RASTER square side=3

T5:
sameObject=true
stateUnchanged=true

before=VECTOR circle radius=2
after=RASTER circle radius=2

T6: ASCII circle radius=2

T7: ASCII square side=3

SUMMARY: 7/7 PASS

## Base commit

79c7c96d3bd2c2760c9f0aad209d21986af4447d