This document describes how to get you up and
running using jbackprop.

Send comments to rabbuhl@hotmail.com.

---------------------------------------------
- Overview
---------------------------------------------

What is JBackprop?

JBackprop supports multi-layer perceptron neural networks
which are trained using the back-propagation learning 
algorithm.  

It contains two executables:

  1. jbackprop.jar:  can be run using JDK 1.3 or greater,
  2. jbackprop.exe:  windows executable built using the gcj
     compiler for java. 

What can you do with JBackprop?

JBackprop is a tool for solving problems.  Using JBackprop,
you can create, test, train, and apply neural networks 
to solve any of these types of problems:

  1. Recognizing patterns and trends in numeric data, 
  2. Recognizing the difference between several types of images 
     or patterns, 
  3. Predicting the outcome of random events such as horse races, 
     soccer game scores, or the stock market, 
  4. Creating approximation fuctions. 

How do I install JBackprop?

Unzip the release into a directory.  

---------------------------------------------
- Running
---------------------------------------------

Run jbackprop using the command jbackprop.exe for the executable
or for the jar file use the batch file jbackprop.bat.  This will
display the help screen.

The normal commands for running jbackprop to solve
the xor problem are

   jbackprop.exe -p xor.trn xor.xml
or
   jbackprop.bat

---------------------------------------------
- XML File
---------------------------------------------

<?xml version="1.0"?>
<bpnetwork
  version="0"                Not required (historical)

  activ_func="2"             1=Sigmoid, 2=Hyperbolic

  max_iterations="900000"    Stop training when iterations is
                             less than max_iterations

  max_total_error="0.001000" Stop training when error is less
                             than max_total_error 

  output_type="1"            When applying the network indicates
                             whether to output the network value
                             or the index of the highest value.
                                NETWORK_OUTPUT   = 1;
                                MAX_OUTPUT_IDX   = 2;

  iteration_print="1"        Training information is output using
                             this interval.

  save_weights="1"           Defines whether to save the weights
                             when training is completed.

  weight_path="weights"      Name of the weights file to be saved.

  momentum="0.500000"        Network momentum learning rate.

  bias="0.100000"            Network bias learning rate.

  a1="0.050000"              Weight learning rate = a1 + (a2 * 
                             power(a3, iteration));

  a2="0.450000"              Weight learning rate = a1 + (a2 * 
                             power(a3, iteration));

  a3="0.980000"              Weight learning rate = a1 + (a2 * 
                             power(a3, iteration));

  random_init_val="13"       Value used to initialize the random
                             number generator.

  num_layers="3"             Defines the number of layers in the network.

  layer_size0="2"            Defines the size of the input layer.

  layer_size1="8"            Defines the size of the hidden layer.
 
  layer_size2="1">           Defines the size of the output layer.
</bpnetwork> 

---------------------------------------------
- Building (source available upon request)
---------------------------------------------

There are two options for building jbackprop.

OPTION - 1, GCJ Compiler

   Steps:
   1. Type make to build jbackprop.

OPTION - 2, Build jbackprop.jar

   Steps:
   1. Make sure you have ant installed.
   2. Type the command, ant make jar.

---------------------------------------------
- Version history
---------------------------------------------

+ Version 1.0.1:  February 9, 2005
Updated release.  Renamed release to JBackprop.

+ Version 1.0.0:  December 9, 2002
First public Java version.