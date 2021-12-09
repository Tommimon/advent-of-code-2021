import parameters
import numpy as np

X = parameters.X
zeros = parameters.zeros
sign = parameters.sign


def func(n):
    s = np.divide(np.multiply(X, zeros ** (-n)), -zeros)
    return round(np.sum(s).real) * sign


def fish(n):
    c = [1, 1, 2, 2, 2, 2, 2, 2, 1]
    return sum(np.multiply(list(map(lambda e: func(e + 6 - n), range(248, 257))), c))


with open('input', 'r') as file:
    numbers = list(map(int, file.read().split(',')))
print(sum(map(fish, numbers)))
