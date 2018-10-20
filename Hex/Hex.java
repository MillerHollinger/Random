import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;
import java.net.*;

import java.math.*;
import java.rmi.*;
import java.rmi.server.*;

/* HEX VERSION 2: GOALS
 * - High efficiency
 * - Command line compatibility
 * - Focus on macros, no other programs necessary
 * 
 * Autoclicker
 *   - Repeat Clicks
 *   - Alternated actions
 *   - Macros
 *     - Integrated pixel measure
 *     - Record and write
 *     - Efficient saving
 *     - Edit pre-existing macros
 * Library
 *   - See macros, delete as well
 * 
 * Two Modes
 * > Menu Mode
 *   - Walks the user through each step, never asking for more than one variable at a time
 * > Hardline Mode
 *   - Uses command-esque single-line commands to setup and execute everything
 * 
 * 
 */

public class Hex {
	public static void main(String[] args) throws Exception {
		// Menu Mode or Hardline Mode
		// Main Loop
		// Menu Mode
		// Autoclick, Alternate, or Macro
		// Get information for selected action
		// Autoclick
		// Load file or new?
		// New
		// Click delay?
		// Total clicks?
		// Load
		// Display applicable
		// Load it
		// Alternate
		// Load file or new?
		// New
		// Click delay?
		// Total clicks?
		// Key to press?
		// Load
		// Display applicable
		// Load it
		// Macro
		// Load file or new?
		// New
		// IPress, HPress, IClick, HClick, Delay
		// IPress
		// HPress
		// IClick
		// HClick
		// Delay
		// Load
		// Display applicable
		// Load it
		// Hardline mode
		// Get all information
		// Execute action
	}

	// TODO Does autoclicking
	public static void autoclick(String fileName) {

	}

	// TODO Does alternating
	public static void alternate(String fileName) {

	}

	// TODO Does a macro
	public static void macro(String fileName) {

	}

