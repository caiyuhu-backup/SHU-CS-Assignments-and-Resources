#ifndef __ELEM_NODE_H__
#define __ELEM_NODE_H__

// 节点类
template<class ElemType>
struct ElemNode {
    ElemType data;                // 数据域
    int parent;                   // 双亲域
};

#endif

#ifndef __UFSETS_H__
#define __UFSETS_H__

// 并查集
template<class ElemType>
class UFSets {
public:
// 成员函数如下:
    int size;                             // 结点个数
    int CollapsingFind(ElemType e) const;// 查找元素e所在等价类的根
    int Find(ElemType e) const;             // 查找元素e所在等价类的根
    UFSets(int n);         // 构造函数 
    virtual ~UFSets();                     // 析构函数
    void Union(ElemType a, ElemType b);     // 根据结点多少合并a与b所在的等价类

// 数据成员如下:
    ElemNode<ElemType> *virus;
};

// 并查集的实现部分
template<class ElemType>
UFSets<ElemType>::UFSets(int n)
// 操作结果：根据数组es中的元素，构造n个单元素等价类
{
    size = n;                                    // 设置容量
    virus = new ElemNode<ElemType>[size];        // 分配空间
    for (int i = 0; i < size; i++) {
        virus[i].data = i + 1;
        virus[i].parent = -1;
    }
}

template<class ElemType>
UFSets<ElemType>::~UFSets()
// 操作结果：释放对象占用的空间——析构函数
{
    delete[]virus;                            // 释放数组parent
}

template<class ElemType>
void UFSets<ElemType>::Union(ElemType a, ElemType b)
// 操作结果：根据结点多少合并a与b所在的等价类
{
    int r1 = Find(a);                    // 查找a所在等价类的根
    int r2 = Find(b);                    // 查找b所在等价类的根
    if (r1 != r2 && r1 != -1 && r2 != -1) {
        int temp = virus[r1].parent + virus[r2].parent;
        if (virus[r1].parent <= virus[r2].parent) {
            virus[r2].parent = r1;
            virus[r1].parent = temp;
        } else {
            virus[r1].parent = r2;       //r1中的结点个数少，r1指向r2
            virus[r2].parent = temp;
        }
    }
}

template<class ElemType>
int UFSets<ElemType>::CollapsingFind(ElemType e) const
// 操作结果：带压缩路径功能，查找元素e所在树的根
{
    int i, k, p = 0;
    while (p < size && virus[p].data != e)
        p++;
    if (p == size)
        return -1;                                // 集合中不存在元素e
    for (i = p; virus[i].parent >= 0; i = virus[i].parent); //查找p的根结点的序号i
    while (i != virus[p].parent) {  //从p开始向上逐层压缩
        k = virus[p].parent;
        virus[p].parent = i;
        p = k;
    }
    return i;
}

template<class ElemType>
int UFSets<ElemType>::Find(ElemType e) const
// 操作结果：查找元素e所在树的根
{
    int p = 0;
    while (p < size && virus[p].data != e)
        p++;
    if (p == size)
        return -1;                                // 集合中不存在元素e
    while (virus[p].parent > -1)
        p = virus[p].parent;                     // 查找根
    return p;
}
#endif
