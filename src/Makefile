JFLAGS = -g
JC = javac
EXE = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java
	$(EXE) $ main

# This uses the line continuation character (\) for readability
# You can list these all on a single line, separated by a space instead.
# If your version of make can't handle the leading tabs on each
# line, just remove them (these are also just added for readability).
CLASSES = main.java Task1.java Task2.java Task3A.java Task3B.java Task4.java Task5.java Task1.java
		
		

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
run:
	java main