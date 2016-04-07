/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytdlgui;

import gui.ytdlFRAME;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Pifko
 */
public class Connector {
    
    private gui.ytdlFRAME gui;
    private UIfunctions uifuncs;
    private ExecutorService exesrv;
    private ProcessHolder currentproc;
    
    public Connector(){

        try {
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        exesrv = Executors.newFixedThreadPool(1);
        currentproc = new ProcessHolder();
        
        gui = new ytdlFRAME();
        gui.setuiconfig();
        uifuncs = new UIfunctions(gui);
        ChechkFFmpeg();

        setListeners();
        gui.setVisible(true);

        
    }
    
    private void ChechkFFmpeg(){
    
        try {
            Process p = Runtime.getRuntime().exec("ffmpeg -version");
        } catch (IOException ex) {
            uifuncs.WriteToConsole("ffmpeg is not installed !");
        }
    
    }
    
    private void setListeners(){
    
        gui.getDlButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                ytdlFunctions.dlsong(uifuncs,exesrv,currentproc);
                
            }
        });
        
        gui.getStopdl().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                try {
                    //exesrv.shutdownNow();
                    currentproc.stopproc();
                    Thread.sleep(250);
                    uifuncs.ResetDlBar();
                    uifuncs.WriteToConsole("Download stopped by user."+"\n");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    
        gui.getInfoBTN().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                ytdlFunctions.ytinfos(uifuncs);
                
            }
        });
    
    }
    
}
