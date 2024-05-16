import tkinter.ttk
from tkinter import *
import tkinter.messagebox


def cancel():
    ch1.deselect()
    ch2.deselect()
    ch3.deselect()
    ch4.deselect()
    ch5.deselect()
    ch6.deselect()
    ch7.deselect()

    input_name.delete(0, tkinter.END)
    input_num.delete(0, tkinter.END)

    RaVar.set(" ")
    comb.set("")
    lb_hint.config(text=" ")


def commit():
    global s_last, s_first
    if (
            check_var1.get() == 0 and check_var2.get() == 0
            and check_var3.get() == 0 and check_var4.get() == 0
            and check_var5.get() == 0 and check_var6.get() == 0
            and check_var7.get() == 0):
        s_first = "您所有知识点均未掌握,"
    elif not (
            check_var1.get() == 1 and check_var2.get() == 1
            and check_var3.get() == 1 and check_var4.get() == 1
            and check_var5.get() == 1 and check_var6.get() == 1
            and check_var7.get() == 1):
        s_first = "您还未掌握所有知识点"
    elif (
            check_var1.get() == 1 and check_var2.get() == 1
            and check_var3.get() == 1 and check_var4.get() == 1
            and check_var5.get() == 1 and check_var6.get() == 1
            and check_var7.get() == 1):
        s_first = "您已掌握所有知识点"

    ra_str = RaVar.get()
    if not ra_str is None:
        # 后半个字符串
        s_last = ",您觉得" + ra_str + "为最难理解的知识点。"

    s = s_first + s_last
    lb_hint.config(text=s)


def xz():
    name = input_name.get()
    num = input_num.get()
    time = comb.get()
    str_ = name + "(学号：" + num + "),您已预约" + time + "答疑,确定提交?"
    answer = tkinter.messagebox.askokcancel("提交确认", str_)
    if answer:
        commit()


root = Tk()
root.wm_title("20121034关于图形化界面设计调查窗体")
root.wm_geometry('600x600')

lb_name = Label(root, text="姓名:", font=("宋体", 15), anchor=NW)
lb_name.place(relx=0.01, rely=0.01, relwidth=0.1, relheight=0.05)

input_name = Entry(root, font=("宋体", 15), )
input_name.place(relx=0.11, rely=0.01, relwidth=0.2, relheight=0.05)

lb_num = Label(root, text="学号:", font=("宋体", 15), anchor=NW)
lb_num.place(relx=0.41, rely=0.01, relwidth=0.1, relheight=0.05)

input_num = Entry(root, font=("宋体", 15))
input_num.place(relx=0.51, rely=0.01, relwidth=0.2, relheight=0.05)

lb_checkbox = Label(root, text="已掌握的知识点:", font=("宋体", 12), fg='red', anchor=NW)
lb_checkbox.place(relx=0.01, rely=0.11, relwidth=0.3, relheight=0.05)

check_var1 = IntVar()
check_var2 = IntVar()
check_var3 = IntVar()
check_var4 = IntVar()
check_var5 = IntVar()
check_var6 = IntVar()
check_var7 = IntVar()

ch1 = Checkbutton(root, text="标签", font=("宋体", 12), variable=check_var1, onvalue=1, offvalue=0)
ch2 = Checkbutton(root, text="输入框", font=("宋体", 12), variable=check_var2, onvalue=1, offvalue=0)
ch3 = Checkbutton(root, text="组合框", font=("宋体", 12), variable=check_var3, onvalue=1, offvalue=0)
ch4 = Checkbutton(root, text="单选按钮", font=("宋体", 12), variable=check_var4, onvalue=1, offvalue=0)
ch5 = Checkbutton(root, text="复选框", font=("宋体", 12), variable=check_var5, onvalue=1, offvalue=0)
ch6 = Checkbutton(root, text="按钮", font=("宋体", 12), variable=check_var6, onvalue=1, offvalue=0)
ch7 = Checkbutton(root, text="交互对话框", font=("宋体", 12), variable=check_var7, onvalue=1, offvalue=0)

ch1.place(relx=0.01, rely=0.20, relwidth=0.3, relheight=0.05)
ch2.place(relx=0.21, rely=0.20, relwidth=0.3, relheight=0.05)
ch3.place(relx=0.41, rely=0.20, relwidth=0.3, relheight=0.05)
ch4.place(relx=0.61, rely=0.20, relwidth=0.3, relheight=0.05)
ch5.place(relx=0.01, rely=0.28, relwidth=0.3, relheight=0.05)
ch6.place(relx=0.21, rely=0.28, relwidth=0.3, relheight=0.05)
ch7.place(relx=0.41, rely=0.28, relwidth=0.3, relheight=0.05)

RaVar = StringVar()
RaVar.set(0)
ra1 = Radiobutton(root, text='标签', font=("宋体", 12), value="标签", variable=RaVar)
ra2 = Radiobutton(root, text='输入框', font=("宋体", 12), value="输入框", variable=RaVar)
ra3 = Radiobutton(root, text='组合框', font=("宋体", 12), value="组合框", variable=RaVar)
ra4 = Radiobutton(root, text='单选按钮', font=("宋体", 12), value="单选按钮", variable=RaVar)
ra5 = Radiobutton(root, text='复选框', font=("宋体", 12), value="复选框", variable=RaVar)
ra6 = Radiobutton(root, text='按钮', font=("宋体", 12), value="按钮", variable=RaVar)
ra7 = Radiobutton(root, text='交互对话框', font=("宋体", 12), value="交互对话框", variable=RaVar)

lb_radio = Label(root, text="最难掌握的的知识点:", font=("宋体", 12), fg='red', anchor=NW)
lb_radio.place(relx=0.01, rely=0.40, relwidth=0.3, relheight=0.05)

ra1.place(relx=0.01, rely=0.45, relwidth=0.3, relheight=0.05)
ra2.place(relx=0.21, rely=0.45, relwidth=0.3, relheight=0.05)
ra3.place(relx=0.41, rely=0.45, relwidth=0.3, relheight=0.05)
ra4.place(relx=0.61, rely=0.45, relwidth=0.3, relheight=0.05)
ra5.place(relx=0.01, rely=0.55, relwidth=0.3, relheight=0.05)
ra6.place(relx=0.21, rely=0.55, relwidth=0.3, relheight=0.05)
ra7.place(relx=0.41, rely=0.55, relwidth=0.3, relheight=0.05)

lb_radio = Label(root, text="预约答疑时间:", font=("宋体", 12), fg='red', anchor=NW)
lb_radio.place(relx=0.01, rely=0.70, relwidth=0.3, relheight=0.05)

var1 = StringVar()
comb = tkinter.ttk.Combobox(root, textvariable=var1,
                            values=["10:00-10:40", "11:00-11:40", "12:00-12:40", "13:00-13:40"])
comb.place(relx=0.01, rely=0.75, relwidth=0.3, relheight=0.05)

commit_button = Button(root, text='提交', command=xz)
commit_button.place(relx=0.21, rely=0.85, relwidth=0.2, relheight=0.05)

reset_button = Button(root, text='重置', command=cancel)
reset_button.place(relx=0.51, rely=0.85, relwidth=0.2, relheight=0.05)

lb_hint = Label(root, text="", font=("宋体", 12), fg='blue', anchor=NW)
lb_hint.place(relx=0.01, rely=0.90)

root.mainloop()
