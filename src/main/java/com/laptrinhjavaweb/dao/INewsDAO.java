package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewsDAO extends GenericDAO<NewsModel> {
	List<NewsModel> findByCategoryId(Long categoryId);
	Long save(NewsModel newsModel);
	NewsModel findOne(Long newsId);
	void update(NewsModel newsModel);
	void delete(long id);
	List<NewsModel> findAll(Pageble pagenle);
	int getTotalItem();
}
