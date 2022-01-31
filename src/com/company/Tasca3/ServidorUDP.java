package com.company.Tasca3;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {
    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    boolean continueRunning = true;
    Llista llista;

    public ServidorUDP(int portValue, String strIp) throws IOException {
        socket = new MulticastSocket(portValue);
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        llista = new Llista();
    }


    public void runServer() throws IOException{
        DatagramPacket packet;
        byte [] sendingData;


        while(continueRunning){
            sendingData = llista.getParaula();
            packet = new DatagramPacket(sendingData, sendingData.length,multicastIP, port);
            socket.send(packet);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.getMessage();
            }


        }
        socket.close();
    }


    public static void main(String[] args) throws IOException {
        //Canvieu la X.X per un número per formar un IP.
        //Que no sigui la mateixa que la d'un altre company
        ServidorUDP server = new ServidorUDP(5557, "224.0.10.10");
        server.runServer();
        System.out.println("Parat!");

        }
    }


