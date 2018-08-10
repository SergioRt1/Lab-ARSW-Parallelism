/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;

/**
 *
 * @author 2135494
 */
public class ThreadValidator extends Thread{
    
    private HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    
    private int ocurrencesCount;
    private LinkedList<Integer> blackListOcurrences;
    private int checkedListsCount;
    private int inicio;
    private int fin;
    private String ipaddress;
    
    public ThreadValidator(int inicio,int fin, String ipaddress){
        super();
        this.inicio = inicio;
        this.fin = fin;
        this.ipaddress = ipaddress;
        this.blackListOcurrences = new LinkedList<>();
    }
    
    @Override
    public void run() {
        checkedListsCount = 0;
        ocurrencesCount = 0;
        for(int i = inicio; i < fin; i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, ipaddress)){
                
                blackListOcurrences.add(i);
            
                ocurrencesCount++;
            }
        }
    }
    
    public LinkedList<Integer> getBlackListOcurrences(){
        return blackListOcurrences;
    }
    
    public int getOcurrences(){
        return ocurrencesCount;
    }

    public int getCheckedListsCount() {
        return checkedListsCount;
    }
    
}
