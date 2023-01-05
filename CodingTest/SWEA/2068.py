t = int(input())
for i in range(t):
    numbers = list(map(int,input().split()))
    maximum = max(numbers)
 
    print(f'#{i+1} {maximum}')
