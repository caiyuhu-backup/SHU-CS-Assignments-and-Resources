from flask import Flask, jsonify
from flask_cors import CORS
from jieba.analyse import extract_tags
import string
import db


app = Flask(__name__)
CORS(app, supports_credentials=True)


@app.route("/l1")
def get_l1_data():
    data = db.get_l1_data()
    day, confirm_add, suspect_add = [], [], []
    for a, b, c in data:
        day.append(a.strftime("%m-%d"))  # a是datatime类型
        confirm_add.append(b)
        suspect_add.append(c)
    return jsonify({"day": day, "confirm_add": confirm_add, "suspect_add": suspect_add})


@app.route("/l2")
def get_l2_data():
    data = db.get_l2_data()
    # end_update_time, province, city, county, address, type
    details = []
    risk = []
    end_update_time = data[0][0]
    for a, b, c, d, e, f in data:
        risk.append(f)
        details.append(f"{b}\t{c}\t{d}\t{e}")
    return jsonify({"update_time": end_update_time, "details": details, "risk": risk})


@app.route("/c1")
def get_c1_data():
    data = db.get_c1_data()
    return jsonify({"confirm": int(data[0]), "confirm_now": int(data[1]), "heal": int(data[2]), "dead": int(data[3])})


@app.route("/map")
def get_map_data():
    res = []
    for tup in db.get_c2_data():
        # [{'name': '上海', 'value': 318}, {'name': '云南', 'value': 162}]
        res.append({"name": tup[0], "value": int(tup[1])})
    return jsonify({"data": res})


@app.route("/r1")
def get_r1_data():
    data = db.get_r1_data()
    city = []
    confirm = []
    for k, v in data:
        city.append(k)
        confirm.append(int(v))
    return jsonify({"city": city, "confirm": confirm})


@app.route("/r2")
def get_r2_data():
    data = db.get_r2_data()  # 格式 (('民警抗疫一线奋战16天牺牲1037364',), ('四川再派两批医疗队1537382',)
    d = []
    for i in data:
        k = i[0].rstrip(string.digits)  # 移除热搜数字
        v = i[0][len(k):]  # 获取热搜数字
        ks = extract_tags(k)  # 使用jieba 提取关键字
        for j in ks:
            if not j.isdigit():
                d.append({"name": j, "value": v})
    return jsonify({"kws": d})


@app.route("/trend")
def get_add_trend():
    data = db.get_add_data()
    day_ls, confirm_add_ls, suspect_add_ls, heal_add_ls, dead_add_ls = [], [], [], [], []
    for date, confirm_add, suspect_add, heal_add, dead_add in data:
        day_ls.append(date.strftime("%m-%d"))  # a是datatime类型
        confirm_add_ls.append(confirm_add)
        suspect_add_ls.append(suspect_add)
        heal_add_ls.append(heal_add)
        dead_add_ls.append(dead_add)

    return jsonify(
        {"day": day_ls, "confirm_add_ls": confirm_add_ls, "suspect_add_ls": suspect_add_ls, "heal_add_ls": heal_add_ls,
         "dead_add_ls": dead_add_ls})


@app.route("/shanghai")
def get_shanghai_data():
    data = db.get_shanghai_data()
    day_ls, confirm_add_ls, suspect_add_ls, heal_add_ls, dead_add_ls = [], [], [], [], []
    for date, a, b, c, d, confirm_add, suspect_add, heal_add, dead_add in data:
        day_ls.append(date.strftime("%m-%d"))  # a是datatime类型
        confirm_add_ls.append(confirm_add)
        suspect_add_ls.append(suspect_add)
        heal_add_ls.append(heal_add)
        dead_add_ls.append(dead_add)

    return jsonify(
        {"day": day_ls, "confirm_add_ls": confirm_add_ls, "suspect_add_ls": suspect_add_ls, "heal_add_ls": heal_add_ls,
         "dead_add_ls": dead_add_ls})


if __name__ == '__main__':
    app.run(debug=True, port=5000)
