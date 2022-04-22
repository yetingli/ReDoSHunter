use warnings FATAL => 'all';
use strict;
use JSON;
use Try::Tiny;
use Encode;
my $out = $ARGV[0];
my $id = $ARGV[1];
my $times = $ARGV[2];

my $content;
open TXT, $out or die "Can't open out!\n";
while (<TXT>) {
    $content .= $_;
}
close TXT;
my $json = decode_json($content);
my $regex = $json->{'regex'};
print($regex);
print("\n");
my @attacks = $json->{'attackArrayList'};
my $attack = $attacks[0][$id];
my $prefix = $attack->{'prefix'};
my $infix = $attack->{'infix'};
my $suffix = $attack->{'suffix'};
print($regex);
print("\n");
print($prefix);
print("\n");
print($infix);
print("\n");
print($suffix);
print("\n");
my $str = $prefix;

for ($a = 0; $a < $times; $a = $a + 1) {
    $str .= $infix;
}
$str .= $suffix;
try {
    if ($str =~ $regex) {
        print "匹配\n";
    }
    else {
        print "不匹配\n";
    }
    exit(11)
}
catch {
    exit(12)
};