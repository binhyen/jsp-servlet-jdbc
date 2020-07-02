package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sorting.Sorter;

public interface Pageble {
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
