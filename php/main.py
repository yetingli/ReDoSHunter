import json
import os
import subprocess
import sys



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
        ret = subprocess.run(args=["php", "php/index.php", str(out), str(i), str(times)], capture_output=True, timeout=1)
        return_msg = ret.returncode
        # print(return_msg)
        if return_msg == 12:
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
    file_name = sys.argv[1]
    output_file_name = sys.argv[2]
    model = sys.argv[3]
    data = json.loads(open(file_name, encoding="utf-8").read())
    l = list()
    for item in data:
        redos = "False"
        id = item["id"]
        patternType = ""
        type = ""
        d = dict()
        d["id"] = id
        attacks = item["attackArrayList"]
        if len(attacks) > 0:
            # print(len(attacks))
            out = str(id) + ".json"
            # print(item)
            with open(out, 'w') as f:
                json.dump(item, f, indent=" ")
            for i in range(0, min(1000, len(attacks))):
                attack = attacks[i]
                # print("attack " + str(i))
                result = get_duration(out, i, attacks[i])
                # print(result)
                if result == "Backtrack limit was exhausted":
                    redos = "Backtrack limit was exhausted"
                    attack["redos"]  = redos
                elif result != "":
                    type = result
                    patternType = attacks[i]["patternType"]
                    redos = "True"
                    attack["type"] = result
                    attack["patternType"] = patternType
                    attack["redos"]  = redos
                    if model =="s":
                        break
            os.remove(out)
        # print(redos)
        # d["redos"] = redos
        # print(type)
        # d["type"] = type
        # print(patternType)
        # d["patternType"] = patternType
        # l.append(d)
    with open(output_file_name, 'w') as f:
        json.dump(data, f, indent=" ")
