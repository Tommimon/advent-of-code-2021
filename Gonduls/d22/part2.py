from part1 import getInstructions
from solids import *

instructions = getInstructions()
shapes = []
overlappings = []

for instruction in instructions:

    cuboid = Cuboid(instruction[1][0], instruction[1][1])
    shapesAdd = []
    overlAdd = []

    for positive in shapes:
        #print(positive, cuboid)
        if(not cuboid.intercepts(positive)):
            continue
        intersection = cuboid.intersection(positive)

        overlAdd.append(intersection)
    
    for negative in overlappings:

        if(not cuboid.intercepts(negative)):
            continue
        intersection = cuboid.intersection(negative)

        shapesAdd.append(intersection)
    
    
    if instruction[0]:
        shapesAdd.append(cuboid)
    
    shapes.extend(shapesAdd)
    overlappings.extend(overlAdd)

result = 0
for shape in shapes:
    result += shape.volume()
for overlapping in overlappings:
    result -= overlapping.volume()
print('Result part 2 = ', result)