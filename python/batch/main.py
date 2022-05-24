# coding=utf-8
# 测试utf-8编码
import json
import re
import sys

import signal, functools

import os


class TimeoutError(Exception):
    pass


def timeout(seconds, error_message="Timeout Error: the cmd have not finished."):
    def decorated(func):
        result = ""

        def _handle_timeout(signum, frame):
            global result
            result = error_message
            raise TimeoutError(error_message)

        def wrapper(*args, **kwargs):
            global result
            signal.signal(signal.SIGALRM, _handle_timeout)
            signal.alarm(seconds)

            try:
                result = func(*args, **kwargs)
            finally:
                signal.alarm(0)
                return result
            return result

        return functools.wraps(func)(wrapper)

    return decorated


@timeout(1)  # 限定下面的slowfunc函数如果在5s内不返回就强制抛TimeoutError Exception结束
def slowfunc(regex, attackstr):
    b = re.fullmatch(regex, attackstr)
    return "False"


def run(regex, attack):
    type = attack["type"]
    prefix = attack["prefix"]
    infix = attack["infix"]
    suffix = attack["suffix"]
    if type == "EXPONENT":
        attackStr = prefix + infix * 1000 + suffix
        return slowfunc(regex, attackStr) + "type" + "EXPONENT"
    elif type == "POLYNOMIAL":
        attackStr = prefix + infix * 100000 + suffix
        return slowfunc(regex, attackStr) + "type" + "POLYNOMIAL"
    else:
        attackStr = prefix + infix * 1000 + suffix
        result = slowfunc(regex, attackStr)
        if result == "False":
            attackStr = prefix + infix * 100000 + suffix
            return slowfunc(regex, attackStr) + "type" + "POLYNOMIAL"
        else:
            return result + "type" + "EXPONENT"


def fun(file="1_only_check_s_python_11111_0_2021_12_07_13_58_03.txt"):
    output = "result/" + file.replace(".txt", ".result.json")
    if os.path.exists(output):
        return
    print(file)
    # return
    data = json.loads(open(path + "/" + file, encoding="utf-8").read())
    l = list()
    for item in data:
        redos = False
        id = item["id"]
        regex = item["regex"]
        attacks = item["attackArrayList"]
        patternType = ""
        type = ""
        d = dict()
        print(id)
        d["id"] = id
        if len(attacks) > 0 and id != 205:
            for attack in attacks:
                result = run(regex, attack)
                if "Timeout Error: the cmd have not finished." in result:
                    redos = True
                    patternType = attack["patternType"]
                    type = result.split("type")[-1]
                    break
        print(redos)
        d["redos"] = redos
        print(type)
        d["type"] = type
        print(patternType)
        d["patternType"] = patternType
        l.append(d)
    with open("result/" + file.replace(".txt", ".result.json"), 'w') as f:
        json.dump(l, f, indent=" ")

if __name__ == '__main__':
    path = "check_data"
    files = os.listdir(path)
    for file in files:
        if file.endswith(".txt"):
            fun(file)
