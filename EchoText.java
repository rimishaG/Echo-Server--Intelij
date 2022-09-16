package language.Networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoText  extends Thread{
    private Socket socket;
    public EchoText(Socket socket){
        this.socket = socket;


    }

    @Override
    public void run() {
       try{
           BufferedReader input = new BufferedReader(
                   new InputStreamReader(socket.getInputStream()));
           PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
           while(true){
               String echoString = input.readLine();
               if(echoString.equals("exit")){
                   break;
               }
               output.println("Echo from server: "+ echoString);

           }

       }catch(IOException e){
           System.out.println("Oops : " + e.getMessage());
       }
       finally{
           try{
               socket.close();
           }catch(IOException e){
               System.out.println("Exception happened while closing the socket");
           }

       }
    }


}
