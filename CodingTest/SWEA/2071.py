n = int(input())
 
for i in range(n):
    numbers = list(map(int, input().split()))
    average = sum(numbers)
    average /= len(numbers)
 
    print(f'#{i+1} {round(average)}')
