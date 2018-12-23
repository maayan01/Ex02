
package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class My_GIS_element implements GIS_element
{
	//------------------fields-----------------------------
	private Point3D p ;
	private	My_Meta_data md;

	//------------------constructor-------------------------
	public My_GIS_element(String[] userData)
	{
		double lat,lon,alt;
		lat = Double.parseDouble(userData[6]);
		lon = Double.parseDouble(userData[7]);
		alt = Double.parseDouble(userData[8]);
		p =new Point3D (lat,lon,alt);
		md = new My_Meta_data(userData);
	}


	//-----------------Get's methods--------------------------
	public Geom_element getGeom() 
	{
		return p;
	}
	
	public Meta_data getData() 
	{
		return md;
	}
	
	//--------------------------------------------------------
	public void translate(Point3D vec) 
	{
		// TODO Auto-generated method stub
	}
}
