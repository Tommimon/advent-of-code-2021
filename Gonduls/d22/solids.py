from email.policy import default
import sys
sys.path.append(sys.path[0].replace("d22", "d19"))
from orientations import *

class Cuboid:
    def __init__(self, start: Point, end: Point):
        self.start = start
        self.end = end

    def volume(self):
        a, b, c = self.start.x, self.start.y, self.start.z
        d, e, f = self.end.x, self.end.y, self.end.z

        l1 = a-d if a-d > 0 else d-a
        l2 = b-e if b-e > 0 else e-b
        l3 = c-f if c-f > 0 else f-c
        #print(l1, l2, l3)
        
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
        return not(s1.x > e2.x or s1.y > e2.y or s1.z > e2.z or s2.x > e1.x or s2.y > e1.y or s2.z > e1.z)

    def intersection(self, other):
        if not self.intercepts(other):
            return None
        s1, e1 = self.start, self.end
        s2, e2 = other.start, other.end
        
        xs, ys, zs = max(s1.x, s2.x), max(s1.y, s2.y), max(s1.z, s2.z)
        xe, ye, ze = min(e1.x, e2.x), min(e1.y, e2.y), min(e1.z, e2.z)
        return(Cuboid(Point(xs, ys, zs), Point(xe, ye, ze)))
        

    def __str__(self) -> str:
        return f'({self.start}, {self.end})'

    def __repr__(self) -> str:
        return Point.__str__(self)

    def __eq__(self, other: object) -> bool:
        if not (isinstance(other, self.__class__) and hasattr(other, 'start') and hasattr(other, 'end')):
            return False
        
        return set(self.getVertices()) == set(other.getVertices())
        
    def __ne__(self, __o: object) -> bool:
        return not(Point.__eq__(self, __o))

    def __hash__(self) -> int:
        return hash((self.start, self.end))

if __name__ == '__main__':
    a = Point(10, -2, 7)
    b = Point(-25, -2, -29) 
    c = Point(-47, -16, -7)
    d = Point(7, 35, 45)

    e = Cuboid(a, b)
    f = Cuboid(c, d)

    print(f.intersection(e))
    print(f.intercepts(e))