package cn.ac.ios.Bean;

import java.util.ArrayList;

public class Output {
    public int id;
    public String regex;
    public ArrayList<Attack> attackArrayList;

    public Output(int id, String regex, ArrayList<Attack> attackArrayList) {
        this.id = id;
        this.regex = regex;
        this.attackArrayList = attackArrayList;
    }
}
