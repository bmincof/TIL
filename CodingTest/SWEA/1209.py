n = 100
 
for i in range(10):
    t = int(input())
    horizontal = [0] * (n+1)
    vertical = [0] * (n+1)
    diagonal = [0, 0]
 
    matrix = [[0] * (n+1)]
    for i in range(n):
        matrix.append([0]+list(map(int, input().split())))
 
    for i in range(n+1):
        horizontal[i] = sum(matrix[i])
        diagonal[0] += matrix[i][i]
        for j in range(n+1):
            vertical[j] += matrix[i][j]
            if i + j == n + 1:
                diagonal[1] += matrix[i][j]
 
    maximum = max(horizontal + vertical + diagonal)
    print(f'#{t} {maximum}')
