ParseCode: parse identifiers and their semantically related identifiers

SemanticExpand:
|---src/main/Step1_AddAbbrAndH.java: parse abbreviations and search for all the potential expansions
|---src/main/Step2_ComputePriority.java: calculate priorities
|---src/main/Step3_PrintLevels.java: print priorities and put them into src/util/GlobelVariable.java
|---src/main/Step4_Expan.java: expand abbreviations according to the above priorities