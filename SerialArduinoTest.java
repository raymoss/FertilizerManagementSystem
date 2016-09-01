import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.io.*;


public class SerialArduinoTest implements SerialPortEventListener {
boolean status=false;
FileWriter fw;
BufferedWriter bw;
byte buffer[] = new byte[32768];
  int bufferIndex;
  int bufferLast;	
SerialPort serialPort;char a;

        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM5", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	//private BufferedReader inputConsole;
              private InputStream inputArduino;
	/** The output stream to the port */
	private OutputStream output;
private PrintStream printWrapper;	
/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			inputArduino= serialPort.getInputStream();
			output = serialPort.getOutputStream();
                                      //    inputConsole=new BufferedReader(new InputStream(System.in));
			printWrapper = new PrintStream(output);			
			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */

	public synchronized void serialEvent(SerialPortEvent oEvent) {
	
if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			
try {
while (inputArduino.available() > 0) {	
synchronized (buffer) {			
if (bufferLast == buffer.length) {
              byte temp[] = new byte[bufferLast << 1];
              System.arraycopy(buffer, 0, temp, 0, bufferLast);
              buffer = temp;
            }
a=(char)inputArduino.read();
if(status==true)
bw.append(a);
System.out.print(a);
}}}catch(Exception e){
e.printStackTrace();
}}}
void openFile() throws Exception{
fw=new FileWriter("output.txt",true);
bw=new BufferedWriter(fw);
status=true;}
void closeFile() throws Exception{
status=false;
bw.close();
fw.close();
}
public int available() {
    return (bufferLast - bufferIndex);
  }

void transferValues(){

}
public void print(String s){
		printWrapper.print(s);
	}

/*	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		main.initialize();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Thread t=new Thread() {
						
public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {

Thread.sleep(100000);} catch (InterruptedException ie) {}
			}
		};
System.out.println("Started");
System.out.println("Enter data to be transferred");

main.print(br.readLine());		
t.start();
		
                
	}*/
}
