package com.caueruleum.pshop.model;

import java.util.List;

/**
 * Generic type for modeling pages for pagination
 * @author caueruleum
 * @since 23-02-2020
 * @param <T> The type of entity
 */
public class PageModel<T>
{
	/* The list of items in the page */
	private List<T> items;
	
	/*The maximum pages */
	private int pages;
	
	/* Maximum items in a page */
	private int pageSize;
	
	/* Count for the retrieved items from the db */
	private int resultCount;
	
	/*The current page the user is on*/
	private int currentPage;
	
	/**
	 * Constructor, there are no setters, we directly create the object
	 * 
	 * @param items The items to be stored in the page
	 * @param pageSize Maximum number of items to be stored in the page
	 * @param currentPage The current page that the user is on.
	 */
	public PageModel(List<T> items, int pageSize, int currentPage) 
	{
		this.items = items;
		this.pageSize = pageSize;
		this.resultCount = this.items.size();
		this.pages = calculatePages();
		this.currentPage = currentPage;
	}
	
	public List<T> getItems() 
	{
		return items;
	}
	
	public int getPages() 
	{
		return pages;
	}

	public int getPageSize() 
	{
		return pageSize;
	}

	public int getResultCount() 
	{
		return resultCount;
	}

	private int calculatePages() 
	{
		if(this.resultCount == 0) 
		{
			this.resultCount = 1;
		}
		
		return (this.resultCount + this.pageSize - 1) / pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

}
