SCISSORS = 1
ROCK = 2
PAPER = 3
 
a, b = map(int,input().split())
a_is_win = False
 
if a == SCISSORS:
    a_is_win = b == PAPER
elif a == ROCK:
    a_is_win = b == SCISSORS
else:                           # a == PAPER
    a_is_win = b == ROCK
 
print('A' if a_is_win else 'B')
