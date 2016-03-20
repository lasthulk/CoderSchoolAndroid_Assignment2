package com.tam.nytimes.models;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by toan on 3/20/2016.
 */
public class FilterOptions implements Serializable {
    private SortOrder order;
    private Calendar beginDate;
    private ArticleType articleType;
    private boolean cbArts;
    private boolean cbFashionStyle;
    private boolean cbSports;


    public FilterOptions() {
        this.order = SortOrder.Newest;
        this.beginDate = Calendar.getInstance();
        this.articleType = ArticleType.All;
    }

    public boolean isCbArts() {
        return cbArts;
    }

    public void setCbArts(boolean cbArts) {
        this.cbArts = cbArts;
    }

    public boolean isCbFashionStyle() {
        return cbFashionStyle;
    }

    public void setCbFashionStyle(boolean cbFashionStyle) {
        this.cbFashionStyle = cbFashionStyle;
    }

    public boolean isCbSports() {
        return cbSports;
    }

    public void setCbSports(boolean cbSports) {
        this.cbSports = cbSports;
    }

    public SortOrder getSortOrder() {
        return order;
    }

    public void setSortOrder(SortOrder order) {
        this.order = order;
    }

    public Calendar getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Calendar beginDate) {
        this.beginDate = beginDate;
    }

    public ArticleType getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleType type) {
        this.articleType = type;
    }
}

