package com.tensquare.article.service.impl;

import com.tensquare.article.entity.Article;
import com.tensquare.article.mapper.ArticleMapper;
import com.tensquare.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author GoogleSearch
 * @since 2019-06-23
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
