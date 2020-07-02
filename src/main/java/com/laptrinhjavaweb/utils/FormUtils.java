package com.laptrinhjavaweb.utils;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class FormUtils {
	public static <E> E toModel(Class<E> eClass , HttpServletRequest request) {
		E object = null;
		try {
			object = eClass.newInstance();
			BeanUtils.populate(object, request.getParameterMap());
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;
	}
}
