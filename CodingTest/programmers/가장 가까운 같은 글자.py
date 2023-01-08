def solution(s):
    last_idx = dict()
    solution = [-1] * len(s)
    for i, c in enumerate(s):
        if c in last_idx:
            solution[i] = i - last_idx[c]
        else:
            solution[i] = -1

        last_idx[c] = i
    return solution
print(solution('banana'))
