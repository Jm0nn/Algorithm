t1, e1, f1 = map(int, input().split())
t2, e2, f2 = map(int, input().split())
a1 = t1 * 3 + e1 * 20 + f1 * 120
a2 = t2 * 3 + e2 * 20 + f2 * 120
if a1 > a2:
    answer = 'Max'
elif a1 < a2:
    answer = 'Mel'
else:
    answer = 'Draw'
print(answer)