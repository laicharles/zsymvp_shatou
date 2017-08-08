package com.wisewater.wechatpublic.message.out;

import java.util.List;
/**
 * 图文消息
 * @author Administrator
 *
 */
public class OutNewsMessage extends OutBaseMessage {
	//图文消息条数，限制为10条以内
	private int ArticleCount;
	//多条图文消息信息，默认第一个item为大图
	private List<OutArticle> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<OutArticle> getArticles() {
		return Articles;
	}

	public void setArticles(List<OutArticle> articles) {
		Articles = articles;
	}

}
