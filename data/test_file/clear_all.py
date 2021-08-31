import os

file_dir = os.getcwd()
sub_folder_list = os.listdir(file_dir)
sub_folder_list.remove(os.path.split(__file__)[-1])
for sub_folder in sub_folder_list:
    sub_folder2_list = os.listdir(sub_folder)
    for sub_folder2 in sub_folder2_list:
        file_name_list = os.listdir(os.path.join(file_dir, sub_folder, sub_folder2))
        for file_name in file_name_list:
            if file_name != 'batch_run.py':
                os.remove(os.path.join(file_dir, sub_folder, sub_folder2, file_name))