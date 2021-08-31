# ReDoSHunter
ReDoSHunter is a combined static and dynamic approach for regular expression DoS detection.

You can find more information in the paper [ReDoSHunter: A Combined Static and Dynamic Approach for Regular Expression DoS Detection](https://www.usenix.org/system/files/sec21-li-yeting.pdf).

```tex
@inproceedings {Li2021ReDoSHunter,
    author = {Yeting Li and Zixuan Chen and Jialun Cao and Zhiwu Xu and Qiancheng Peng and Haiming Chen and Liyuan Chen and Shing-Chi Cheung},
    title = {ReDoSHunter: A Combined Static and Dynamic Approach for Regular Expression DoS Detection},
    booktitle = {30th {USENIX} Security Symposium ({USENIX} Security 21)},
    year = {2021},
    isbn = {978-1-939133-24-3},
    pages = {3847--3864},
    url = {https://www.usenix.org/conference/usenixsecurity21/presentation/li-yeting},
    publisher = {{USENIX} Association},
    month = aug,
}
```

## Usage

### 1. Detect a single regular expression (cannot set timeout)
The main function is in ./src/main/java/cn/ac/ios/ReDoSMain.java

For example, if you type ```String regex = "^(a+)+b";``` then run this file,

If the input regex is vulnerable, you will get:
```
^(a+)+b
Is attack success: true
Attack time: 1016 (ms)
Vulnerability Position: ^►(▻a+◅)+◄b
Attack String: "a"+"a"*32+" "
Vulnerability Source: There is a nested quantifier node a+ in (a+)+.
Vulnerability Degree: EXPONENT
```

### 2. Test regexes from datasets (can set timeout)
The file structure is
```
ReDosHunter
├─ ...
├─ data	
│   ├─ expr  # The results will show here!
│   │  ├─ empty.txt  # This file is only used to keep an empty folder in github.
│   │  └─ ...
│   ├─ paper_dataset # Corpus, RegexLib, Snort are the data set used in our paper.
│   │  ├─ corpus.txt 
│   │  ├─ regexlib.txt
│   │  ├─ snort.txt
│   │  └─ test.txt   # put your dataset here!
│   └─ ...
└─ ...
```

The main function is in ./src/main/java/cn/ac/ios/Test.java

You can change ```String filename``` in the main function and run this file.

### 3. The parameters of static detection & dynamic verification
The default parameter is: 15 threads are used for static detection and 1 thread is used for dynamic verification. For a regular, the upper limit time of static detection is 60s. Other parameters are in ./src/main/java/cn/ac/ios/Bean/AttackBean.java.

Static detection contains five patterns (i.e., NQ, EOD, EOA, POA, SLQ in our paper): By default, The five patterns are all detected. If you want to detect not all patterns, change the parameter ```options``` in the ```checkReDoS``` function.

Dynamic verification is divided into two modes:
S mode (default): for each regular expression, if one attack string attack is successful, stop the verification of other attack strings.
M mode: verify all attack strings.
The change method is to change the parameter ```model``` in the ```validateReDoS``` function.

Both static detection and dynamic verification support regular expressions under different languages. Java (default), python, JavaScript and PCRE. 
If you want to change language, please change the ```language``` parameter in the ```checkReDoS``` and ```validateReDoS``` functions.


## License
Check the LICENSE.md file.
