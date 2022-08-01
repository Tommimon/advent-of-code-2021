# Solution: just add as many dots around the starting image as it matters,
# so just 2 off on each side per iteration, and hope it scales to 50 iterations just fine

with open('./Gonduls/d20/input.txt', 'r')as inputf:
    lines = inputf.read().split('\n')

algorithm = lines[0]
image = ['.' * (len(lines[2]) + 2*2*50)] * 100
image.extend(lines[2:])

for i in range(4, len(image)):
    image[i] = ".."*50 + image[i] + ".."*50

image.extend(['.' * (len(lines[2]) + 2*2*50)] * 100)

def getIndex(image, x, y):
    string = list(image[y][x] * 9)
    if x > 0 and y > 0: string[0] = image[y -1][x - 1]
    if y > 1: string[1] = image[y -1][x]
    if x < len(image[0]) - 1 and y > 0: string[2] = image[y -1][x +1]
    
    if x > 0 : string[3] = image[y][x - 1]
    if x < len(image[0]) - 1 : string[5] = image[y][x +1]
    
    if x > 1 and y < len(image) - 1: string[6] = image[y + 1][x - 1]
    if y < len(image) - 1: string[7] = image[y + 1][x]
    if x < len(image[0]) - 1 and y < len(image) - 1: string[8] = image[y + 1][x + 1]
    
    string = ''.join(string)
    string = string.replace('#', '1').replace('.', '0')
    return int(string, 2)
    

for i in range(50):
    newImage = []
    for y in range(0, len(image[0])):
        newImage.append('')
        for x in range(0, len(image)):
            newImage[-1] += algorithm[getIndex(image, x, y)]
    image = newImage
    if i == 1:
        print("Result part 1: ", sum(map(lambda l: l.count('#'), image)))

print("Result part 2: ", sum(map(lambda l: l.count('#'), image)))