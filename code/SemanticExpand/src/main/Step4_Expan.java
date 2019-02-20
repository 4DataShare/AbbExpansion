package main;

import util.Dic;
import util.GlobleVariable;
import util.Heu;
import util.Util;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// based on trainSampleFromAddAbbrAndHResultFile
public class Step4_Expan {
    public static int idIndex = 0;
    public static int filesIndex = 1;
    public static int abbrIndex = 2;
    public static int nameIndex = 3;
    public static int typeOfIdentifierIndex = 4;
    public static int subclassIndex = 5;
    public static int subsubclassIndex = 9;
    public static int parentsIndex = 13;
    public static int ancestorIndex = 17;
    public static int methodsIndex = 21;
    public static int fieldsIndex = 25;
    public static int commentIndex = 29;
    public static int typeIndex = 33;
    public static int enclosingClassIndex = 37;
    public static int assignmentIndex = 41;
    public static int methodInvocatedIndex = 45;
    public static int parameterArgumentIndex = 49;
    public static int parameterIndex = 53;
    public static int enclosingMethodIndex = 57;
    public static int argumentIndex = 61;
    public static int expansionIndex = 65;

    public static void main(String[] args) throws Exception {
        for (int o = 1; o <= 9; o++) {
            GlobleVariable.rank = GlobleVariable.rantk[o-1];
            GlobleVariable.testProjectName = "data" + o;
            GlobleVariable.testSampleFromAddAbbrAndHResultFile = "data/" + GlobleVariable.testProjectName + "_sampleAbbrH.csv";
            GlobleVariable.testMyExpansionFile = "data/" + GlobleVariable.testProjectName + "_myExpansion.csv";
            GlobleVariable.testSampleFromAddAbbrAndHResultFile = "data/" + GlobleVariable.testProjectName + "_abbrAndHResult.csv";
            GlobleVariable.testMyExpansionFile = "data/" + GlobleVariable.testProjectName + "_myExpansionAll.csv";

            System.setOut(new PrintStream(new File(GlobleVariable.testMyExpansionFile)));
            ArrayList<String> file = Util.readFile(GlobleVariable.testSampleFromAddAbbrAndHResultFile);
            System.out.println(file.get(0) + ",expansion");
            for (int i = 1; i < file.size(); i++) {
                String[] line = file.get(i).split(",", -1);
                String expansion = expanOne(line);

                System.out.println(file.get(i) + "," + expansion);
            }
        }
    }

