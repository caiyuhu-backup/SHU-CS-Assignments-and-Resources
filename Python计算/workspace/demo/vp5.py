def print_grid():
    rows = 2
    cols = 2
    width = 4
    for i in range(-1, rows):
        if i >= 0:
            for _ in range(width):
                print('|', end='')
                for _ in range(cols):
                    for _ in range(width):
                        print('  ', end='')
                    print(' |', end='')
                print()
        print('+', end='')
        for _ in range(cols):
            for _ in range(width):
                print(' -', end='')
            print(' +', end='')
        print()


def print_grid():
    print('''
+ - - - - + - - - - +
|         |         |
|         |         |
|         |         |
|         |         |
+ - - - - + - - - - +
|         |         |
|         |         |
|         |         |
|         |         |
+ - - - - + - - - - +
''')


print_grid()
