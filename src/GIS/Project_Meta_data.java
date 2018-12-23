package GIS;

import java.util.ArrayList;

import Geom.Point3D;

public class Project_Meta_data implements Meta_data
{
	//------------------fields-----------------------------
	ArrayList <String> myRoads_MD;
	
	//------------------constructor------------------------
	public Project_Meta_data()
	{
		myRoads_MD= new  ArrayList<String>();
	}
	
	//--------------method-----------------------
	public boolean add (String arg0)
	{
		return myRoads_MD.add(arg0);
	}
	//-------------implements methods-----------------
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
