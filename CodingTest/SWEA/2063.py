n = int(input())
arr = list(map(int,input().split()))
arr.sort()
 
mid = n//2
median = arr[mid]
 
print(median)
