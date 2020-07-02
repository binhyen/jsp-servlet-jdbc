package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewsService;
import com.laptrinhjavaweb.service.impl.NewsService;
import com.laptrinhjavaweb.sorting.Sorter;
import com.laptrinhjavaweb.utils.FormUtils;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet{
	
	@Inject
	private INewsService newsService;
	
	@Inject
	private ICategoryService categoryService;
	private static final long serialVersionUID = -2187472896630637526L;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewsModel model = FormUtils.toModel(NewsModel.class, req);
		String view = "";
		if (model.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(), new Sorter(model.getSortName(), model.getSortBy()));
			model.setListModel(newsService.findAll(pageble));
			model.setTotalItem(newsService.getTotalItem());
			model.setTotalPage((int) Math.ceil((double) model.getTotalItem()/model.getMaxPageItem()));
			view = "views/admin/news/list.jsp";
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = newsService.findOne(model.getId());
			} else {
				
			}
			req.setAttribute("categories", categoryService.findAll());
			view = "views/admin/news/edit.jsp";
		}
		
		MessageUtil.showMessage(req);
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
