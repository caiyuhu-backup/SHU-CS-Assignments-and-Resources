//
// Created by Silence on 2022/4/19.
//

#ifndef DS_PERSON_H
#define DS_PERSON_H

#include <ostream>

class Person {
private:
    int number; // ���
    int blockNumber; // ¥����
    bool is_negative; // ����
    bool is_diagnosed; // ȷ��
    bool is_suspicious; // ����
    bool is_contagious; // �ܽ�
    bool is_secondary_contagious; // ���ܽ�
    bool is_waiting_for_upload; // ���ϴ����
    bool is_in_line; // ���Ŷ�
    bool is_not_detected; // δ���
public:
    Person(int number);
    Person();
    virtual ~Person();
    friend std::ostream &operator<<(std::ostream &os, const Person &person);
    int getNumber() const;
    void setNumber(int number);
    int getBlockNumber() const;
    void setBlockNumber(int blockNumber);
    bool isNegative() const;
    void setIsNegative(bool isNegative);
    bool isDiagnosed() const;
    void setIsDiagnosed(bool isDiagnosed);
    bool isSuspicious() const;
    void setIsSuspicious(bool isSuspicious);
    bool isContagious() const;
    void setIsContagious(bool isContagious);
    bool isSecondaryContagious() const;
    void setIsSecondaryContagious(bool isSecondaryContagious);
    bool isWaitingForUpload() const;
    void setIsWaitingForUpload(bool isWaitingForUpload);
    bool isInLine() const;
    void setIsInLine(bool isInLine);
    bool isNotDetected() const;
    void setIsNotDetected(bool isNotDetected);
};


#endif //DS_PERSON_H