	// Clicks and holds for some time
	public static void click(int ms) throws Exception {
		Robot bot = new Robot();
		bot.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(ms);
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	// Presses a key and holds for some time
	public static void press(String key, int ms) throws Exception {
		Robot bot = new Robot();
		bot.setAutoDelay(ms);
		switch (key) {
		case "0":
			bot.keyPress(KeyEvent.VK_0);
			bot.keyRelease(KeyEvent.VK_0);
			break;
		case "1":
			bot.keyPress(KeyEvent.VK_1);
			bot.keyRelease(KeyEvent.VK_1);
			break;
		case "2":
			bot.keyPress(KeyEvent.VK_2);
			bot.keyRelease(KeyEvent.VK_2);
			break;
		case "3":
			bot.keyPress(KeyEvent.VK_3);
			bot.keyRelease(KeyEvent.VK_3);
			break;
		case "4":
			bot.keyPress(KeyEvent.VK_4);
			bot.keyRelease(KeyEvent.VK_4);
			break;
		case "5":
			bot.keyPress(KeyEvent.VK_5);
			bot.keyRelease(KeyEvent.VK_5);
			break;
		case "6":
			bot.keyPress(KeyEvent.VK_6);
			bot.keyRelease(KeyEvent.VK_6);
			break;
		case "7":
			bot.keyPress(KeyEvent.VK_7);
			bot.keyRelease(KeyEvent.VK_7);
			break;
		case "8":
			bot.keyPress(KeyEvent.VK_8);
			bot.keyRelease(KeyEvent.VK_8);
			break;
		case "9":
			bot.keyPress(KeyEvent.VK_9);
			bot.keyRelease(KeyEvent.VK_9);
			break;
		case "a":
			bot.keyPress(KeyEvent.VK_A);
			bot.keyRelease(KeyEvent.VK_A);
			break;
		case "b":
			bot.keyPress(KeyEvent.VK_B);
			bot.keyRelease(KeyEvent.VK_B);
			break;
		case "c":
			bot.keyPress(KeyEvent.VK_C);
			bot.keyRelease(KeyEvent.VK_C);
			break;
		case "d":
			bot.keyPress(KeyEvent.VK_D);
			bot.keyRelease(KeyEvent.VK_D);
			break;
		case "e":
			bot.keyPress(KeyEvent.VK_E);
			bot.keyRelease(KeyEvent.VK_E);
			break;
		case "f":
			bot.keyPress(KeyEvent.VK_F);
			bot.keyRelease(KeyEvent.VK_F);
			break;
		case "g":
			bot.keyPress(KeyEvent.VK_G);
			bot.keyRelease(KeyEvent.VK_G);
			break;
		case "h":
			bot.keyPress(KeyEvent.VK_H);
			bot.keyRelease(KeyEvent.VK_H);
			break;
		case "i":
			bot.keyPress(KeyEvent.VK_I);
			bot.keyRelease(KeyEvent.VK_I);
			break;
		case "j":
			bot.keyPress(KeyEvent.VK_J);
			bot.keyRelease(KeyEvent.VK_J);
			break;
		case "k":
			bot.keyPress(KeyEvent.VK_K);
			bot.keyRelease(KeyEvent.VK_K);
			break;
		case "l":
			bot.keyPress(KeyEvent.VK_L);
			bot.keyRelease(KeyEvent.VK_L);
			break;
		case "m":
			bot.keyPress(KeyEvent.VK_M);
			bot.keyRelease(KeyEvent.VK_M);
			break;
		case "n":
			bot.keyPress(KeyEvent.VK_N);
			bot.keyRelease(KeyEvent.VK_N);
			break;
		case "o":
			bot.keyPress(KeyEvent.VK_O);
			bot.keyRelease(KeyEvent.VK_O);
			break;
		case "p":
			bot.keyPress(KeyEvent.VK_P);
			bot.keyRelease(KeyEvent.VK_P);
			break;
		case "q":
			bot.keyPress(KeyEvent.VK_Q);
			bot.keyRelease(KeyEvent.VK_Q);
			break;
		case "r":
			bot.keyPress(KeyEvent.VK_R);
			bot.keyRelease(KeyEvent.VK_R);
			break;
		case "s":
			bot.keyPress(KeyEvent.VK_S);
			bot.keyRelease(KeyEvent.VK_S);
			break;
		case "t":
			bot.keyPress(KeyEvent.VK_T);
			bot.keyRelease(KeyEvent.VK_T);
			break;
		case "u":
			bot.keyPress(KeyEvent.VK_U);
			bot.keyRelease(KeyEvent.VK_U);
			break;
		case "v":
			bot.keyPress(KeyEvent.VK_V);
			bot.keyRelease(KeyEvent.VK_V);
			break;
		case "w":
			bot.keyPress(KeyEvent.VK_W);
			bot.keyRelease(KeyEvent.VK_W);
			break;
		case "x":
			bot.keyPress(KeyEvent.VK_X);
			bot.keyRelease(KeyEvent.VK_X);
			break;
		case "y":
			bot.keyPress(KeyEvent.VK_Y);
			bot.keyRelease(KeyEvent.VK_Y);
			break;
		case "z":
			bot.keyPress(KeyEvent.VK_Z);
			bot.keyRelease(KeyEvent.VK_Z);
			break;
		case "#": // Enter
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
			break;
		case "/":
			bot.keyPress(KeyEvent.VK_SLASH);
			bot.keyRelease(KeyEvent.VK_SLASH);
			break;
		case ",":
			bot.keyPress(KeyEvent.VK_COMMA);
			bot.keyRelease(KeyEvent.VK_COMMA);
			break;
		case "+": // Shift Press
			bot.keyPress(KeyEvent.VK_SHIFT);
			break;
		case "-": // Shift Release
			bot.keyRelease(KeyEvent.VK_SHIFT);
			break;
		}
	}

	// Types multiple keys with a delay
	public static void type(String keys, int ms) throws Exception {
		for (int i = 0; i < keys.length(); i++)
			press(keys, ms);
	}

	/*public static void AppendFile(String name, String toAdd) throws FileNotFoundException, IOException// appends text to the given file, or creates it
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
   */
	
	// File writer
	public static void writeTo(String fileName, String toAdd) throws Exception {
		if (!(new File(fileName).exists()))
	      {
	         FileWriter libraryf = new FileWriter("library", true);
	         PrintWriter libraryp = new PrintWriter(libraryf);
	         libraryp.printf("%s" + "%n" , fileName);
	         libraryp.close();
	      }
	      
	      FileWriter filew = new FileWriter(fileName, true);
	      PrintWriter writer = new PrintWriter(filew);
	      writer.printf("%s" + "%n" , toAdd);
	      writer.close();
	}

	// File reader
	public static String readFrom(String fileName) throws FileNotFoundException {
		String out = "";
		Scanner fileReader = new Scanner(new File(fileName));
	    while (fileReader.hasNext())
	    {
	         out += fileReader.nextLine();
	         if (fileReader.hasNext())
	        	 out += "\n";
	    }
	    fileReader.close();
	    return out;
	}
}