1. batch 用于单独批量处理正则表达式（需要手动执行，只执行验证阶段，检测阶段由Test.onlyCheck()完成）
2. 其余代码用于配合ReDoSHunter完成多语言功能，无法单独执行，只能通过ReDoSHunter调用。

---
1. `batch` is used to process regexes in batches (it needs to be executed manually, only the verification phase is executed, and the detection phase is completed by Test.onlyCheck())

2. Other codes are used to cooperate with ReDoSHunter to complete multi language functions. They cannot be executed alone and can only be called through ReDoSHunter.