/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.de.pd.directoria;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Rafael
 */
public class TrabalhoDePDDirectoria {

    static final int port = 7000;
    static final int timeToWait = 5000;
    static String EndIP;
    static boolean exec = true;
    
    /**
     * @param args the command line arguments
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws UnknownHostException, IOException, SocketException, InterruptedException{
        receberIP();
        enviarIP();
    }
    
    public static void receberIP() throws SocketException, UnknownHostException, IOException{      
        byte[] receiveData = new byte[1024];
        
        System.out.println("A iniciar servidor de directoria...");

        try (DatagramSocket clientSocket = new DatagramSocket(port)) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            
            EndIP = receivePacket.getAddress().toString();
        }
        
        System.out.println("O IP do servidor primário é " + EndIP);
    }
    
    public static void enviarIP() throws SocketException, IOException, InterruptedException{
        do{
            byte[] receiveData = new byte[1024];
            String temp;
            
            System.out.println("A procura de clientes...");
            
            DatagramSocket clientSocket = new DatagramSocket(port);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);    
            
            System.out.println("Encontrado um cliente...");
            
            temp = new String(receivePacket.getData());
            
            Thread.sleep(timeToWait);
        }while(exec);                                        
    }

}
