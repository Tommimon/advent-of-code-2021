import sys
sys.path.append(sys.path[0].replace("d22", "d19"))
from orientations import *

with open("advent-of-code-2021/Gonduls/d22/input.txt") as f:
    lines = f.readlines()

instructions = []
for line in lines:
    instruction = []
    instruction.append(line.startswith("on"))
    line = line.replace("on ", "").replace("off ", "").replace("x=","").replace("y=","").replace("z=","").replace("..", ",").strip()
    x1, x2, y1, y2, z1, z2 = map(lambda l: int(l), line.split(","))
    instruction.append((Point(x1, y1, z1), Point(x2, y2, z2)))
    instructions.append(instruction)

points = set()
for instruction in instructions:
    if instruction[1][0].x > 50 or instruction[1][0].x < -50:
        break
    
    start = instruction[1][0]
    end = instruction[1][1]
    for x in range(start.x, end.x + 1):
        for y in range(start.y, end.y + 1):
            for z in range(start.z, end.z + 1):
                if(instruction[0]):
                    points.add(Point(x,y,z))
                else:
                    points.discard(Point(x,y,z))

print("Result part 1: ", len(points))
