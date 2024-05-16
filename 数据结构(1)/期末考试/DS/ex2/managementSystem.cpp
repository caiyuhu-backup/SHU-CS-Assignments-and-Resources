//
// Created by Silence on 2022/4/19.
//

#include "managementSystem.h"

/**
 * 功能1: 排队
 */
void managementSystem::line_up() {
    /**
     * n 混合测试排队人数
     * m 单管测试排队人数
     */
    int n, m;

    ifstream lineupFile;

    lineupFile.open("ex2/line_up.in");
    lineupFile >> n >> m;

    /**
     * 混合测试入队
     */
    for (int i = 0; i < n; i++) {
        int num;
        lineupFile >> num;
        mixedTest.EnQueue(num);
        // 人员状态更新
        Person p = Person(num);
        p.setIsInLine(true);
        // 在状态哈希表中进行插入此人
        personMap[num] = p;
    }
    /**
     * 单管测试入队
     */
    for (int i = 0; i < m; i++) {
        int num;
        lineupFile >> num;
        singleTest.EnQueue(num);
        // 人员状态更新
        Person p = Person(num);
        p.setIsInLine(true);
        // 第一次在哈希表中进行插入
        personMap[num] = p;
    }
    // 关闭文件流
    lineupFile.close();
}

/**
 * 功能2: 核酸检测
 */
void managementSystem::nucleic_acid_test() {
    ifstream nucleicAcidFile;
    nucleicAcidFile.open("ex2/nucleic_acid_test.in");

    /**
     * x: 已经进行了混合监测的人数
     * y: 已经进行了单管监测的人数
     */
    int x, y;
    nucleicAcidFile >> x >> y;

    int mixedTubeNum;
    int singleTubeNum;


    /**
     * 混合检测出队
     */
    int *personNum; // 10个人员的编号数组
    for (int i = 0; i < x; i++) {
        int temp;
        mixedTest.DelQueue(temp);
        // 设置为等待上传检测结果
        personMap[temp].setIsWaitingForUpload(true);
        personMap[temp].setIsInLine(false);

        // 每10人,在堆中重新申请一个数组
        if (i % 10 == 0) {
            // 初始化,同时赋初始值0
            personNum = new int[10]();
            mixedTubeNum = i / 10;
            mixedTube[mixedTubeNum] = personNum;
        }
        personNum[i % 10] = temp;
        mixedWaiting.push_back(temp);
    }

    /**
     * 单管检测出队
     */
    for (singleTubeNum = 10000; singleTubeNum < y + 10000; singleTubeNum++) {
        // temp暂存当前队伍之中人的编号
        int temp;
        singleTest.DelQueue(temp);
        // 设置为等待上传检测结果
        personMap[temp].setIsWaitingForUpload(true);
        personMap[temp].setIsInLine(false);
        singleWaiting.push_back(temp);
        singleTube[singleTubeNum] = temp;
    }
    nucleicAcidFile.close();
}

/**
 * 功能3: 查看排队情况
 */
void managementSystem::show_line_up() {
    /**
     * 混合测试入队
     */
    cout << "=======混合测试排队========" << endl;
    int count = 1;
    if (mixedTest.IsEmpty()) {
        cout << "目前混合测试无人排队!" << endl;
    } else {
        for (Node<int> *p = mixedTest.front->next; p != nullptr; p = p->next) {
            cout << '(' << count << ")\t" << " 人员编号: " << setw(8) << setfill('0') << p->data << endl;
            count++;
        }
    }
    cout << "=======混合测试排队========" << endl;


    /**
     * 单管测试入队
     */
    cout << "=======单管测试排队========" << endl;
    count = 1;
    if (singleTest.IsEmpty()) {
        cout << "目前单管测试无人排队!" << endl;
    } else {
        for (auto p = singleTest.front->next; p != nullptr; p = p->next) {
            cout << '(' << count << ")\t" << " 人员编号: " << setw(8) << setfill('0') << p->data << endl;
            count++;
        }
    }
    cout << "=======单管测试排队========" << endl;
}

/**
 * 功能4: 登记测试管信息
 */
