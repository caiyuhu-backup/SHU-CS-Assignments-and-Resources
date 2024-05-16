import time


# 递归查找，判断子单词里有没有可缩减单词，返回单词本身为可缩减单词，返回None为非可缩减单词
def find(w: str):
    global max_len, words_set, succeed_words_set, failed_words_set
    if len(w) > 1:
        for i in range(len(w)):
            nw = w[:i] + w[i + 1:]  # 第i个字母左边与右边拼起来成为一个子单词nw
            # 条件：nw已经是可缩减单词了 或 (nw是个单词 且 nw不是已知的非可缩减单词 且 nw的子单词里有可缩减单词)
            if nw in succeed_words_set or \
                    (nw in words_set and nw not in failed_words_set and find(nw) is not None):
                break  # 满足条件就是可缩减单词
        else:  # 循环完毕break没有执行，进入for-else的else
            failed_words_set.add(w)
            return None
    succeed_words_set.add(w)
    max_len = max(max_len, len(w))  # 更新已找到最长的可缩减单词长度
    return w


# 打印输出
def print_seq(w: str, *, root: bool = True):
    global words_set
    arrow = ' → '
    if len(w) > 1:
        for i in range(len(w)):
            nw = w[:i] + w[i + 1:]
            if nw in {'a', 'i'}:
                print(nw, end=arrow)
                break
            elif nw in words_set and print_seq(nw, root=False) is not None:
                break
        else:
            return None
    print(w, end=' ({})\n'.format(len(w)) if root else arrow)
    return w


if __name__ == '__main__':
    start = time.time()  # 记录开始时间

    # 制作词表
    with open('words.txt') as fp:
        words_list = [x.strip() for x in fp if 'a' in x or 'i' in x]  # 读取文件并初筛，不含'a'和'i'的一定不是可缩减单词

    words_set = set(words_list)  # 全部词集
    succeed_words_set = {'a', 'i'}  # 成功词集，可缩减单词的子单词一定是可缩减单词
    failed_words_set = set()  # 失败词集，非可缩减单词的子单词一定不是可缩减单词
    max_len = 1  # 记录已找到最长的可缩减单词长度
    result = 'a'
    for word in words_list:
        if len(word) > max_len:
            sub_result = find(word)
            if sub_result is not None:
                result = sub_result

    end = time.time()  # 记录结束时间

    print_seq(result)  # 打印输出序列
    # print(result)
    print(end - start, 's')
