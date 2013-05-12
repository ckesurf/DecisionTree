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

3. Run "make output". This will run the OutputTree executable on the test data.

*************************************************************************

--- Program Descripton ---

	This program is the implementation of the Decision Tree Learning algorithm from Ch. 18 of Russell and Norvig's AI textbook. 
	
Files:

Makefile
	- make 		# for compiling
	- make play	# for executing the game
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
