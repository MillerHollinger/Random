import java.awt.*;
import java.awt.event.InputEvent;
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

	// Does autoclicking
	public static void autoclick(String fileName) {

	}

	// Does alternating
	public static void alternate(String fileName) {

	}

	// Does a macro
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
	public static void press(String key, int ms) {

	}

	// Types multiple keys with a delay
	public static void type(String key, int ms) {

	}

	// File writer
	public static void writeTo(String fileName, String toAdd) {

	}

	// File reader
	public static String readFrom(String fileName) {
		String out = "";

		return out;
	}
}