import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import javax.swing.JFrame;
import java.io.File;

class Testinggsm {
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
SerialArduinoTest main = new SerialArduinoTest();
Testinggsm ts=new Testinggsm();
main.initialize();
//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
Thread t=new Thread() {
//int sensordata[]=new int[3];				
public void run() {
		//the following line will keep this app alive for 1000 seconds,
		//waiting for events to occur and responding to them (printing incoming messages to console).
		try {

Thread.sleep(2000);} catch (InterruptedException ie) {
ie.printStackTrace();
}
	}
};
System.out.println("Started");
System.out.println("Enter data to be transferred");
t.start();
int sensordata[]=new int[3];
//main.openFile();
do{
try{
file=new File("C:/Users/MOHSIN/Desktop/Documents/Documents/Project/Field project/output.txt");
main.openFile();
main.print("n");
System.out.println("n");
Thread.sleep(1000);
//sensordata[0]=24;
//main.transferValues();
main.print("p");
System.out.println("p");
Thread.sleep(1000);
//sensordata[1]=34;
//main.transferValues();
//Integer.parseInt(main.transferValues());
main.print("k");
System.out.println("k");
Thread.sleep(1000);
//sensordata[2]=56;
//main.transferValues();
//System.out.println("this "+sensordata[2]);
main.closeFile();
System.out.println("closing file");
Thread.sleep(1000);
ts.openFile();
System.out.println("opening  file for read");
sensordata[0]=Integer.parseInt(ts.br.readLine().trim());
sensordata[1]=Integer.parseInt(ts.br.readLine().trim());
sensordata[2]=Integer.parseInt(ts.br.readLine().trim());
System.out.println("Values Read");
ts.closeFile();
System.out.println("closed  file for read");
boolean stat=file.delete();
System.out.println("stat="+stat);
System.out.println("ready");
Thread.sleep(5000);
}catch(Exception e){
e.printStackTrace();
System.out.println("closing file exception");
ts.closeFile();
}
}
while(sensordata[0]<=0||sensordata[1]<=0||sensordata[2]<=0);
main.print("?");
main.print("hello his is  rocking the world this fhdk shfjsjhf shfjjsgf sf}");
}
}
