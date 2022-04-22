<?php
$out = $argv[1];
$id = $argv[2];
$times = $argv[3];
$content = json_decode(file_get_contents($out), true);
$regex = "/";
$regex .= $content["regex"];
$regex .= "/";
$attacks = $content["attackArrayList"];
$attack = $attacks[$id];
$prefix = $attack["prefix"];
$infix = $attack["infix"];
$suffix = $attack["suffix"];

$str = $prefix;
$str .= str_repeat($infix, (int)$times);
$str .= $suffix;
$a = preg_match($regex, $str);
if (preg_last_error() == PREG_BACKTRACK_LIMIT_ERROR) {
    echo "Backtrack limit was exhausted";
    return;
} else {
    echo "no error";
    return;
}
?>