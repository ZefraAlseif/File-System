/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.system;

import static file.system.TestBasicFile.display;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author carlo
 */
public class Input {
       
    public void copyFile(String s, String d) throws FileNotFoundException, IOException // Class that allows for the copy of the selected file
    {
        // Define Input and Output stream
        InputStream is = null;
        OutputStream os = null;
        try // Give locations and then it writes the input data to the output file
        {
            is = new FileInputStream(s);
            os = new FileOutputStream(d);
            byte[] buffer = new byte [1024];
            int length;
            while ((length = is.read(buffer))> 0)
            {
                os.write(buffer,0,length);
            }
        }finally {
            is.close();
            os.close();
        }
    
JOptionPane.showMessageDialog(null,"Copy was succesful");
    }
     
    public void writeFile(String o) // Class use to overwrite file or append it
    {
        String menu = "Enter option\n1. Appending to a Text File\n2. Over-writing a file" ;
        String s = JOptionPane.showInputDialog( menu);
                
                int i = Integer.parseInt(s);
                switch(i)
                {
                    case 1:  // Use a JOptionPane to enter the information that will then be inserted in the file
                        try 
                        {
                        String t = o;
                        String data = JOptionPane.showInputDialog("Enter information");
                        File f1 = new File(t);
                        if(!f1.exists())
                        {
                            f1.createNewFile();
                        }
                        FileWriter fileWriter = new FileWriter(f1.getName(),true);
                        BufferedWriter bw = new BufferedWriter(fileWriter);
                        bw.write("\n"+data);
                        bw.close();
                } catch(IOException e) {
                    e.printStackTrace();
}
                        JOptionPane.showMessageDialog(null,"Appending file was succesful");
                        break;
                    case 2: //Perform the same thing bu this time because FileWriter is false it will overwrite the information
                        try
                        {
                        String data = JOptionPane.showInputDialog("Enter information that you want to use to overwrite file");
                        File f1 = new File(o);
                        FileWriter fileWriter = new FileWriter(f1.getName(),false);
                        BufferedWriter bw = new BufferedWriter(fileWriter);
                        bw.write(data);
                        bw.close();
                        } catch(IOException e) {
                    e.printStackTrace();
}
                        JOptionPane.showMessageDialog(null,"Over-write file was succesful");
                        break;
}   
                
            }
    
    public void displayContent(String f) throws FileNotFoundException, IOException
    {
        JTextArea text = new JTextArea(50,50);
        text.read(new BufferedReader(new FileReader(f)), null);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, "File Content", JOptionPane.INFORMATION_MESSAGE);
    }
                
    }
