import random
import re
import time
from typing import TextIO

from googletrans import Translator
from nltk.tokenize import sent_tokenize

translator = Translator(service_urls=['translate.google.cn'])


class Markov:
    @staticmethod
    def __process_word(word: str):  # 处理单词
        word = word.strip(r'"#$%&' r"'()*+-/:;<=>@[\]^_`{|}~")  # 去除奇怪的标点符号
        word = word.lstrip(r',.!?')  # 去除前边的标点符号
        if not word[:-1].replace("'", '').replace('-', '').isalpha() or (len(word) > 1 and word.isupper()):
            word = ''
        return word


    def __is_end_word(self, word: str):  # 判断是否为最后一个单词
        return word in self.end_words


    def __get_first_prefix(self):
        return list(random.choice(self.start_prefixes))  # 开头前缀的随机一项


    # def __get_next_word(self, prefix: str):
    def __get_next_word(self, prefix: tuple):  # 通过已有的
        seq = self.chain[prefix]
        return random.choice(seq)


    def __init__(self, fp: TextIO, n: int = 1):
        self.chain = {}  # 构造的马尔可夫字典 key为prefix, value为存储过的单词
        self.start_prefixes_set = set()  # 存句子输入的第一个单词
        self.end_words = set()  # 一句话结束的单词
        self.n = n  # 马尔科夫分析的前缀阶数
        fp.seek(0)
        text = ' '.join(line.strip() for line in fp)  # 源文本
        prefix = []
        for sentence in sent_tokenize(text):
            real_start = ''  # 针对每一句话而言,这个句子的开头
            real_end = ''  # 针对每一句话而言,这个句子的结尾   
            for word in re.split(r'\s', sentence):  # 匹配所有空白
                word = self.__process_word(word)  # 将单词进行处理，保留单词后的 ',' '.' 例如 "sad." "way,"
                if word != '':
                    if real_start == '':  # 训练集句子的开头大写字母
                        if not word[0].isupper():
                            break
                        real_start = word
                    if len(prefix) == n:
                        # key = ' '.join(prefix)
                        key = tuple(prefix)
                        if prefix[0] == real_start:
                            self.start_prefixes_set.add(tuple(prefix))
                        if key in self.chain:
                            self.chain[key].append(word)
                        else:
                            self.chain[key] = [word]
                    prefix.append(word)
                    # 滑动窗口
                    prefix = prefix[-n:]
                    real_end = word
            if real_end != '':
                if real_end[-1] in '.!?' and real_end.lower() not in ('mr.', 'mrs.'):
                    self.end_words.add(real_end)
        self.start_prefixes = list(self.start_prefixes_set)  # 设置句子开头的前缀


    def __call__(self, n: int):
        while True:
            words = self.__get_first_prefix()  # 最终返回的语句,不断往此列表中添加单词
            pos = 0
            count = 0
            appeared_first_prefix = set()
            while True:
                try:
                    # prefix = ' '.join(words[-self.n:])
                    prefix = tuple(words[-self.n:])
                    word = self.__get_next_word(prefix)
                    words.append(word)
                    pos += 1
                    if pos == self.n:
                        if prefix in appeared_first_prefix:
                            words = words[:-self.n]
                            break
                        else:
                            appeared_first_prefix.add(prefix)
                    if word == '':
                        break
                    elif self.__is_end_word(word):
                        if count == n:
                            break
                        count += 1
                        pos = 0
                except KeyError:
                    break
            if not self.__is_end_word(words[-1]):
                continue
            return ' '.join(words)


def generate(book: str, m: int, n: int):
    assert book in ('emma', 'whitefang')
    time_1 = time.time()
    with open('{}.txt'.format(book)) as f:
        markov = Markov(f, n)
    time_2 = time.time()
    text = markov(m)
    time_3 = time.time()
    print('\n{}  m = {}  n = {}  构造马尔可夫链用时: {:.16f} 秒  生成句子用时: {:.16f} 秒\n'.format(
        book, m, n, time_2 - time_1, time_3 - time_2
    ))
    print(text)
    # print('翻译中...', end='')
    # translated_text = translator.translate(text, dest='zh-cn', src='en').text
    # print('\r{}\n'.format(translated_text))


def main():
    # generate('emma', 10, 1)
    # generate('emma', 10, 2)
    generate('emma', 10, 3)
    generate('whitefang', 10, 1)
    generate('whitefang', 10, 2)
    generate('whitefang', 10, 3)
    while i := input('输入文章(emma|whitefang) 句数m 阶数n: '):
        try:
            book, m, n = i.split()
            generate(book.lower(), int(m), int(n))
        except (AssertionError, ValueError, TypeError):
            print('输入有误！')


if __name__ == '__main__':
    main()
