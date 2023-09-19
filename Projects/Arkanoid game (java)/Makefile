JAVAC = javac
JAVACFLAGS = -sourcepath src -d bin -cp .:biuoop-1.4.jar

SRCDIR = src
BINDIR = bin

SOURCES = $(wildcard $(SRCDIR)/*.java)
CLASSES = $(patsubst $(SRCDIR)/%.java,$(BINDIR)/%.class,$(SOURCES))

MAIN_CLASS = AssGame

all: $(CLASSES)
	java -cp .:$(BINDIR):biuoop-1.4.jar $(MAIN_CLASS)

$(BINDIR)/%.class: $(SRCDIR)/%.java
	$(JAVAC) $(JAVACFLAGS) $<

clean:
	rm -rf $(BINDIR)/*.class

