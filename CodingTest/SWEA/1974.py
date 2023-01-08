def check_horizontal(sudoku):
     
    for row in sudoku:
        if set(row) != solution:
            return False
 
    return True
 
def check_vertical(sudoku):
     
    for col in [set([sudoku[j][i] for j in range(9)]) for i in range(9)]:
        if col != solution:
            return False
 
    return True
 
def check_squares(sudoku):
     
    for i in range(0, 7, 3):
        for j in range(0, 7, 3):
            if set(sudoku[i][j:j + 3] + sudoku[i + 1][j:j + 3] + sudoku[i + 2][j:j + 3]) != solution:
                return False
 
    return True
 
T = int(input())
solution = {1, 2, 3, 4, 5, 6, 7, 8, 9}
     
for test_case in range(1, T + 1):
    sudoku = []
    for i in range(9):
        sudoku += [list(map(int, input().split()))]
 
    is_solution = check_horizontal(sudoku) and check_vertical(sudoku) and check_squares(sudoku)
 
    print(f'#{test_case} {1 if is_solution else 0}')
