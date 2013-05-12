main:
	javac  ProgWriter.java Attribute.java DecisionTree.java Node.java
	java ProgWriter restaurant2_test.csv

classify:
	javac OutputTree.java Attribute.java DecisionTree.java Node.java
	java OutputTree restaurant2_train.csv

clean:
	rm -f *.class OutputTree.java