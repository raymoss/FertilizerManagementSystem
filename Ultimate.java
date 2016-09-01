import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import javax.swing.JFrame;
import java.io.File;

import javax.swing.JFrame;


class Ultimate{
FileReader fin;
BufferedReader br;
void openFile() throws Exception{
fin=new FileReader("output.txt");
br=new BufferedReader(fin);
}
void closeFile() throws Exception{
br.close();
fin.close();
}
public static synchronized void main(String[] args) throws Exception{
	// TODO Auto-generated method stub
File file;
Ultimate ts=new Ultimate();
IDESec a=new IDESec("Ferman");
SerialTest main = new SerialTest();
main.initialize();
Thread t=new Thread() {
//int sensordata[]=new int[3];				
public void run() {
		//the following line will keep this app alive for 1000 seconds,
		//waiting for events to occur and responding to them (printing incoming messages to console).
		try {

Thread.sleep(1000);} catch (InterruptedException ie) {
ie.printStackTrace();
}
	}
};
System.out.println("Started");
System.out.println("Enter data to be transferred");
t.start();
		
int sensordata[]=new int[3];
main.openFile();
do{
try{
main.openFile();
main.print("n");
System.out.println("n");
Thread.sleep(1000);
main.print("p");
System.out.println("p");
Thread.sleep(1000);
main.print("k");
System.out.println("k");
Thread.sleep(1000);
main.closeFile();
System.out.println("closing file");
Thread.sleep(1000);
ts.openFile();
System.out.println("opening  file for read");
sensordata[0]=Integer.parseInt(ts.br.readLine());
sensordata[1]=Integer.parseInt(ts.br.readLine());
sensordata[2]=Integer.parseInt(ts.br.readLine());
System.out.println("Values Read");
ts.closeFile();
System.out.println("closed  file for read");
file=new File("C:/Users/MOHSIN/Desktop/Documents/Documents/Project/Field project/output.txt"); //made change

boolean stat=file.delete();
System.out.println("stat="+stat);
}catch(Exception e){
e.printStackTrace();
System.out.println("closing file exception");
ts.closeFile();
}
}
while(sensordata[0]<=0||sensordata[1]<=0||sensordata[2]<=0);
a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
a.createGUI(a.getContentPane());
a.pack();
a.setVisible(true);
System.out.println("Sucessmain");
System.out.println("Printing sensor value");
for(int i=0;i<3;i++){
System.out.println(sensordata[i]);
}
a.retriveData(sensordata);
a.transferMain(main);
}
}
