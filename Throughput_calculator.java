package comp40660;
/**
 * robert barnes
 * 14746631
 * comp40660 assignment 1
 * throughtPut calculator GUI
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Throughput_calculator {
	Calculator cal = new Calculator();
	public static String protocolValue;
	private static String standardValue;
	private static String dataValue;
	
	private JTextArea textAreaOut=new JTextArea();
	private JTextArea textAreaSet=new JTextArea();
	private JTextArea textAreabreak=new JTextArea();
	private JTextArea textAreabreakset=new JTextArea();
	private JFrame mainFrame; 
	private JPanel panel_one,Panel_two,Panel_three,Panel_four,Panel_five,Panel_six,panel_seven,panel_eight;
	private JLabel protocol;
	private JLabel rate;
	private JLabel standard;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JPanel controlPanel2;
	private JPanel controlPanel3;
	private  JButton button; 
	private ButtonGroup group = new ButtonGroup();
	private ButtonGroup group2 = new ButtonGroup();
	private JRadioButton udp ;
	private  JRadioButton tcp;
	private JRadioButton eight_02_11a;
	private JRadioButton eight_02_11g;
	private JRadioButton eight_02_11n;
	private JRadioButton eight_02_11ac_w1;
	private JRadioButton eight_02_11ac_w2;
	private String Bit_rate_one[]={"54","48","36","24","18","12","9","6"};   
	private String Bit_rate_two[]= {"96.3","86.7","72.2","65","57.8","43.3","28.9","21.7","14.4","7.2"};
	private final JComboBox<String>cb = new JComboBox<>();
	@SuppressWarnings("unchecked")
	private ComboBoxModel<String>[] models = new ComboBoxModel[2];
	


	public Throughput_calculator(){
		
		prepareGUI();
		
	}
	
	public static void main(String[] args){
		
		Throughput_calculator  TC = new Throughput_calculator();      
		TC.controls();
		
	}
	///////////////////////////////////////////////////////////////
	private void prepareGUI(){
		mainFrame = new JFrame("");
		//mainFrame = new JPanel();
		mainFrame.setSize(800,600);
		mainFrame.setLayout(new GridLayout(1, 3 ));
		panel_one = new JPanel();
    	panel_one.setBorder(BorderFactory.createTitledBorder("Controls"));
    	panel_one.setLayout(new GridLayout(7,1));
    	Panel_two = new JPanel();
    	Panel_two.setBorder(BorderFactory.createTitledBorder("Output"));
    	Panel_two.setLayout(new GridLayout(2,1));
    	Panel_three = new JPanel();
    	Panel_three.setLayout(new GridLayout(1,2));
    	Panel_four = new JPanel();
    	Panel_four.setBorder(BorderFactory.createTitledBorder("Breakdown"));
    	Panel_five = new JPanel();
    	Panel_six =  new JPanel();
    	panel_seven = new JPanel();
    	panel_eight =  new JPanel();
    	Panel_four.setLayout(new GridLayout(1,2));
    	Panel_five.setLayout(new GridLayout(1,1));
    	Panel_six.setLayout(new GridLayout(1,1));
    	panel_seven.setLayout(new GridLayout(1,1));
    	panel_eight.setLayout(new GridLayout(1,1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
		
		button = new JButton("Calculate");
		protocol = new JLabel("", JLabel.CENTER);  
		
		rate = new JLabel("",JLabel.CENTER);
		standard = new JLabel("",JLabel.CENTER);
		

		statusLabel = new JLabel("",JLabel.CENTER);    
		statusLabel.setSize(10,10);
		
		Panel_five.add(textAreaSet);
		Panel_six.add(textAreaOut);
		panel_seven.add(textAreabreakset);
		panel_eight.add(textAreabreak);
		textAreaOut.setEditable(false);
		textAreaSet.setEditable(false);
		controlPanel = new JPanel();
		

		controlPanel2 = new JPanel();
		
		controlPanel2.setLayout(new FlowLayout());


		controlPanel3 = new JPanel();
		
		
		controlPanel3.setLayout(new FlowLayout());
		mainFrame.add(panel_one);
    	mainFrame.add(Panel_two);
    	
    	Panel_two.add(Panel_three);
    	Panel_two.add(Panel_four);
    	Panel_three.add(Panel_five);
    	Panel_three.add(Panel_six);
    	Panel_four.add(panel_seven);
    	Panel_four.add(panel_eight);


    	panel_one.add(protocol);
    	panel_one.add(controlPanel);
    	panel_one.add(standard);
    	panel_one.add(controlPanel3);      
    	panel_one.add(rate); 
    	panel_one.add(controlPanel2);
    	panel_one.add(button);
		display();
		mainFrame.setVisible(true);  

	}
	/////////////////////////////////////////////////////////
	private void ComboBox() {
		Dimension preferredSize = cb.getPreferredSize();
		preferredSize.width = 50;
		cb.setPreferredSize(preferredSize);

		models[0] = new DefaultComboBoxModel<String>(Bit_rate_one);      
		models[1] = new DefaultComboBoxModel<String>(Bit_rate_two);
		cb.setModel(models[0]);

	}
	///////////////////////////////////////////////////////////////////////////   
	private void controls(){
		protocol.setText("Select Protocol"); 
		selectProtocol();


		standard.setText("Select Standard");
		selectStandard();

		rate.setText("Select Rate");
		ComboBox();

		calculate();

		//Group the radio buttons.
		

		group.add(udp);
		group.add(tcp);
		

		group2.add(eight_02_11a);
		group2.add(eight_02_11g);
		group2.add(eight_02_11n);
		group2.add(eight_02_11ac_w1);
		group2.add(eight_02_11ac_w2);

		controlPanel.add(udp);
		controlPanel.add(tcp);
		controlPanel2.add(cb);       
		controlPanel3.add(eight_02_11a); 
		controlPanel3.add(eight_02_11g); 
		controlPanel3.add(eight_02_11n); 
		controlPanel3.add(eight_02_11ac_w1); 
		controlPanel3.add(eight_02_11ac_w2); 
		
		mainFrame.setVisible(true);  
	}
	public void selectProtocol() {
		udp = new JRadioButton("UDP",true);
		udp.setActionCommand("udp");
		tcp = new JRadioButton("TCP");
		tcp.setActionCommand("tcp");

	}
	private void selectStandard(){
		eight_02_11a = new JRadioButton("802.11a",true);
		eight_02_11a.setActionCommand("802.11a");
		eight_02_11g = new JRadioButton("802.11g");
		eight_02_11g.setActionCommand("802.11g");
		eight_02_11n = new JRadioButton("802.11n");
		eight_02_11n.setActionCommand("802.11n");
		eight_02_11ac_w1 = new JRadioButton("802.11ac_w1");
		eight_02_11ac_w1.setActionCommand("802.11ac_w1");
		eight_02_11ac_w2 = new JRadioButton("802.11ac_w2");
		eight_02_11ac_w2.setActionCommand("802.11ac_w2");
		
		eight_02_11a.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {  
				cb.setModel(models[0]);
			}           
		});
		eight_02_11g.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {  
				cb.setModel(models[0]);
			}           
		});
		eight_02_11n.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {  
				cb.setModel(models[1]);
		}           
		});

		eight_02_11ac_w1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {  
				cb.setModel(models[1]);
			}           
		});
		eight_02_11ac_w2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {  
				cb.setModel(models[1]);
			}           
		});
	}

	public void calculate() {
		
		button.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent e) { 
				textAreaOut.setText("");
	            String str = "";
	            String str2 = "";
				if (e.getActionCommand().equals("Calculate")) 
				protocolValue = group.getSelection().getActionCommand();
				standardValue = group2.getSelection().getActionCommand();
				dataValue = cb.getItemAt(cb.getSelectedIndex()); 
					cal.Calculator( protocolValue, standardValue,dataValue);
				str = cal.results;
				str2 = cal.breakdown;
				displayString(str, str2);
				
			}
		});
	}
	public void displayString (String text, String text2) {
		Font font = new Font("Times Roman",Font.PLAIN, 15);
        textAreaOut.setFont(font);
		textAreaOut.setText(textAreaOut.getText()+""+ protocolValue+"\n\n"+standardValue+
				"\n\n"+dataValue +"\n"+text);
		
		textAreabreak.setText(text2);
		textAreabreakset.setText("Preamble:\nSIFS: \nDIFS:\nsymbols:\nSymbol Duration: "
				+ "\nData Frame Time:\nFrame size:");
	}
	public void display () {
		Font font = new Font("Times Roman", Font.BOLD, 15);
	   textAreaSet.setFont(font);
		textAreaSet.setText("Selected protocol:\n "
				+ "\nSelected standard:\n"
				+ "\nSelected Data rate:\n"
				
				+ "\nTime per pack: \n\nThroughput in Mbps: \n\nTotal Time in seconds:");
	}
	
	
		
	
}