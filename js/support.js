const fs = require("fs");
var arguments = process.argv[2];
file_name = arguments;
const source = fs.readFileSync(file_name, {encoding: 'utf8'});
const lines = source.split("\n");
let regex = lines[0];
let modifiers = lines[1];
let pattern = new RegExp(regex);
let prefix = lines[2];
let repeat = lines[3];
let suffix = lines[4];
let times = lines[5];
s = Date.now();
let str = prefix;
for (i = 0; i < times; i++) {
    str = str + repeat;
}
str = str + suffix;
str = str.replace(/\\n/g, '\n').replace(/\\v/g, '\v').replace(/\\r/g, '\r').replace(/\\f/g, '\f').replace(/\\\\/g, "\\");
let start = Date.now();
start = Date.now() - start;
pattern.test(str);
s = Date.now() - s;
console.log(s);