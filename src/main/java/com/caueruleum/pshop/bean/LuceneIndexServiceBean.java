package com.caueruleum.pshop.bean;

import javax.persistence.EntityManagerFactory;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

public class LuceneIndexServiceBean 
{
	
	private FullTextEntityManager fullTextEntityManager;
	
	public LuceneIndexServiceBean(EntityManagerFactory entityManagerFactory) 
	{
        fullTextEntityManager = Search.getFullTextEntityManager(entityManagerFactory.createEntityManager());
        
	}
	
	public void triggerIndexing() 
	{
		try 
		{
			this.fullTextEntityManager.createIndexer().startAndWait();
		}catch(InterruptedException  ie) 
		{
			throw new RuntimeException(ie);
		}
	}
	

}
