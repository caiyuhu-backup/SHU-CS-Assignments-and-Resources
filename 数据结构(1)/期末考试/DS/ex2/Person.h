//
// Created by Silence on 2022/4/19.
//

#ifndef DS_PERSON_H
#define DS_PERSON_H

#include <ostream>

class Person {
private:
    int number; // 编号
    int blockNumber; // 楼栋号
    bool is_negative; // 阴性
    bool is_diagnosed; // 确诊
    bool is_suspicious; // 可疑
    bool is_contagious; // 密接
    bool is_secondary_contagious; // 次密接
    bool is_waiting_for_upload; // 待上传结果
    bool is_in_line; // 在排队
    bool is_not_detected; // 未检测
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
