import datetime

if __name__ == '__main__':
    while True:
        try:
            input_time_raw = input("请输入一个时间,格式为 (__:__:__)：")
            input_time_processed = datetime.datetime.strptime(input_time_raw, "%H:%M:%S")
            input_time_processed += datetime.timedelta(seconds=1)
            print("1秒后的时间为：", input_time_processed.strftime("%H:%M:%S"))
            print("输入合法,程序退出")
            break
        except Exception:
            print("请输入合法的时间！")
# 23:44:20
# 23:59:59
# 29:20:10
