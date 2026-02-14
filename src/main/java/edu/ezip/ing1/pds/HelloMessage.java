package edu.ezip.ing1.pds;
public class HelloMessage{
    private String IP;
    private int port;
    public HelloMessage(String IP,int port){
        this.IP=IP;
        this.port=port;
    }
    public String getIP(){
        return IP;
    }
    public int getPort(){
        return port;
    }
    public void setIP(String IP){
        this.IP = IP;
    }
    public void setPort(int port){
        this.port= port;
    }
    public String toString(){
        return "HELLO\n" + IP + "\n" + port;
    }

        }