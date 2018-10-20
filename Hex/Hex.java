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
 * Data Formatting
 * Autoclicker:
 * 	AC[Clicks][ms]
 * 		Clicks = how many clicks to do
 * 		ms = time between clicks
 * Alternating:
 * 	LT[Clicks][Type][ms]
 * 		Clicks = how many clicks to do
 * 		Type = what to type between clicks
 * 		ms = time between clicks
 * Macro:
 * 	MC[Action(Data)][Action(Data)][ ... ]
 * 		Action = an action: Press, Click, Delay
 * 			Press(Data) = (ToPress, ms, loopsPer)
 * 				ToPress = what key(s) to press
 * 			Click(Data) = (ms, loopsPer)
 * 			Delay(Data) = (ms, loopsPer)
 * 		    	ms = time between parts of this step
 * 				loopsPer = how many loops per run of this action
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
		// Press, Click, Delay
		// Press
		// Press
		// Click
		// Click
		// Delay
		// Load
		// Display applicable
		// Load it
		// Hardline mode
		// Get all information
		// Execute action
	}

	// Asks a yes or no question.
	public static boolean yes(String question)
	{
		println(question + " Yes/No");
		String choice = "";
		boolean invalid = false;
		while (!choice.equalsIgnoreCase("y") || !choice.equalsIgnoreCase("yes") || !choice.equalsIgnoreCase("n") || !choice.equalsIgnoreCase("no"))
		{
			if (invalid)
				println("ERROR: Bad user input.\nEnter Yes or Y for Affirmative; No or N for Negative.");
			choice = new Scanner(System.in).nextLine();
			invalid = true;
		}
		return choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes");
	}	
	
	// Printers.
	public static void print(Object o)
	{
		System.out.print(o.toString());
	}
	public static void println(Object o)
	{
		System.out.println(o.toString());
	}
	
	// Does autoclicking
	public static void autoclick(String fileName) throws Exception {
		String data = readFrom(fileName);
		int clicks = 0;
		int ms = 0;
		if (data.substring(0, 4).equals("AC[")) {
			try {
				clicks = Integer.parseInt(data.substring(data.indexOf("["), data.indexOf("]")));
				data = data.substring(data.indexOf("]") + 1);
				ms = Integer.parseInt(data.substring(data.indexOf("["), data.indexOf("]")));
				data = data.substring(data.indexOf("]") + 1);
				println("Data accepted. Clicking " + clicks + " times with a " + ms + " ms delay.");
				if (yes("Continue?))"))
					for (int i = 0; i < clicks; i++)
						click(ms);
				else
					println("Cancelled.");
			} catch (Exception e) {
				println("ERROR: Bad file data. Failed to execute.");
			}
		}
		else
			println("ERROR: Bad file data. Failed to execute.");
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
	public static String readFrom(String fileName) throws Exception {
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