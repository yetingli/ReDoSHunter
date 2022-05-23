const fs = require("fs");
const child_process = require('child_process');
const os = require('os').type();
var arguments = process.argv[2];
file_name = arguments;
const source = fs.readFileSync(file_name, {encoding: 'utf8'});
lines = [];
if (os === "'Windows_NT'") {
    lines = source.split("\r\n");
} else {
    lines = source.split("\n");
}
const regex = lines[0];
const modifiers = lines[1];
const model = lines[2];
let attackBeans = [];
let i = 3;
while (i < lines.length - 1) {
    let attacks = lines[i + 1].split("IOS_AC_CN");
    let o = createAttackBean(lines[i], attacks[0], attacks[1], attacks[2]);
    attackBeans.push(o);
    i += 2
}

for (j = 0, len = attackBeans.length; j < len; j++) {
    if (attackBeans[j].type.toString().trim() === "EXPONENT") {
        let times = 1000;
        let duration = get_duration(attackBeans[j], times, 150);
        if (duration >= 150) {
            attackBeans[j].is_attack_success = true;
            attackBeans[j].times = times;
            attackBeans[j].duration = duration;
            break;
        }
    } else if (attackBeans[j].type.toString().trim() === "POLYNOMIAL") {
        let times = 100000;
        let duration = get_duration(attackBeans[j], times, 1000);
        if (duration >= 1000) {
            attackBeans[j].is_attack_success = true;
            attackBeans[j].times = times;
            attackBeans[j].duration = duration;
        }
    } else if (attackBeans[j].type.toString().trim() === "EXPONENT_OR_POLYNOMIAL") {
        let times = 1000;
        let duration = get_duration(attackBeans[j], times, 150);
        if (duration >= 150) {
            attackBeans[j].is_attack_success = true;
            attackBeans[j].times = times;
            attackBeans[j].duration = duration;
        }
        if (!attackBeans[j].is_attack_success) {
            times = 100000;
            let duration = get_duration(attackBeans[j], times, 1000);
            if (duration >= 1000) {
                attackBeans[j].is_attack_success = true;
                attackBeans[j].times = times;
                attackBeans[j].duration = duration;
            }
        }
    }
    if (model.toString().trim() === "s" && attackBeans[j].is_attack_success) {
        break
    }
}

let results = "";
for (j = 0, len = attackBeans.length; j < len; j++) {
    let bean = attackBeans[j];
    let line = bean.is_attack_success + "IOS_AC_CN" + bean.times + "IOS_AC_CN" + bean.duration;
    results = results + line + "\n";
}
fs.writeFileSync(file_name.replace(".txt", "_result.txt"), results);


function createAttackBean(type, prefix, repeat, suffix) {
    let o = {};
    o.type = type;
    o.prefix = prefix;
    o.repeat = repeat;
    o.suffix = suffix;
    o.is_attack_success = false;
    o.duration = 0;
    o.times = 0;
    return o;
}

function get_duration(bean, times, timeout) {
    let text = regex + "\n" + modifiers + "\n" + bean.prefix + "\n" + bean.repeat + "\n" + bean.suffix + "\n" + times + "\n";
    let temp = "js/temp_" + Date.now() + ".txt";
    fs.writeFileSync(temp, text);
    let duration = 0;
    var time = Date.now();
    try {
        duration = Number(child_process.execSync('node js/support.js ' + temp, {timeout: timeout}).toString().trim());
    } catch (e) {
        duration = Date.now() - time;
    }
    fs.unlinkSync(temp);
    return duration;
}