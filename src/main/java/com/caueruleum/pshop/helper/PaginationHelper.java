package com.caueruleum.pshop.helper;

public class PaginationHelper
{
	/**
	 * This is used for calculation of pagination coordinates
	 * 
	 * @param page the page
	 * @return int[] the coordinates, first element is the offset, second is limit
	 */
	
	public static int[] calculatePaginationCoordinates(int page, int maxPageItems) 
	{
 		
 		int max = maxPageItems*page;
		int offset = (max-maxPageItems);
		
		int[] coordinates = {offset,max};
		
		return coordinates;
	}

	
	
	
	

}
