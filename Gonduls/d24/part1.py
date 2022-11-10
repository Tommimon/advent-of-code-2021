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

lines = getInput()
for _ in range(14):
    found = False
    for i in range(9, 0, -1):
        #print('i = ', i)
        first = -1
        x, y, w, z = {0}, {0}, {0}, {0}
        for num, line in enumerate(lines):
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
                case 'x':
                    a = x
                    x = evaluate(oper, x, b)
                case 'y':
                    a = y
                    y = evaluate(oper, y, b)
                case 'w':
                    a = w
                    w = evaluate(oper, w, b)
                case 'z':
                    a = z
                    z = evaluate(oper, z, b)
            if oper == 'imp':
                if first< 0:
                    first = num
                    a.empty()
                    a.add(i)
        
        if 0 in z:
            print(i, end ='')
            lines[num] = 'mul w 0'
            lines.insert(num + 1, 'add w ' + str(i))
            found = True
            break
    if not found:
        print('Not found, exit')
        break
