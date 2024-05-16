import pymysql


def get_conn():
    """
    :return: 连接，游标
    """
    # 创建连接
    conn = pymysql.connect(host="127.0.0.1",
                           user="root",
                           password="hcy123450",
                           db="cov",
                           charset="utf8")
    # 创建游标
    cursor = conn.cursor()  # 执行完毕返回的结果集默认以元组显示
    return conn, cursor


def close_conn(conn, cursor):
    cursor.close()
    conn.close()


def query(sql, *args):
    """
    封装通用查询
    :param sql:
    :param args:
    :return: 返回查询到的结果，((),(),)的形式
    """
    conn, cursor = get_conn()
    cursor.execute(sql, args)
    res = cursor.fetchall()
    close_conn(conn, cursor)
    return res


def get_c1_data():
    """
    :return: 返回大屏div id=c1 的数据
    """
    # 因为会更新多次数据，取时间戳最新的那组数据
    sql = """
    SELECT total,total-heal-dead,heal,dead from (
    select sum(confirm) total, 
    (SELECT heal from history ORDER BY ds desc LIMIT 1) heal ,
      sum(dead) dead 
    from details where update_time=(
      select update_time from details order by update_time desc limit 1)
    ) d;
    """
    res = query(sql)
    return res[0]


def get_c2_data():
    """
    :return:  返回各省数据
    """
    # 因为会更新多次数据，取时间戳最新的那组数据
    sql = "select province,sum(confirm_now) from details " \
          "where update_time=(select update_time from details " \
          "order by update_time desc limit 1) " \
          "group by province"
    res = query(sql)
    return res


def get_l1_data():
    sql = "select ds,confirm_add,suspect_add from history"
    res = query(sql)
    return res


def get_l2_data():
    # 因为会更新多次数据，取时间戳最新的那组数据
    sql = "select end_update_time,province,city,county,address,type" \
          " from risk_area " \
          "where end_update_time=(select end_update_time " \
          "from risk_area " \
          "order by end_update_time desc limit 1) "
    res = query(sql)
    return res


def get_r1_data():
    """
    :return:  返回剩余确诊人数前5名的省份
    """
    sql = 'SELECT province,confirm FROM ' \
          '(select province ,sum(confirm_now) as confirm from details  ' \
          'where update_time=(select update_time from details ' \
          'order by update_time desc limit 1) ' \
          'group by province) as a ' \
          'ORDER BY confirm DESC LIMIT 5'
    res = query(sql)
    return res


def get_r2_data():
    """
    :return:  返回最近的30条热搜
    """
    sql = 'select content from hotsearch order by id desc limit 30'
    res = query(sql)  # 格式 (('民警抗疫一线奋战16天牺牲 1037364',), ('四川再派两批医疗队 1537382',)
    return res


def get_shanghai_data():
    """
    :return:  返回上海疫情数据
    """
    sql = "select date_, con_num, sus_num, cure_num, death_num, con_add, sus_add, cure_add, death_add from shanghai_history order by date_ desc limit 100"
    res = query(sql)
    return res[::-1]
