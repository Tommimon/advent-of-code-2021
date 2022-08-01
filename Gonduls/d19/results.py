from orientations import *

# Reads from file and returns a tuple containing all scanners in the for of a list of points (beacons)
def getScanners() -> list[list[Point]]:
    with open('./Gonduls/d19/input.txt') as inputf:
        lines = inputf.read().split('\n')

    lines.append('')
    scanners = []
    for line in lines:
        if line.startswith('---'):
            beacons = []
            continue

        if len(line) == 0:
            scanners.append(beacons[:])
            continue

        x, y, z = map(lambda l : int(l), line.split(','))
        beacons.append(Point(x, y, z))

    return scanners

# Returns the outmost points in the 8 different possible directions:
# xyz, xy-z, x-yz, x-y-z, -xyz, -xy-z, -x-yz, -x-y-z
def getExtreme(scan :list[Point]) -> list[Point]:
    result = [Point(0,0,0)] * 8

    for point in scan:
        index = 0
        if point.x < 0: index += 4
        if point.y < 0: index += 2
        if point.z < 0: index += 1
        if(result[index].value() < point.value()):
            result[index] = point
            
    return result

# Checks if two scanners actually overlap points, 
# returning (true, the orientation number of the second scanner and its relative positions) if so happens, 
# (false none none) otherwise
# Scanner A is treated as if it were in position (0,0,0), with orientation 0 (orientation if fixed directly in scanners)
def overlaps(scanA:list[Point], scanB:list[Point]) -> tuple[bool, int, Point]:
    # prestores all points in scanA relative to all points in scanA: list[list[Points]]
    relativePoints = []
    for point in scanA:
        relativePoints.append(list(map(lambda l: point.relativePoint(l), scanA)))
    
    # iters for every orientation, for every "extreme" point 
    # it checks if scan b, relative to the extreme point, matches scan a relative to any point
    for orientation in range(24):
        scanBChanged = list(map(lambda l: changeOrientation(l, orientation), scanB))
        extreme = getExtreme(scanBChanged)
        for expoint in extreme:
            relativeToExt = list(map(lambda l: expoint.relativePoint(l), scanBChanged))

            for scanAPoint, relativeToScanAPoint in enumerate(relativePoints):

                # & used to get elements contained in both sets
                if len(set(relativeToScanAPoint) & set(relativeToExt)) > 11:
                    return(True, orientation, expoint.relativePoint(scanA[scanAPoint]))

    return(False, None, None)


if __name__ == '__main__':
    scanners = getScanners()        # reads from input file
    scanPositions = [Point(0,0,0)]  # stores actual positions of all scanners relative to first scanner
    allPoints = set(scanners[0])    # all final points in the combined scan
    checked = [0]                   # stores indexes of scanners as soon as I know their position to scanner 0
    index = 0                       # gives checked que like properties, without actually emptying checked

    while(len(checked) < len(scanners)):
        for scan in range(1, len(scanners)):
            if scan in checked: continue

            match, orient, scanPos = overlaps(scanners[checked[index]], scanners[scan])
            if not match: continue

            # corrects found scanner position with position of old scanner used
            scanPos = scanPositions[index].addPoint(scanPos)

            # need to fix both position and orientation of all points relative to new scanner
            scanners[scan] = list(map(lambda l: changeOrientation(l, orient), scanners[scan]))  # orientation, saved in scaners
            allPoints.update(set(map(lambda l: scanPos.addPoint(l), scanners[scan])))           # position, not saved but points added to allPoints
            checked.append(scan)
            scanPositions.append(scanPos)

        index += 1

    print('result part 1 = ' + str(len(allPoints)))
    print('result part 2 = ' + str(max(map(lambda l : max(map(lambda m: l.manDistance(m), scanPositions)), scanPositions))))