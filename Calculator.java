package comp40660;
/**
 * robert barnes
 * 14746631
 * comp40660 assignment 1
 * throughtPut calculator
 */

import java.text.DecimalFormat;

import javax.swing.JTextArea;

public class Calculator {
	DecimalFormat dec = new DecimalFormat("##.00");
	private static final String IEEE_802_11a = "802.11a";
	private static final String IEEE_802_11g = "802.11g";
	private static final String IEEE_802_11n = "802.11n";
	private static final String IEEE_802_11ac_w1 = "802.11ac_w1";
	private static final String IEEE_802_11ac_w2 = "802.11ac_w2";
	private static final String UDP = "udp";
	private static final String TCP = "tcp";
	final int ACK = 4;    
	final int tcp_ACK = 24; 
	final int Slot_Time = 9; //9 μs
	private String protocol;
	private double data_Frame_Time;
	private static double Mbps;
	private double data_Rate;
	String results,breakdown;
	private static String standard;
	private static int nChan;
	private double throughput;
	private int difs;
	private double time = 0.0;
	private static double preamble;
	private int SIFS;
	private final double _10gb_package = 10*8000;
	private double total_send_time;




/**
 * get difs method.
 * calculation from lecture slides -> DIFS=(2*Slot Time) + SIFS 
 * @param sifs
 * @return
 */
	private int getdifs(int SIFS) {
		int d = (2*Slot_Time)+SIFS;
		return d;
	}
/**
 * method to calculate time taken to transfer one packet 
 * depending on protocol chosen
 * @param DIFS
 * @param data_Frame_Time
 * @param protocol
 * @return
 */
	private double timePerPacket(int DIFS, double data_Frame_Time ) {
		switch(protocol) {
		case UDP:  	 
			this.time = DIFS + preamble + data_Frame_Time + SIFS + preamble + ACK;
			break;

		case TCP:
			this.time = DIFS + data_Frame_Time + SIFS + ACK + difs + tcp_ACK + SIFS + ACK;
		}
		return time;
	}
/**
 * depending on the stanadrd.
 * 11a/11g ->Using OFDM each frame has 6 bits (tail) appended
 * n/wc_1/wc_2 extra bits included for MAC header 
 * @param standard
 * @return
 */
	public static int getbits(String standard) {
		int nbits = 0;
		switch(standard) {
		case IEEE_802_11a:  	 
		case IEEE_802_11g:
			nbits = 8 *(1542)+ 6;
			break;
		default:
			nbits = 8 *(1542 + 6)+ 6; 
		}    
		return nbits;	
	}
	
