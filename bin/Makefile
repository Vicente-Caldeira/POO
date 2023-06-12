# Specify the Java compiler
JAVAC = javac

# Specify the Java compiler flags
JFLAGS = -g

# Specify the JAR command
JAR = jar

# Specify the JAR file name
JARFILE = project.jar

# Specify the source and target directories
SRCDIR = src
BINDIR = ../bin

# Specify the list of Java source files
SOURCES := $(wildcard $(SRCDIR)/*.java)

# Specify the list of class files to compile
CLASSES := $(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

# Default target
all: $(BINDIR) $(CLASSES)

# Create the target directory
$(BINDIR):
	mkdir -p $(BINDIR)

# Compile Java classes
$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) $(JFLAGS) -d $(BINDIR) $<

# Create JAR file
jar: $(BINDIR) $(CLASSES)
	$(JAR) cvfm $(JARFILE) manifest.mf -C $(BINDIR) .

# Clean target to remove compiled class files and JAR file
clean:
	rm -rf $(BINDIR) $(JARFILE)
