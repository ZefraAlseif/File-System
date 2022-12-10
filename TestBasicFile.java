/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.system;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author carlo
 */
public class TestBasicFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        
        // Starting objects and Program
        JOptionPane.showMessageDialog(null, "Choose a file from the computer");
        Input in = new Input();
        Search sh = new Search();
        BasicFile f = new BasicFile();
        boolean done = false;
        
       
        while (!done)
        {
            // Dialog Box to Look for a file
            String menu = "Enter option\n1. File attributes\n2. Copy the File\n3. Append/Overwrite File\n4. Display content of File\n5. Search for a string\n6. Tokenize the File\n7. Select different File\n10. Quit";
            String s = JOptionPane.showInputDialog( menu);
            try
            {
                int i = Integer.parseInt(s);
                switch(i)
                {
                    
                    case 1: //Retrieve the attributes of the selected file
                        f.bufferedTime();
                        String st = f.getInfo();
                        f.paneDisplay(st, "File Details", JOptionPane.INFORMATION_MESSAGE);
                        f.restartData();
                        break;
                    case 2: //Copying a file by taking the selected file and copying to a file name of the user's choosing
                        String rec = f.getAbsPath();
                        String t = JOptionPane.showInputDialog("Enter where the file name that you would like to give the copy");
                        in.copyFile(rec,t);
                        break;
                    case 3: //Write to an existing file either by overwritting it or appending to it
                        in.writeFile(f.getAbsPath());
                        break;
                    case 4: //Display the selected file 
                        in.displayContent(f.getAbsPath());
                        break;
                    case 5: //Find a specific string in the file (Ignore Case)
                        sh.findString(f.getAbsPath());
                        break;
                    case 6: // Tokenize the file 
                        sh.tokenFile(f.getAbsPath());
                        break;
                    case 7: // Choose a different File
                        main(args);
                        f = new BasicFile();
                        break;
                    case 10: // Exit the program
                        done = true;
                        System.exit(0);
                        break;
                    default: // Error 
                        display("This option is underfined", "Error");
                        break;
                }
                
            }
            catch( NumberFormatException | NullPointerException | IOException e)
            {
                display(e.toString(), "Error");
            }
    }
}
    static void display(String s, String err)
    {
        JOptionPane.showMessageDialog(null, s, err, JOptionPane.ERROR_MESSAGE);
    }
    
}

       
    
