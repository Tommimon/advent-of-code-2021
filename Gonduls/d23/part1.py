from heaps import *

class Matrix:
    def __init__(self, lines: list):
        self.m = []
        for line in lines:
            line = line.replace('#\n', '##').replace('###', '##').replace(' #', '##').replace('#..', '..').strip()[:11]
            self.m.append(list(line))

    def __str__(self) -> str:
        a = map(lambda l: ''.join(l), self.m)
        result = ''
        for line in a:
            result += line + '\n'
        return result

    def __repr__(self) -> str:
        return Matrix.__str__(self)

    def __eq__(self, other: object) -> bool:
        return str(self) == str(other)
        
    def __ne__(self, __o: object) -> bool:
        return str(self) != str(__o)

    def __hash__(self) -> int:
        return hash(str(self))

    def getType(letter: str) -> int:
        match letter:
            case 'A': return 0
            case 'B': return 1
            case 'C': return 2
            case 'D': return 3
        
        return -1

    def getM(self) -> list:
        return [''.join(i) for i in self.m]

    def getDest(letter: str) -> int:
        return Matrix.getType(letter) * 2 + 2

    def isFinalPosition(self, position: tuple) -> bool:
        x, y = position

        if y == 2 and Matrix.getDest(self.m[y][x]) == x:
            return True
        
        if y == 1 and Matrix.getDest(self.m[y][x]) == x and self.m[1][x] == self.m[2][x]:
            return True
        
        return False
    
    def getPath(self, start: tuple, end: tuple) -> str:
        xs, ys = start
        xe, ye = end

        if xs == xe:
            res = ''
            for i in range(ys, ye):
                res += self.m[i + 1][xs]
            for i in range(ys, ye, -1):
                res += self.m[i - 1][xs]

            return res
        
        if ys == ye == 0:
            res = ''
            for i in range(xs, xe):
                res += self.m[0][i + 1]
            for i in range(xs, xe, -1):
                res += self.m[0][i - 1]

            return res
        
        return self.getPath((xs, ys), (xs, 0)) + self.getPath((xs, 0), (xe, 0)) + self.getPath((xe, 0), (xe, ye))

    def getCost(self, start: tuple, end: tuple) -> int:
        steps = len(self.getPath(start, end))
        letter = self.m[start[1]][start[0]]
        return 10 ** Matrix.getType(letter) * steps

    def applyMove(self, start:tuple, end:tuple):
        result = Matrix(self.getM())
        
        result.m[end[1]][end[0]] = result.m[start[1]][start[0]]
        result.m[start[1]][start[0]] = '.'
        return result

    def isValidMove(self, start: tuple, end: tuple) -> bool:
        if not self.getPath(start, end) == '.'* len(self.getPath(start, end)):
            return False
        
        if self.isFinalPosition(start):
            return False
            
        if start[0] == end[0] or start[1] == end[1]:
            return False
        
        if self.applyMove(start, end).isFinalPosition(end):
            return True
        
        # if we start in a pocket we can noly end on top of a # - divide into 2 the movement
        if start[1] > 0 and (end[1] > 0 or self.m[1][end[0]] != '#'):
            return False
        
        return True

    # Returns a list of tuples (start, end) defining all possible moves
    def getActions(self) -> list:
        result = []
        for i, letter in enumerate(self.m[0]):
            if letter == '.':
                continue
            
            col = Matrix.getDest(letter)
            start = (i, 0)
            if self.isValidMove(start, (col, 2)):
                result.append((start, (col, 2)))
            elif self.isValidMove(start, (col, 1)):
                result.append((start, (col, 1)))
        
        for j in range(1, 3):
            for i, letter in enumerate(self.m[j]):
                if letter == '.' or letter == '#':
                    continue

                start = (i, j)
                for n in [0, 1, 3, 5, 7, 9, 10]:
                    if self.isValidMove(start, (n, 0)):
                        result.append((start, (n, 0)))
        
        return result
            
    def heuristic(self) -> int:
        result = 0
        for y in range(2, -1, -1):
            for x in range(11):
                letter = self.m[y][x]
                if letter == '.' or letter == '#':
                    continue
                
                if self.isFinalPosition((x,y)):
                    continue

                path = self.getPath((x, y), (Matrix.getDest(letter), 1))
                if len(path) == 0:
                    continue
                
                result += (len(path))* 10 ** Matrix.getType(letter)
        
        return result

def isGoal(m : Matrix):
    return m == Matrix(['...........', '##A#B#C#D##','##A#B#C#D##'])

def getInput():
    with open("advent-of-code-2021/Gonduls/d23/input.txt") as f:
        lines = f.readlines()

    return Matrix(lines[1:4])

def aStar(start: Matrix) -> int:
    frontier = []
    current = nodeInHeap(start, 0, start.heuristic())
    hitset = set()
    while not isGoal(current.obj):
        actions = current.obj.getActions()
        cost = current.cost
        for action in actions:
            m = current.obj.applyMove(action[0], action[1])

            if hash(m) in hitset:
                continue
            hitset.add(hash(m))
            c = current.obj.getCost(action[0], action[1]) + cost
            h = m.heuristic()
            insertInHeap(frontier, nodeInHeap(m, c, h))
        
    
        current = pop(frontier)
    return(current.cost)


if __name__ == '__main__':
    matrix = getInput()
    print('result part 1: ' + str(aStar(matrix)))

