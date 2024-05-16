#include "managementSystem.h"

int main(void) {
    char flag = 'y';
    managementSystem mySystem = managementSystem();
    bool line_up = false;
    bool nucleic_acid_test = false;
    while (flag == 'y' || flag == 'Y') //�� flag ����ѭ��
    {
        cout << "1: �Ŷ�!\n";
        cout << "2: ���!\n";
        cout << "3: �鿴�Ŷ����!\n";
        cout << "4: �Ǽǲ��Թ���Ϣ!\n";
        cout << "5: ������Ա��ѯ!\n";
        cout << "6: ���˲�ѯ!\n";
        cout << "7: �˳�!\n ����ѡ��";
        int f;
        cin >> f;

        switch (f) {
            case 1:
                cout << "====================================" << endl;
                cout << "�Ŷ���Ϣ�洢��line_up.in�ļ���!" << endl;
                mySystem.line_up();
                cout << "�Ŷӳɹ�!" << endl;
                cout << "====================================" << endl;
                line_up = true;
                break;
            case 2:
                if (line_up) {
                    cout << "====================================" << endl;
                    cout << "�Ŷ���Ϣ�洢��nucleic_acid_test.in�ļ���!" << endl;
                    mySystem.nucleic_acid_test();
                    cout << "������ɹ�!" << endl;
                    cout << "====================================" << endl;
                    nucleic_acid_test = true;
                } else {
                    cout << "====================================" << endl;
                    cout << "��δ�����Ŷ�,�����Ŷ�!" << endl;
                    cout << "====================================" << endl;
                }
                break;
            case 3:
                if (line_up) {
                    mySystem.show_line_up();
                } else {
                    cout << "====================================" << endl;
                    cout << "��δ�����Ŷ�,�����Ŷ�!" << endl;
                    cout << "====================================" << endl;
                }
                break;
            case 4:
                if (line_up && nucleic_acid_test) {
                    cout << "====================================" << endl;
                    cout << "���Թ���Ϣ�洢��registration.in�ļ���! (0Ϊ���� 1Ϊ����)" << endl;
                    cout << "���������Ǽǳɹ�!" << endl;
                    mySystem.Register_test_tube();
                    cout << "====================================" << endl;
                } else {
                    cout << "====================================" << endl;
                    cout << "��δ�����Ŷ�"
                            "�������,�����ŶӲ���ɺ�����!" << endl;
                    cout << "====================================" << endl;
                }
                break;
            case 5: {
                cout << "====================================" << endl;
                cout << "��ѯ�������:" << endl;
                cout << "====================================" << endl;
                mySystem.status_category_query();
            }
                break;
            case 6:
                mySystem.query_for_person();
                break;
            default:
                flag = 'n';
        }
    }
    system("pause");
    return 0;
}