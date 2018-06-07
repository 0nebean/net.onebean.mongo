package net.onebean.core.mongo;

import net.onebean.core.mongo.common.Pagination;

import java.util.List;


/**
 * 分页结果集
 * @author 0neBean
 *
 * @param <T>
 */
public class MongoPagination <T> extends Pagination {
	/**
	 * 添加序列化反序列化ID
	 */
	private static final long serialVersionUID = 8045903828118195043L;
	/**
	 * 封装查询结果在此
	 */
	private List<T> rows;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
