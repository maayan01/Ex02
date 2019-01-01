/*
 * in this class i use this code example: https://stackoverflow.com/questions/26637168/how-to-convert-a-date-to-milliseconds
 * */

package GIS;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Geom.Point3D;

public class My_Meta_data  implements Meta_data
{
	//---------------------fields-----------------------------
	private	String name_SSID; //name
	private	double rssi; 
	private long UTC;//time
	private String p_color;
	private String date;

	//------------------constructor---------------------------
	public My_Meta_data(String[] userData )
	{
		name_SSID = userData[1];
		UTC = DateToUTC(userData[3]);
		date = userData[3];
		rssi = Double.parseDouble(userData[5]);
		p_color = ChooseColor(userData ,rssi);
	}

	public My_Meta_data()
	{
		name_SSID = "";
		UTC = 0;
		rssi = 0;
		p_color = "";
	}
	//---------------------Help methods-----------------------
	/** convert from Date form to UTC*/
	private long DateToUTC(String dateStr)
	{
		long millis;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date= new Date();
		try 
		{
			date = sdf.parse(dateStr);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		millis = date.getTime();
		return millis;
	}
	//--------------------------------------------------------------
	/** choose the color of the point according to RSSI value*/
	private String ChooseColor (String[] userData ,double rssi)
	{
		if(rssi < -90)
		{
			return "#green";
		}
		else
		{
			return "#red";
		}
	}
	//-----------------------------------------------------
	public Point3D get_Orientation() 
	{
		return null;
	}
	//-----------------Get's methods-----------------------
	public long getUTC() 
	{
		return UTC;
	}
	public String getColor() 
	{
		return p_color;
	}
	public String getName()
	{
		return name_SSID;
	}
	public double getRssi()
	{
		return rssi;
	}
	public String getDate()
	{
		return this.date;
	}
	//------------------toString---------------------------
	/** return a String representing this data */
	public String toString()
	{
		String data="";
		String name,color,time;
		name = name_SSID;
		color=p_color;
		time=String.valueOf(UTC);
		data = "Name:"+name+ name_SSID+" Color:"+color+" Time:"+time;
		return data;
	}
}
