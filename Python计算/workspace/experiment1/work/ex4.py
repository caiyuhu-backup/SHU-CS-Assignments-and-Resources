from collections import Counter
import string


def task1():
    with open('timemachine.txt', 'r') as f:
        text = f.read()

    words_list = [word.strip(string.punctuation).lower() for word in text.split()]
    words_counter = Counter(words_list)

    for k, v in words_counter.most_common():
        print(k, v)


def task2():
    with open('timemachine.txt', 'r') as f:
        text = f.read()

    words_list = [word.strip(string.punctuation).lower() for word in text.split()]
    words_counter = Counter(words_list)

    hot_words = ['time', 'machine', 'well', 'said']
    print(hot_words)
    for word in hot_words:
        print(word, words_counter.get(word))


if __name__ == '__main__':
    task1()
    task2()