	/**
	 * Number of bits per  OFDM symbol 
	 * nChan -> number of sub channels
	 * 11a/g nchan = 48
	 * otherwise nChan = 52 
	 * @param Mbps
	 * @param standard
	 * @return
	 */
	public static double getDataBitsPerSymbol(double Mbps, String standard){
		double dataBitsPerSymbol = 0.0;
		switch(standard) {  	 
		case IEEE_802_11a:
		case IEEE_802_11g:
			nChan = 48;
			if(Mbps==6){dataBitsPerSymbol = dataBitsPerOdfm(1,0.5,nChan);
			}if(Mbps==9){dataBitsPerSymbol = dataBitsPerOdfm(1,0.75,nChan);
			}if(Mbps==12){dataBitsPerSymbol = dataBitsPerOdfm(2,0.75,nChan);
			}if(Mbps==18){dataBitsPerSymbol = dataBitsPerOdfm(2,0.75,nChan);
			}if(Mbps==24){dataBitsPerSymbol = dataBitsPerOdfm(4,0.5,nChan);
			}if(Mbps==36){dataBitsPerSymbol = dataBitsPerOdfm(4,0.75,nChan);
			}if(Mbps==48){dataBitsPerSymbol = dataBitsPerOdfm(6,0.66,nChan);
			}if(Mbps==54){dataBitsPerSymbol = dataBitsPerOdfm(6,0.75,nChan);
			}
			break;
		case IEEE_802_11n:
		case IEEE_802_11ac_w1:
		case IEEE_802_11ac_w2:
			nChan = 52;
			if(Mbps == 7.2){dataBitsPerSymbol = dataBitsPerOdfm(1,0.5,nChan);
			}if(Mbps == 14.4){dataBitsPerSymbol = dataBitsPerOdfm(2,0.5,nChan);
			}if(Mbps == 21.7){dataBitsPerSymbol = dataBitsPerOdfm(2,0.75,nChan);
			}if(Mbps == 28.9){dataBitsPerSymbol = dataBitsPerOdfm(4,0.5,nChan);
			}if(Mbps == 43.3){dataBitsPerSymbol = dataBitsPerOdfm(4,0.75,nChan);
			}if(Mbps == 57.8){dataBitsPerSymbol = dataBitsPerOdfm(6,0.66,nChan);
			}if(Mbps == 65){dataBitsPerSymbol = dataBitsPerOdfm(6,0.75,nChan);
			}if(Mbps == 72.2){dataBitsPerSymbol = dataBitsPerOdfm(6,0.83,nChan);
			}if(Mbps == 86.7){dataBitsPerSymbol = dataBitsPerOdfm(8,0.75,nChan);
			}if(Mbps == 96.3){dataBitsPerSymbol = dataBitsPerOdfm(8,0.83,nChan);
			}
		}
		return dataBitsPerSymbol;
	}


/**
 * returns the preamble used in udp calculation.
 * figures dependent on standard
 * @param standard
 * @return
 */
	public static double getPreamble(String standard){
		switch(standard) {
		case IEEE_802_11a:  	 
		case IEEE_802_11g:
			preamble = 20.0;
			break;
		case IEEE_802_11n:
			preamble = 46.0;
			break;
		case IEEE_802_11ac_w1:
			preamble = 56.8;
			break;
		default:
			preamble = 92.8;
		}
		return preamble;
	}
/**
 * returns duration each symbol takes to transmit.
 * as per the notes figures depend on standard.
 * @param standard
 * @return
 */
	public static double getSymbolDuration(String standard){
		double sDur;
		switch(standard) {  	 
		case IEEE_802_11a:
		case IEEE_802_11g:
			sDur = 4.0;
			break;
		default:
			sDur = 3.6;
		}
		return sDur;
	}
	/**
	 * getter for SIFS
	 * SIFS stands for short interframe space
	 * figures from slides.
	 * @param standard
	 * @return
	 */
	public  int getSIFS(String standard){
		switch(standard) {  	 
		case IEEE_802_11g:
			this.SIFS = 10;
			break;
		default:
			this.SIFS = 16;
		}
		return SIFS;
	}
/**
 * method used in getDataBitsPerSymbol to calculate Nbits * CRate * nChan;
 * depending on stanadard considers NSS value and returns
 * @param Nbits
 * @param CRate
 * @param nChan
 * @return
 */
	private static double dataBitsPerOdfm(int Nbits, double CRate, int nChan) {
		double dp = Nbits * CRate * nChan;
		
	switch(standard) {
		case IEEE_802_11a:  	 
		case IEEE_802_11g:
			dp = dp *1;
			break;
		case IEEE_802_11n:
			dp = dp * 4;
			break;
		case IEEE_802_11ac_w1:
			dp = dp * 3;
			break;
		case IEEE_802_11ac_w2:
			dp = dp * 8; 
			break;
		}
		
		return dp;
		
		
		

	}
	/**
	 * calculates the throughput
	 * converts to mb and seconds
	 * divides mb by seconds to return megabits per second.
	 * @param time
	 * @param nBits
	 * @return
	 */
		public double getThroughput(double time, int nBits){
			double megaB = nBits * 0.000001;
			double seconds = time * 0.000001;
			this.throughput = megaB/seconds;
			return throughput;
		}
	public void Calculator(String protocolValue, String standardValue, String dataValue) {
		this.protocol = protocolValue;
		this.data_Rate = Double.parseDouble(dataValue);
		this.standard = standardValue; 


	
		double bps = getDataBitsPerSymbol(data_Rate, standard);//bits per symbol
		int bits =  getbits(standard);
		int symbols = (int) (bits /bps); 
		
		double sDur = getSymbolDuration(standard);
		data_Frame_Time = symbols * sDur;  
		
		preamble = getPreamble(standard);
		getSIFS(standard);
		
		difs = getdifs(SIFS);
		time = timePerPacket(difs,data_Frame_Time);
		
		throughput = getThroughput(time, bits);//throughput for one packet.
		total_send_time = _10gb_package/throughput;// for sending 10 gigabytes


		this.results = ("\n"+dec.format(time))+
		("\n\n"+ dec.format(throughput) ) +
		("\n\n"+dec.format(total_send_time) );
		
		this.breakdown = ""+preamble+"\n"+SIFS+"\n"+difs +
				"\n"+symbols+"\n"+sDur+"\n"+dec.format(data_Frame_Time)+"\n"+bits;


	}

}