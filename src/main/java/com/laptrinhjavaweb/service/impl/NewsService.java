package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.ICommentDAO;
import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {

	@Inject
	private INewsDAO newsDAO;
	
	@Inject 
	private ICategoryDAO categoryDAO;
	
	@Inject 
	private ICommentDAO commentDAO;
	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newsDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel save(NewsModel news) {
		news.setCreatedDate(new Timestamp(System.currentTimeMillis()));	
		CategoryModel category = categoryDAO.findOneByCode(news.getCategoryCode());
		news.setCategoryId(category.getId());
		Long newsId = newsDAO.save(news);
		System.out.println("new id: "+newsId);
		return newsDAO.findOne(newsId);
	}

	@Override
	public NewsModel update(NewsModel newsModel) {
		NewsModel oldNews = newsDAO.findOne(newsModel.getId());
		newsModel.setCreatedDate(oldNews.getCreatedDate());
		newsModel.setCreatedBy(oldNews.getCreatedBy());
		newsModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryModel category = categoryDAO.findOneByCode(newsModel.getCategoryCode());
		newsModel.setCategoryId(category.getId());
		newsDAO.update(newsModel);
		return newsDAO.findOne(newsModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			//delete comment before delete news(khoa ngoai newsId)
			commentDAO.delete(id);
			newsDAO.delete(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newsDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newsDAO.getTotalItem();
	}

	@Override
	public NewsModel findOne(Long id) {
		NewsModel newsModel = newsDAO.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryId());
		newsModel.setCategoryCode(categoryModel.getCode());
		return newsModel;
	}

}
