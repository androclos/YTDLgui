/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytdlgui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pifko
 */
public class YTDLgui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                                           
               
                Connector c = new Connector();
                
            }
        });  
        
    }
    
}
