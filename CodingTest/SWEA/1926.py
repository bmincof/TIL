def include_3n(n):
    if n == '3' or n == '6' or n == '9':
        return True
    else:
        return False
 
n = int(input())
 
s = list(range(1,n+1))
 
for number in s:
    count = len([i for i in str(number) if include_3n(i)])
    print('-' * count if count != 0 else number, end=' ')
