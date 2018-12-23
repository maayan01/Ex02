package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class My_GIS_project  implements GIS_project
{
	//------------------fields-----------------------------
	Set<GIS_layer>	myRoads ;
	Project_Meta_data myRoads_md;
	//------------------constructor------------------------
	public My_GIS_project ()
	{
		myRoads = new HashSet<GIS_layer>() ;
	}
	
	//--------------implements methods------------------------
	public boolean add(GIS_layer arg0) 
	{
		return myRoads.add(arg0);
	}
	
	public boolean addAll(Collection<? extends GIS_layer> arg0)
	{
		return myRoads.addAll(arg0);
	}
	
	public void clear() 
	{
		myRoads.clear();
	}

	public boolean contains(Object arg0)
	{
		return myRoads.contains(arg0);
	}

	public boolean containsAll(Collection<?> arg0)
	{
		return myRoads.containsAll(arg0);
	}

	public boolean isEmpty()
	{
		return myRoads.isEmpty();
	}

	public Iterator<GIS_layer> iterator() 
	{
		return myRoads.iterator();
	}

	public boolean remove(Object arg0) 
	{
		return myRoads.remove(arg0);
	}

	public boolean removeAll(Collection<?> arg0) 
	{
		return myRoads.removeAll(arg0);
	}

	public boolean retainAll(Collection<?> arg0) 
	{
		return myRoads.retainAll(arg0);
	}

	public int size() 
	{
		return myRoads.size();
	}

	public Object[] toArray() 
	{
		return myRoads.toArray();
	}

	public <T> T[] toArray(T[] arg0) 
	{
		return myRoads.toArray(arg0);
	}
	
	//-------------- The MetaData of the all roads------------------------
	public Meta_data get_Meta_data() 
	{
		int count=1;
		String temp="";
		for(GIS_layer road : myRoads)
		{
			temp ="\n road number "+count+"is:"+road.get_Meta_data().toString();
			count++;
			myRoads_md.add(temp);
		}
		return myRoads_md;
	}
}
