/*--------------------------------------------------------
*  Copyright Richard Abbuhl, 2002-2003
*
*  mtrain.c (train a neural network)
*  R. Abbuhl, 24 Jul 1991
*  %W% %G%
*
*   This program is used to train a neural network
*  to approximate a function.  It uses either the 
*  hyperbolic tangent activation function or the
*  sigmoid activation function for training.
*
*  Revision History:
*     12/01/02, R. Abbuhl   
*     Converted to Java.
*
*     07/01/93, R. Abbuhl   
*     Removed unncessary code.
*
--------------------------------------------------------*/

package com.jmentor.backprop;

import com.jmentor.backprop.networkrec;
import com.jmentor.backprop.bpnetwork;
import com.jmentor.backprop.cputime;

/*--------------------------------------------------------
*
* main - driver for the train program.
*
--------------------------------------------------------*/
public class mtrain {

   static int TEST_NETWORK = networkrec.FALSE;
   static int APPLY_NETWORK = networkrec.FALSE;
   static int VERBOSE = networkrec.FALSE;
   static int CONTAINS_DESIRED = networkrec.TRUE;
   static int XML_DESIRED = networkrec.TRUE;
   static String ntf_file;

   public static void main(String[] args)
   {
      networkrec net = new networkrec();
      bpnetwork bpnet = new bpnetwork();
      cputime cpu = new cputime();
      long [] elapsed_iters = new long[1];
      long start,end;
      double train_time;
      double [] rms_error = new double[1];
      int result = 0;
      int [] npatterns = { 0 };

      /* Set the netork record to its defaults */
      bpnet.Init_Network_Rec( net );

      /* Get the options and check the input arguments */
      if (!get_options( args, net )) {
         show_usage();
         System.exit(0);
      }

      /* Check if the pattern file is specified */
      if (net.pattern_file == null) {
         show_usage();
         System.exit(0);
      }

      /* Read the NTF file */
      if (XML_DESIRED > 0) {
         System.out.println("Opening XML NTF file = " + ntf_file );
         result = bpnet.Read_XML_NTF_File( net, ntf_file );
      } else {
         System.out.println("Opening NTF file = " + ntf_file );
         result = bpnet.Read_NTF_File( net, ntf_file );
      }
      if (result < 0) {
         System.out.println("\nError opening NTF file " + ntf_file );
         System.exit(0);
      }

      /* Allocate the network */
      result = bpnet.Allocate_Network( net );
      if (result < 0) {
         System.out.println("Error allocating network\n" );
         System.exit(0);
      }

      /* Scan the file */
      result = bpnet.Scan_Pattern_File( net, net.pattern_file,
                  CONTAINS_DESIRED, npatterns );
      net.num_patterns = npatterns[0];
      if (net.num_patterns <= 0) {
         System.out.println("Error reading pattern file " + net.pattern_file );
         System.exit(0);
      }

      /* Allocate the patterns */
      result = bpnet.Allocate_Patterns( net );
      if (result < 0) {
         System.out.println("Error allocating patterns\n");
         System.exit(0);
      }

      /* Read the patterns */
      System.out.println("Opening pattern file = " + net.pattern_file );
      System.out.println("Read " + net.num_patterns + " patterns\n");
      result = bpnet.Read_Pattern_File( net, net.pattern_file, CONTAINS_DESIRED );
      if (result < 0) {
         System.out.println("Error reading pattern file " + net.pattern_file );
         System.exit(0);
      }

      /* Initialize the random seed */
      result = bpnet.Random_Init( net );
      if (result < 0) {
         System.out.println("Error initializing the random number generator\n");
         System.exit(0);
      }

      if (net.RESTART_TRAINING > 0 || TEST_NETWORK > 0 || APPLY_NETWORK > 0) {
         /* Read the weights to restart training */
         System.out.println("Retrieving weights from " + net.WEIGHT_PATH );
         result = bpnet.RetrieveWeightsHeader( net, net.WEIGHT_PATH );
         if (result < 0) {
            System.out.println("Error reading weights header " + net.WEIGHT_PATH);
            System.exit(0);
         }
         result = bpnet.RetrieveWeights( net, net.WEIGHT_PATH );
         if (result < 0) {
            System.out.println("Error reading weights " + net.WEIGHT_PATH);
            System.exit(0);
         }
      }
      else {
         /* Initialize the weights and bias values */
         bpnet.Init_Weights( net );
      }

      /* Return the near heap size */
      //if (net.MEMTEST) {
      //   System.out.println("Memory available (near heap) %u\n",_memavl());
      //   System.exit(0);
      //}

      /* Display the backpropagation parameters */
      if (VERBOSE > 0) {
         bpnet.DisplayParams( net );
      }

      /* Test the network if necessary */
      if (TEST_NETWORK > 0) {
         System.out.println("Testing network...\n");
         bpnet.TestNetwork( net, rms_error );
         System.out.println("RMS Error = " + bpnetwork.decfmt.format(rms_error[0]) );
         System.exit( 0 );
      }

      /* Apply the network if desired */
      if (APPLY_NETWORK > 0) {
         System.out.println("Writing network output to mentor.log\n");
         result = bpnet.ApplyNetwork( net, "mentor.log" );
         if (result < 0) {
            System.out.println("Error applying network\n");
         }
         System.exit( 0 );
      }

      /* Display restart message */
      if (net.RESTART_TRAINING > 0) {
         System.out.println("\nRestarting training...\n");
      } else {
         System.out.println("\nStarting training...\n");
      }

      /* Display the timestamp */
      start = cpu.TimeStamp();

      /* Start the timer */
      cpu.BeginTimer();
      System.out.println("\n\n");

      /* Train the network */
      bpnet.TrainNetwork( net, elapsed_iters );

      /* Stop the timer and report the time used */
      train_time = cpu.EndTimer();
      cpu.TimerReport("\n\nFinished training", train_time);

      /* Display the ending timestamp and the elapsed time */
      end = cpu.TimeStamp();
      cpu.ElapsedTime(start,end);
      cpu.IterateTime(start,end,elapsed_iters[0]);

      /* Save the results of the network */
      if (net.SAVE_WEIGHTS > 0) {
         System.out.println("Saving weights to " + net.WEIGHT_PATH );
         bpnet.SaveWeights( net, net.WEIGHT_PATH );
      }
   }

