In this part, you are required to use GP to classify the Wisconsin data set instances into two classes. This data set was obtained from the UCI machine learning repository (http://mlearn.ics.uci.edu/MLRepository.html).

## Problem Description
This data set consists of 699 instances of breast cancer diagnosis data, obtained from the University of Wisconsin Hospitals, Madison from Dr. William H.Wolberg. The data instances are defined by 9 numerically
valued attributes (excluding the sample id number) and result in either a classification of benign or malignant. This dataset contains 458 \benign" instances (65.5%) and 241 \malignant" instances (34.5%). The file breast-cancer-wisconsin.data is the data set consisting of these 699 instances.

All of the 9 attributes are integer valued between 1 and 10 and are listed as follows: Clump Thickness (CT), Uniformity of Cell Size (USz), Uniformity of Cell Shape (UShp), Marginal Adhesion (MA), Single
Epithelial Cell Size (SESz), Bare Nuclei (BN), Bland Chromatin (BC), Normal Nucleoli (NN), Mitoses (M). Sixteen of the instances also contain \missing attributes" (denoted by \?"). For these attribute values,
one suggestion is to replace these question marks (\?") with -1 for classification using GP. All the 699 instances including the 16 instances with missing attributes should be used in the experiments. The file
breast-cancer-wisconsin.names gives detailed description of the data.

## Requirements
Your job is to use any of the genetic programming packages with necessary changes of the terminal set,
function set, fitness function, parameters and termination criteria to solve the simple task described above.

##Prerequisite
- Make sure /ecj folder and ec.params, koza.params, training.txt, test.txt, simple.params, tutorial4.params and ecj.jar files are in the same directory.

Running steps:
1. Open Terminal console
2. Go to the project /part3 directory
3. Run command:
```
java -jar ecj.jar -classpath "." ec.Evolve -file tutorial4.params -p stat.file=out.stat
```
4. Check out.stat for output details in the same folder
