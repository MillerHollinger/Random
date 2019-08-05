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
 * 			(Data) = (ToPress,ms,loopsPer)
 * 				ToPress = what key(s) to press (does not apply to Click or Delay)
 * 		    	ms = time between parts of this step
 * 				loopsPer = how many loops per run of this action
 */

/* To Do List
 *  > Can't put commas in Press commands as they are used to parse info -- replace parse character with another
 * 
 */

public class Hex {
	// VERSION NUMBER
	final static String version = "v0.5.2";
	
	// Display debug information
	final static boolean DEBUG = true;

	// User interactions
	public static void main(String[] args) throws Exception {
		println("Hex by MillerHollinger : Input Automation");
		println("   " + version);
		println("Please enter a file name to begin.");
		String fileName = new Scanner(System.in).nextLine();
		try {
			switch (readFrom(fileName).substring(0, 2)) {
			case "AC":
				autoclick(fileName);
				break;
			case "LT":
				alternate(fileName);
				break;
			case "MC":
				macro(fileName);
				break;
			default:
				println("ERROR: Bad file data; type unspecified.");
				break;
			}
		} catch (Exception e) {
			println("ERROR: File does not exist.");
		}
	}

	private static class Command {
		public String type; // Press, Click, Delay
		public String press; // Used only in Press, otherwise "-", what to type
		public int ms; // Always used, time per command action
		public int loopsPer; // Always used, loops per run of this command

		// Parses a string to a command of form: [type(press,ms,loopsPer)
		public Command(String in) {
			if (DEBUG)
				println("Attempting to load command with info: " + in); // DEBUG
			try {
				type = in.substring(1, 6);
				in = in.substring(6);
				if (DEBUG)
					println("Loaded type of " + type + "; in = " + in); // DEBUG

				press = in.substring(1, in.indexOf(","));
				in = in.substring(in.indexOf(",") + 1);
				if (DEBUG)
					println("Loaded press of " + press + "; in = " + in); // DEBUG

				ms = Integer.parseInt(in.substring(0, in.indexOf(",")));
				in = in.substring(in.indexOf(",") + 1);
				if (DEBUG)
					println("Loaded ms of " + ms + "; in = " + in); // DEBUG

				loopsPer = Integer.parseInt(in.substring(0, in.indexOf(")")));
				if (DEBUG)
					println("Loaded loopsPer of " + loopsPer + "; in = " + in); // DEBUG
			} catch (Exception e) {
				if (DEBUG)
					println("ERROR: Failed to load command. " + e); // DEBUG
				else
					println("ERROR: Failed to load command. ");
			}
		}

		// Fills in data directly
		public Command(String t, String p, int m, int l) {
			type = t;
			press = p;
			ms = m;
			loopsPer = l;
		}

		// Executes this command
		public void execute(int loopNum) throws Exception {
			if (loopNum % loopsPer == 0) {
				if(DEBUG)println("Executing " + this.toString() + "; loopNum = " + loopNum); // DEBUG
				switch (type) {
				case "Press":
					type(press, ms);
					break;
				case "Click":
					click(ms);
					break;
				case "Delay":
					Thread.sleep(ms);
					break;
				}
			}
		}

		// Returns data based on type
		public String toString() {
			switch (type) {
			case "Press":
				return "Press() : Presses \"" + press + "\"; " + ms + " ms delay; runs every " + loopsPer + " loops";
			case "Click":
				return "Click() : Clicks once with " + ms + " ms delay; runs every " + loopsPer + " loops";
			case "Delay":
				return "Delay() : Pause for " + ms + " ms; runs every " + loopsPer + " loops";
			}
			return "ERROR: Invalid Command type";
		}
	}

