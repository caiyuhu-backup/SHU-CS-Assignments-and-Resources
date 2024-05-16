# 实验三 Python 函数定义与面向对象程序设计

## 导航

[返回](https://github.com/ZKLlab/python-computing-experiments)

## 实验要求

1. 函数和数据结构复习
   1. 编写 Ackermann 函数的递归实现 Ack(m, n)

      ![Ackermann 函数](https://render.githubusercontent.com/render/math?math=A%5Cleft%28m%2Cn%5Cright%29%3D%5Cleft%5C%7B%5Cbegin%7Barray%7D%7Bll%7Dn%2B1%26%5Crm%7Bif%7D%5C%3Bm%3D0%5C%5CA%5Cleft%28m-1%2C1%5Cright%29%26%5Crm%7Bif%7D%5C%3Bm%5Cgt0%5C%3B%5Crm%7Band%7D%5C%3Bn%3D0%5C%5CA%5Cleft%28m-1%2CA%5Cleft%28m%2Cn-1%5Cright%29%5Cright%29%26%5Crm%7Bif%7D%5C%3Bm%5Cgt0%5C%3B%5Crm%7Band%7D%5C%3Bn%5Cgt0.%5Cend%7Barray%7D%5Cright.)

      测试 Ack(3, 4)的值，阅读[https://en.wikipedia.org/wiki/Ackermann_function](https://en.wikipedia.org/wiki/Ackermann_function)，分析 m 和 n 取值对函数值计算的影响，深入理解递归；

   2. 编写一个函数，实现从序列中移除重复项，且保持元素间顺序不变。生成随机的列表和字典，验证所实现函数的功能。

2. 编写拥有 a) 对象成员 hour、minute 和 second 的时间类 Time； b) 重载\_\_str\_\_和\_\_add\_\_方法； c) 方法 time2int：把时间对象转换为秒数； d) 方法 printtime：输出时间； e) 方法 isafter：判断两个时间对象的先后； f) 方法 increment：计算对象经过 n>0 秒后时间； g) 方法 isvalid：判断时间对象合法性。在主函数设计代码验证 Time 各个方法的正确性。

3. 马尔可夫文本分析和应用

   1. 马尔可夫文本分析计算文本中单词组合和其后续单词（含标点符号）的映射，这个单词组合被称为马尔可夫分析的前缀，前缀中单词的个数被称为马尔可夫分析的“阶数”。编写Python代码实现某个文本的n阶马尔可夫文本分析，并且将分析结果记录在字典中；

   2. 采用 i. 所实现的马尔可夫分析模块，对“emma.txt”或“whitefang.txt”进行马尔可夫分析，运用n阶马尔可夫分析的结果生成由m个句子（注意首字母大写和结尾标点符号）组成的随机文本。分析所生成文本的语义自然性和阶数n的关系；

   3. 尝试采用 Python 不同的序列数据结构表示前缀，比较运行效率的差异。

4. 模拟快餐订餐场景

   1. 定义 4 个类：Customer 顾客类，Employee 商户类，Food 食物类以及 Lunch 订餐管理；

   2. Lunch 类包含 Customer 和 Employee 实例，具有下单 order 方法，该方法要求 Customer 实例调用自身的 placeOrder 向 Employee 对象要求下单，并且获得 Employee 对象调用 takeOrder 生成和返回一个 Food 对象，Food 对象应当包含了食物名字符串。调用关系如下：

      Lunch.order → Customer.placeOrder → Employee.takeOrder → Food；

   3. Lunch 类包含 result 方法，要求 Customer 打印所收到的食物订单；

   4. 编写交互式界面验证所设计的订餐系统。

