#include "UFSets.h"                    // ������
#include <iostream>
#include <fstream>

using namespace std;
int main1() {
    int N, M; // N��������M���ѯ
    char c; // �� S H
    int a, b; // ����

    ifstream infile;
    ofstream outfile, detailFile;

    infile.open("ex1/virus.in");
    infile >> N >> M;

    UFSets<int> sets(N); // ���첢�鼯
    int restriction_virus[N];

    for (int i = 0; i < N; i++) {
        restriction_virus[i] = 0;
    }

    for (int i = 0; i < M; i++) {
        infile >> c >> a >> b;

        if (c == 'S') { // ͬ�ಡ��
            sets.Union(a, b);
        } else if (c == 'H') { // ��˲���
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
        detailFile << "�ڵ��±�:\t" << i
                   << "\t�ڵ�����: " << sets.virus[i].data
                   << "\t�ڵ����ڼ��ϸ��ڵ��±�: " << sets.virus[i].parent
                   << endl;
    }

    outfile.open("ex1/virus.out");

    int res = 0;

    // �жϲ��鼯֮�и��ڵ�����
    for (int i = 0; i < N; i++)
        if (sets.virus[i].parent <= -1) res++;
    outfile << res << endl;
    cout << res;

    // �ر��ļ���
    infile.close();
    detailFile.close();
    outfile.close();

    return 0;
}
