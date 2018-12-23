package GIS;

import java.util.ArrayList;
import Geom.Point3D;

public class Layer_Meta_data implements Meta_data
{
	//------------------fields-----------------------------
	ArrayList <String> road_MD;

	//------------------constructor------------------------
	public Layer_Meta_data()
	{
		road_MD = new  ArrayList<String>();
	}
	
	//---------------methods----------------------
	public boolean add (String arg0)
	{
		return road_MD.add(arg0);
	}
	
	public String toString()
	{
		String ans="";
	 for(String singleMD : road_MD)
	 {
		 ans = ans+singleMD+",";
	 }
		return ans;
	}
	//-------------implements methods----------------
	public long getUTC() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Point3D get_Orientation() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
