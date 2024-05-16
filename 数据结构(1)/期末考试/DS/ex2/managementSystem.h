//
// Created by Silence on 2022/4/19.
//
#ifndef DS_MANAGEMENTSYSTEM_H
#define DS_MANAGEMENTSYSTEM_H

#include <iostream>
#include <fstream>
#include <iomanip>
#include <string>
#include <vector>
#include <set>
#include <unordered_map>
#include "Person.h"
#include "LinkQueue.h"
#include "Utils.h"

using namespace std;
class managementSystem {
private:
    LinkQueue<int> mixedTest; // ��ϲ����Ŷ�
    LinkQueue<int> singleTest; // ���ܲ����Ŷ�

    vector<int> mixedWaiting; // ��ϲ��Դ��ϴ�
    vector<int> singleWaiting; // ���ܲ��Դ��ϴ�

    set<int> secondaryContagiousBlockNum; // ���ܽ�¥��

    unordered_map<int, int *> mixedTube; // ��ϲ����Թ�Hash��
    unordered_map<int, int> singleTube; // ���ܲ����Թ�Hash��

    unordered_map<int, Person> personMap; // ��Ա״̬Hash��
public:
    void line_up(); // ����1: �Ŷ�
    void nucleic_acid_test(); // ����2: ������
    void show_line_up(); // ����3: �鿴�Ŷ����
    void Register_test_tube(); // ����4: �Ǽǲ��Թ���Ϣ
    void status_category_query(); // ����5: ������Ա��ѯ
    void query_for_person(); // ����6: ���˲�ѯ


    void handle_negative_cases(int tubeNum, bool isSingle); // �����������
    void handle_positive_cases(int tubeNum, bool isSingle); // �����������

    void handle_contagious(int personNum); // �����ܽ����

    void set_secondary_contagious(); // ������ܽ����
};

#endif //DS_MANAGEMENTSYSTEM_H
