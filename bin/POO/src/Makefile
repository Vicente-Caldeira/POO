# Makefile

# Specify the Java compiler
JAVAC = javac

# Specify the Java compiler flags
JFLAGS = -g

# Specify the JAR command
JAR = jar

# Specify the JAR file name
JARFILE = project.jar

# Specify the list of Java source files
SOURCES = Interface.java

# Specify the list of class files to compile
CLASSES = $(SOURCES:.java=.class)

# Default target
all: $(CLASSES)

# Compile Java classes
%.class: %.java
	$(JAVAC) $(JFLAGS) $<

# Create JAR file
jar: $(CLASSES)
	$(JAR) cvfm $(JARFILE) manifest.mf $(CLASSES)

# Clean target to remove compiled class files and JAR file
clean:
	rm -f $(CLASSES) $(JARFILE)
