/*
Welcome to the User Version of HexNet! 
By MillerHollinger.

This program is used to connect to all HexNet compatible games.
Simply enter in the IP once the game is online and you're connected!

Known Errors:
- Text panel stops autoscrolling when clicked on.
*/

//All the stuff to import.
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;


public class HexNet {

   //The objects that read and send data to the server.
   BufferedReader in;
   PrintWriter out;
   
   //The UI that appears on the screen.
   JFrame frame = new JFrame("HexNet");
   JTextField textField = new JTextField(40);
   JTextArea messageArea = new JTextArea(8, 40);
   DefaultCaret caret = (DefaultCaret)messageArea.getCaret();
   public HexNet() {
     //Configuration of the UI. Sets up the font and everything so it looks all cool.
      Font textFont = new Font("Monospaced", Font.PLAIN, 18);
      textField.setFont(textFont);
      textField.setEditable(false);
      messageArea.setEditable(false);
      messageArea.setFont(textFont);
      frame.getContentPane().add(textField, "South");
      frame.getContentPane().add(new JScrollPane(messageArea), "Center");
      frame.pack();
   
      
      
   
      //An action listener. Does something when text is sent to the user.
      textField.addActionListener(
         new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               out.println(textField.getText());
               textField.setText("");
            }
         });
   }
   
   //The prompt for the user to type in the UI address.
   private String getServerAddress() {
      return JOptionPane.showInputDialog(
         frame,
         "Enter Server IP",
         "HexNet Server IP",
         JOptionPane.QUESTION_MESSAGE);
   }
   
   //The code that actually does the game.
   private void run() throws IOException {
      //Connects the user to the server.
      String serverAddress = getServerAddress();
      Socket socket = new Socket(serverAddress, 6000);
      in = new BufferedReader(new InputStreamReader(
         socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream(), true);
      
      //Once connected, let the user run commands in the line.
      textField.setEditable(true);
   
      //This while(true) keeps the game running at all times, until the user closes the game.
      while(true) {
         //Reads the user's command and, if it isn't null, sends it to the server for processing.
         String line = in.readLine();
         caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
         if (line != null)
            messageArea.append(line + "\n");
      }
   }

   public static void main(String[] args) throws Exception {
      //Starts up the game. Creates a new player and runs the above code.
      HexNet client = new HexNet();
      client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      client.frame.setVisible(true);
      client.run();
   }
}