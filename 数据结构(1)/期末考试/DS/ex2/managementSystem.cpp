//
// Created by Silence on 2022/4/19.
//

#include "managementSystem.h"

/**
 * ����1: �Ŷ�
 */
void managementSystem::line_up() {
    /**
     * n ��ϲ����Ŷ�����
     * m ���ܲ����Ŷ�����
     */
    int n, m;

    ifstream lineupFile;

    lineupFile.open("ex2/line_up.in");
    lineupFile >> n >> m;

    /**
     * ��ϲ������
     */
    for (int i = 0; i < n; i++) {
        int num;
        lineupFile >> num;
        mixedTest.EnQueue(num);
        // ��Ա״̬����
        Person p = Person(num);
        p.setIsInLine(true);
        // ��״̬��ϣ���н��в������
        personMap[num] = p;
    }
    /**
     * ���ܲ������
     */
    for (int i = 0; i < m; i++) {
        int num;
        lineupFile >> num;
        singleTest.EnQueue(num);
        // ��Ա״̬����
        Person p = Person(num);
        p.setIsInLine(true);
        // ��һ���ڹ�ϣ���н��в���
        personMap[num] = p;
    }
    // �ر��ļ���
    lineupFile.close();
}

/**
 * ����2: ������
 */
void managementSystem::nucleic_acid_test() {
    ifstream nucleicAcidFile;
    nucleicAcidFile.open("ex2/nucleic_acid_test.in");

    /**
     * x: �Ѿ������˻�ϼ�������
     * y: �Ѿ������˵��ܼ�������
     */
    int x, y;
    nucleicAcidFile >> x >> y;

    int mixedTubeNum;
    int singleTubeNum;


    /**
     * ��ϼ�����
     */
    int *personNum; // 10����Ա�ı������
    for (int i = 0; i < x; i++) {
        int temp;
        mixedTest.DelQueue(temp);
        // ����Ϊ�ȴ��ϴ������
        personMap[temp].setIsWaitingForUpload(true);
        personMap[temp].setIsInLine(false);

        // ÿ10��,�ڶ�����������һ������
        if (i % 10 == 0) {
            // ��ʼ��,ͬʱ����ʼֵ0
            personNum = new int[10]();
            mixedTubeNum = i / 10;
            mixedTube[mixedTubeNum] = personNum;
        }
        personNum[i % 10] = temp;
        mixedWaiting.push_back(temp);
    }

    /**
     * ���ܼ�����
     */
    for (singleTubeNum = 10000; singleTubeNum < y + 10000; singleTubeNum++) {
        // temp�ݴ浱ǰ����֮���˵ı��
        int temp;
        singleTest.DelQueue(temp);
        // ����Ϊ�ȴ��ϴ������
        personMap[temp].setIsWaitingForUpload(true);
        personMap[temp].setIsInLine(false);
        singleWaiting.push_back(temp);
        singleTube[singleTubeNum] = temp;
    }
    nucleicAcidFile.close();
}

/**
 * ����3: �鿴�Ŷ����
 */
void managementSystem::show_line_up() {
    /**
     * ��ϲ������
     */
    cout << "=======��ϲ����Ŷ�========" << endl;
    int count = 1;
    if (mixedTest.IsEmpty()) {
        cout << "Ŀǰ��ϲ��������Ŷ�!" << endl;
    } else {
        for (Node<int> *p = mixedTest.front->next; p != nullptr; p = p->next) {
            cout << '(' << count << ")\t" << " ��Ա���: " << setw(8) << setfill('0') << p->data << endl;
            count++;
        }
    }
    cout << "=======��ϲ����Ŷ�========" << endl;


    /**
     * ���ܲ������
     */
    cout << "=======���ܲ����Ŷ�========" << endl;
    count = 1;
    if (singleTest.IsEmpty()) {
        cout << "Ŀǰ���ܲ��������Ŷ�!" << endl;
    } else {
        for (auto p = singleTest.front->next; p != nullptr; p = p->next) {
            cout << '(' << count << ")\t" << " ��Ա���: " << setw(8) << setfill('0') << p->data << endl;
            count++;
        }
    }
    cout << "=======���ܲ����Ŷ�========" << endl;
}

/**
 * ����4: �Ǽǲ��Թ���Ϣ
 */
