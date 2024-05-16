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
    LinkQueue<int> mixedTest; // 混合测试排队
    LinkQueue<int> singleTest; // 单管测试排队

    vector<int> mixedWaiting; // 混合测试待上传
    vector<int> singleWaiting; // 单管测试待上传

    set<int> secondaryContagiousBlockNum; // 次密接楼号

    unordered_map<int, int *> mixedTube; // 混合测试试管Hash表
    unordered_map<int, int> singleTube; // 单管测试试管Hash表

    unordered_map<int, Person> personMap; // 人员状态Hash表
public:
    void line_up(); // 功能1: 排队
    void nucleic_acid_test(); // 功能2: 核酸检测
    void show_line_up(); // 功能3: 查看排队情况
    void Register_test_tube(); // 功能4: 登记测试管信息
    void status_category_query(); // 功能5: 各类人员查询
    void query_for_person(); // 功能6: 个人查询


    void handle_negative_cases(int tubeNum, bool isSingle); // 处理阴性情况
    void handle_positive_cases(int tubeNum, bool isSingle); // 处理阳性情况

    void handle_contagious(int personNum); // 处理密接情况

    void set_secondary_contagious(); // 处理次密接情况
};

#endif //DS_MANAGEMENTSYSTEM_H
