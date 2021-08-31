import subprocess
import os

file_dir = os.getcwd()
file_list = os.listdir(file_dir)
python_file_list = []
txt_file_list = []
for i in file_list:
    # os.path.splitext():分离文件名与扩展名
    file_name, file_extension = os.path.splitext(i)[0], os.path.splitext(i)[1]
    if file_extension == '.js':
        python_file_list.append(file_name)
    elif file_extension == '.txt':
        txt_file_list.append(file_name)

# 对于.txt 先全删除
for i in txt_file_list:
    os.remove(os.path.join(file_dir, i) + '.txt')


batch_run_result = []
for python_file in python_file_list:
    print(python_file, end='')
    time_limit = 2
    try:
        subprocess.check_output(['node', python_file + '.js'], shell = False, timeout = time_limit)
        batch_run_result.append(python_file + '\t' + str(False))
    except subprocess.TimeoutExpired as time_e:
        if (os.path.exists(python_file + '.txt')):
            with open(python_file + '.txt', 'r') as f:
                file_content = f.readlines()
                last_content = file_content[-1].rstrip('\n')
                length, time = last_content.split(' ')
                length = int(length)
                time = float(time)
                # print(length, time)
                if (length <= 100000 and float(time) >= 0.5):
                    batch_run_result.append(python_file + '\t' + str(True))
                elif (len(file_content) < 5):
                    batch_run_result.append(python_file + '\t' + str(True))
                elif (length <= 1000 and float(time) >= 0.1):
                    batch_run_result.append(python_file + '\t' + str(True))
                else:
                    batch_run_result.append(python_file + '\t' + str(False))
        else:
            batch_run_result.append(python_file + '\t' + str(True))
    except subprocess.CalledProcessError as call_e:
        batch_run_result.append(python_file + '\t' + str(call_e))
        print(call_e)
    except Exception as e:
        batch_run_result.append(python_file + '\t' + str(e))
        print(e)

    print('\t', batch_run_result[-1].split('\t')[-1])

# 输出结果
with open(os.path.join(file_dir, 'batch_run_result.log'), 'w+') as f:
    for idx, _ in enumerate(batch_run_result):
        f.write(batch_run_result[idx] + '\n')
