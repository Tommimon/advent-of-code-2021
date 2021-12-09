import numpy as np

# Enter the coefficients of the poly in the array
NUM = [0, 0, 1, 0, 0, 0, 0, 0, 0]
DEN = [-1, 0, -1, 0, 0, 0, 0, 0, 0, 1]
sign = np.sign(DEN[0])

zeros = np.roots(DEN)
denominators = list(map(lambda e: [1, -e], zeros.tolist()))

poly = []
for i, d in enumerate(denominators):
    p = [1]
    for j, f in enumerate(denominators):
        if i != j:
            p = np.polynomial.polynomial.polymul(p, f)
    poly.append(p)

A = []
for i in range(len(poly)):
    row = []
    for p in poly:
        row.append(p[i])
    A.append(row)

A = np.array(A)
B = np.array(NUM)
X = np.linalg.inv(A).dot(B)
