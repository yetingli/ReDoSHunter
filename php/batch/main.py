import json
import os
import subprocess




def get_duration(out, i, attack):
    type = attack["type"]
    redos = "False"
    if type == "EXPONENT":
        redos = run(out, i, 1000)
    elif type == "POLYNOMIAL":
        redos = run(out, i, 100000)
    else:
        type = "EXPONENT"
        redos = run(out, i, 1000)
        if redos != "True":
            type = "POLYNOMIAL"
            redos = run(out, i, 100000)
    if redos == "True":
        return type
    elif redos == "Backtrack limit was exhausted":
        return redos
    else:
        return ""


def run(out, i, times):
    try:
        ret = subprocess.run(args=["php", "index.php", str(out), str(i), str(times)], capture_output=True, timeout=1)
        return_msg = ret.stdout.decode()
        if return_msg == "Backtrack limit was exhausted":
            return "Backtrack limit was exhausted"
        return "False"
    except TimeoutError as e:
        print(e)
        return "True"
    except subprocess.TimeoutExpired as e:
        print(e)
        return "True"
    except Exception as e:
        print(e)
        return "False"


if __name__ == '__main__':
    # 待批量的数据，由test.onlyCheck()函数 产生（只执行检测步骤，并将信息记录在txt文件中）
    path = "check_data"
    for file in os.listdir(path):
        index = (int(file.split("_")[0]) - 1) * 1000
        data = json.loads(open(path + "/" + file, encoding="utf-8").read())
        output = "result/" + file.replace(".txt", ".result.json")
        if os.path.exists(output):
            continue
        l = list()
        for item in data:
            redos = "False"
            id = item["id"]
            print(id)
            patternType = ""
            type = ""
            d = dict()
            d["id"] = id
            attacks = item["attackArrayList"]
            if len(attacks) > 0:
                print(len(attacks))
                out = "/Users/pqc/Desktop/redos/temp/" + str(index + id) + ".json"
                with open(out, 'w') as f:
                    json.dump(item, f, indent=" ")
                for i in range(0, min(1000, len(attacks))):
                    print("attack " + str(i))
                    result = get_duration(out, i, attacks[i])
                    if result == "Backtrack limit was exhausted":
                        redos = "Backtrack limit was exhausted"
                    elif result != "":
                        type = result
                        patternType = attacks[i]["patternType"]
                        redos = "True"
                        break
                os.remove(out)
            print(redos)
            d["redos"] = redos
            print(type)
            d["type"] = type
            print(patternType)
            d["patternType"] = patternType
            l.append(d)
        with open("result/" + file.replace(".txt", ".result.json"), 'w') as f:
            json.dump(l, f, indent=" ")
