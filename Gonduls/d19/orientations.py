from functools import total_ordering

@total_ordering
class Point:
    def __init__(self, x : int, y : int, z : int):
        self.x = x
        self.y = y
        self.z = z

    def manDistanceToZero(self):
        x = self.x if self.x > 0 else -self.x
        y = self.y if self.y > 0 else -self.y
        z = self.z if self.z > 0 else -self.z
        return x+y+z

    def manDistance(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return Point(self.x - other.x, self.y - other.y, self.z - other.z).manDistanceToZero()

    def value(self):
        return (self.x ** 2 + self.y ** 2 + self.z ** 2) ** 0.5

    def relativePoint(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return Point(other.x - self.x, other.y - self.y, other.z - self.z)
    
    def addPoint(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return Point(other.x + self.x, other.y + self.y, other.z + self.z)
    
    def __str__(self) -> str:
        return f'({self.x}, {self.y}, {self.z})'

    def __repr__(self) -> str:
        return Point.__str__(self)

    def __eq__(self, other: object) -> bool:
        return (isinstance(other, self.__class__) and
            getattr(other, 'x', None) == self.x and
            getattr(other, 'y', None) == self.y and
            getattr(other, 'z', None) == self.z)
        
    def __ne__(self, __o: object) -> bool:
        return not(Point.__eq__(self, __o))

    def __hash__(self) -> int:
        return hash((self.x, self.y, self.z))
    
    def _is_valid_operand(self, other):
        return (hasattr(other, 'x') and hasattr(other, 'y') and hasattr(other, 'z'))

    def __lt__(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return self.x < other.x and self.y < other.y and self.z < other.z

    def __gt__(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return self.x > other.x and self.y > other.y and self.z > other.z 

    def __le__(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return self.x <= other.x and self.y <= other.y and self.z <= other.z

    def __ge__(self, other):
        if not self._is_valid_operand(other):
            return NotImplemented
        return self.x >= other.x and self.y >= other.y and self.z >= other.z  

def rotate(a, b, times):
    for _ in range(times):
        a, b = b, -a
    return(a, b)
    

def changeOrientation(point : Point, orientation : int) -> Point:
    if orientation < 0 or orientation > 23:
        raise Exception("Orientation can only be a number ranging from 0 to 23")

    x, y, z = point.x, point.y, point.z

    # rotates axes once or twice, depending on orientation, mantaining xyz orientation intact
    if orientation > 7 :
        x, y, z = y, z, x
        orientation -= 8
    if orientation > 7 :
        x, y, z = y, z, x
        orientation -= 8
    # orientation now > 0, < 8
    # switches from negative x to positive x, mantaining xyz orientation intact
    if orientation > 3:
        x, y, z = -x, y, -z
        orientation -= 4
    # orientation now > 0, < 4
    # rotates around x axis from 0 to 3 times
    y, z = rotate(y, z, orientation)
    return Point(x, y, z)