void managementSystem::Register_test_tube() {
    ifstream registrationFile;
    registrationFile.open("ex2/registration.in");

    /**
     * mΪ������Ϣ����
     */
    int m;
    registrationFile >> m;
    /**
     * ��������status,�ֱ��Ӧ����״̬
     */
    for (int i = 0; i < m; i++) {
        int tubeNum, status;
        registrationFile >> tubeNum >> status; // ���ļ��ж�ȡ�Թܺ�����״̬(���ԡ����ԡ����)


        // ���˹� �ܺ���1��ͷ, �ж��Ƿ�Ϊ���˹�
        bool isSingle = tubeNum >= 10000;

        switch (status) {
            case 1: // ��������
                handle_negative_cases(tubeNum, isSingle);
                break;
            case 2: // ��������
                handle_positive_cases(tubeNum, isSingle);
                break;
        }
    }
    /**
     * ȫ�������֮��,ͳһ������ܽ�
     */
    set_secondary_contagious();

    // �������,��մ��ϴ�vector
    singleWaiting.clear();
    mixedWaiting.clear();
}

/**
 * ����5: ������Ա��ѯ
 */
void managementSystem::status_category_query() {
    cout << "[����]:\t";
    int i = 0;
    for (auto &item: personMap) {
        if (item.second.isNegative()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "���Թ�" << i << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[ȷ��]:\t";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isDiagnosed()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "ȷ�ﹲ" << i << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[����]:\t";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isSuspicious()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "���ɹ�" << i << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[�ܽ�]:\t";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isContagious() && !item.second.isDiagnosed()) {
            i++;
            cout << '(' << i << ')' << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "�ܽӹ�" << i << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[���ܽ�]:";
    i = 0;
    for (auto &item: personMap) {
        if (item.second.isSecondaryContagious() && !item.second.isContagious() && !item.second.isDiagnosed()) {
            i++;
            cout << '(' << i << ")" << setw(8) << setfill('0') << item.first << ' ';
        }
    }
    cout << endl << "���ܽӹ�" << i << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[���ϴ����]:";
    // ����waiting����
    int count = 0;
    for (int j: singleWaiting) {
        count++;
        cout << '(' << count << ")" << setw(8) << setfill('0') << j << ' ';
    }
    // ���waiting����
    for (int j: mixedWaiting) {
        count++;
        cout << '(' << count << ")" << setw(8) << setfill('0') << j << ' ';
    }
    if (count == 0) {
        cout << endl << "û�����ڵȴ��ϴ����" << endl;
    } else
        cout << endl << "�ȴ��ϴ������" << count << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

    cout << "[���Ŷ�]:";
    // ���������ŶӶ���
    count = 0;
    for (auto *p = singleTest.front->next; p != nullptr; p = p->next) {
        count++;
        cout << '(' << count << ")"  << setw(8) << setfill('0') << p->data << ' ';
    }
    // ��������ŶӶ���
    for (auto *p = mixedTest.front->next; p != nullptr; p = p->next) {
        count++;
        cout << '(' << count << ")"  << setw(8) << setfill('0') << p->data << ' ';
    }
    cout << endl << "�����Ŷӹ�" << count << "��,��Ա�������ϡ�" << endl;
    cout << "--------------------------------------------------------";
    cout << "--------------------------------------------------------" << endl;

}

/**
 * ����6: ���˲�ѯ
 */
void managementSystem::query_for_person() {
    cout << "��������Ա����:(8λxxxyyyzz)" << endl;
    int num;

    cin >> num;
    cout << "====================================" << endl;
    cout << "��Ա����:\t" << setw(8) << setfill('0') << num << endl;

    if (personMap.count(num) == 0) {
        cout << "״̬:\t" << "δ���(û�вμ��ŶӼ��)" << endl;
    } else {
        if (personMap[num].isInLine()) {
            cout << "״̬:\t" << "���Ŷ�" << endl;
        }
        if (personMap[num].isWaitingForUpload()) {
            cout << "״̬:\t" << "�ȴ��ϴ����" << endl;
        }
        if (personMap[num].isDiagnosed()) {
            cout << "״̬:\t" << "ȷ��" << endl;
        } else if (personMap[num].isContagious()) {
            cout << "״̬:\t" << "�ܽ�" << endl;
        } else if (personMap[num].isSecondaryContagious()) {
            cout << "״̬:\t" << "���ܽ�" << endl;
        }
        if (personMap[num].isNegative()) {
            cout << "״̬:\t" << "����" << endl;
        } else if (personMap[num].isSuspicious()) {
            cout << "״̬:\t" << "����" << endl;
        }
    }
    cout << "====================================" << endl;

}

/**
 * �����ܽ���Ա���,�����ܽ���Ա״̬�Լ������ܽ���Ա����¥Ϊ���ܽ����
 * @param personNum �ܽ���Ա���
 */
void managementSystem::handle_contagious(int personNum) {
    personMap[personNum].setIsContagious(true); // ����Ϊ�ܽ�
    int blockNum = personMap[personNum].getBlockNumber(); // ȡ������ܽ�������¥��
    secondaryContagiousBlockNum.insert(blockNum); // �ܽ���Ա����¥������ܽ�¥�ż���
}

/**
 * �����������
 */
void managementSystem::handle_negative_cases(int tubeNum, bool isSingle) {
    int personNum;
    // ����
    if (isSingle) {
        // ��Ϊ����,�ڵ����Թ�map�в���
        personNum = singleTube[tubeNum];
        personMap[personNum].setIsNegative(true);
        // ����Ϊ���ϴ������
        personMap[personNum].setIsWaitingForUpload(false);
    } else {
        // ��Ϊ��ϼ��,�ڻ�ϼ���Թ�map�в���
        for (int i = 0; i < 10; i++) {
            personNum = mixedTube[tubeNum][i];
            if (personNum != 0) {
                personMap[personNum].setIsNegative(true);
                // ����Ϊ���ϴ������
                personMap[personNum].setIsWaitingForUpload(false);
            }
        }
    }
}

/**
 * ���������������Ϊ�����������
 * 1. ����
 *  1.1 ������Ա������¥������������Ϊ�ܽ�
 *  1.2 ���ϴ����vector��ǰʮ����ֱ��ĩβ����Ϊ�ܽ�
 *    1.2.1 ���ǵ�����¥������������Ϊ���ܽ�
 *  1.3 �����Ŷӵ�queue������������Ϊ�ܽ�
 *    1.3.1 ���ǵ�����¥������������Ϊ���ܽ�
 * 2. ���
 *
 * @param tubeNum �ļ���ԭʼ���Թܱ��
 * @param isSingle �ǵ��ܻ��ǻ��
 */
void managementSystem::handle_positive_cases(int tubeNum, bool isSingle) {
    // ����
    int personNum;
    if (isSingle) {
        // ��Ϊ����,�ڵ����Թ�map�в���
        personNum = singleTube[tubeNum];
        personMap[personNum].setIsDiagnosed(true); // ����������Ա״̬Ϊȷ��
        // ����Ϊ���ϴ������
        personMap[personNum].setIsWaitingForUpload(false);
        int i;
        for (i = 0; singleWaiting[i] != personNum; i++); // ��waiting���ҵ����Ե��Ǹ���
        // 1. ������������Ա������¥��������
        int blockNum = personMap[personNum].getBlockNumber(); // ȡ��¥��
        secondaryContagiousBlockNum.insert(blockNum); // ������Ա����¥���뼯��

        for (auto &item: personMap) {
            // ������Ե�¥�����ϣ���е���¥����ͬ�����޸���״̬Ϊ�ܽ�
            if (blockNum == Utils::getBlockNum(item.first)) {
                item.second.setIsContagious(true);
            }
        }
        int j;
        // 2. �����ڵ��ܶ��������ڵȴ������������
        if (i <= 10) // �������������ǰ10����,���޸���ʼλ��
            j = 0;
        else
            j = i - 10;
        for (; j < singleWaiting.size(); j++) {
            int frontNumbers = singleWaiting[j]; // ȡ�����ں�����˵Ĵ���
            handle_contagious(frontNumbers);
        }

        // 3. �����ڵ��ܶ����������Ŷӵ�������
        for (Node<int> *p = singleTest.front->next; p != nullptr; p = p->next) {
            int subsequentNumbers = p->data; // ȡ�����ں�����˵Ĵ���
            handle_contagious(subsequentNumbers);
        }
    } else {
        // ��Ϊ��ϼ��,�ڻ�ϼ���Թ�map�в���
        for (int i = 0; i < 10; i++) {
            personNum = mixedTube[tubeNum][i];
            if (personNum != 0) {
                personMap[personNum].setIsSuspicious(true);
                // ����Ϊ���ϴ������
                personMap[personNum].setIsWaitingForUpload(false);
            }
        }
    }
}

/**
 * �������ܽ�¥�ż���,���ô��ܽ�״̬
 */
void managementSystem::set_secondary_contagious() {
    for (int iter: secondaryContagiousBlockNum) {
//            cout << "=======��ϣ========" << endl;
        for (auto &item: personMap) {
            // ������ܽ�¥�����ϣ���е���¥����ͬ�����޸���״̬
            if (iter == Utils::getBlockNum(item.first)) {
                item.second.setIsSecondaryContagious(true);
            }
        }
    }
}

