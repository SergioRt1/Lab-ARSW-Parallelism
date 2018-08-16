/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;
/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    private static Thread hilo1;
    private static Thread hilo2;
    private static Thread hilo3;
    public static void main(String a[]){
        hilo1 = new Thread(new CountThread(0, 99));
        hilo2 = new Thread(new CountThread(99 , 199));
        hilo3 = new Thread(new CountThread(200, 299));
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
    
}
