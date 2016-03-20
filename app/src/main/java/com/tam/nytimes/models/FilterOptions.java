package com.tam.nytimes.models;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by toan on 3/20/2016.
 */
public class FilterOptions implements Serializable {
    private SortOrder order;
    private Calendar beginDate;
    private boolean cbArts;
    private boolean cbFashionStyle;
    private boolean cbSports;

    public String getArticleTypeFilter() {
        StringBuilder builder = new StringBuilder();
        builder.append("new_desk:(");
        if (this.isCbArts()) {
            builder.append("\"Arts\"");
        }
        if(this.isCbFashionStyle()) {
            builder.append("\"Fashion & Style\"");
        }
        if (this.isCbSports()) {
            builder.append("\"Sports\"");
        }
        builder.append(")");
        return  builder.toString();
    }

    public String getBeginDateFilter() {
        Calendar calendar = this.beginDate;
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        String beginDate = String.valueOf(year) + String.valueOf(month < 10 ? "0" + month : month)
                            + String.valueOf(day < 10 ? "0" + day : day);
        return  beginDate;
    }

    public FilterOptions() {
        this.beginDate = Calendar.getInstance();
        this.order = SortOrder.Oldest;
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

}

