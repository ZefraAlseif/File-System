/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author carlo
 */
public class BasicFile {
    File f;
    String s = "Files and Directories in the path of the file";

    public BasicFile() //Structure that allows the user to use the dialog box and select a file
    {
        JFileChooser choose = new JFileChooser(".");
        int status = choose.showOpenDialog(null);
        
        try 
        {
            if ( status != JFileChooser.APPROVE_OPTION) throw new IOException();
            f = choose.getSelectedFile(); 
            if (!f.exists()) throw new FileNotFoundException();
            
            display(f.getName(), "File has been choosen", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (FileNotFoundException e)
        {
            display("File not found ....", e.toString(), JOptionPane.WARNING_MESSAGE);
        }
        catch(IOException e)
        {
            display("Approved option was not selected", e.toString(), JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    
    public void display(String msg, String s, int t)
    {
        JOptionPane.showMessageDialog(null, s, msg, t);
    }
        
    void bufferedTime() throws IOException //Class that provides name and size of the file
    {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f), 4096);
        double calc = f.length();
        JOptionPane.showMessageDialog(null, f.getName() + " - " +f.getAbsolutePath()+ " - "+ (calc /1000) + " kilobytes");
        long startTime = System.nanoTime();
        while(bis.read() != -1)
            ;
        
        long endTime = System.nanoTime();
        fileInfo(); // sends to check the line number of a file and its path files/directories
        System.out.println("Time elapse when buffered " + (endTime - startTime)/1000000.0 + " msec");
    }

    void fileInfo() throws FileNotFoundException, IOException 
    {
        // Scans the file to check how many lines the file contains
        int count = 0;
        Scanner sc2 = new Scanner(new FileInputStream(f.getAbsolutePath()));
        while(sc2.hasNextLine()) {
        String line = sc2.nextLine();
        count = count+1;
        }
        JOptionPane.showMessageDialog(null,"The total number of lines in the file: "+count);
        
        // Locates the directory of the File aka. parent of file
        File dir = new File(f.getParent());
        listRecursive(dir);
          
    }
    
    public void listRecursive(File dir) // Class that finds the information pertaining to the directory
    {
        if (dir.isDirectory())
        {
            File [] d = dir.listFiles();
            for (File i: d)
            {
                if (i.isFile())
                    s = s + "\n" + ("\tFile: " + i.getAbsoluteFile() + "\t"+ i.length() + " bytes");
                else
                {
                    s = s + "\n" + ("Directory: "+ i.getName());
                    listRecursive(i);
                }
            }
           
        }
        
    }
    
    public void paneDisplay(String info, String heading, int MESSAGE_TYPE) // Display information in a JScroll Pane
    {
        JTextArea text = new JTextArea(info, 50,50);
        
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, heading, MESSAGE_TYPE);
        
    }
    
     public String getInfo() // Returns information about string 
     {
         return s;
     }
     
     public String getAbsPath() // Returns the path of the selected file
     {
         return f.getAbsolutePath();
     }
     
     public String getName() //Returns the name of the file
     {
         return f.getName();
     }
     
     public void restartData() //clears the data of the string in order to re-use it.
     {
         s = "Files and Directories in the path of the file";
     }
}