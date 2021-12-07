from collections import Counter;

def sumOfDistances_1(dictionary, where_to):
    result = 0
    for key in dictionary.keys():
        result += abs(where_to - key) * dictionary[key]
    
    return result


def sumOfDistances_2(dictionary, where_to):
    result = 0
    
    # using gauss formula to calculate sum of integers:
    for key in dictionary.keys():
        a = abs(where_to - key)
        result += (int) ( ( a * (a+1) /2) * dictionary[key])
    
    return result


# storing every position occupied by at least a crab as key,
# the number of crabs in that position as value in the counter dictionary
positions = Counter(int(x) for x in open("Gonduls/d07/input.txt").read().strip().split(","))

MAX_CRAB_POSITION = max(x for x in positions.keys())

############### part 1 ##################
result_1 = MAX_CRAB_POSITION * len(positions)       # just a big enough number

# checking every position in range of the ones already occupied,
# outside positions are for sure not a valid solution
for i in range(MAX_CRAB_POSITION):
    result_1 = min(result_1, sumOfDistances_1(positions, i))

print(result_1)



############### part 2 ##################
result_2 = MAX_CRAB_POSITION * len(positions) * 1000 # just another big enough number

for i in range(MAX_CRAB_POSITION):
    result_2 = min(result_2, sumOfDistances_2(positions, i))

print(result_2)