T = int(input())
 
for test_case in range(1, T+1):
    word = input()
    print(f'#{test_case} {1 if word == word[::-1] else 0}')
