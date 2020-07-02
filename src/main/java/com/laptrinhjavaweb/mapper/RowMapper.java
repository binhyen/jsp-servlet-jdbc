package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

public interface RowMapper<E> {
	E mapRow(ResultSet rs);
}
