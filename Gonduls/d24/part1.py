def getInput():
    with open("Gonduls/d24/input.txt") as f:
        lines = f.readlines()
        return lines

def evaluate(operation, varA: set, varB: set=None) -> set:
    if operation == 'inp':
        return set(range(1, 10))
    
    if(operation == 'mul' and varB == {0}):
        return {0}
    
    result = set()
    for b in varB:
        for a in varA:
            match operation:
                case 'add':
                    result.add(a + b)
                case 'mul':
                    result.add(a * b)
                case 'mod':
                    result.add(a % b)
                    if len(varB) == 1 and len(result) in varB:
                        return result
                case 'div':
                    result.add(a // b)
                case 'eql':
                    result.add(1 if a==b else 0)
                    if len(result) == 2:
                        return result
    
    return result

def solve(lines: list, x: set={0}, y: set={0}, z: set={0}, w: set={0}, limit:int = 6) -> str:

    for num, line in enumerate(lines):
        #print(line)
        line = line.strip()
        b = None
        if len(line.split(' ')) > 2:
            match line.split(' ')[2]:
                case 'x': b = x
                case 'y': b = y
                case 'w': b = w
                case 'z': b = z
                case _:
                    b = set()
                    b.add(int(line.split(' ')[2]))
        
        oper = line.split(' ')[0]
        match line.split(' ')[1]:
            case 'x': x = evaluate(oper, x, b)
            case 'y': y = evaluate(oper, y, b)
            case 'w': w = evaluate(oper, w, b)
            case 'z': z = evaluate(oper, z, b)
        if oper == 'inp':
            if limit == 0:
                w = set(range(1, 10))
                continue

            #for i in range(1, 10):
            for i in range(9, 0, -1):
                #print(' ' * limit, i)
                w = {i}
                res = solve(lines[num + 1:], x, y, z, w, limit -1)
                if len(res) == 0:
                    continue

                if res == ' ':
                    #return str(i)
                    res = solve(lines[num + 1:], x, y, z, w, 8)
                return str(i) + res
        
    if 0 in z:
        return ' '
    return ''

if __name__ == '__main__':
    lines = getInput()
    print('Result part 1: ', solve(lines))