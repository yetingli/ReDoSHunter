const fs = require("fs");
var arguments = process.argv;
out = arguments[2];
id = arguments[3];
times = arguments[4]
let a = fs.readFileSync(out);
let data = JSON.parse(a);
regex = data["regex"]
attacks = data["attackArrayList"]
attack = attacks[id]
prefix = attack["prefix"]
infix = attack["infix"]
suffix = attack["suffix"]
let attackStr = prefix
for (i = 0; i < times; i++) {
    attackStr = attackStr + infix
}
attackStr = attackStr + suffix

pattern = new RegExp(regex);
let redos = pattern.test(attackStr)

