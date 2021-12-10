import numpy as np

'''
Decomposing F(z), the generating function of f(n) where f(n) is the number of fish with lifetime 0 at step n starting
with only one fish with lifetime 6
f(0)=0, f(1)=0, f(2)=0, f(3)=0, f(4)=0, f(5)=0, f(6)=1, f(7)=0, f(8)=0, f(n)=f(n-7)+f(n-9)
F(z) = sum[f(n)*z^n] for n: 0->inf
F(z) = f(6)*z^6 + sum[(f(n-7)+f(n-9))*z^n] for n: 9->inf
F(z) = z^6 + z^7*F(z) + z^9*F(z)
F(z) = z^6/(1-z^9-z^7)
'''

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

numerators = X * sign
print(numerators.tolist())
print(zeros.tolist())
