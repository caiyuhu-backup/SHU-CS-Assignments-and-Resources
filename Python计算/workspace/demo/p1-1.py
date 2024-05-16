import sys

sys.setrecursionlimit(3000)


def ack(m: int, n: int):
    if m == 0:
        return n + 1
    elif n == 0:
        return ack(m - 1, 1)
    else:
        return ack(m - 1, ack(m, n - 1))


if __name__ == '__main__':
    print(f'A(3, 4) = {ack(3, 4)}')
    while x := input('输入 m 和 n 的值 (如 2 4): '):
        try:
            print(ack(*map(int, x.split())))
        except RecursionError:
            print('无法计算')
        except (ValueError, TypeError):
            print('输入有误')
