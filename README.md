# Terribly Tiny Tales
Project of company TTT
Developed in:
Project is Developed in android with programming language java 
Technologies used in android:
AsynTask Class:
For extracting the texts from the given link https://terriblytinytales.com/test.txt
Input Stream and Buffer Reader to read the file .
Progress Dialog Box to load until the file is taken and store into  Textview.
Textview is splited with the regex "Space".
Two methods are used  to get all the words and count the number of there frequency with higher precedence.
Tabular layout is made to show the words with higher precedence .
It is made with fill parent fuctionality to occupy according to inputs given by users.
All the designs are done in xml file of this project.
Dependencies

implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    implementation 'androidx.cardview:cardview:1.0.0'

    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'




