import math

from swampy.AmoebaWorld import *

import swampy.World


class MethodClass:
    def test(self):
        pass

# 设置全局变量，引用MethodClass类中的test方法
global_method = MethodClass.test
# 创建MethodClass类实例对象class_instance
class_instance = MethodClass()
# 方式1
class_instance.test()
# 方式2
global_method(class_instance)



if __name__ == '__main__':
    def foo():
        print(111)


    print(type(foo))

    a(Test())
    a()
