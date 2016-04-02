/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytdlgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Pifko
 */
public class ytdlFunctions {
    
    public static void dlsong(final UIfunctions uifuncs, final ExecutorService exesrv, final ProcessHolder currentproc) {
    
        try {
            
            Runnable r = new Runnable() {

                @Override
                public void run() {
                    
                    try {
                        
                        String dlspeed = "100000K";
                        
                        if(uifuncs.Dlspeedcheck()){
                            try{
                                Integer.valueOf(uifuncs.Dlspeed());
                            }
                            catch(Exception c){
                                uifuncs.WriteToConsole("Wrong speed format."+"\n");
                                return;
                            }
                            dlspeed = uifuncs.Dlspeed()+"K";
                        }
                        
                        String[] cmd;
                        if(uifuncs.IsFormatCode() && !uifuncs.IsEmptyDropdownlist()){
                           String[] tempcm1 = {"youtube-dl.exe","-r",dlspeed, "-f", uifuncs.DetSelectedFormat(), uifuncs.GetYtdlLink()};
                           cmd = tempcm1;
                        }
                        else{
                           String[] tempcm2 = {"youtube-dl.exe","-r",dlspeed, "--extract-audio" ,"--audio-format", "mp3", uifuncs.GetYtdlLink()};
                           cmd = tempcm2;
                        }
                        
                        Process p = Runtime.getRuntime().exec(cmd);
                        currentproc.setP(p);
                        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        
                        String s = null;
                        uifuncs.ResetDlBar();
                        uifuncs.WriteToConsole("----------------------------------------------------" + "\n");
                        while((s = stdInput.readLine()) != null){
                            
                            if(s.contains("KiB/s ETA")){
                            
                                StringTokenizer st = new StringTokenizer(s," ");
                                st.nextToken();
                                String token = st.nextToken(); 
                                int perc = Math.round(Float.parseFloat((token.replace("%", ""))));
                                uifuncs.SetProgBar(perc);

                            }
                            
                            uifuncs.WriteToConsole(s+"\n");
                        }
                    } catch (IOException ex) {
                        uifuncs.WriteToConsole(ex.getMessage());
                        Logger.getLogger(ytdlFunctions.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
            exesrv.submit(r);
            //new Thread(r).start();
            
        } catch (Exception ex) {
            Logger.getLogger(ytdlFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void ytinfos(final UIfunctions uifuncs) {
    
        try {
            
            Runnable r = new Runnable() {

                @Override
                public void run() {
 
                    try {
                        uifuncs.ClearDropdownlist();
                        final String[] cmd = {"youtube-dl.exe", "-F", uifuncs.GetYtdlLink()};
                        Process p = Runtime.getRuntime().exec(cmd);
                        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        
                        String s = null;
                        uifuncs.WriteToConsole("----------------------------------------------------" + "\n");
                        while((s = stdInput.readLine()) != null){
                            
                            StringTokenizer st = new StringTokenizer(s," ");
                            String token = st.nextToken();
                            if(Character.isDigit(token.charAt(0)))
                                uifuncs.AddItemToDropdown(token);
                            uifuncs.WriteToConsole(s+"\n");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(ytdlFunctions.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
            new Thread(r).start();

        } catch (Exception ex) {
            Logger.getLogger(ytdlFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
}