void managementSystem::Register_test_tube() {
    ifstream registrationFile;
    registrationFile.open("ex2/registration.in");

    /**
     * m为检测的信息条数
     */
    int m;
    registrationFile >> m;
    /**
     * 设置三个status,分别对应三种状态
     */
    for (int i = 0; i < m; i++) {
        int tubeNum, status;
        registrationFile >> tubeNum >> status; // 从文件中读取试管号与检测状态(阴性、阳性、混合)


        // 单人管 管号由1打头, 判断是否为单人管
        bool isSingle = tubeNum >= 10000;

        switch (status) {
            case 1: // 处理阴性
                handle_negative_cases(tubeNum, isSingle);
                break;
            case 2: // 处理阳性
                handle_positive_cases(tubeNum, isSingle);
                break;
        }
    }
    /**
     * 全部处理好之后,统一处理次密接
     */
    set_secondary_contagious();

    // 处理完成,清空待上传vector
    singleWaiting.clear();
    mixedWaiting.clear();
}

/**
 * 功能5: 各类人员查询
 */
void managementSystem::status_category_query() {
    cout << "[阴性]:\t";
    int i = 0;
    for (auto &item: personMap) {
        if (item.second.isNegative()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "阴性共" << i << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[确诊]:\t";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isDiagnosed()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "确诊共" << i << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[可疑]:\t";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isSuspicious()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "可疑共" << i << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[密接]:\t";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isContagious() && !item.second.isDiagnosed()) {
            i++;
            cout << '(' << i << ')' << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "密接共" << i << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[次密接]:";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isSecondaryContagious() && !item.second.isContagious() && !item.second.isDiagnosed()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "次密接共" << i << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[待上传结果]:";
    // 单管waiting队列
    int count = 0;
    for (int j: singleWaiting) {
        count++;
        cout << '(' << count << ")" << setw(8) << setfill('0') << j << ' ';
    }
    // 混合waiting队列
    for (int j: mixedWaiting) {
        count++;
        cout << '(' << count << ")" << setw(8) << setfill('0') << j << ' ';
    }
    if (count == 0) {
        cout << endl << "没有人在等待上传结果" << endl;
    } else
        cout << endl << "等待上传结果共" << count << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[在排队]:";
    // 单管正在排队队列
    count = 0;
    for (auto *p = singleTest.front->next; p != nullptr; p = p->next) {
        count++;
        cout << '(' << count << ")"  << setw(8) << setfill('0') << p->data << ' ';
    }
    // 混合正在排队队列
    for (auto *p = mixedTest.front->next; p != nullptr; p = p->next) {
        count++;
        cout << '(' << count << ")"  << setw(8) << setfill('0') << p->data << ' ';
    }
    cout << endl << "正在排队共" << count << "人,人员代码如上。" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

}

/**
 * 功能6: 个人查询
 */
void managementSystem::query_for_person() {
    cout << "请输入人员代码:(8位xxxyyyzz)" << endl;
    int num;

    cin >> num;
    cout << "====================================" << endl;
    cout << "人员代码:\t" << setw(8) << setfill('0') << num << endl;

    if (personMap.count(num) == 0) {
        cout << "状态:\t" << "未检测(没有参加排队检测)" << endl;
    } else {
        if (personMap[num].isInLine()) {
            cout << "状态:\t" << "在排队" << endl;
        }
        if (personMap[num].isWaitingForUpload()) {
            cout << "状态:\t" << "等待上传结果" << endl;
        }
        if (personMap[num].isDiagnosed()) {
            cout << "状态:\t" << "确诊" << endl;
        } else if (personMap[num].isContagious()) {
            cout << "状态:\t" << "密接" << endl;
        } else if (personMap[num].isSecondaryContagious()) {
            cout << "状态:\t" << "次密接" << endl;
        }
        if (personMap[num].isNegative()) {
            cout << "状态:\t" << "阴性" << endl;
        } else if (personMap[num].isSuspicious()) {
            cout << "状态:\t" << "可疑" << endl;
        }
    }
    cout << "====================================" << endl;

}

/**
 * 根据密接人员编号,处理密接人员状态以及设置密接人员所在楼为次密接情况
 * @param personNum 密接人员编号
 */
void managementSystem::handle_contagious(int personNum) {
    personMap[personNum].setIsContagious(true); // 设置为密接
    int blockNum = personMap[personNum].getBlockNumber(); // 取出这个密接者所在楼号
    secondaryContagiousBlockNum.insert(blockNum); // 密接人员所在楼号入次密接楼号集合
}

/**
 * 处理阴性情况
 */
void managementSystem::handle_negative_cases(int tubeNum, bool isSingle) {
    int personNum;
    // 阴性
    if (isSingle) {
        // 若为单管,在单管试管map中查找
        personNum = singleTube[tubeNum];
        personMap[personNum].setIsNegative(true);
        // 设置为已上传检测结果
        personMap[personNum].setIsWaitingForUpload(false);
    } else {
        // 若为混合检查,在混合检查试管map中查找
        for (int i = 0; i < 10; i++) {
            personNum = mixedTube[tubeNum][i];
            if (personNum != 0) {
                personMap[personNum].setIsNegative(true);
                // 设置为已上传检测结果
                personMap[personNum].setIsWaitingForUpload(false);
            }
        }
    }
}

/**
 * 对于阳性情况，分为以下两种情况
 * 1. 单管
 *  1.1 阳性人员的宿舍楼中所有人设置为密接
 *  1.2 待上传结果vector中前十个人直至末尾设置为密接
 *    1.2.1 他们的宿舍楼中所有人设置为次密接
 *  1.3 正在排队的queue中所有人设置为密接
 *    1.3.1 他们的宿舍楼中所有人设置为次密接
 * 2. 混合
 *
 * @param tubeNum 文件中原始的试管编号
 * @param isSingle 是单管还是混检
 */
void managementSystem::handle_positive_cases(int tubeNum, bool isSingle) {
    // 阳性
    int personNum;
    if (isSingle) {
        // 若为单管,在单管试管map中查找
        personNum = singleTube[tubeNum];
        personMap[personNum].setIsDiagnosed(true); // 设置阳性人员状态为确诊
        // 设置为已上传检测结果
        personMap[personNum].setIsWaitingForUpload(false);
        int i;
        for (i = 0; singleWaiting[i] != personNum; i++); // 在waiting中找到阳性的那个人
        // 1. 处理在阳性人员的宿舍楼中所有人
        int blockNum = personMap[personNum].getBlockNumber(); // 取出楼号
        secondaryContagiousBlockNum.insert(blockNum); // 阳性人员所在楼号入集合

        for (auto &item: personMap) {
            // 如果阳性的楼号与哈希表中的人楼号相同，则修改其状态为密接
            if (blockNum == Utils::getBlockNum(item.first)) {
                item.second.setIsContagious(true);
            }
        }
        int j;
        // 2. 处理在单管队列中正在等待结果的所有人
        if (i <= 10) // 如果这名阳性是前10个人,则修改起始位置
            j = 0;
        else
            j = i - 10;
        for (; j < singleWaiting.size(); j++) {
            int frontNumbers = singleWaiting[j]; // 取得排在后面的人的代码
            handle_contagious(frontNumbers);
        }

        // 3. 处理在单管队列中正在排队的所有人
        for (Node<int> *p = singleTest.front->next; p != nullptr; p = p->next) {
            int subsequentNumbers = p->data; // 取得排在后面的人的代码
            handle_contagious(subsequentNumbers);
        }
    } else {
        // 若为混合检查,在混合检查试管map中查找
        for (int i = 0; i < 10; i++) {
            personNum = mixedTube[tubeNum][i];
            if (personNum != 0) {
                personMap[personNum].setIsSuspicious(true);
                // 设置为已上传检测结果
                personMap[personNum].setIsWaitingForUpload(false);
            }
        }
    }
}

/**
 * 遍历次密接楼号集合,设置次密接状态
 */
void managementSystem::set_secondary_contagious() {
    for (int iter: secondaryContagiousBlockNum) {
//            cout << "=======哈希========" << endl;
        for (auto &item: personMap) {
            // 如果次密接楼号与哈希表中的人楼号相同，则修改其状态
            if (iter == Utils::getBlockNum(item.first)) {
                item.second.setIsSecondaryContagious(true);
            }
        }
    }
}

