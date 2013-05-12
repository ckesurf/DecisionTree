main:
	javac  ProgWriter.java Attribute.java DecisionTree.java Node.java
	java ProgWriter restaurant2_train.csv

classify:
	javac OutputTree.java Attribute.java DecisionTree.java Node.java
	java OutputTree restaurant2_test.csv

clean:
	rm -f *.class OutputTree.java