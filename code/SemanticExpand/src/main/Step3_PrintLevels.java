package main;

import util.Util;

import java.util.ArrayList;
import java.util.Collections;

public class Step3_PrintLevels implements Comparable<Step3_PrintLevels> {
    public String typeOfIdentifier;
    public String relation;
    public String H;
    public String Type;
    public String TypeRelation;
    public String TypeRelationH;
    public String rightExpan;
    public String precision;
    public String metric;


    public Step3_PrintLevels(String typeOfIdentifier, String relation, String h, String type, String typeRelation, String typeRelationH, String rightExpan, String precision, String metric) {
        this.typeOfIdentifier = typeOfIdentifier;
        this.relation = relation;
        H = h;
        Type = type;
        TypeRelation = typeRelation;
        TypeRelationH = typeRelationH;
        this.rightExpan = rightExpan;
        this.precision = precision;
        this.metric = metric;
    }

    public static void main(String[] args) throws Exception {
        for (int o = 1; o <= 9; o++) {
            ArrayList<String> file = Util.readFile("data/train" + o + "_priority.csv");

            ArrayList<Step3_PrintLevels> levels = new ArrayList<>();
            for (int i = 1; i < file.size(); i++) {
                String[] strs = file.get(i).split(",", -1);
                Step3_PrintLevels level = new Step3_PrintLevels(strs[0], strs[1], strs[2], strs[3], strs[4], strs[5], strs[6], strs[7], strs[8]);
                levels.add(level);
            }

            Collections.sort(levels);

            String result = "public static String[] rank" + o + "t = {\"";
            for (int i = 0; i < levels.size(); i++) {
                if (!levels.get(i).metric.equals("0.0")) {
                    String temp = "";
                    temp += levels.get(i).typeOfIdentifier;
                    temp += "#";
                    temp += levels.get(i).relation;
                    if (levels.get(i).H.length() != 0) {
                        temp += "_";
                        temp += levels.get(i).H;
                    }
                    result += temp;
                    result += "\",\"";
                }
            }
            result = result.substring(0, result.length()-3);
            result += "\"};";
            System.out.println(result);

        }
    }

    @Override
    public int compareTo(Step3_PrintLevels o) {
        double a = Double.valueOf(this.metric);
        double b = Double.valueOf(o.metric);
        if (a < b) {
            return 1;
        } else if (a == b) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Step3_PrintLevels{" +
                "typeOfIdentifier='" + typeOfIdentifier + '\'' +
                ", relation='" + relation + '\'' +
                ", H='" + H + '\'' +
                ", metric='" + metric + '\'' +
                '}';
    }
}
