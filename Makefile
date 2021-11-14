JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Producer.java \
	Buffer.java \
	Consumer.java \
	Coordinator.java

default: classes

all: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
