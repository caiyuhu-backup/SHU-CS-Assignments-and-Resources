import math


def estimate_pi(epsilon=1e-15):
    factor = 2 * math.sqrt(2) / 9801
    base = 0.0
    k = 0
    while True:
        molecular = math.factorial(4 * k) * (1103 + 26390 * k)  # 分子
        denominator = math.pow(396, 4 * k) * math.pow(math.factorial(k), 4)  # 分母
        step = factor * molecular / denominator  # 每一次迭代大小
        base += step
        k += 1
        if step < epsilon:
            pi = 1 / base
            return k, pi


def main():
    epsilon = 1e-15
    k, pi = estimate_pi(epsilon)
    print("k的值为{}".format(k))
    print("pi的值为{}".format(pi))


if __name__ == '__main__':
    main()
