package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

import com.laptrinhjavaweb.model.NewsModel;

public class NewsMapper implements RowMapper<NewsModel>{

	@Override
	public NewsModel mapRow(ResultSet rs) {
		NewsModel news = new NewsModel();
		try {
			news.setId(rs.getLong("id"));
			news.setTitle(rs.getString("title"));
			news.setThumbnail(rs.getString("thumbnail"));
			news.setShortDescription(rs.getString("shortdescription"));
			news.setContent(rs.getString("content"));
			news.setCategoryId(rs.getLong("categoryid"));
			news.setCreatedDate(rs.getTimestamp("createddate"));
			news.setCreatedBy(rs.getString("createdby"));
			if (rs.getTimestamp("modifieddate") != null) {
				news.setModifiedDate(rs.getTimestamp("modifieddate"));
			}
			
			if (rs.getString("modifiedby") != null) {
				news.setModifiedBy(rs.getString("modifiedby"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return news;
	}
	
}
