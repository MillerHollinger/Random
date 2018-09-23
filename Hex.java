import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.awt.MouseInfo;
import java.io.*;
import java.util.*;
import java.net.*;

import java.math.*;
import java.rmi.*;
import java.rmi.server.*;

public class Hex
{
   public static void main(String[] args) throws InterruptedException, AWTException, FileNotFoundException, IOException
   {
      AppendFile("library","");
      printIntro();
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->MENU<-----+");
         charPrint("atc > Autoclicker");
         charPrint("pxm > PixelMeasure");
         charPrint("npd > Notepad");
         charPrint("sci > Sci Notation Mult");
         charPrint("ips > IP System");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("atc"))
         {
            Autoclicker();
         }
         else if (input.equalsIgnoreCase("pxm"))
         {
            PixelMeasure();
         }
         else if (input.equalsIgnoreCase("npd"))
         {
            Notepad();
         }
         else if (input.equalsIgnoreCase("sci"))
         {
            SciCalc();
         }
         else if (input.equalsIgnoreCase("ips"))
         {
            IPSys();
         }
         else if (input.equalsIgnoreCase("ext"))
         {
         }
         else
         {
            charPrint("ERROR: INPUT_NOT_RECOGNIZED :: Pick Applicable Option");
         }
      }
   }
   
   
   //Template program.
   /*public static void Notepad() throws InterruptedException, AWTException
   {
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->[NAME]<-----+");
         charPrint(">[OPTION]");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("")) [OPTION NAME 1]
         {
            [OPTION CODE]
         }
         else if (input.equalsIgnoreCase("")) [OPTION NAME 2] (Copy for more options)
         {
            [OPTION CODE]
         }
         else if (input.equalsIgnoreCase("ext")){}
         else
         {
         charPrint("ERROR: INPUT_NOT_RECOGNIZED :: Pick Applicable Option");
         }
      }
   }*/
   
   //IP System program.
   public static void IPSys() throws InterruptedException, AWTException, SocketException, IOException
   {
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->IP System<-----+");
         charPrint("lcs > Local Systems");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("lcs"))
         {
            charPrint("Printing the local system."); 
            Hex temp = new Hex();
            temp.printData(true);
         }
      }
   }
  
   public void printData(boolean showData) throws SocketException
   {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      Enumeration<InetAddress> addresses;
      InetAddress current = null;
      while (interfaces.hasMoreElements())
      {
         NetworkInterface net = interfaces.nextElement();
         addresses = net.getInetAddresses();
         if ((!showData && addresses.hasMoreElements()) || showData)
            System.out.println("---"+net.getName()+"---------#"+net.getIndex());
         if (showData)
         {
            System.out.println(">Display Name");
            System.out.println(net.getDisplayName());
            System.out.println(">Addresses");
            if (addresses.hasMoreElements())
            {
               while (addresses.hasMoreElements())
               {
                  System.out.println(addresses.nextElement().getHostAddress());
               }
            }
            else
               System.out.println("ERROR: ADDRESS_HIDDEN :: Reduce Security");
            System.out.println(">MTU");
            System.out.println(net.getMTU());
            System.out.println(">Hashcode");
            System.out.println(net.hashCode());
            System.out.println(">Loopback");
            System.out.println(net.isLoopback());
            System.out.println(">Point to Point");
            System.out.println(net.isPointToPoint());
            System.out.println(">Active");
            System.out.println(net.isUp());
            System.out.println(">Virtual");
            System.out.println(net.isVirtual());
            System.out.println(">Support Multicast");
            System.out.println(net.supportsMulticast());
            System.out.println("----------------------");
         }
      }
      
   }
   
   
   //Template program.
   public static void SciCalc() throws InterruptedException, AWTException, FileNotFoundException, IOException
   {
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->Sci Notation Mult<-----+");
         charPrint("nml > Normal (2 nums)");
         charPrint("pwr > Power (1 num^x)");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("nml"))
         {
            charPrint("First decimal number.");
            double first = DoubleIn();
            charPrint("First power of 10.");
            int pow1 = IntIn();
            charPrint("Second decimal number.");
            double second = DoubleIn();
            charPrint("Second power of 10.");
            int pow2 = IntIn();
            double mult = first*second;
            while (mult > 10)
            {
               mult /= 10;
               pow1++;
            }
            while (mult < -10)
            {
               mult *= 10;
               pow1--;
            }
            int power = pow1+pow2;
            charPrint("Result:");
            charPrint(""+mult+" x 10^"+power);
            charPrint("Record? Y/N");
            if (StringIn().equalsIgnoreCase("Y"))
            {
               charPrint("What file to record to?");
               AppendFile(StringIn(), "Sci Notation Mult : Normal :: "+mult+" x 10^"+power);
               charPrint("Success.");
            }
         }
         else if (input.equalsIgnoreCase("pwr"))
         {
            charPrint("Decimal number.");
            double firstp = DoubleIn();
            charPrint("Power of 10.");
            int pow1p = IntIn();
            charPrint("Power.");
            int power = IntIn();
            double multp = Math.pow(pow1p, power);
            int powerp = pow1p*power;
            charPrint("Result:");
            charPrint(""+multp+" x 10^"+powerp);
            charPrint("Record? Y/N");
            if (StringIn().equalsIgnoreCase("Y"))
            {
               charPrint("What file to record to?");
               AppendFile(StringIn(), "Sci Notation Mult : Power :: "+multp+" x 10^"+powerp);
               charPrint("Success.");
            }
         }
         else if (input.equalsIgnoreCase("ext")){}
         else
         {
            charPrint("ERROR: INPUT_NOT_RECOGNIZED :: Pick Applicable Option");
         }
      }
   }
   
   //Pixel Measurer program.
   public static void PixelMeasure() throws InterruptedException, AWTException, FileNotFoundException, IOException
   {
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->PixelMeasure<-----+");
         charPrint("loc > See Current Location");
         charPrint("fnd > Find Location");
         charPrint("frd > Find and Record");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("fnd")) 
         {
            charPrint("Enter X and Y coords to move mouse to.");
            charPrint("X:");
            int x = IntIn();
            charPrint("Y:");
            int y = IntIn();
            charPrint("Moving mouse to X"+x+" Y"+y+".");
            movePointer(x,y);
         }
         else if (input.equalsIgnoreCase("loc")) 
         {
            charPrint("Mouse position:");
            charPrint("X: "+MouseInfo.getPointerInfo().getLocation().x);
            charPrint("Y: "+MouseInfo.getPointerInfo().getLocation().y);
         }
         else if (input.equalsIgnoreCase("frd")) 
         {
            charPrint("Mouse position:");
            charPrint("X: "+MouseInfo.getPointerInfo().getLocation().x);
            charPrint("Y: "+MouseInfo.getPointerInfo().getLocation().y);
            charPrint("Enter file name to record to.");
            AppendFile(StringIn(), "PixelMeasure : Find and Record :: X:"+MouseInfo.getPointerInfo().getLocation().x+", Y:"+MouseInfo.getPointerInfo().getLocation().y);
            charPrint("Success.");
         }
         else if (input.equalsIgnoreCase("ext")){}
         else
         {
            charPrint("ERROR: INPUT_NOT_RECOGNIZED :: Pick Applicable Option");
         }
      }
   }
   
   //Autoclicker program.
   public static void Autoclicker() throws InterruptedException, AWTException, FileNotFoundException, IOException
   {
      Scanner userInput = new Scanner(System.in);
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->Autoclicker<-----+");
         charPrint("nml > Normal");
         charPrint("alt > Alternating");
         charPrint("mac > Macro");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("nml"))
         {
            charPrint("+Normal Autoclicker Setup+");
            charPrint("How many MS between clicks?");
            int delay = IntIn();
            charPrint("How many clicks?");
            int clicks = IntIn();
            charPrint("READY TO AUTOCLICK");
            charPrint("Press ENTER to start 5-second countdown, or X to quit.");
            if (!userInput.nextLine().equalsIgnoreCase("X"))
            {
               charPrint("5");
               Thread.sleep(1000);
               charPrint("4");
               Thread.sleep(1000);
               charPrint("3");
               Thread.sleep(1000);
               charPrint("2");
               Thread.sleep(1000);
               charPrint("1");
               Thread.sleep(1000);
               charPrint("PROGRESS : [      ]");
               for (int i = 1; i <= clicks; i++)
               {
                  click();
                  Thread.sleep(delay);
                  if (i == clicks/6)
                  {
                     charPrint("PROGRESS : [|     ]");
                  }
                  if (i == clicks/3)
                  {
                     charPrint("PROGRESS : [||    ]");
                  }
                  if (i == clicks/2)
                  {
                     charPrint("PROGRESS : [|||   ]");
                  }
                  if (i == clicks*2/3)
                  {
                     charPrint("PROGRESS : [||||  ]");
                  }
                  if (i == clicks*5/6)
                  {
                     charPrint("PROGRESS : [||||| ]");
                  }
                  if (i == clicks)
                  {
                     charPrint("PROGRESS : [||||||]");
                  }
               }
            }
         }
         else if (input.equalsIgnoreCase("alt"))
         {
            charPrint("+Alternating Autoclicker Setup+");
            charPrint("How many MS between clicks?");
            int delay = IntIn();
            charPrint("How many clicks?");
            int clicks = IntIn();
            charPrint("What is the type click interval?");
            int interval = IntIn();
            charPrint("What to type each interval?");
            String phrase = StringIn();
            charPrint("READY TO AUTOCLICK");
            charPrint("Press ENTER to start 5-second countdown, or X to quit.");
            if (!userInput.nextLine().equalsIgnoreCase("X"))
            {
               charPrint("5");
               Thread.sleep(1000);
               charPrint("4");
               Thread.sleep(1000);
               charPrint("3");
               Thread.sleep(1000);
               charPrint("2");
               Thread.sleep(1000);
               charPrint("1");
               Thread.sleep(1000);
               charPrint("PROGRESS : [      ]");
               for (int i = 1; i <= clicks; i++)
               {
                  click();
                  if (i % interval == 0)
                     say(phrase);
                  Thread.sleep(delay);
                  if (i == clicks/6)
                  {
                     charPrint("PROGRESS : [|     ]");
                  }
                  if (i == clicks/3)
                  {
                     charPrint("PROGRESS : [||    ]");
                  }
                  if (i == clicks/2)
                  {
                     charPrint("PROGRESS : [|||   ]");
                  }
                  if (i == clicks*2/3)
                  {
                     charPrint("PROGRESS : [||||  ]");
                  }
                  if (i == clicks*5/6)
                  {
                     charPrint("PROGRESS : [||||| ]");
                  }
                  if (i == clicks)
                  {
                     charPrint("PROGRESS : [||||||]");
                  }
               }
            }
         }
         else if (input.equalsIgnoreCase("mac"))
         {
            int clicks = 0;
            int stepDelay = 0;
            int loopDelay = 1;
            int wait = 300;
            charPrint("+Macro Setup+");
            boolean finished = false;
            ArrayList<String> currentMacro = new ArrayList<String>();
            while (!finished)
            {
               for (int i = 0; i < currentMacro.size(); i++)
               {
                  charPrint(i + ": "+currentMacro.get(i));
               }
               charPrint("Enter a Command, I to import a macro, or F to Finish");
               charPrint("Commands: Click, MoveX, MoveY, Press, Delay");
               String choice = StringIn();
               if (choice.equalsIgnoreCase("I"))
               {
                  charPrint("Enter in file name.");
                  charPrint("NOTE: File must be soley a macro.");
                  File toImport = new File(StringIn());
                  if (toImport.exists())
                  {
                     charPrint("File found. Importing.");
                     Scanner reader = new Scanner(toImport);
                     String macroData = reader.nextLine();
                     macroData = macroData.substring(1, macroData.length()); //cut off brackets
                     while (macroData.indexOf(",") != -1)
                     {
                        currentMacro.add(macroData.substring(0, macroData.indexOf(", "))); // from the start to before the comma
                        macroData = macroData.substring(macroData.indexOf(", ")+2, macroData.length()); // remove the added item and the comma as well as the space after the comma
                     }
                     charPrint("Import success.");
                  }
                  else
                  {
                     charPrint("ERROR: FILE_NOT_EXIST :: Assure File Name Validity");
                  }
               }
               
               if (choice.equalsIgnoreCase("Click"))
               {
                  charPrint("How many MS should this step be?");
                  stepDelay = IntIn();
                  charPrint("This step should run every how many loops?");
                  loopDelay = IntIn();
                  currentMacro.add("Click()["+stepDelay+"]%"+loopDelay);
               }
               
               if (choice.equalsIgnoreCase("MoveX"))
               {
                  charPrint("X:");
                  int x = IntIn();
                  charPrint("How many MS should this step be?");
                  stepDelay = IntIn();
                  charPrint("This step should run every how many loops?");
                  loopDelay = IntIn();
                  currentMacro.add("MoveX("+x+")["+stepDelay+"]%"+loopDelay);
               }
               if (choice.equalsIgnoreCase("MoveY"))
               {
                  charPrint("Y:");
                  int y = IntIn();
                  charPrint("How many MS should this step be?");
                  stepDelay = IntIn();
                  charPrint("This step should run every how many loops?");
                  loopDelay = IntIn();
                  currentMacro.add("MoveY("+y+")["+stepDelay+"]%"+loopDelay);
               }
               
               if (choice.equalsIgnoreCase("Press"))
               {
                  charPrint("To Type: (NOTE: # == ENTER)");
                  String pressChoice = StringIn();
                  charPrint("How many MS should this step be?");
                  stepDelay = IntIn();
                  charPrint("This step should run every how many loops?");
                  loopDelay = IntIn();
                  currentMacro.add("TypTx("+pressChoice+")["+stepDelay+"]%"+loopDelay);
               }
               if (choice.equalsIgnoreCase("Delay"))
               {
                  charPrint("How many MS should this step be?");
                  stepDelay = IntIn();
                  charPrint("This step should run every how many loops?");
                  loopDelay = IntIn();
                  currentMacro.add("Delay()["+stepDelay+"]%"+loopDelay);
               }
               if (choice.equalsIgnoreCase("F"))
               {
                  charPrint("Macro set.");
                  charPrint("How many times to run macro?");
                  clicks = IntIn();
                  charPrint("How MS between each run?");
                  wait = IntIn();
                  if (wait <= 19)
                     charPrint("WARNING: VERY SHORT DELAYS MAY CRASH PROGRAMS.");
                  finished = true;
               }
            }
            charPrint("MACRO READY");
            charPrint("Press S to start, E to export and quit, or X to quit and delete.");
            String macroChoice = StringIn();
            if (macroChoice.equalsIgnoreCase("E"))
            {
               String export = "";
               export += ("[");
               for (int i = 0; i < currentMacro.size(); i++)
               {
                  export += (currentMacro.get(i) + ", ");
               } 
               export += ("BUFFER");
               export += ("]");
               charPrint("Macro formatted.");
               charPrint("Enter file name to export to.");
               AppendFile(StringIn(), export);
               charPrint("Success.");
               charPrint("Press ENTER to start, or X to quit.");
            }
            if (macroChoice.equalsIgnoreCase("S"))
            {
               charPrint("5");
               Thread.sleep(1000);
               charPrint("4");
               Thread.sleep(1000);
               charPrint("3");
               Thread.sleep(1000);
               charPrint("2");
               Thread.sleep(1000);
               charPrint("1");
               Thread.sleep(1000);
               charPrint("PROGRESS : [      ]");
               int parLoc = 0; // the closing parentheses
               int bracketLoc = 0; // the closing bracket
               for (int i = 1; i <= clicks; i++)
               { 
                  for (int j = 0; j < currentMacro.size(); j++)
                  {
                     if (i % Integer.parseInt(currentMacro.get(j).substring(currentMacro.get(j).indexOf("%")+1, currentMacro.get(j).length())) == 0)
                     {
                        parLoc = currentMacro.get(j).indexOf(")");
                        bracketLoc = currentMacro.get(j).indexOf("]");
                        if (currentMacro.get(j).substring(0,5).equalsIgnoreCase("Click"))
                        {
                           click();
                        }
                        if (currentMacro.get(j).substring(0,5).equalsIgnoreCase("TypTx"))
                        {
                           say(currentMacro.get(j).substring(6, parLoc));
                        }
                        if (currentMacro.get(j).substring(0,5).equalsIgnoreCase("MoveX"))
                        {
                           movePointer(Integer.parseInt(currentMacro.get(j).substring(6, parLoc)), MouseInfo.getPointerInfo().getLocation().y);
                        }
                        if (currentMacro.get(j).substring(0,5).equalsIgnoreCase("MoveY"))
                        {
                           movePointer(MouseInfo.getPointerInfo().getLocation().x, Integer.parseInt(currentMacro.get(j).substring(6, parLoc)));
                        }
                        if (currentMacro.get(j).substring(0,5).equalsIgnoreCase("Delay"))
                        {
                        }
                        Thread.sleep(Integer.parseInt(currentMacro.get(j).substring(parLoc+2, bracketLoc)));
                     }
                  }
                  
                  Thread.sleep(wait);
                  if (i == clicks/6)
                  {
                     charPrint("PROGRESS : [|     ]");
                  }
                  if (i == clicks/3)
                  {
                     charPrint("PROGRESS : [||    ]");
                  }
                  if (i == clicks/2)
                  {
                     charPrint("PROGRESS : [|||   ]");
                  }
                  if (i == clicks*2/3)
                  {
                     charPrint("PROGRESS : [||||  ]");
                  }
                  if (i == clicks*5/6)
                  {
                     charPrint("PROGRESS : [||||| ]");
                  }
                  if (i == clicks)
                  {
                     charPrint("PROGRESS : [||||||]");
                  }
               }
            }
            else
            {
               charPrint("Macro cancelled.");
            }
         }
         else if (input.equalsIgnoreCase("ext")){}
         else
         {
            charPrint("ERROR: INPUT_NOT_RECOGNIZED :: Pick Applicable Option");
         }
      }
   }
   
   //Notepad program.
   public static void Notepad() throws InterruptedException, AWTException, FileNotFoundException, IOException
   {
      String input = "";
      while (!input.equalsIgnoreCase("ext"))
      {
         charPrint("+----->NOTEPAD<-----+");
         charPrint("lib > View Library");
         charPrint("opn > View File");
         charPrint("add > Add Text to File/Create");
         charPrint("clr > Clear File");
         charPrint("ext > Exit");
         input = StringIn();
         if (input.equalsIgnoreCase("lib"))
         {
            ViewLibrary();
         }
         else if (input.equalsIgnoreCase("opn"))
         {
            charPrint("Please enter a file name.");
            String toOpen = StringIn();
            if (!(new File(toOpen).exists()))
            {
               charPrint("ERROR: FILE_NOT_EXIST :: Assure File Name Validity");
            }
            else
            {
               ReadFile(toOpen);
            }
         }
         else if (input.equalsIgnoreCase("add"))
         {
            charPrint("Please type a file to access.");
            String toAccess = StringIn();
            File file = new File(toAccess);
            if (file.exists())
            {
               charPrint("File "+toAccess+" found.");
               charPrint("Enter text to add.");
               AppendFile(toAccess, StringIn());
               charPrint("Success.");
            }
            else
            {
               charPrint("Affirm creation of file "+toAccess+". Y/N");
               String affirmation = StringIn();
               if (affirmation.equalsIgnoreCase("Y"))
               {
                  charPrint("Creating file "+toAccess+".");
                  charPrint("Enter text to add.");
                  AppendFile(toAccess, StringIn());
                  charPrint("Success.");
               }
               else
               {
                  charPrint("Creation cancelled.");
               }
            }
         }
         else if (input.equalsIgnoreCase("clr"))
         {
            charPrint("Enter file name to clear.");
            String toClear = StringIn();
            if (!(new File(toClear).exists()))
            {
               charPrint("ERROR: FILE_NOT_EXIST :: Assure File Name Correctness");
            }
            else
            {
               charPrint("File "+toClear+" exists. Type CLEAR to clear the file.");
               if (StringIn().equals("CLEAR"))
               {
                  ClearFile(toClear);
                  charPrint("Cleared.");
               }
               else
                  charPrint("Averted. File not cleared.");
            }
         }
         else if (input.equalsIgnoreCase("ext")){}
         else
         {
            charPrint("ERROR: INPUT_NOT_RECOGNIZED :: Pick Applicable Option");
         }
      }
   }
   
   //File methods.
   public static void ViewLibrary() throws FileNotFoundException, IOException, InterruptedException, AWTException // prints all available text files as string names
   {
      try
      {
         ReadFile("library");
      }
      catch (FileNotFoundException e)
      {
         charPrint("ERROR: LIBRARY_DELETED :: Restore Library File");
      }
   }
   public static void AppendFile(String name, String toAdd) throws FileNotFoundException, IOException// appends text to the given file, or creates it
   {
      if (!(new File(name).exists()))
      {
         FileWriter libraryf = new FileWriter("library", true);
         PrintWriter libraryp = new PrintWriter(libraryf);
         libraryp.printf("%s" + "%n" , name);
         libraryp.close();
      }
      
      FileWriter filew = new FileWriter(name, true);
      PrintWriter writer = new PrintWriter(filew);
      writer.printf("%s" + "%n" , toAdd);
      writer.close();
      
   }
   public static void ClearFile(String name) throws FileNotFoundException, IOException// clears a file of all data
   {
      FileWriter filew = new FileWriter(name, false);
      PrintWriter writer = new PrintWriter(filew);
      writer.printf("%s" + "%n" , "");
      writer.close();
   }
   public static void ReadFile(String name) throws FileNotFoundException, IOException, InterruptedException, AWTException // reads a file
   {
      Scanner fileReader = new Scanner(new File(name));
      charPrint("|-FILE:"+name+"-------|");
      while (fileReader.hasNext())
         charPrint(fileReader.nextLine());
      charPrint("--<EOF>--");
   }
   
   
   //Input getters.
   public static String StringIn() throws InterruptedException, AWTException
   {
      Scanner userInput = new Scanner(System.in);
      while (true)
      {
         try
         {
            String out = userInput.nextLine();
            return out;
         }
         catch (InputMismatchException e)
         {
            userInput.nextLine();
            charPrint("ERROR: FAULTY_INPUT_FORMAT :: String Expected");
         }
      }
   }
   public static int IntIn() throws InterruptedException, AWTException
   {
      Scanner userInput = new Scanner(System.in);
      while (true)
      {
         try
         {
            int out = userInput.nextInt(); userInput.nextLine();
            return out;
         }
         catch (InputMismatchException e)
         {
            userInput.nextLine();
            charPrint("ERROR: FAULTY_INPUT_FORMAT :: Integer Expected");
         }
      }
   }
   public static double DoubleIn() throws InterruptedException, AWTException
   {
      Scanner userInput = new Scanner(System.in);
      while (true)
      {
         try
         {
            double out = userInput.nextDouble(); userInput.nextLine();
            return out;
         }
         catch (InputMismatchException e)
         {
            userInput.nextLine();
            charPrint("ERROR: FAULTY_INPUT_FORMAT :: Decimal Expected");
         }
      }
   }
   
   //Prints the logo.
   public static void printIntro() throws InterruptedException, AWTException
   {
      charPrint("+-----=======-----+");
      charPrint("|    |---|---|    |");
      charPrint("|    |   |   |    |");
      charPrint("|    |   |   |    |");
      charPrint("|    |---|---|    |");
      charPrint("|  |---|---|---|  |");
      charPrint("|  |   |   |   |  |");
      charPrint("|  |   |   |   |  |");
      charPrint("|  |---|---|---|  |");
      charPrint("|    |---|---|    |");
      charPrint("|    |   |   |    |");
      charPrint("|    |   |   |    |");
      charPrint("|    |---|---|    |");
      charPrint("+-----==HEX==-----+");
      Thread.sleep(1000);
      charPrint("WELCOME TO HEX.");
      charPrint("-VERSION 0.8.1-");
   }
   
   
   //executes one click instantly
   public static void click() throws InterruptedException, AWTException
   {
      Robot bot = new Robot();
      bot.mousePress(InputEvent.BUTTON1_MASK);
      bot.mouseRelease(InputEvent.BUTTON1_MASK);
   }
   
   
   //moves mouse
   public static void movePointer(int x, int y) throws InterruptedException, AWTException
   {
      Robot bot = new Robot();
      bot.mouseMove(x, y);
   }
   
   
   //walks up and back down, used to avoid idle kicks
   public static void walkUpAndDown(int distance) throws InterruptedException, AWTException
   {
      Robot bot = new Robot();
      bot.keyPress(KeyEvent.VK_W);
      Thread.sleep(distance);
      bot.keyRelease(KeyEvent.VK_W);
      bot.keyPress(KeyEvent.VK_S);
      Thread.sleep(distance);
      bot.keyRelease(KeyEvent.VK_S);
   }
   
   
   //printers
   public static void println(String str)
   {
      System.out.println(str);
   }
   public static void print(String str)
   {
      System.out.print(str);
   }
   
   
   //prints 1char at a time, fancifier. not type, print to output
   public static void charPrint(String str) throws InterruptedException, AWTException
   {
      for (int i = 0; i < str.length(); i++)
      {
         print(str.substring(i, i+1));
         Thread.sleep(10);
      }
      println("");
   }
   
   
   //Types a phrase
   public static void say(String quote) throws InterruptedException, AWTException
   {
      Robot bot = new Robot();
      bot.setAutoDelay(30);
      for (int i = 0; i < quote.length(); i++) // says each character
      {
         if (quote.substring(i, i+1).equalsIgnoreCase("0"))
         {
            bot.keyPress(KeyEvent.VK_0);
            bot.keyRelease(KeyEvent.VK_0);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("1"))
         {
            bot.keyPress(KeyEvent.VK_1);
            bot.keyRelease(KeyEvent.VK_1);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("2"))
         {
            bot.keyPress(KeyEvent.VK_2);
            bot.keyRelease(KeyEvent.VK_2);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("3"))
         {
            bot.keyPress(KeyEvent.VK_3);
            bot.keyRelease(KeyEvent.VK_3);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("4"))
         {
            bot.keyPress(KeyEvent.VK_4);
            bot.keyRelease(KeyEvent.VK_4);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("5"))
         {
            bot.keyPress(KeyEvent.VK_5);
            bot.keyRelease(KeyEvent.VK_5);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("6"))
         {
            bot.keyPress(KeyEvent.VK_6);
            bot.keyRelease(KeyEvent.VK_6);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("7"))
         {
            bot.keyPress(KeyEvent.VK_7);
            bot.keyRelease(KeyEvent.VK_7);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("8"))
         {
            bot.keyPress(KeyEvent.VK_8);
            bot.keyRelease(KeyEvent.VK_8);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("9"))
         {
            bot.keyPress(KeyEvent.VK_9);
            bot.keyRelease(KeyEvent.VK_9);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("."))
         {
            bot.keyPress(KeyEvent.VK_PERIOD);
            bot.keyRelease(KeyEvent.VK_PERIOD);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("a"))
         {
            bot.keyPress(KeyEvent.VK_A);
            bot.keyRelease(KeyEvent.VK_A);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("b"))
         {
            bot.keyPress(KeyEvent.VK_B);
            bot.keyRelease(KeyEvent.VK_B);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("c"))
         {
            bot.keyPress(KeyEvent.VK_C);
            bot.keyRelease(KeyEvent.VK_C);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("d"))
         {
            bot.keyPress(KeyEvent.VK_D);
            bot.keyRelease(KeyEvent.VK_D);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("e"))
         {
            bot.keyPress(KeyEvent.VK_E);
            bot.keyRelease(KeyEvent.VK_E);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("f"))
         {
            bot.keyPress(KeyEvent.VK_F);
            bot.keyRelease(KeyEvent.VK_F);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("g"))
         {
            bot.keyPress(KeyEvent.VK_G);
            bot.keyRelease(KeyEvent.VK_G);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("h"))
         {
            bot.keyPress(KeyEvent.VK_H);
            bot.keyRelease(KeyEvent.VK_H);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("i"))
         {
            bot.keyPress(KeyEvent.VK_I);
            bot.keyRelease(KeyEvent.VK_I);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("j"))
         {
            bot.keyPress(KeyEvent.VK_J);
            bot.keyRelease(KeyEvent.VK_J);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("k"))
         {
            bot.keyPress(KeyEvent.VK_K);
            bot.keyRelease(KeyEvent.VK_K);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("l"))
         {
            bot.keyPress(KeyEvent.VK_L);
            bot.keyRelease(KeyEvent.VK_L);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("m"))
         {
            bot.keyPress(KeyEvent.VK_M);
            bot.keyRelease(KeyEvent.VK_M);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("n"))
         {
            bot.keyPress(KeyEvent.VK_N);
            bot.keyRelease(KeyEvent.VK_N);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("o"))
         {
            bot.keyPress(KeyEvent.VK_O);
            bot.keyRelease(KeyEvent.VK_O);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("p"))
         {
            bot.keyPress(KeyEvent.VK_P);
            bot.keyRelease(KeyEvent.VK_P);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("q"))
         {
            bot.keyPress(KeyEvent.VK_Q);
            bot.keyRelease(KeyEvent.VK_Q);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("r"))
         {
            bot.keyPress(KeyEvent.VK_R);
            bot.keyRelease(KeyEvent.VK_R);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("s"))
         {
            bot.keyPress(KeyEvent.VK_S);
            bot.keyRelease(KeyEvent.VK_S);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("t"))
         {
            bot.keyPress(KeyEvent.VK_T);
            bot.keyRelease(KeyEvent.VK_T);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("u"))
         {
            bot.keyPress(KeyEvent.VK_U);
            bot.keyRelease(KeyEvent.VK_U);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("v"))
         {
            bot.keyPress(KeyEvent.VK_V);
            bot.keyRelease(KeyEvent.VK_V);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("w"))
         {
            bot.keyPress(KeyEvent.VK_W);
            bot.keyRelease(KeyEvent.VK_W);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("x"))
         {
            bot.keyPress(KeyEvent.VK_X);
            bot.keyRelease(KeyEvent.VK_X);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("y"))
         {
            bot.keyPress(KeyEvent.VK_Y);
            bot.keyRelease(KeyEvent.VK_Y);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("z"))
         {
            bot.keyPress(KeyEvent.VK_Z);
            bot.keyRelease(KeyEvent.VK_Z);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase(" "))
         {
            bot.keyPress(KeyEvent.VK_SPACE);
            bot.keyRelease(KeyEvent.VK_SPACE);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("/"))
         {
            bot.keyPress(KeyEvent.VK_SLASH);
            bot.keyRelease(KeyEvent.VK_SLASH);
         }
         if (quote.substring(i, i+1).equalsIgnoreCase("#"))
         {
            bot.keyPress(KeyEvent.VK_ENTER);
            bot.keyRelease(KeyEvent.VK_ENTER);
         }
      }
      bot.keyPress(KeyEvent.VK_ENTER); // press enter to send message
      bot.keyRelease(KeyEvent.VK_ENTER);
   }
}