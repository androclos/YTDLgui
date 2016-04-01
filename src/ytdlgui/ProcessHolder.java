/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytdlgui;

/**
 *
 * @author Pifko
 */
public class ProcessHolder {
    
    private Process p;

    public void setP(Process p) {
        this.p = p;
    }
    
    public void stopproc(){
    
        p.destroy();
        
    }
    
}