	// Asks a yes or no question.
	public static boolean yes(String question) {
		println(question + " Yes/No");
		String choice = "";
		boolean invalid = false;
		while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("yes") && !choice.equalsIgnoreCase("n")
				&& !choice.equalsIgnoreCase("no")) {
			if (invalid)
				println("ERROR: Bad user input.\nEnter Yes or Y for Affirmative; No or N for Negative.");
			choice = new Scanner(System.in).nextLine();
			invalid = true;
		}
		return choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes");
	}

	// Printers.
	public static void print(Object o) {
		System.out.print(o.toString());
	}

	public static void println(Object o) {
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
				println("Autoclick: Data accepted. Clicking " + clicks + " times with a " + ms + " ms delay.");
				if (yes("Continue?"))
					for (int i = 0; i < clicks; i++)
						click(ms);
				else
					println("Cancelled.");
			} catch (Exception e) {
				println("ERROR: Bad file data. Failed to execute.");
			}
		} else
			println("ERROR: Bad file data. Failed to execute.");
	}

	// Does alternating
	public static void alternate(String fileName) throws Exception {
		String data = readFrom(fileName);
		int clicks = 0;
		String type = "";
		int ms = 0;
		if (data.substring(0, 3).equals("LT[")) {
			try {
				clicks = Integer.parseInt(data.substring(data.indexOf("[") + 1, data.indexOf("]")));
				data = data.substring(data.indexOf("]") + 1);
				type = data.substring(data.indexOf("[") + 1, data.indexOf("]"));
				data = data.substring(data.indexOf("]") + 1);
				ms = Integer.parseInt(data.substring(data.indexOf("[") + 1, data.indexOf("]")));
				println("Alternate: Data accepted. Clicking " + clicks + " times alternating \"" + type + "\" with a "
						+ ms + " ms delay.");
				if (yes("Continue?"))
					for (int i = 0; i < clicks; i++) {
						click(ms);
						press(type, ms);
					}
				else
					println("Cancelled.");
			} catch (Exception e) {
				println("ERROR: Bad file data. Failed to execute." + e);
			}
		} else
			println("ERROR: Bad file data. Failed to execute.");
	}

	// Does a macro
	@SuppressWarnings("resource")
	public static void macro(String fileName) throws Exception {
		try {
			String[] data = readFrom(fileName).substring(2).split("]"); // MC[...][...][...] -> [...][...][...] -> [...
																		// , [... , [...
			ArrayList<Command> commands = new ArrayList<Command>();

			for (String info : data)
				commands.add(new Command(info));

			println("Macro \"" + fileName + "\" loaded:");
			for (Command c : commands)
				println(c);

			if (yes("Continue?")) {
				println("How many loops to run?");
				int loops = new Scanner(System.in).nextInt();
				println("Beginning in five seconds.");
				Thread.sleep(1000);
				println("             four seconds.");
				Thread.sleep(1000);
				println("            three seconds.");
				Thread.sleep(1000);
				println("              two seconds.");
				Thread.sleep(1000);
				println("               one second.");
				Thread.sleep(1000);
				println("                Executing:");
				for (int i = 1; i <= loops; i++)
					for (int j = 0; j < commands.size(); j++)
						commands.get(j).execute(i);
				println("Finished.");
			} else
				println("Cancelled.");

		} catch (Exception e) {
			if (DEBUG)
				println("ERROR: Bad file data. Failed to execute. " + e); // DEBUG
			else
				println("ERROR: Bad file data. Failed to execute. ");
		}

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
		case " ":
			bot.keyPress(KeyEvent.VK_SPACE);
			bot.keyRelease(KeyEvent.VK_SPACE);
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
			press(keys.substring(i, i + 1), ms);
	}

	// File writer
	public static void writeTo(String fileName, String toAdd) throws Exception {
		if (!(new File(fileName).exists())) {
			FileWriter libraryf = new FileWriter("library", true);
			PrintWriter libraryp = new PrintWriter(libraryf);
			libraryp.printf("%s" + "%n", fileName);
			libraryp.close();
		}

		FileWriter filew = new FileWriter(fileName, true);
		PrintWriter writer = new PrintWriter(filew);
		writer.printf("%s" + "%n", toAdd);
		writer.close();
	}

	// File reader
	public static String readFrom(String fileName) throws Exception {
		String out = "";
		Scanner fileReader = new Scanner(new File(fileName + ".txt"));
		while (fileReader.hasNext()) {
			out += fileReader.nextLine();
			if (fileReader.hasNext())
				out += "\n";
		}
		fileReader.close();
		return out;
	}
}