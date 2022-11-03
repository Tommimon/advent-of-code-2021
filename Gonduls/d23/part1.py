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
        # If path is not clear -> False
        if not self.getPath(start, end) == '.'* len(self.getPath(start, end)):
            return False
        
        # If already in a final position -> False
        if self.isFinalPosition(start):
            return False
            
        # If moving but remaining in same column or row -> False
        if start[0] == end[0] or start[1] == end[1]:
            return False
        
        # If by applying the move we end up in a final position -> True
        if self.applyMove(start, end).isFinalPosition(end):
            return True
        
        # if we start in a pocket we can only end on top of a # or in a final position
        if start[1] > 0 and end[1] == 0 and self.m[1][end[0]] == '#':
            return True
        
        return False
        
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
                return result
            elif self.isValidMove(start, (col, 1)):
                result.append((start, (col, 1)))
                return result
        
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
        return 0
        result = 0
        pos = [2, 2, 2, 2]
        for y in range(2, -1, -1):
            for x in range(11):
                letter = self.m[y][x]
                if letter == '.' or letter == '#':
                    continue
                
                exp = Matrix.getType(letter)
                if self.isFinalPosition((x,y)):
                    pos[exp] -= 1
                    continue

                path = self.getPath((x, y), (Matrix.getDest(letter), pos[exp]))
                pos[exp] -= 1
                
                result += (len(path))* 10 ** exp
        return result

def isGoal(m : Matrix):
    return m == Matrix(['...........', '##A#B#C#D##','##A#B#C#D##'])

def getInput():
    with open("Gonduls/d23/input.txt") as f:
        lines = f.readlines()

    return Matrix(lines[1:4])

def aStar(start: Matrix) -> int:
    frontier = []
    current = nodeInHeap(start, 0, start.heuristic())
    hitset = set()
    hitMap = dict()
    fatherMap = dict()

    while not isGoal(current.obj):
        actions = current.obj.getActions()
        cost = current.cost
        for action in actions:
            m = current.obj.applyMove(action[0], action[1])
            h = hash(m)
            if h in hitset:
                continue
            hitset.add(h)
            hitMap[h] = m
            fatherMap[h] = hash(current.obj)

            c = current.obj.getCost(action[0], action[1]) + cost
            h = m.heuristic()
            insertInHeap(frontier, nodeInHeap(m, c, h))
            
        current = pop(frontier)
    
    printPath(hitMap, fatherMap, hash(current.obj))
    return(current.cost)

def printPath(hitMap: dict, fatherMap:dict, currentH: int):
    if currentH not in hitMap.keys():
        print(getInput())
        return
    
    printPath(hitMap, fatherMap, fatherMap[currentH])

    print(hitMap[currentH])
    

if __name__ == '__main__':
    matrix = getInput()
    print('result part 1: ' + str(aStar(matrix)))

