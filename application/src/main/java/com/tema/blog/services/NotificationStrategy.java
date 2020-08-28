package com.tema.blog.services;

import com.tema.blog.bo.ArticleInfo;

public interface NotificationStrategy {

	public void notify(ArticleInfo articleNotification);
}
