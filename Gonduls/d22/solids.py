from email.policy import default
import sys
sys.path.append(sys.path[0].replace("d22", "d19"))
from orientations import *

class Solid:
    def __init__(self, start: Point, end: Point):
        self.start = start
        self.end = end

    def volume(self):
        a, b, c = self.start.x, self.start.y, self.start.z
        d, e, f = self.end.x, self.end.y, self.end.z

        l1 = a-d if a-d > 0 else d-a
        l2 = b-e if b-e > 0 else e-b
        l3 = c-f if c-f > 0 else f-c
        print(l1, l2, l3)
        
        return (l1 + 1) * (l2 + 1) * (l3 + 1)

    def getVertices(self) -> list:
        a, b, c = self.start.x, self.start.y, self.start.z
        d, e, f = self.end.x, self.end.y, self.end.z
        return [
            Point(a,b,c),
            Point(a,b,f),
            Point(a,e,c),
            Point(a,e,f),
            Point(d,b,c),
            Point(d,b,f),
            Point(d,e,c),
            Point(d,e,f)
        ]

    def containsPoint(self, point: Point) -> bool:
        s, e = self.start, self.end
        ex1 = Point(min(s.x, e.x), min(s.y, e.y), min(s.z, e.z))
        ex2 = Point(max(s.x, e.x), max(s.y, e.y), max(s.z, e.z))
        #print(self, point, (ex1 <= point and ex2 >= point))
        return((ex1 <= point and ex2 >= point))

    def intercepts(self, other):
        s1, e1 = self.start, self.end
        s2, e2 = other.start, other.end

        if s1 < s2 and s1 < e2 and e1 < s2 and e2 < e2:
            return False
        if s1 > s2 and s1 > e2 and e1 > s2 and e2 > e2:
            return False
        
        if self.containsPoint(s2) or self.containsPoint(e2) or other.containsPoint(s1) or other.containsPoint(e1):
            return True

        for x in range(min(s1.x, e1.x),  max(s1.x, e1.x)+ 1):
            if other.containsPoint(Point(x, s1.y, s1.z)) or other.containsPoint(Point(x, e1.y, e1.z)):
                return True

        for y in range(min(s1.y, e1.y),  max(s1.y, e1.y)+ 1):
            if other.containsPoint(Point(s1.x, y, s1.z)) or other.containsPoint(Point(e1.x, y, e1.z)):
                return True

        for z in range(min(s1.z, e1.z),  max(s1.z, e1.z)+ 1):
            if other.containsPoint(Point(s1.x, s1.y, z)) or other.containsPoint(Point(e1.x, e1.y, z)):
                return True

        for x in range(min(s2.x, e2.x),  max(s2.x, e2.x)+ 1):
            if self.containsPoint(Point(x, s2.y, s2.z)) or self.containsPoint(Point(x, e2.y, e2.z)):
                return True

        for y in range(min(s2.y, e2.y),  max(s2.y, e2.y)+ 1):
            if self.containsPoint(Point(s2.x, y, s2.z)) or self.containsPoint(Point(e2.x, y, e2.z)):
                return True

        for z in range(min(s2.z, e2.z),  max(s2.z, e2.z)+ 1):
            if self.containsPoint(Point(s2.x, s2.y, z)) or self.containsPoint(Point(e2.x, e2.y, z)):
                return True
        
        return False

    def intersection(self, other):
        s1, e1 = self.start, self.end
        s2, e2 = other.start, other.end
        
        if self.containsPoint(s2) and self.containsPoint(e2):
            return other

        if other.containsPoint(s1) and other.containsPoint(e1):
            return self

        vertixInSelf = False
        foundVertices = []
        for point in other.getVertices():
            if self.containsPoint(point):
                foundVertices.append(point)


        if len(foundVertices) == 0:
            vertixInSelf = True
            for point in self.getVertices():
                if other.containsPoint(point):
                    foundVertices.append(point)

        match len(foundVertices):
            case 0:
                return Solid(Point(0,0,0), Point(0,0,0))
            case 1:
                end = filter(lambda x : other.containsPoint(x), self.getVertices()).__next__()
                start = filter(lambda x : self.containsPoint(x), other.getVertices()).__next__()
                return Solid(start, end)
            case 2:
                foundSolid = other
                checkVertices = self.getVertices()
                if vertixInSelf:
                    foundSolid = self
                    checkVertices = other.getVertices()

                direction = 'x'
                a = foundVertices[0]
                b = foundVertices[1]
                if a.x == b.x:
                    direction = 'z' if a.y == b.y else 'y'
                
                match direction:
                    case 'x':
                        iterable = set(map(lambda point: Point(b.x, point.y, point.z), checkVertices))
                    case 'y':
                        iterable = set(map(lambda point: Point(point.x, b.y, point.z), checkVertices))
                    case 'z':
                        iterable = set(map(lambda point: Point(point.x, point.y, b.z), checkVertices))
                c = filter(lambda x : foundSolid.containsPoint(x), iterable).__next__()

                return Solid(a, c)
            
            case 4:
                foundSolid, notFoundSolid = other, self
                if vertixInSelf:
                    foundSolid, notFoundSolid = self, other

                foundVertix = foundSolid.start if foundSolid.start in foundVertices else foundSolid.end
                notFoundVertix = foundSolid.start if foundSolid.start not in foundVertices else foundSolid.end
                
                direction = 'x'
                if foundVertices[0].y == foundVertices[1].y and foundVertices[2].y == foundVertices[1].y:
                    direction = 'y'
                if foundVertices[0].z == foundVertices[1].z and foundVertices[2].z == foundVertices[1].z:
                    direction = 'z'

                match direction:
                    case 'x':
                        a = Point(notFoundSolid.start.x, notFoundVertix.y, notFoundVertix.z)
                        b = Point(notFoundSolid.end.x, notFoundVertix.y, notFoundVertix.z)
                    case 'y':
                        a = Point(notFoundVertix.x, notFoundSolid.start.y, notFoundVertix.z)
                        b = Point(notFoundVertix.x, notFoundSolid.end.y, notFoundVertix.z)
                    case 'z':
                        a = Point(notFoundVertix.x, notFoundVertix.y, notFoundSolid.start.z)
                        b = Point(notFoundVertix.x, notFoundVertix.y, notFoundSolid.end.z)

                if(foundSolid.containsPoint(a)):
                    return Solid(foundVertix, a)
                return Solid(foundVertix, b)
            case _:
                print(len(foundVertices))
                return Solid(Point(0,0,0), Point(0,0,0))

    def __str__(self) -> str:
        return f'({self.start}, {self.end})'

    def __repr__(self) -> str:
        return Point.__str__(self)

    def __eq__(self, other: object) -> bool:
        return (isinstance(other, self.__class__) and
            getattr(other, 'start', None).__eq__(self.start) and
            getattr(other, 'end', None).__eq__(self.end))
        
    def __ne__(self, __o: object) -> bool:
        return not(Point.__eq__(self, __o))

    def __hash__(self) -> int:
        return hash((self.start, self.end))

a = Point(0,0,0)
b = Point(8,8,4)
c = Point(1,1,1)
d = Point(2, 2, 16)

e = Solid(a, b)
f = Solid(c, d)

print(f.intersection(e))
print(e.getVertices())
print(f.getVertices())