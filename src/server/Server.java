package server;

import other.Packet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;


public class Server {

	private ArrayList <Socket> clientList;
	private ArrayList <ObjectOutputStream> objectOutList;
     	private ServerSocket server; 
	public Server() throws IOException {
	
		
       		clientList = new ArrayList<Socket>();
       		objectOutList = new ArrayList<ObjectOutputStream>();
		server = new ServerSocket(8080);
		new Thread ( new ConnectionThread()).start();

	}

	private class ThreadClass implements Runnable{

		private ObjectInputStream in;
		

		public ThreadClass(ObjectInputStream in){
			
			this.in = in;


		}
	
		@Override
		public void run(){
			while(true){

				try{
					Packet pk;
					
					pk = (Packet) in.readObject();
					for(int i=0;i<objectOutList.size();i++){
						objectOutList.get(i).writeObject(pk);
					}
				}catch(Exception e){e.printStackTrace();}
			}
		}
	}


	private class ConnectionThread implements Runnable{

		@Override
		public void run(){
			while(true){
				
				try{
		
					clientList.add(server.accept());
					objectOutList.add(new ObjectOutputStream(clientList.get(clientList.size()-1).getOutputStream()));
					new Thread(new ThreadClass(new ObjectInputStream(clientList.get(clientList.size()-1).getInputStream()))).start();
				}catch(Exception e){e.printStackTrace();}
			}
		}
	}
}
