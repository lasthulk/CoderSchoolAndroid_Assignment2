package com.tam.nytimes.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tam.nytimes.R;
import com.tam.nytimes.models.Article;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by toan on 3/20/2016.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    static class ArticleViewHolder {
        @Bind(R.id.tvTitle)
        TextView tvTitle;

        @Bind(R.id.ivImage)
        ImageView ivImage;

        public ArticleViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {

            Article article = this.getItem(position);
            ArticleViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_article_result, parent, false);
                holder = new ArticleViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ArticleViewHolder) convertView.getTag();
            }
            holder.ivImage.setImageResource(0);
            holder.tvTitle.setText(article.getHeadLine());
            String thumnbnail = article.getThumbNail();
            if (!TextUtils.isEmpty(thumnbnail)) {
                Picasso.with(getContext()).load(article.getThumbNail())
                        //.resize(width, width)
                        .fit()
                        .centerCrop()
                        .into(holder.ivImage);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return convertView;
    }
}
