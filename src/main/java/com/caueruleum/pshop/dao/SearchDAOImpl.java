package com.caueruleum.pshop.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Product;

/**
 * Implementation for the SearchDAO interface.
 * 
 *
 * 
 * @author caueruleum
 * @since 2020-02-21
 */

@Repository
public class SearchDAOImpl implements SearchDAO
{
	@Autowired
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findCategoryByName(String name)
	{
		FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
		
		QueryBuilder queryBuilder = ftem.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Category.class)
				.get();
		
		
		Query query = queryBuilder
				.keyword()
				.onField("name").matching(name)
				.createQuery();
		
		FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, Category.class);
		
		
		return fullTextQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findGeneralPaginate(String searchQuery, int offset, int limit) 
	{
		FullTextEntityManager ftem = Search.getFullTextEntityManager(em);
		
		QueryBuilder queryBuilder = ftem.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Product.class)
				.get();
		
		
		Query query = queryBuilder
				.keyword()
				.onFields("title", "description", "categories.name").matching(searchQuery)
				.createQuery();
		
		FullTextQuery fullTextQuery = ftem.createFullTextQuery(query, Product.class)
				.setFirstResult(offset)
				.setMaxResults(limit);
		
		return fullTextQuery.getResultList();
		
	}
	

}
