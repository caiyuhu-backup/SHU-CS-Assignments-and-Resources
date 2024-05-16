#include "UFSets.h"                    // 并查类
#include <iostream>
#include <fstream>

using namespace std;
int main1() {
    int N, M; // N个病毒　M组查询
    char c; // 存 S H
    int a, b; // 存编号

    ifstream infile;
    ofstream outfile, detailFile;

    infile.open("ex1/virus.in");
    infile >> N >> M;

    UFSets<int> sets(N); // 构造并查集
    int restriction_virus[N];

    for (int i = 0; i < N; i++) {
        restriction_virus[i] = 0;
    }

    for (int i = 0; i < M; i++) {
        infile >> c >> a >> b;

        if (c == 'S') { // 同类病毒
            sets.Union(a, b);
        } else if (c == 'H') { // 相克病毒
            if (!restriction_virus[a])
                restriction_virus[a] = b;
            if (!restriction_virus[b])
                restriction_virus[b] = a;
            sets.Union(b, restriction_virus[a]);
            sets.Union(a, restriction_virus[b]);
        }
    }
    detailFile.open("ex1/virus.detail");
    for (int i = 0; i < N; i++) {
        detailFile << "节点下标:\t" << i
                   << "\t节点数据: " << sets.virus[i].data
                   << "\t节点所在集合根节点下标: " << sets.virus[i].parent
                   << endl;
    }

    outfile.open("ex1/virus.out");

    int res = 0;

    // 判断并查集之中根节点数量
    for (int i = 0; i < N; i++)
        if (sets.virus[i].parent <= -1) res++;
    outfile << res << endl;
    cout << res;

    // 关闭文件流
    infile.close();
    detailFile.close();
    outfile.close();

    return 0;
}
