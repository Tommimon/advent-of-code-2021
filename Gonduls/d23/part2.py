from part1 import *

class Matrix2(Matrix):

    def __init__(self, lines: list, start=False):
        super().__init__(lines)
        if start:
            self.m.insert(2, '##D#C#B#A##')
            self.m.insert(3, '##D#B#A#C##')

    def isFinalPosition(self, position: tuple) -> bool:
        x, y = position
        if self.m[y][x] == '.' or self.m[y][x] == '#':
            return False

        if y > 4 or y <= 0:
            return False
        
        if y == 4 :
            return Matrix.getDest(self.m[y][x]) == x
        
        if Matrix.getDest(self.m[y][x]) == x:
            return self.isFinalPosition((x, y+1))
        
        return False
    
    def applyMove(self, start:tuple, end:tuple):
        result = Matrix2(self.getM())
        
        result.m[end[1]][end[0]] = result.m[start[1]][start[0]]
        result.m[start[1]][start[0]] = '.'
        return result

    # Returns a list of tuples (start, end) defining all possible moves
    def getActions(self) -> list:
        result = []
        for i, letter in enumerate(self.m[0]):
            if letter == '.':
                continue
            
            col = Matrix.getDest(letter)
            start = (i, 0)
            for j in range(4, 0, -1):
                if self.isValidMove(start, (col, j)):
                    result.append((start, (col, j)))
                    return result
        
        for j in range(1, 5):
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
        pos = [4, 4, 4, 4]
        for y in range(4, -1, -1):
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

def isGoal(m: Matrix2):
    return all(map(lambda index: m.isFinalPosition((index, 1)), [2, 4, 6, 8]))

def getInput():
    with open("Gonduls/d23/input.txt") as f:
        lines = f.readlines()

    return Matrix2(lines[1:4], True)

def aStar(start: Matrix2) -> int:
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

            c = current.obj.getCost(action[0], action[1]) + cost
            h = hash(m)
            if h in hitset:
                continue

            hitset.add(h)
            hitMap[h] = m
            fatherMap[h] = hash(current.obj)


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
    print('result part 2: ' + str(aStar(matrix)))
