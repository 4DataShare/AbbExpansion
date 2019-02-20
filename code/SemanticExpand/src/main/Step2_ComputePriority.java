package main;

import util.*;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;

// based on trainSelfExpansionFile
public class Step2_ComputePriority {
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

    public static ArrayList<String[]> lines = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        generateTrainData();
        for (int o = 1; o <= 9; o++) {
            long begintime =System.currentTimeMillis();
            GlobleVariable.trainProjectName = "train" + o;
            GlobleVariable.trainSelfExpansionFile = "data/" + GlobleVariable.trainProjectName + "_selfExpansion.csv";
            GlobleVariable.priorityFile = "data/" + GlobleVariable.trainProjectName + "_priority.csv";
            System.setOut(new PrintStream(new File(GlobleVariable.priorityFile)));
            ArrayList<String> file = Util.readFile(GlobleVariable.trainSelfExpansionFile);

            for (int i = 1; i < file.size(); i++) {
                String[] strs = file.get(i).split(",", -1);
                lines.add(strs);
            }

            System.out.println("typeOfIdentifier,relation,H,#Type,#TypeRelation,#TypeRelationH,#rightExpan,precision,metric");
            String[] abbrTypeName = {"ClassName", "MethodName",
                    "FieldName", "ParameterName", "VariableName"};
            String[] relationName = {"subclass","subsubclass","parents","ancestor","methods","fields","comment","type","enclosingClass","assignment","methodInvocated","parameterArgument","parameter","enclosingMethod", "argument", "LinsenAbbr", "ComputerAbbr"};
            String[] heuName = {"H1", "H2", "H3"};
            int[] relationIndex = {subclassIndex,subsubclassIndex,parentsIndex,ancestorIndex,methodsIndex,fieldsIndex,commentIndex,typeIndex,enclosingClassIndex,assignmentIndex,methodInvocatedIndex,parameterArgumentIndex,parameterIndex,enclosingMethodIndex, argumentIndex};
            for (int i = 0; i < abbrTypeName.length; i++) {
                for (int j = 0; j < relationIndex.length; j++) {
                    for (int k = 0; k < heuName.length; k++) {
                        System.out.print(abbrTypeName[i] + "," + relationName[j] + "," + heuName[k] + ",");
                        handleTypeRelationH(abbrTypeName[i], relationIndex[j], heuName[k]);
                    }

                }
                // linsen abbr
                System.out.print(abbrTypeName[i] + "," + relationName[relationIndex.length] + ",,");
                handleAbbr(abbrTypeName[i], "Linsen");

                // computer abbr
                System.out.print(abbrTypeName[i] + "," + relationName[relationIndex.length+1] + ",,");
                handleAbbr(abbrTypeName[i], "Computer");
            }
            long endtime =System.currentTimeMillis();

            long costTime = (endtime - begintime);
            System.err.println(costTime);
        }
    }


    public static void generateTrainData() throws Exception {
        System.setOut(new PrintStream(new File("data/train" + 0 + "_selfExpansion.csv")));
        ArrayList<String> temp = Util.readFile("data/data" + 1 + "_selfExpansion.csv");
        System.out.println(temp.get(0));

        for (int i = 1; i <= 9; i++) {
            ArrayList<String> file = Util.readFile("data/data" + i + "_selfExpansion.csv");
            for (int k = 1; k < file.size(); k++) {
                System.out.println(file.get(k));
            }
        }
    }
    private static void handleAbbr(String typeOfIdentifier, String type) {
        int typeNum = 0;
        int typeRelationNum = 0;
        int rightNum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i);
            if (line[typeOfIdentifierIndex].equalsIgnoreCase(typeOfIdentifier)) {
                typeNum++;
                String abbr = line[abbrIndex];
                String expansion = line[expansionIndex];
                if (type.equals("Linsen")) {
                    if (Dic.abbrDicHashMap.containsKey(abbr.toLowerCase())) {
                        typeRelationNum++;
                        String dicExpansion = Dic.abbrDicHashMap.get(abbr.toLowerCase());
                        if (dicExpansion.equalsIgnoreCase(expansion)) {
                            rightNum++;
                        }
                    }
                } else if (type.equals("Computer")) {
                    if (Dic.computerAbbrDicHashMap.containsKey(abbr.toLowerCase())) {
                        typeRelationNum++;
                        String dicExpansion = Dic.computerAbbrDicHashMap.get(abbr.toLowerCase());
                        if (Util.equalComputerExpansion(expansion, dicExpansion)) {
                            rightNum++;
                        }
                    }
                }
            }
        }

        double p = 1.0*rightNum/typeRelationNum;
        double m = p * Util.sigmoid(rightNum);
        System.out.print(typeNum + ",");
        System.out.print(typeRelationNum + ",");
        System.out.print(",");
        System.out.print(rightNum + ",");
        if (Double.isNaN(p)) {
            System.out.print("0,");
        } else {
            System.out.print(p + ",");
        }
        if (Double.isNaN(m)) {
            System.out.println("0");
        } else {
            System.out.println(m);
        }
    }

    private static void handleTypeRelationH(String typeOfIdentifier, int relationIndex, String H) {
        int typeNum = 0;
        int typeRelationNum = 0;
        int typeRelationHNum = 0;
        int rightNum = 0;

        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i);
            if (line[typeOfIdentifierIndex].equals(typeOfIdentifier)) {
                typeNum++;
                if (line[relationIndex].length() > 0 ) {
                    typeRelationNum++;
                } else {
                    continue;
                }

                switch (H) {
                    case "H1":
                        if (line[relationIndex+1].length() > 0) {
                            typeRelationHNum++;
                        }
                        if (Heu.H1EqualOf(line[expansionIndex], line[relationIndex+1])) {
                            rightNum++;
                        }
                        break;
                    case "H2":
                        if (line[relationIndex+2].length() > 0) {
                            typeRelationHNum++;
                        }
                        if (Heu.H2H3EqualOf(line[expansionIndex], line[relationIndex+2])) {
                            rightNum++;
                        }
                        break;
                    case "H3":
                        if (line[relationIndex+3].length() > 0) {
                            typeRelationHNum++;
                        }
                        if (Heu.H2H3EqualOf(line[expansionIndex], line[relationIndex+3])) {
                            rightNum++;
                        }
                        break;
                }
            }
        }
        double p = 1.0*rightNum/typeRelationHNum;
        double m = p * Util.sigmoid(rightNum);
        System.out.print(typeNum + ",");
        System.out.print(typeRelationNum + ",");
        System.out.print(typeRelationHNum + ",");
        System.out.print(rightNum + ",");
        if (Double.isNaN(p)) {
            System.out.print("0,");
        } else {
            System.out.print(p + ",");
        }
        if (Double.isNaN(m)) {
            System.out.println("0");
        } else {
            System.out.println(m);
        }
    }
}