    public static String expanOne(String[] line) {
        for (int i = 0; i < GlobleVariable.rank.length; i++) {
            String[] temp = GlobleVariable.rank[i].split("#");
            String expansion = "";
            if (temp[0].equals(line[typeOfIdentifierIndex])) {
                switch (temp[1]) {
                    case "subclass_H1":
                    expansion = Heu.handleExpansionForH(line[abbrIndex], line[subclassIndex], "H1");
                    break;
                    case "subsubclass_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[subsubclassIndex], "H1");
                        break;
                    case "parents_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parentsIndex], "H1");
                        break;
                    case "ancestor_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[ancestorIndex], "H1");
                        break;
                    case "methods_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[methodsIndex], "H1");
                        break;
                    case "fields_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[fieldsIndex], "H1");
                        break;
                    case "comment_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[commentIndex], "H1");
                        break;
                    case "type_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[typeIndex], "H1");
                        break;
                    case "enclosingClass_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[enclosingClassIndex], "H1");
                        break;
                    case "assignment_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[assignmentIndex], "H1");
                        break;
                    case "methodInvocated_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[methodInvocatedIndex], "H1");
                        break;
                    case "parameterArgument_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parameterArgumentIndex], "H1");
                        break;
                    case "parameter_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parameterIndex], "H1");
                        break;
                    case "enclosingMethod_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[enclosingMethodIndex], "H1");
                        break;
                    case "argument_H1":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[argumentIndex], "H1");
                        break;
                    case "subclass_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[subclassIndex], "H2");
                        break;
                    case "subsubclass_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[subsubclassIndex], "H2");
                        break;
                    case "parents_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parentsIndex], "H2");
                        break;
                    case "ancestor_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[ancestorIndex], "H2");
                        break;
                    case "methods_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[methodsIndex], "H2");
                        break;
                    case "fields_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[fieldsIndex], "H2");
                        break;
                    case "comment_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[commentIndex], "H2");
                        break;
                    case "type_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[typeIndex], "H2");
                        break;
                    case "enclosingClass_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[enclosingClassIndex], "H2");
                        break;
                    case "assignment_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[assignmentIndex], "H2");
                        break;
                    case "methodInvocated_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[methodInvocatedIndex], "H2");
                        break;
                    case "parameterArgument_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parameterArgumentIndex], "H2");
                        break;
                    case "parameter_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parameterIndex], "H2");
                        break;
                    case "enclosingMethod_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[enclosingMethodIndex], "H2");
                        break;
                    case "argument_H2":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[argumentIndex], "H2");
                        break;
                    case "subclass_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[subclassIndex], "H3");
                        break;
                    case "subsubclass_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[subsubclassIndex], "H3");
                        break;
                    case "parents_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parentsIndex], "H3");
                        break;
                    case "ancestor_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[ancestorIndex], "H3");
                        break;
                    case "methods_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[methodsIndex], "H3");
                        break;
                    case "fields_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[fieldsIndex], "H3");
                        break;
                    case "comment_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[commentIndex], "H3");
                        break;
                    case "type_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[typeIndex], "H3");
                        break;
                    case "enclosingClass_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[enclosingClassIndex], "H3");
                        break;
                    case "assignment_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[assignmentIndex], "H3");
                        break;
                    case "methodInvocated_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[methodInvocatedIndex], "H3");
                        break;
                    case "parameterArgument_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parameterArgumentIndex], "H3");
                        break;
                    case "parameter_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[parameterIndex], "H3");
                        break;
                    case "enclosingMethod_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[enclosingMethodIndex], "H3");
                        break;
                    case "argument_H3":
                        expansion = Heu.handleExpansionForH(line[abbrIndex], line[argumentIndex], "H3");
                        break;
                    case "LinsenAbbr":
                        expansion = LinsenAbbrDic(line[abbrIndex]);
                        break;
                    case "ComputerAbbr":
                        expansion = ComputerAbbrDic(line[abbrIndex]);
                        break;
                }
            }

            if (expansion.length() > 0) {
                HashMap<String, Integer> hashMap = new HashMap<>();
                String[] strs = expansion.split(";");
                for (int j = 0; j < strs.length; j++) {
                    String[] strss = strs[j].split("#");
                    for (int k = 0; k < strss.length; k++) {
                        if (strss[k].length() > 0) {
                            if (hashMap.containsKey(strss[k])) {
                                int curr = hashMap.get(strss[k]);
                                hashMap.put(strss[k], curr + 1);
                            } else {
                                hashMap.put(strss[k], 1);
                            }
                        }
                    }
                }
                int max = 0;
                ArrayList<String> arrayList = new ArrayList<>();
                for (String key :
                        hashMap.keySet()) {
                    if (hashMap.get(key) > max) {
                        max = hashMap.get(key);
                        arrayList = new ArrayList<>();
                        arrayList.add(key);
                    } else if (hashMap.get(key) == max) {
                        arrayList.add(key);
                    }
                }
                String result = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                for (int j = 0; j < arrayList.size(); j++) {
                    if (arrayList.get(j).length() < result.length()) {
                        result = arrayList.get(j);
                    }
                }
                return result;
            }
        }
        return "";
    }

    private static String LinsenAbbrDic(String part) {
        if (Dic.abbrDicHashMap.containsKey(part)) {
            return Dic.abbrDicHashMap.get(part);
        }
        return "";
    }
    private static String ComputerAbbrDic(String part) {
        if (Dic.computerAbbrDicHashMap.containsKey(part)) {
            return Dic.computerAbbrDicHashMap.get(part);
        }
        return "";
    }
}
