package com.company.Tasca3;

import java.io.IOException;
import java.net.*;

public class ClientUDP {
    private boolean continueRunning = true;
    private MulticastSocket socket;
    private InetAddress multicastIP;
    private int port;
    NetworkInterface netIf;
    InetSocketAddress group;

    public ClientUDP(int portValue, String strIp) throws IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
        netIf = socket.getNetworkInterface();
        group = new InetSocketAddress(strIp,portValue);
    }

    public void runClient() throws IOException{

        Imprimeix monitor= new Imprimeix();

        DatagramPacket packet;
        byte [] receivedData = new byte[5];

        socket.joinGroup(group,netIf);
        System.out.printf("Connectat a %s:%d%n",group.getAddress(),group.getPort());

        while(continueRunning){
            packet = new DatagramPacket(receivedData, receivedData.length);
            socket.setSoTimeout(5000);
            try{
                socket.receive(packet);
                continueRunning = getData(packet.getData(),monitor);
            }catch(SocketTimeoutException e){
                System.out.println("S'ha perdut la connexió amb el servidor.");
                continueRunning = false;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            monitor.printMonitor();


        }

        socket.leaveGroup(group,netIf);
        socket.close();
    }

    protected  boolean getData(byte[] data, Imprimeix monitor) {
        boolean ret=true;

        String paraula= new String(data);
        monitor.add(paraula);

        //if (v==1) ret=false;

        return ret;
    }

    public static void main(String[] args) throws IOException {
        ClientUDP client = new ClientUDP(5557, "224.0.10.10");
        client.runClient();
        System.out.println("Parat!");

    }
}