   /*-----------------------------------------------------------
   *
   * show_usage - display how to call this program.
   *
   -------------------------------------------------------------*/
   static void show_usage()
   {
      System.out.println("JMENTOR V1.0.0, Copyright 2003 Richard Abbuhl.");
      System.out.println("usage:  jmentor [-srtavx123] -p <pattern file> <XML file>");
      System.out.println("   -s = start network training (default).");
      System.out.println("   -r = restart network training.");
      System.out.println("   -t = test network.");
      System.out.println("   -a = apply network.");
      System.out.println("   -v = verbose parameter display.");
      System.out.println("   -p = pattern file for training (required).");
      System.out.println("   -x = pattern file does not contain desired outputs.");
      //System.out.println("   -0 = display memory usage test.");
      System.out.println("   -1 = display parameters (during input).");
      System.out.println("   -2 = display patterns (during input).");
      System.out.println("   -3 = display network values (during training).");
   }

   /*-----------------------------------------------------------
   * 
   * get_options - get the command line options.
   *
   -------------------------------------------------------------*/
   static boolean get_options( String[] args, networkrec net )
   {
      /*
       * Parse command line arguments.
       */
      try {

        /* Stage 4: Process options. */
        for (int arg=0; arg < args.length; arg++) {

           //if (args[arg].equals("-0"))
           //   net.MEMTEST = networkrec.TRUE;

           if (args[arg].equals("-1"))
              net.DISPLAY_NTF_IO = networkrec.TRUE;

           else if (args[arg].equals("-2"))
              net.DISPLAY_PAT_IO = networkrec.TRUE;

           else if (args[arg].equals("-3"))
              net.DISPLAY_NET_IO = networkrec.TRUE;

           else if (args[arg].equals("-p")) {
              /* Set the pattern filename */
              net.pattern_file = args[++arg];
              net.use_pattern_file = networkrec.TRUE;

           } else if (args[arg].equals("-r"))
              net.RESTART_TRAINING = networkrec.TRUE;

           else if (args[arg].equals("-s"))
              net.RESTART_TRAINING = networkrec.FALSE;

           else if (args[arg].equals("-t"))
              TEST_NETWORK = networkrec.TRUE;

           else if (args[arg].equals("-a"))
              APPLY_NETWORK = networkrec.TRUE;

           else if (args[arg].equals("-v"))
              VERBOSE = networkrec.TRUE;

           else if (args[arg].equals("-x"))
              CONTAINS_DESIRED = networkrec.FALSE;

           // else if (args[arg].equals("-X"))
           // XML_DESIRED = networkrec.TRUE;

           else
              ntf_file = args[arg];
         }

         if (ntf_file == null) return( false );

      } catch (Exception e) {
         return( false );
      }

      /* No error occurred */
      return( true );
   }

}
