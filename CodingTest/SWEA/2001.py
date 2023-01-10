T = int(input())

for test_case in range(1, T+1):
    N, M = map(int, input().split())
    flies = [[0]*N]
    for i in range(N):
        flies.append([0] + list(map(int, input().split())))

    maximum = 0

    S = [[0] * (N + 1) for _ in range(N + 1)]

    for i in range(1, N+1):
        for j in range(1, N+1):
            S[i][j] = flies[i][j] + S[i-1][j] + S[i][j-1] - S[i-1][j-1]

    for i in range(M, N+1):
        for j in range(M, N+1):
            n_of_flies = S[i][j] - S[i-M][j] - S[i][j-M] + S[i-M][j-M]
            if n_of_flies > maximum:
                maximum = n_of_flies

    print(f'#{test_case} {maximum}')
