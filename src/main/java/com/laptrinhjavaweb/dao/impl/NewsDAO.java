package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewsDAO extends AbsTractDAO<NewsModel> implements INewsDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryId = ?";
		return query(sql, new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newsModel) {
//		String sql = "INSERT INTO news(title, content, categoryid) VALUES(?, ?, ?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news(title, thumbnail, shortDescription, ");
		sql.append("content, categoryid, createddate, createdby");
		sql.append(") VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), newsModel.getShortDescription(),
				newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getCreatedBy());
		
	}

	@Override
	public NewsModel findOne(Long newsId) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> news = query(sql, new NewsMapper(), newsId);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, modifieddate = ?, createdby = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), newsModel.getTitle(), newsModel.getThumbnail(), 
				newsModel.getShortDescription(), newsModel.getContent(), 
				newsModel.getCategoryId(), newsModel.getCreatedDate(), newsModel.getModifiedDate(),
				newsModel.getCreatedBy(), newsModel.getModifiedBy(),
				newsModel.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

	@Override
	public List<NewsModel> findAll(Pageble pagenle) {
//		String sql = "SELECT * FROM news LIMIT ?,?";
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if (pagenle.getSorter() != null && StringUtils.isNotBlank(pagenle.getSorter().getSortName()) && StringUtils.isNotBlank(pagenle.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pagenle.getSorter().getSortName()+" "+pagenle.getSorter().getSortBy()+"");
		}
		if (pagenle.getOffset() != null && pagenle.getLimit() != null) {
			sql.append(" LIMIT "+pagenle.getOffset()+", "+pagenle.getLimit()+"");
		}
		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT COUNT(*) FROM news";
		return count(sql);
	}
}
