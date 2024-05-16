import time


def set_test():
    with open('words.txt', 'r') as f:
        # 文件中每个单词除去换行符之后存入集合
        words_set = {line.strip('\n') for line in f.readlines()}

    # 集合推导式构造逆序集合
    reversed_words_set = {word[::-1] for word in words_set}

    start = time.time()

    count = 0
    # 集合内元素Hash存储,查找时间复杂度 O(1)
    for word in words_set:
        if word in reversed_words_set:
            print(word, word[::-1])
            count+=1

    end = time.time()
    print("时间开销为: {} ".format(end - start))

    print("-------------------")
    print(count)

def list_test():
    with open('words.txt', 'r') as f:
        # 文件中每个单词除去换行符之后存入列表
        words_list = [line.strip('\n') for line in f.readlines()]

    reversed_words_list = [word[::-1] for word in words_list]

    start = time.time()

    for word in words_list:
        if word in reversed_words_list:
            print(word, word[::-1])
    end = time.time()

    start = time.time()

    print("时间开销为: {} ".format(end - start))


if __name__ == '__main__':
    set_test()
    # list_test()
