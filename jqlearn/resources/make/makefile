#--------------------------------------------------------------
#
# This is the makefile for the min-max function.
#
#--------------------------------------------------------------

tdir  = ../ttt
udir  = ../bputils
idir  = ../random
CFLAGS = -O3 --CLASSPATH=.:$(tdir):$(udir):$(idir)

all: qmain.exe

TOBJ = Board.o Teach.o QLearn.o QMinimax.o Player.o Human.o Minimax.o Train.o Random.o Game.o 
#UTIL = cputime.o RandomElement.o RandomSeedable.o Ranlux.o MersenneTwister.o
UTIL = cputime.o MersenneTwister.o
qmain.exe:  $(TOBJ) $(UTIL)
	gcj $(CFLAGS) --main=Teach -o qmain.exe $(TOBJ) $(UTIL) -lm 

clean: 
	rm -f qmain.exe *.o

Teach.o:  Teach.java
	gcj -c $(CFLAGS) Teach.java 

QLearn.o:  QLearn.java
	gcj -c $(CFLAGS) QLearn.java 

QMinimax.o:  QMinimax.java
	gcj -c $(CFLAGS) QMinimax.java 

Board.o:  $(tdir)/Board.java
	gcj -c $(CFLAGS) $(tdir)/Board.java 

Player.o:  $(tdir)/Player.java
	gcj -c $(CFLAGS) $(tdir)/Player.java 

Minimax.o:  $(tdir)/Minimax.java
	gcj -c $(CFLAGS) $(tdir)/Minimax.java 

Random.o:  $(tdir)/Random.java
	gcj -c $(CFLAGS) $(tdir)/Random.java 

Human.o:  $(tdir)/Human.java
	gcj -c $(CFLAGS) $(tdir)/Human.java 

Game.o:  $(tdir)/Game.java
	gcj -c $(CFLAGS) $(tdir)/Game.java 

Train.o:  $(tdir)/Train.java
	gcj -c $(CFLAGS) $(tdir)/Train.java 

bp.o:  $(bpdir)/bp.java
	gcj -c $(CFLAGS) $(bpdir)/bp.java 

myalloc.o:  $(bpdir)/myalloc.java
	gcj -c $(CFLAGS) $(bpdir)/myalloc.java 

cputime.o:  $(udir)/cputime.java
	gcj -c $(CFLAGS) $(udir)/cputime.java 

mutils.o:  $(udir)/mutils.java
	gcj -c $(CFLAGS) $(udir)/mutils.java 

RandomElement.o:  $(idir)/RandomElement.java
	gcj -c $(CFLAGS) $(idir)/RandomElement.java

RandomSeedable.o:  $(idir)/RandomSeedable.java
	gcj -c $(CFLAGS) $(idir)/RandomSeedable.java

Ranlux.o:  $(idir)/Ranlux.java
	gcj -c $(CFLAGS) $(idir)/Ranlux.java

MersenneTwister.o:  $(idir)/MersenneTwister.java
	gcj -c $(CFLAGS) $(idir)/MersenneTwister.java
