package GIS;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class My_GIS_layer implements GIS_layer
{
	//------------------fields-----------------------------
	Set<GIS_element> road;
    Layer_Meta_data road_md;
    
	//------------------constructor------------------------
	public My_GIS_layer ()
	{
		road = new HashSet<GIS_element>();
		road_md = new Layer_Meta_data();
	}
	
	//--------------implements methods------------------------
	public boolean add(GIS_element arg0) 
	{
		return road.add(arg0);
	}
	public boolean addAll(Collection<? extends GIS_element> arg0) 
	{
		return road.addAll(arg0);
	}
	public void clear() 
	{
		road.clear();
	}
	public boolean contains(Object arg0) 
	{
		return road.contains(arg0);
	}
	public boolean containsAll(Collection<?> arg0) 
	{
		return road.containsAll(arg0);
	}
	public boolean isEmpty() 
	{
		return road.isEmpty();
	}
	public Iterator<GIS_element> iterator() 
	{
		return road.iterator();
	}
	public boolean remove(Object arg0) 
	{
		return road.remove(arg0);
	}
	public boolean removeAll(Collection<?> arg0) 
	{
		return road.removeAll(arg0);
	}
	public boolean retainAll(Collection<?> arg0) 
	{
		return road.retainAll(arg0);
	}
	public int size() 
	{
		return road.size();
	}
	public Object[] toArray()
	{
		return road.toArray();
	}
	public <T> T[] toArray(T[] arg0) 
	{
		return road.toArray(arg0);
	}
	
	//--------------The MetaData of the road ------------------------
	public Meta_data get_Meta_data() 
	{		
		String temp;
		for(GIS_element des : road)
		{
			temp = des.getData().toString();
			road_md.add(temp);
		}
		return road_md;
	}
}
