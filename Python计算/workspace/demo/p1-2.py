import random


def distinct(seq):
    appeared = set()
    if isinstance(seq, list):
        result = []
        for k in seq:
            if k not in appeared:
                appeared.add(k)
                result.append(k)
        seq.clear()
        seq.extend(result)
    elif isinstance(seq, dict):
        items = list(seq.items())
        for k, v in items:
            if v not in appeared:
                appeared.add(v)
            else:
                del seq[k]


def verify(seq: list):
    return len(set(seq)) == len(seq)


def case_list():
    seq = [random.randint(1, 10) for _ in range(20)]
    print('去重前', seq)
    distinct(seq)
    print('去重后', seq)


def case_dict():
    seq = {i: random.randint(1, 10) for i in range(20)}
    print('去重前', seq)
    distinct(seq)
    print('去重后', seq)


if __name__ == '__main__':
    case_list()
    case_dict()
