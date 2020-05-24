package com.caueruleum.pshop.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caueruleum.pshop.bean.LuceneIndexServiceBean;

@Configuration
public class LuceneIndexConfig 
{
	@Bean
	public LuceneIndexServiceBean luceneIndexServiceBean(EntityManagerFactory emf) 
	{
		LuceneIndexServiceBean luceneIndexServiceBean = new LuceneIndexServiceBean(emf);
		luceneIndexServiceBean.triggerIndexing();
		return luceneIndexServiceBean;
	}

}
