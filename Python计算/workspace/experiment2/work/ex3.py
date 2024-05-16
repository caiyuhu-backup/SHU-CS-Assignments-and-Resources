import random
import time
import seaborn as sns
import matplotlib.pyplot as plt


def get_rate():
    global rate
    m = 1000
    q = 0
    rate_list = []
    for i in range(1, 101):
        for j in range(m):
            # 集合推导式生成日期集合，并且与人数比较
            # 利用了集合去重的特点
            birthday_set = {random.randint(1, 365) for j in range(i)}
            if len(birthday_set) < i:
                q += 1
            rate = q / m
        q = 0
        rate_list.append(rate)
    fig = sns.scatterplot(x=range(1, 101), y=rate_list, markers="1", color='#3366cc', label='Experimental result')
    sns.lineplot(x=range(1, 101), y=rate_list, color='#ff9966')
    plt.plot((1, 101), (0.5, 0.5), c='#9E9E9E', linestyle=':')
    plt.plot((23, 23), (0, 1), c='#9E9E9E', linestyle=':')
    plt.annotate('(23, 0.5)', xy=(23, 0.5), xytext=(25, 0.44))
    plt.xlabel('N')
    plt.ylabel('P')
    scatter_fig = fig.get_figure()
    scatter_fig.savefig('figures/m={}.png'.format(m))
    plt.show()
    print("图片以保存")


def main():
    start = time.time()
    get_rate()
    end = time.time()
    print("所用时间为{}".format(end - start))


if __name__ == '__main__':
    main()
