n = int(input())
conv = list(map(int, input().split()))
sum = 0
for i in range(n):
    sum += conv[i]
if sum < 0:
    print('Left')
elif sum > 0:
    print('Right')
else:
    print('Stay')