#--------------------------------------------------------------
#
# This is the makefile for the min-max function.
#
#--------------------------------------------------------------

srcdir=src/com/jmentor/jbackprop
nandir=modules/jnanoxml/src/com/jmentor/jnanoxml
# ldir = /usr/local/jdev9i/lib
# --CLASSPATH=/usr/local/jdev9i/jdev/lib/jdev-rt.jar:/usr/local/jdev9i/lib/xmlparserv2.jar:/usr/local/jdev9i/lib/xmlcomp.jar 
# ldir = /home/rabbuhl/mentor/jbackprop
# CFLAGS = --CLASSPATH=lib/ant.jar:lib/collections.jar:lib/crimson.jar:lib/jaxp.jar:lib/jdom.jar:lib/xalan.jar:lib/xerces.jar
CFLAGS = -O3 --CLASSPATH=.

#TOBJ = mtrain.o bpnetwork.o cputime.o networkrec.o network.o gnufix.o XMLElement.o XMLParseException.o
TOBJ = mtrain.o bpnetwork.o cputime.o networkrec.o network.o XMLElement.o XMLParseException.o
jbackprop.exe:  $(TOBJ)
	gcj $(CFLAGS) --main=mtrain -o jbackprop.exe $(TOBJ) -lm

mtrain.o:  $(srcdir)/mtrain.java
	gcj -c $(CFLAGS) $(srcdir)/mtrain.java

bpnetwork.o:  $(srcdir)/bpnetwork.java
	gcj -c $(CFLAGS) $(srcdir)/bpnetwork.java

cputime.o:  $(srcdir)/cputime.java
	gcj -c $(CFLAGS) $(srcdir)/cputime.java

networkrec.o:  $(srcdir)/networkrec.java
	gcj -c $(CFLAGS) $(srcdir)/networkrec.java

network.o:  $(srcdir)/network.java
	gcj -c $(CFLAGS) $(srcdir)/network.java

gnufix.o:  gnufix.java
	gcj -c $(CFLAGS) gnufix.java 

XMLElement.o:  $(nandir)/XMLElement.java
	gcj -c $(CFLAGS) $(nandir)/XMLElement.java

XMLParseException.o:  $(nandir)/XMLParseException.java
	gcj -c $(CFLAGS) $(nandir)/XMLParseException.java

clean:
	rm -f jbackprop.exe *.o
