import math as m
import random as r

letterDict = {
  "A" : ".###.#...##...#######...##...#",
  "B" : "####.#...#####.#...##...#####.",
  "C" : ".#####....#....#....#.....####",
  "D" : "####.#...##...##...##...#####.",
  "E" : "######....####.#....#....#####",
  "F" : "######....####.#....#....#....",
  "G" : ".###.#....#.####...##...#.###.",
  "H" : "#...##...#######...##...##...#",
  "I" : ".###...#....#....#....#...###.",
  "J" : "#####...#....#....#.#..#..##..",
  "K" : "#...##..#.###..#..#.#..#.#...#",
  "L" : "#....#....#....#....#....#####",
  "M" : "#...###.###.#.##...##...##...#",
  "N" : "#...###..##.#.##..###...##...#",
  "O" : ".###.#...##...##...##...#.###.",
  "P" : "####.#...#####.#....#....#....",
  "Q" : ".###.#...##...##...##..#..##.#",
  "R" : "####.#...#####.#..#.#...##...#",
  "S" : ".#####.....###.....#....#####.",
  "T" : "#####..#....#....#....#....#..",
  "U" : "#...##...##...##...##...#.###.",
  "V" : "#...##...##...#.#.#..#.#...#..",
  "W" : "#...##...##.#.#.#.#..#.#..#.#.",
  "X" : "#...#.#.#...#...#.#.#...##...#",
  "Y" : "#...#.#.#...#....#....#....#..",
  "Z" : "#####...#...#...#...#....#####",
  " " : "..............................",
}

folds = []

def convert(a):
  points = set()
  pivot = 0
  for c in a:
    for i in range(0, len(letterDict[c])):
      if (letterDict[c][i] == '#'):
        points.add(((6 * pivot + (i % 5), m.floor(i/5))))
    pivot += 1
  return points

def createData(points, n):
  xfold = 6*n - 1
  yfold = 6

  ##first two folds
  #yfold
  pointsCopy = set()
  folds.append(('y', yfold))
  for p in points:
    pointsCopy.add(p)
    if r.randint(0, 2) > 0:
      pointsCopy.add( (p[0], 2*yfold - p[1]) )
      if r.randint(0, 1) > 0:
        pointsCopy.remove(p)
  yfold = (yfold * 2) + 1
  points = pointsCopy

  #yfold
  pointsCopy = set()
  folds.append(('y', yfold))
  for p in points:
    pointsCopy.add(p)
    if r.randint(0, 2) > 0:
      pointsCopy.add( (p[0], 2*yfold - p[1]) )
      if r.randint(0, 1) > 0:
        pointsCopy.remove(p)
  yfold = (yfold * 2) + 1
  points = pointsCopy

  for rep in range(0, 4):
    #yfold
    pointsCopy = set()
    folds.append(('y', yfold))
    for p in points:
      pointsCopy.add(p)
      if r.randint(0, 2) > 0:
        pointsCopy.add( (p[0], 2*yfold - p[1]) )
        if r.randint(0, 1) > 0:
          pointsCopy.remove(p)
    yfold = (yfold * 2) + 1
    points = pointsCopy

    #xfold
    pointsCopy = set()
    folds.append(('x', xfold))
    for p in points:
      pointsCopy.add(p)
      if r.randint(0, 2) > 0:
        pointsCopy.add( (2*xfold - p[0], p[1]) )
        if r.randint(0, 1) > 0:
          pointsCopy.remove(p)
    xfold = (xfold * 2) + 1
    points = pointsCopy

  return points

def printData(points):
  f = open("inputCustom.txt", "w")
  foldsN = folds[::-1]
  for p in points:
    f.write(str(p[0]) + "," + str(p[1]) + "\n")
  f.write("\n")
  for i in range(0, len(foldsN) - 1):
    f.write("fold along " + str(foldsN[i][0]) + "=" + str(foldsN[i][1]) + "\n")
  f.write("fold along " + str(foldsN[len(foldsN) - 1][0]) + "=" + str(foldsN[len(foldsN) - 1][1]))
  f.close()

inp = input("Enter the string you want to convert: ")
printData(createData(convert(inp.upper()), len(inp)))
input("Done (any key to continue)")