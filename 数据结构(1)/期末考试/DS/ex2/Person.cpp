//
// Created by Silence on 2022/4/19.
//

#include "Person.h"
#include "Utils.h"

Person::~Person() {}
Person::Person(int number) : number(number), is_negative(false), is_diagnosed(false), is_suspicious(false),
                             is_contagious(
                                     false), is_secondary_contagious(false), is_waiting_for_upload(false),
                             is_in_line(true), is_not_detected(false) {
    blockNumber = Utils::getBlockNum(number);
}
Person::Person() {}
int Person::getNumber() const {
    return number;
}
void Person::setNumber(int number) {
    Person::number = number;
}
int Person::getBlockNumber() const {
    return blockNumber;
}
void Person::setBlockNumber(int blockNumber) {
    Person::blockNumber = blockNumber;
}
bool Person::isNegative() const {
    return is_negative;
}
void Person::setIsNegative(bool isNegative) {
    is_negative = isNegative;
}
bool Person::isDiagnosed() const {
    return is_diagnosed;
}
void Person::setIsDiagnosed(bool isDiagnosed) {
    is_diagnosed = isDiagnosed;
}
bool Person::isSuspicious() const {
    return is_suspicious;
}
void Person::setIsSuspicious(bool isSuspicious) {
    is_suspicious = isSuspicious;
}
bool Person::isContagious() const {
    return is_contagious;
}
void Person::setIsContagious(bool isContagious) {
    is_contagious = isContagious;
}
bool Person::isSecondaryContagious() const {
    return is_secondary_contagious;
}
void Person::setIsSecondaryContagious(bool isSecondaryContagious) {
    is_secondary_contagious = isSecondaryContagious;
}
bool Person::isWaitingForUpload() const {
    return is_waiting_for_upload;
}
void Person::setIsWaitingForUpload(bool isWaitingForUpload) {
    is_waiting_for_upload = isWaitingForUpload;
}
bool Person::isInLine() const {
    return is_in_line;
}
void Person::setIsInLine(bool isInLine) {
    is_in_line = isInLine;
}
bool Person::isNotDetected() const {
    return is_not_detected;
}
void Person::setIsNotDetected(bool isNotDetected) {
    is_not_detected = isNotDetected;
}

std::ostream &operator<<(std::ostream &os, const Person &person) {
    os << "================0为否 1为是================" << std::endl;
    os << "人员代码:\t" << person.number << std::endl
       << "楼号代码:\t" << person.blockNumber << std::endl
       << "是否阴性:\t" << person.is_negative << std::endl
       << "是否确诊:\t" << person.is_diagnosed << std::endl
       << "是否可疑:\t" << person.is_suspicious << std::endl
       << "是否密接:\t" << person.is_contagious << std::endl
       << "是否次密接:\t" << person.is_secondary_contagious << std::endl
       << "是否等待上传:\t" << person.is_waiting_for_upload << std::endl
       << "是否在排队:\t" << person.is_in_line << std::endl
       << "是否未检测:\t" << person.is_not_detected << std::endl;
    return os;
}


