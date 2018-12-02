package Coords;
import Geom.Point3D;
public class MyCoords implements coords_converter
{
	private final int  R_earth = 6371*1000;
	//-----------------------------------------------------------------------------------
	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) 
	{
		System.out.println("i am here");
		double r,pX,pY, theta;
		double lat,lon,alt;

		alt =  local_vector_in_meter.z()- gps.z();

		pX= local_vector_in_meter.x()*local_vector_in_meter.x();
		pY= local_vector_in_meter.y()*local_vector_in_meter.y();
		r=Math.sqrt(pX+pY);
		theta = Math.atan(local_vector_in_meter.y()/local_vector_in_meter.x());

		lat = r*Math.cos(theta);
		lon = r*Math.sin(theta);

		lat = gps.x()+lat;
		lon = gps.y()+lon;
        
		Point3D p = new  Point3D(lat,lon,alt);
		System.out.println("The destantion is"+p);
		
		return  new Point3D(lat,lon,alt);
	}
	//---------------------------------------------------------------------------------
	/** computes the 3D distance (in meters) between the two gps like points */
	public double distance3d(Point3D gps0, Point3D gps1) 
	{
		double distance;
		Point3D p = vector3D(gps0, gps1);
		distance = Math.sqrt(p.x()*p.x()+p.y()*p.y());
		return distance;
	}	
	//---------------------------------------------------------------------------------
	/** computes the 3D vector (in meters) between two gps like points */
	public Point3D vector3D(Point3D gps0, Point3D gps1) 
	{
		// gps1-gps0
		double diff_lat,diff_lon,diff_alt;
		double diff_rad_lat, diff_rad_lon;
		double meter_lat, meter_lon, meter_alt;

		double lonNorm = Math.cos(gps0.x()*Math.PI/180);

		diff_lat = gps1.x()-gps0.x();
		diff_lon = gps1.y()- gps0.y();
		diff_alt = gps1.z() - gps0.z();

		diff_rad_lat = diff_lat*Math.PI/180;
		diff_rad_lon = diff_lon*Math.PI/180;

		diff_lat= Math.toRadians(diff_lat);
		meter_lat= Math.sin(diff_lat)*R_earth;

		diff_lon= Math.toRadians(diff_lon);
		meter_lon = Math.sin(diff_lon)*R_earth*lonNorm;
		meter_alt= diff_alt;

		return  new Point3D(meter_lat,meter_lon,meter_alt);
	}
	//--------------------------------------------------------------------------
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) 
	{
		double [] result = new double[3];

		double azimuth ,elevation ,dist;
		double x1,x0;
		double delta_y ,delta_z ;
		//~~~~~~~~~~~~~azimuth~~~~~~~~~~~~~~
		x0 = Math.toRadians(gps0.x());
		x1 = Math.toRadians(gps1.x());

		delta_y = Math.toRadians(gps1.y()-gps0.y());

		double arg1= Math.sin(delta_y)*Math.cos(x1);
		double arg2=Math.cos(x0)*Math.sin(x1)-Math.sin(x0)*Math.cos(x1)*Math.cos(delta_y);

		azimuth= (Math.toDegrees(Math.atan2(arg1, arg2))+360)%360;
		result[0] = azimuth;
		//~~~~~~~~~~~~elevation~~~~~~~~~~~~~~
		delta_z = gps1.z() - gps0.z();
		elevation = Math.toDegrees(Math.asin(delta_z/distance3d(gps0, gps1)));
		result[1] = elevation;
		//~~~~~~~~~~~distance~~~~~~~~~~~~~~~
		dist = distance3d(gps0, gps1);
		result[2]= dist;

		return result;
	}
	//---------------------------------------------------------------------------------
	/**
	 * return true if this point is a valid lat, lon , alt  coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	public boolean isValid_GPS_Point(Point3D p) 
	{
		double lat ,lon , alt;
		lat=p.x();
		lon=p.y();
		alt=p.z();

		if(lat <-90 || lat >90)
		{
			return false;
		}
		if(lon <-180 || lon >180)
		{
			return false;
		}
		if(alt < -450)
		{
			return false;
		}
		return true;
	}
}
