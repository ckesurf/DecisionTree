DecisionTree
============

DecisionTree function, machine learning project for AI. Final assignment.


Chris Erlendson
cke2106
11 May 2012
Project 4: Decision Tree Learning
COMS W4701 * Artificial Intelligence

*********************************

--- Development Information ---

-Programming Language: Java 
-Language Version: 1.6
-Development Environment: Eclipse

*************************************************************************

--- Instructions to compile  ---

	1. Unzip all files from cke2106_Project4_Java.zip.

	2. Run "make". This will compile ProgWriter and run the executable 
	on the training data and generate the Decision Learning Tree. It also
	writes the OutputTree.java program, the program that results from 
	generating the tree.  

	3. Run "make classify". This will run the OutputTree executable on the 
	test data. This will output the classifiers.
	
	4. ???
	
	5. Profit!

*************************************************************************

--- Program Description ---

	This program is the implementation of the Decision Tree Learning algorithm from 
	Ch. 18 of Russell and Norvig's AI textbook. We first generate a tree of Nodes 
	that we can traverse to find what our algorithm thinks is the best classifier 
	for some input. The algorithm will be fed training data (specifically, 
	restaurant.csv) to help build the tree. The dlt() function grows and branches 
	the tree based on which attribute it believes will give it the best information
	gain (or rather, the best decrease in entropy, or uncertainty) based on the 
	entropy functions in Ch. 18. Then, we write code to a file "OutputTree.java", 
	which essentially becomes an if-elseif flowchart-like program that takes in a 
	csv file of inputs and outputs the classifier for each input. 
	
Files:

Makefile
	- make 			# for compiling and writing the output program, OutputTree.java
	- make classify	# for classifying the re
	- clean			# for removing the generated .class files and the
					# generated output Java program

 Attribute.java DecisionTree.java Node.java

ProgWriter.java
	- First main Java program, writes the appropriate code to OutputTree.java,
	which should then be compiled and executed next. 
	
DecisionTree.java
	- Contains algorithm for generating a Decision Learning Tree, must be
	given a collection of input data vectors, (the training set).
	
Node.java
	- Node wrapper for Attribute, for traversing and generating trees.

Attribute.java
	- Attribute that either represents an attribute (column from a data vector)
	or a classifier, in some cases. 
	
OutputTree.java
	- Created after running ProgWriter ("make" from my Makefile). Basically
	a flowchart of ifs and else ifs for classifying new data. 