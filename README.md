DecisionTree
============

DecisionTree function, machine learning project for AI. Final assignment.

Stole Node class from:
http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html

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

	2. Run "make".

	3. Run the executable on the training data:
		> ProgWriter.class restaurant2_train.csv

	4. Run "make output". This will run the OutputTree executable on the test data.

	5. Run the executable on the test data
		> OutputTree.class restaurant2_test.csv

	   This will output the classifiers.

*************************************************************************

--- Program Descripton ---

	This program is the implementation of the Decision Tree Learning algorithm from Ch. 18 of Russell and Norvig's AI textbook. We first generate a tree of Nodes that we can traverse to find what our algorithm thinks is the best classifier for some input. The algorithm will be fed training data (specifically, restaurant.csv) to help build the tree. The dlt() function grows and branches the tree based on which attribute it believes will give it the best informatin gain (or rather, the best decrease in entropy, or uncertainty) based on the entropy functions in Ch. 18. Then, we write code to a file "OutputTree.java", which essentially becomes an if-elseif flowchart-like program that takes in a csv file of inputs and outputs the classifier for each input. 
	
Files:

Makefile
	- make 		# for compiling
	- make output	# for executing the game
	- clean		# for removing the generated .class files

Board.java
	- This implements the Board structure and all of the game functions.
	Most importantly it has BESTMOVE, which implements the Minimax and
	alpha-beta pruning algorithms

Pair.java
	- This implements the Pair class which is only used for returning
	the evaluation (or "score") of a Board and the Board itself in 
	BESTMOVE.

TimerBoard.java
	- this implements timer functionality for ensuring that the program
	does not take more than one minute to come to a decision during
	BESTMOVE. 
