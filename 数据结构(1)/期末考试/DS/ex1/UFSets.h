#ifndef __ELEM_NODE_H__
#define __ELEM_NODE_H__

// �ڵ���
template<class ElemType>
struct ElemNode {
    ElemType data;                // ������
    int parent;                   // ˫����
};

#endif

#ifndef __UFSETS_H__
#define __UFSETS_H__

// ���鼯
template<class ElemType>
class UFSets {
public:
// ��Ա��������:
    int size;                             // ������
    int CollapsingFind(ElemType e) const;// ����Ԫ��e���ڵȼ���ĸ�
    int Find(ElemType e) const;             // ����Ԫ��e���ڵȼ���ĸ�
    UFSets(int n);         // ���캯�� 
    virtual ~UFSets();                     // ��������
    void Union(ElemType a, ElemType b);     // ���ݽ����ٺϲ�a��b���ڵĵȼ���

// ���ݳ�Ա����:
    ElemNode<ElemType> *virus;
};

// ���鼯��ʵ�ֲ���
template<class ElemType>
UFSets<ElemType>::UFSets(int n)
// �����������������es�е�Ԫ�أ�����n����Ԫ�صȼ���
{
    size = n;                                    // ��������
    virus = new ElemNode<ElemType>[size];        // ����ռ�
    for (int i = 0; i < size; i++) {
        virus[i].data = i + 1;
        virus[i].parent = -1;
    }
}

template<class ElemType>
UFSets<ElemType>::~UFSets()
// ����������ͷŶ���ռ�õĿռ䡪����������
{
    delete[]virus;                            // �ͷ�����parent
}

template<class ElemType>
void UFSets<ElemType>::Union(ElemType a, ElemType b)
// ������������ݽ����ٺϲ�a��b���ڵĵȼ���
{
    int r1 = Find(a);                    // ����a���ڵȼ���ĸ�
    int r2 = Find(b);                    // ����b���ڵȼ���ĸ�
    if (r1 != r2 && r1 != -1 && r2 != -1) {
        int temp = virus[r1].parent + virus[r2].parent;
        if (virus[r1].parent <= virus[r2].parent) {
            virus[r2].parent = r1;
            virus[r1].parent = temp;
        } else {
            virus[r1].parent = r2;       //r1�еĽ������٣�r1ָ��r2
            virus[r2].parent = temp;
        }
    }
}

template<class ElemType>
int UFSets<ElemType>::CollapsingFind(ElemType e) const
// �����������ѹ��·�����ܣ�����Ԫ��e�������ĸ�
{
    int i, k, p = 0;
    while (p < size && virus[p].data != e)
        p++;
    if (p == size)
        return -1;                                // �����в�����Ԫ��e
    for (i = p; virus[i].parent >= 0; i = virus[i].parent); //����p�ĸ��������i
    while (i != virus[p].parent) {  //��p��ʼ�������ѹ��
        k = virus[p].parent;
        virus[p].parent = i;
        p = k;
    }
    return i;
}

template<class ElemType>
int UFSets<ElemType>::Find(ElemType e) const
// �������������Ԫ��e�������ĸ�
{
    int p = 0;
    while (p < size && virus[p].data != e)
        p++;
    if (p == size)
        return -1;                                // �����в�����Ԫ��e
    while (virus[p].parent > -1)
        p = virus[p].parent;                     // ���Ҹ�
    return p;
}
#endif
