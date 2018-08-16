/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 2135494
 */
public class ThreadValidator extends Thread{
    
    private HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    
    private static final int BLACK_LIST_ALARM_COUNT=5;

    private AtomicInteger ocurrencesCount;
    private LinkedList<Integer> blackListOcurrences;
    private int checkedListsCount;
    private int inicio;
    private int fin;
    private String ipaddress;
    
    public ThreadValidator(int inicio,int fin, String ipaddress, AtomicInteger ocurrencesCount){
        super();
        this.inicio = inicio;
        this.fin = fin;
        this.ipaddress = ipaddress;
        this.blackListOcurrences = new LinkedList<>();
        this.ocurrencesCount = ocurrencesCount;
    }
    
    @Override
    public void run() {
        checkedListsCount = 0;
        for(int i = inicio; i < fin; i++){
            checkedListsCount++;
            if(ocurrencesCount.get() >= BLACK_LIST_ALARM_COUNT) break;
            if (skds.isInBlackListServer(i, ipaddress)){
                
                blackListOcurrences.add(i);
            
                ocurrencesCount.incrementAndGet();
                
            }
        }
    }
    
    public LinkedList<Integer> getBlackListOcurrences(){
        return blackListOcurrences;
    }

    public int getCheckedListsCount() {
        return checkedListsCount;
    }
    
}
