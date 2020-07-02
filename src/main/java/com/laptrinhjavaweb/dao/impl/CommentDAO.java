package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.ICommentDAO;
import com.laptrinhjavaweb.model.CommentModel;

public class CommentDAO extends AbsTractDAO<CommentModel> implements ICommentDAO {

	@Override
	public void delete(long newsid) {
		String sql = "DELETE FROM comment WHERE news_id = ?";
		update(sql, newsid);
	}

	
}
