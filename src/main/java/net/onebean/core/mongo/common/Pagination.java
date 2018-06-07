package net.onebean.core.mongo.common;

public class Pagination implements java.io.Serializable {
	private static final long serialVersionUID = -6047376008743214596L;
	private final static int DEFAULT_PAGE_SIZE = 10;
	private final static  int DEFAULT_CURRENTPAGE = 1;


	/**
	 * 每页默认10条数据
	 */
	private int pageSize;
	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 总页数
	 */
	private int totalPages;
	/**
	 * 总数据数
	 */
	private int totalCount;

	public Pagination(int currentPage, int pageSize) {
		this.init(currentPage, pageSize);
	}

	public Pagination() {
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.currentPage = DEFAULT_CURRENTPAGE;
	}

	/**
	 * 初始化分页参数:需要先设置totalRows
	 */

	public void init(int currentPage, int pageSize) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;

		if ((currentPage % pageSize) == 0) {
			totalPages = currentPage / pageSize;
		} else {
			totalPages = currentPage / pageSize + 1;
		}

	}

	public void init(int totalCount, int pageSize, int currentPage) {
		this.currentPage = currentPage;
		this.init(totalCount, pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		if(totalCount==null)
		{
			this.totalCount=0;
		}else
		{
		this.totalCount = totalCount;
		}
	}

	@Override
	public String toString() {
		return "Pagination [DEFAULT_PAGE_SIZE=" + DEFAULT_PAGE_SIZE
				+ ", DEFAULT_CURRENTPAGE=" + DEFAULT_CURRENTPAGE
				+ ", pageSize=" + pageSize + ", currentPage=" + currentPage
				+ ", totalPages=" + totalPages + ", totalCount=" + totalCount
				+ "]";
	}

}
