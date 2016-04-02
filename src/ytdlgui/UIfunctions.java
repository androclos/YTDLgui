/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ytdlgui;

import gui.ytdlFRAME;

/**
 *
 * @author Pifko
 */
public class UIfunctions {
    
    
    private final ytdlFRAME frame;
    
    public UIfunctions(ytdlFRAME frame){
    
        this.frame = frame;
    
    }
    
    public String GetYtdlLink(){
    
        return frame.getYtlinkTEXTFIELD().getText();
    
    }
    
    public void WriteToConsole(String s){
    
        frame.getConsole().append(s);
    
    }
    
    public void SetProgBar(int i){
    
        frame.getDlbar().setValue(i);
        frame.getDlbar().setString(String.valueOf(i) + "%");
    
    }
    
    public void ResetDlBar(){
    
        frame.getDlbar().setValue(0);
        frame.getDlbar().setString("0%");
    
    }
    
    public void ClearDropdownlist(){
    
        frame.getFormatCodeList().removeAllItems();
    
    }
    
    public void AddItemToDropdown(String item){
    
        frame.getFormatCodeList().addItem(item);
        
    }
    
    public boolean IsEmptyDropdownlist(){
    
        return !(frame.getFormatCodeList().getItemCount() > 0);
    
    }
    
    public String DetSelectedFormat(){
    
        return (String)frame.getFormatCodeList().getSelectedItem();
    
    }
    
    public boolean IsFormatCode(){
    
        return frame.getFormatCodeBox().isSelected();
    
    }
    
    public String Dlspeed(){
    
        return frame.getDlspeedlimit().getText();
    
    }
    
    public boolean Dlspeedcheck(){
    
        return frame.getLimitspeedcheck().isSelected();
    
    }
    
}
