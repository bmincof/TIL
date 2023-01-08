T = int(input())
 
for test_case in range(1, T+1):
    N, M = map(int, input().split())
 
    if N < M:
        shorter = list(map(int, input().split()))
        longer = list(map(int, input().split()))
    else:
        longer = list(map(int, input().split()))
        shorter = list(map(int, input().split()))
 
    width = len(shorter)
    maximum = max([sum([shorter[j] * longer[i:i+width][j] for j in range(width)]) for i in range(len(longer) - width + 1)])
 
    print(f'#{test_case} {maximum}')
