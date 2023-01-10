def has_next_blank(x, y, turn):
    dir_x, dir_y = directions[turn]
    if turn == 0 and (x + dir_x >= n or snail[y][x+dir_x] > 0):
        return False
    elif turn == 1 and (y + dir_y >= n or snail[y+dir_y][x] > 0):
        return False
    elif turn == 2 and (x + dir_x < 0 or snail[y][x+dir_x] > 0):
        return False
    elif turn == 3 and (y + dir_y < 0 or snail[y+dir_y][x] > 0):
        return False
     
    return True
 
# (x, y)
directions = [(1,0),(0,1),(-1,0),(0,-1)] # right, down, left, up
 
T = int(input())
 
for test_case in range(1, T+1):
    n = int(input())
    x, y = -1, 0 
    turn = 0
    cur = 1
    dir_x, dir_y = directions[turn]
 
    snail = []
 
    for i in range(n): 
        snail.append([0]*n)
 
    while cur <= n**2:
        while has_next_blank(x, y, turn):
            y += dir_y
            x += dir_x
            snail[y][x] = cur
            cur += 1
 
        turn = (turn + 1) % 4
        dir_x, dir_y = directions[turn]
 
    print("#",test_case, sep='')
    for s in snail:
        print(*s)
