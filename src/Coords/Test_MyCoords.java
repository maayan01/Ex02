package Coords;

import java.text.DecimalFormat;

import Coords.MyCoords;
import Geom.Point3D;
import static org.junit.Assert.*;

import org.junit.Test;

public class Test_MyCoords 
{
	//------------------------------------------------------
	@Test
	public void test_add() 
	{
		Point3D start=new Point3D(32.103315,35.209039,670);
		Point3D go=new Point3D(100,200,670);
		Point3D destination =new Point3D(132.103315,235.209039,0);
		MyCoords mc =new MyCoords();
		Point3D  result = mc.add(start, go);
		if(!destination.equals(result))
		{
			fail("There is a Error !!!");
		}
	}
	//----------------------------------------------------
	@Test
	public void test_distance3d() 
	{
		double actual ,expected;
		MyCoords mc = new MyCoords();
		Point3D gps0 = new Point3D (32.103315,35.209039,670);
		Point3D gps1 =new  Point3D (32.106352,35.205225,650);

		actual = mc.distance3d(gps0, gps1);
		expected = 493.05233183241336;
		assertEquals(expected, actual,0);
	}
	//----------------------------------------------------
	@Test
	public void test_vector3D() 
	{
		MyCoords mc = new MyCoords();
		Point3D gps0 = new Point3D (32.103315,35.209039,670);
		Point3D gps1 =new  Point3D (32.106352,35.205225,650);

		Point3D result = mc.vector3D(gps0, gps1);
		Point3D expected = new Point3D(337.6989920612881,-359.24920693881893,-20.0);

		assertTrue(result.equals(expected)== true);
	}
	//-----------------------------------------------------
	@Test
	public void test_azimuth_elevation_dist() 
	{
		MyCoords mc = new MyCoords();
		Point3D gps0 = new Point3D (32.103315,35.209039,670);
		Point3D gps1 =new  Point3D (32.106352,35.205225,650);

		double [] expected = {313.23042032646896,-2.3247635173865278,493.05233183241336};
		double [] result =	mc.azimuth_elevation_dist(gps0, gps1);
		assertTrue(result[0]==expected[0] && result[1]==expected[1]&&result[2]==expected[2] );
	}
	//-----------------------------------------------------
	@Test
	public void test_isValid_GPS_Point() 
	{
		Point3D  p = new Point3D (-90,90,990);
		MyCoords mc = new MyCoords();
		boolean result;
		result = mc.isValid_GPS_Point(p);
		if(!result)
		{
			fail(p.toString()+ " is not Valid GPS Point");
		}
	}
}
