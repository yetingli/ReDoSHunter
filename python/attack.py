import multiprocessing
import time
import re
import signal
import sys



class attack_bean:
    def __init__(self, type, prefix, repeat, suffix):
        self.type = type
        self.prefix = prefix
        self.repeat = repeat
        self.suffix = suffix
        self.is_attack_success = False
        self.duration = 0
        self.times = 0


def time_out(b, c):
    raise TimeoutError


def search_with_timeout(pipe, pattern, attack_str, timeout):
    signal.signal(signal.SIGALRM, time_out)
    signal.setitimer(signal.ITIMER_REAL, timeout)
    start = time.time()
    try:
        re.search(pattern, attack_str, flags=0)
        duration = int((time.time() - start) * 1000)
        pipe.send(duration)
    except TimeoutError:
        duration = int((time.time() - start) * 1000)
        pipe.send(duration)


def get_duration(bean, times, timeout):
    string = (bean.prefix + bean.repeat * times + bean.suffix).replace("\\n", "\n").replace("\\f", "\f").replace(
        "\\v",
        "\v").replace(
        "\\r", "\r").replace("\\t", "\t").replace("\\\\", "\\")

    sub_pipe = multiprocessing.Pipe()
    p = multiprocessing.Process(target=search_with_timeout, args=(sub_pipe[0], regex, string, timeout))
    p.start()
    p.join()
    return sub_pipe[1].recv()

if __name__ == '__main__':
    file_name = sys.argv[1]

    attack_file = open(file_name)
    lines = attack_file.readlines()
    regex = lines[0].strip("\n")
    model = lines[1].strip("\n")
    attack_beans = []
    length = lines.__len__()
    i = 2
    while i < length:
        type = lines[i].rstrip("\n")
        attacks = lines[i + 1].rstrip("\n").split("IOS_AC_CN")
        attack_beans.append(attack_bean(type, attacks[0], attacks[1], attacks[2]))
        i += 2

    for bean in attack_beans:
        type = bean.type
        if type == "EXPONENT":
            times = 10
            while times < 515:
                duration = get_duration(bean, times, 0.15)
                if duration >= 150:
                    bean.is_attack_success = True
                    bean.duration = duration
                    bean.times = times
                    break
                times += 50
        if type == "POLYNOMIAL":
            times = 10000
            while times < 320000:
                duration = get_duration(bean, times, 1)
                if duration >= 1000:
                    bean.is_attack_success = True
                    bean.duration = duration
                    bean.times = times
                    break
                times *= 2
        if type == "EXPONENT_OR_POLYNOMIAL":
            times = 10
            while times < 515:
                duration = get_duration(bean, times, 0.15)
                if duration >= 150:
                    bean.is_attack_success = True
                    bean.duration = duration
                    bean.times = times
                    break
                times += 50
            if not bean.is_attack_success:
                times = 10000
                while times < 320000:
                    duration = get_duration(bean, times, 1)
                    if duration >= 1000:
                        bean.is_attack_success = True
                        bean.duration = duration
                        bean.times = times
                        break
                    times *= 2
        if model == "s" and bean.is_attack_success:
            break

    result_file = open(file_name.replace(".txt", "_result.txt"), "w")
    for bean in attack_beans:
        line = str(bean.is_attack_success) + "IOS_AC_CN" + str(bean.times) + "IOS_AC_CN" + str(bean.duration) + "\n"
        result_file.write(line)
    result_file.close()
    print("python exit")