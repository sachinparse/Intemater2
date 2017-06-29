package com.marse.licence;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.marse.crypto.CryptoUtil;
import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Licence;

public class LicenceService {

	public boolean validateLicence() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
	NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
	IllegalBlockSizeException, BadPaddingException, IOException {

		boolean licenceStatus=false;
		int licenceId=1;
		
		// current LAN Card Number
		//String licence=getLanCard();
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();

		Licence objLicence=(Licence) session.get(Licence.class, 1);
		
		String stopDate=new CryptoUtil().decrypt(objLicence.getStop());
		
		// Date functionality
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		 Date sDate=null;
		 Date currentDate=null;
		 
		 Calendar cal = Calendar.getInstance();
		 SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		 String formatted = format1.format(cal.getTime());
		 //System.out.println(formatted);

		 try {

	            sDate = formatter.parse(stopDate);
	           // System.out.println("1: "+sDate);
	            //System.out.println("2 : "+formatter.format(sDate));

	            currentDate=formatter.parse(formatted);
	            //System.out.println("Current Date: "+ currentDate);
	            
	     } catch (ParseException e) {
	            e.printStackTrace();
	     }
		
		if (null != objLicence) {    // this condition for whether the Licence is exist or not

			if (currentDate.before(sDate)) {

				licenceStatus=true;
				System.out.println("Licence is Valid");
			} else {
				licenceStatus=false;
				System.out.println("Licence is invalid");
			}
		}
		return licenceStatus;
	}
	
	/*public String getLanCard(){
		
		InetAddress ip;
		StringBuilder sb = new StringBuilder();
		
		try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());

			NetworkInterface network = NetworkInterface.getByInetAddress(ip);

			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");

			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.println(sb.toString());

		} catch (UnknownHostException e) {

			e.printStackTrace();

		} catch (SocketException e){

			e.printStackTrace();

		}
		
		return sb.toString();
	}*/

}
