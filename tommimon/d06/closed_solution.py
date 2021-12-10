import numpy as np

# parameters calculated with partial_fractions.py
numerators = np.array([(-0.06867707087211337-6.017389477372975e-17j), (-0.05958278929352701+0.07121671118312572j), (-0.05958278929352722-0.07121671118312578j), (0.16882680902709102+0.0966123090073435j), (0.16882680902709107-0.09661230900734338j), (-0.007559832563919337-0.14490420850010693j), (-0.007559832563919344+0.14490420850010693j), (-0.06734565173358785-0.02984056574559885j), (-0.06734565173358782+0.029840565745598843j)])
zeros = np.array([(0.9165697260294747+0j), (0.6737558584892636+0.6810867407245075j), (0.6737558584892636-0.6810867407245075j), (0.12493819008001533+1.1354149216147027j), (0.12493819008001533-1.1354149216147027j), (-0.402976045293055+0.9488264695079811j), (-0.402976045293055-0.9488264695079811j), (-0.85400286629096+0.3577698511391375j), (-0.85400286629096-0.3577698511391375j)])


# calculates number of fish with lifetime 0 at step n if at the beginning there was only one fish with lifetime 6
# the explicit expression of the function is a sum of exponential
def func(n):
    return round(np.sum(-numerators * zeros ** (-n-1)).real)


# deduce the total number of fish from the number of fish with lifetime 0 in the last 9 steps
def fish(steps, n):
    c = [1, 1, 2, 2, 2, 2, 2, 2, 1]
    return sum(np.multiply(list(map(lambda e: func(e + 6 - n), range(steps - 8, steps + 1))), c))


with open('input', 'r') as file:
    numbers = list(map(int, file.read().split(',')))
print(sum(map(lambda e: fish(80, e), numbers)))
print(sum(map(lambda e: fish(256, e), numbers)))
