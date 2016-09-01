//modified password,comment line 59 cl.show(card,form);s->sensor,?->gsm mode,} terminate string
//import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
/*@ Author Moss     
 * 
 * Protected by BSD license   */
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import oracle.jdbc.pool.OracleDataSource;
class IDESec extends JFrame implements ActionListener,ItemListener{
int sn_value,sp_value,sk_value;
int an_value,ap_value,ak_value;
int reqn_value,reqp_value,reqk_value;
//int temp[];
SerialTest gsm;
JTextField tprecrop; 
	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	    String userid = "moss";
	    String password = "moss"; 
	JPasswordField passf;
JPanel card1,card2,cards,card3back,card3card,card31,card32;
CardLayout cl;
String farmcrop;
JTextField tname;
JTextField tid;
Connection conn;
Statement stmt;
ResultSet rs;
int checkChoice=0;
JTextField tfarmcrop;
String sensorcrop;
public IDESec(String s){
	super(s);
}

boolean checkPasswrd(char[] input){
	boolean a=false;
	String pass="moss";
	if(pass.compareTo(String.valueOf(input))==0)
		a=true;
		return a;
		}
void transferMain(SerialTest main){
System.out.println("Gsm");
gsm=main;
}
public void actionPerformed(ActionEvent ae){
	//System.out.println("Sucess1");
	if(ae.getActionCommand().compareTo("Login")==0){
		if(checkPasswrd(passf.getPassword())){
			//System.out.println("Sucess");
			JLabel l = new JLabel("Sucess");
            JOptionPane.showMessageDialog(null, l);
          //cl.show(cards, "Form");
try{
	getDBConnection();
	}
catch(Exception e){
e.printStackTrace();
System.out.println(e);
System.out.println("Unable to connect to database");}  
}	
else{
	System.out.println("Fail");
	JLabel l = new JLabel("Incorrect Password");
            JOptionPane.showMessageDialog(null, l);
	}
	}
if(ae.getActionCommand().compareTo("Proceed")==0){
	if(checkChoice==1){
	try{
	calculationFarmCrop();
}catch(SQLException s){
s.printStackTrace();
}	
}else{
		try{
	calculationFarmCropNo();
}catch(SQLException s)
{
s.printStackTrace();
}}	
fertilizercal();
try{closeDBConnection();}catch(Exception e){System.out.println("closing db connection");
e.printStackTrace();
}
	}
		
	
}
void getDBConnection() throws SQLException{
OracleDataSource ds = new OracleDataSource();
ds.setURL(jdbcUrl);
  conn = ds.getConnection(userid, password);
 stmt=conn.createStatement();
cl.show(cards, "Form");
calculationSensorCrop();
}
void closeDBConnection() throws SQLException{
	rs.close();
	stmt.close();
	conn.close(); 
	//System.out.println("Connection destroyed");
}
void calculationFarmCropNo() throws SQLException{
	
	stmt=conn.createStatement();
	String query="select n_value,p_value,k_value from crop_info where crop_name='"+sensorcrop+"'";
	rs=stmt.executeQuery(query);
while(rs.next()){an_value=rs.getInt(1);
ap_value=rs.getInt(2);
ak_value=rs.getInt(3);}System.out.println("an_value="+an_value);
System.out.println("ap_value="+ap_value);
System.out.println("ak_value="+ak_value);
}

void calculationFarmCrop() throws SQLException{
farmcrop=tfarmcrop.getText();
stmt=conn.createStatement();
String query="select n_value,p_value,k_value from crop_info where crop_name='"+farmcrop+"'";
rs=stmt.executeQuery(query);
while(rs.next()){an_value=rs.getInt(1);
ap_value=rs.getInt(2);
ak_value=rs.getInt(3);}
System.out.println("an_value="+an_value);
System.out.println("ap_value="+ap_value);
System.out.println("ak_value="+ak_value);
}

void fertilizercal(){
	
reqn_value=an_value-sn_value;
reqp_value=ap_value-sp_value;
reqk_value=ak_value-sk_value;
if(reqn_value<0)
reqn_value=0;
if(reqp_value<0)
reqp_value=0;
if(reqk_value<0)
reqk_value=0;
System.out.println("objective finished");
System.out.println("reqn_value"+reqn_value);
System.out.println("reqp_value"+reqp_value);
System.out.println("reqk_value"+reqk_value);
try{
gsmSection();
}catch(Exception e){e.printStackTrace();}
}
void gsmSection() throws Exception{
String farmer_name=tname.getText();
int farmer_id=Integer.parseInt(tid.getText());
//SerialTest gsm=new SerialTest();
String s="select address from farm_info where farm_id="+farmer_id;
rs=stmt.executeQuery(s);
String address="";
while(rs.next()){address=rs.getString(1);}
System.out.println("address="+address);
s="F n= "+farmer_name+" F a= "+address+" n_v= "+reqn_value+" p_v= "+reqp_value+" k_v= "+reqk_value+"}";
//s="hello his is  rocking the world this fhdk shfjsjhf shfjjsgf sf}";
System.out.println("Started");
System.out.println("Enter data to be transferred");
gsm.print("n");
Thread.sleep(1000);
gsm.print("p");
Thread.sleep(1000);
gsm.print("k");
Thread.sleep(1000);
System.out.println("ready");
Thread.sleep(5000);
gsm.print("?");
Thread.sleep(1000);
gsm.print(s);
}
void retriveData(int temp1[]){
	sn_value=temp1[0];
sp_value=temp1[1];
sk_value=temp1[2];
System.out.println("sn_value"+sn_value);
System.out.println("sp_value"+sp_value);
System.out.println("sk_value"+sk_value);
}
void calculationSensorCrop() throws SQLException{
	//sn_value=23;sp_value=97;sk_value=76;
	//int temp[]=new int[3];
//temp=retriveData;
int error=5;
do{String query="select crop_name from crop_info where ((n_value between "+(sn_value-error)+" and "+(sn_value+error)+" )and(p_value between "+(sp_value-error)+" and "+(sp_value+error)+")) or ((n_value between "+(sn_value-error)+" and "+(sn_value+error)+" )and(k_value between "+(sk_value-error)+" and "+(sk_value+error)+")) or ((p_value between "+(sp_value-error)+" and "+(sp_value+error)+" )and(k_value between "+(sk_value-error)+" and "+(sk_value+error)+"))";
	//return(query);
	//System.out.println(query);
	error=error+5;
	try{rs=stmt.executeQuery(query);}catch(Exception e){System.out.println("calculating sensor crop error");
e.printStackTrace();}}while(!rs.next());
	sensorcrop=rs.getString(1);
    tprecrop.setText(sensorcrop);
}
void createCard1(){
		card1=new JPanel(new GridLayout(4,2));
		JLabel label1=new JLabel("User Name");
		JLabel label2=new JLabel("Password");
		JTextField usrn=new JTextField();
		passf= new JPasswordField();
		JButton jb=new JButton("Login");
		card1.add(label1);
		card1.add(usrn);
		card1.add(label2);
		card1.add(passf);
		card1.add(jb);
	jb.addActionListener(this);
	}
	void createCard2() throws Exception{
	card2=new JPanel();
		JLabel l1=new JLabel("Connecting to database");
		final URL url = new URL("file:///C:/ajax-loader.gif");
		JLabel l = new JLabel(new ImageIcon(url));
		card2.add(l,BorderLayout.WEST);
         card2.add(l1,BorderLayout.CENTER);	
         
	}
	
