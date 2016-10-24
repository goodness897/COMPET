package com.mu.compet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mu.compet.data.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mu on 2016-10-20.
 */
public class MyPostAdapter extends BaseAdapter {
    private List<Post> items = new ArrayList<>();

    private ImageView postImageView;
    private TextView imageCountView;
    private TextView postContentView;
    private ImageView commentImageView;
    private TextView commentCountView;
    private RelativeLayout layout;
    Post post;


    public void add(Post post) {
        items.add(post);
        notifyDataSetChanged();
    }

    public void addAll(List<Post> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface OnPostClickListener {
        public void onPostClick(View view, Post post);
    }

    PostAdapter.OnPostClickListener pListener;

    public void setOnPostClickListener(PostAdapter.OnPostClickListener listener) {
        pListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_my_post, parent, false);
        }

        post = items.get(position);
        initView(convertView, post);
        setBoardView(post);
        return convertView;
    }

    private void initView(View v, final Post post) {

        layout = (RelativeLayout) v.findViewById(R.id.layout);
        postImageView = (ImageView) v.findViewById(R.id.image_post_first_image);
        imageCountView = (TextView) v.findViewById(R.id.text_image_count);
        postContentView = (TextView) v.findViewById(R.id.text_post_content);
        commentImageView = (ImageView) v.findViewById(R.id.image_comment);
        commentCountView = (TextView) v.findViewById(R.id.text_comment_count);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pListener != null) {
                    pListener.onPostClick(v, post);
                }
            }
        });

    }

    private void setBoardView(Post post) {

        postImageView.setImageDrawable(post.getPostImage());
        imageCountView.setText(post.getImageCount());
        postContentView.setText(post.getPostContent());
        commentCountView.setText(post.getReplyCount());
    }
}
