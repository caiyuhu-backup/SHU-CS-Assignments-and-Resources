//
// Created by Silence on 2022/4/21.
//

#include "Utils.h"

int Utils::getBlockNum(int num) {
    if (num >= 100000) {
        return num / 100000;
    } else
        return 0;
}
