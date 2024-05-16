#include "managementSystem.h"

int main(void) {
    char flag = 'y';
    managementSystem mySystem = managementSystem();
    bool line_up = false;
    bool nucleic_acid_test = false;
    while (flag == 'y' || flag == 'Y') //由 flag 控制循环
    {
        cout << "1: 排队!\n";
        cout << "2: 检测!\n";
        cout << "3: 查看排队情况!\n";
        cout << "4: 登记测试管信息!\n";
        cout << "5: 各类人员查询!\n";
        cout << "6: 个人查询!\n";
        cout << "7: 退出!\n 输入选择：";
        int f;
        cin >> f;

        switch (f) {
            case 1:
                cout << "====================================" << endl;
                cout << "排队信息存储在line_up.in文件下!" << endl;
                mySystem.line_up();
                cout << "排队成功!" << endl;
                cout << "====================================" << endl;
                line_up = true;
                break;
            case 2:
                if (line_up) {
                    cout << "====================================" << endl;
                    cout << "排队信息存储在nucleic_acid_test.in文件下!" << endl;
                    mySystem.nucleic_acid_test();
                    cout << "核酸检测成功!" << endl;
                    cout << "====================================" << endl;
                    nucleic_acid_test = true;
                } else {
                    cout << "====================================" << endl;
                    cout << "还未进行排队,请先排队!" << endl;
                    cout << "====================================" << endl;
                }
                break;
            case 3:
                if (line_up) {
                    mySystem.show_line_up();
                } else {
                    cout << "====================================" << endl;
                    cout << "还未进行排队,请先排队!" << endl;
                    cout << "====================================" << endl;
                }
                break;
            case 4:
                if (line_up && nucleic_acid_test) {
                    cout << "====================================" << endl;
                    cout << "测试管信息存储在registration.in文件下! (0为阴性 1为阳性)" << endl;
                    cout << "核酸检测结果登记成功!" << endl;
                    mySystem.Register_test_tube();
                    cout << "====================================" << endl;
                } else {
                    cout << "====================================" << endl;
                    cout << "还未进行排队"
                            "与核酸检测,请先排队并完成核酸检测!" << endl;
                    cout << "====================================" << endl;
                }
                break;
            case 5: {
                cout << "====================================" << endl;
                cout << "查询结果如下:" << endl;
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