	void createCard3(){
	card3back = new JPanel(new GridLayout(2,1));
		
	JPanel card31=new JPanel(new GridLayout(2,2));
	JLabel l1=new JLabel("Farmer Crop");
	 tfarmcrop= new JTextField();
	JButton jb1=new JButton("Proceed");
	card31.add(l1);
	card31.add(tfarmcrop);
	card31.add(jb1);
	jb1.addActionListener(this);
	JPanel card32=new JPanel(new GridLayout(1,2));
	JLabel l2=new JLabel("");
JButton jb=new JButton("Proceed");
card32.add(l2);
card32.add(jb);	
pack();
jb.addActionListener(this);	
JPanel card3cardback=new JPanel();
	card3card = new JPanel(new CardLayout());	
	//cl1.addLayoutComponent(card31, "Label1");
	//cl1.addLayoutComponent(card32, "Label2");
	card3card.add(card32,"Label2");
	card3card.add(card31,"Label1");
card3cardback.add(card3card,BorderLayout.PAGE_END);	
	JPanel card3=new JPanel(new GridLayout(5,2));
	card3.setSize(100,100);
	//JLabel choice = new JLabel("Enter yr choice");
	//JTextField tchoice= new JTextField();
	 JLabel name =new JLabel("Enter Name");
	 JLabel id=new JLabel("Farmer ID");
	JLabel precrop=new JLabel("Preferred Crop");
	 tname=new JTextField();
	 tid=new JTextField();
 tprecrop=new JTextField();
	JLabel w2c=new JLabel("Want to continue?");
	String comboBoxItems[] = {"NO","YES" };
    JComboBox cb = new JComboBox(comboBoxItems);
	card3.add(name);
	card3.add(tname);
	card3.add(id);
	card3.add(tid);
	card3.add(precrop);
	card3.add(tprecrop);
	card3.add(w2c);
	card3.add(cb);
	//card3.add(choice);
	//card3.add(tchoice);
	cb.setEditable(false);
    cb.addItemListener(this);
    card3back.add(card3,BorderLayout.SOUTH);
	card3back.add(card3cardback,BorderLayout.NORTH);
	//CardLayout cl2 = (CardLayout)(card3card.getLayout());
	//cl2.show(card3card,"Label2");
	//card3cardback.setVisible(true);
	//card3card.setVisible(true);
	}
	public void itemStateChanged(ItemEvent ie) {
		String a=(String)ie.getItem();
		System.out.println(a);
		
		try{
		 CardLayout cl2 = (CardLayout)(card3card.getLayout());
		
		 System.out.println("hello");
		if(a.compareTo("YES")==0){
		checkChoice=1;
			//	System.out.println(a);
	     cl2.show(card3card,"Label1");
		}
		else{ checkChoice=0;
			cl2.show(card3card,"Label2");}
	}catch(Exception e){
		e.printStackTrace();	
	}
	
	}
	
	void createGUI(Container pane)throws Exception{
		createCard1();
		try{
		createCard2();
		}catch(Exception e){
			e.printStackTrace();	
		}
		createCard3();
		cards = new JPanel(new CardLayout());
		
		cards.add(card1,"Login");
        cards.add(card2, "Connecting");
        cards.add(card3back,"Form");
	    pane.add(cards);
	     //cl = (CardLayout)(cards.getLayout());
        //cl.show(cards,"Connecting");
	    cl = (CardLayout)(cards.getLayout());
        cl.show(cards, "Login");
	   
	    
	}
	
	


}
