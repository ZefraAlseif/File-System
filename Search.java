/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author carlo
 */
public class Search {
    
    public void findString(String sh) throws FileNotFoundException
    {
      Scanner sc1 = new Scanner(System.in);
      String data = JOptionPane.showInputDialog("Enter string to be found");
      String word ="Results:";
      boolean flag = false;
      int count = 0;
      //Reading the contents of the file
      Scanner sc2 = new Scanner(new FileInputStream(sh));
      while(sc2.hasNextLine()) {
         String line = sc2.nextLine();
         count = count+1;
         // Looks for the string by setting both string to lower case
         if(line.toLowerCase().contains(data.toLowerCase()))
         {
            flag = true;
            word = word + "\n" + count + ": "+line ;
         }
         }
     
      if (flag == false)
      {
      JOptionPane.showMessageDialog(null,"String not in File");
      }
      
      paneDisplay(word,"Findings",JOptionPane.INFORMATION_MESSAGE);
}
 
    public void tokenFile(String fi) throws IOException
    {
        int totalNumOfTokens = 0, numberOfTokens = 0;
        StringTokenizer dataLine=null;
          String s = "Tokenize File";
          Scanner sc2 = new Scanner(new FileInputStream(fi));
          while(sc2.hasNextLine()) {
          String line = sc2.nextLine();
          dataLine = new StringTokenizer(line,"[~!@#$%^&*()`{}[]:';'<>?,.`/*\\qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789//]",true);
          numberOfTokens = dataLine.countTokens();
          totalNumOfTokens = totalNumOfTokens + numberOfTokens ;
          while (dataLine.hasMoreTokens())
            {
                System.out.println(dataLine.nextToken());
                s = s + "\n" + dataLine.nextToken();
            }
          } 
                        // Output result and close file
            
            JOptionPane.showMessageDialog(null,"Number of tokens = " + totalNumOfTokens);
            
        
        paneDisplay(s,"String Token Output", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    static void paneDisplay(String info, String heading, int MESSAGE_TYPE)
    {
        JTextArea text = new JTextArea(info, 50,50);
        
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, heading, MESSAGE_TYPE);
        
    }
}
