package com.mu.compet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mu.compet.data.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mu on 2016-10-20.
 */
public class PostAdapter extends BaseAdapter {
    private List<Post> items = new ArrayList<>();
    Post post;

    private ImageView profileImageView;
    private TextView nickNameTextView;
    private TextView dateView;
    private ImageView postImageView;
    private TextView imageCountView;
    private TextView postContentView;
    private ImageView commentImageView;
    private TextView commentCountView;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.view_post, parent, false);
        }
        post = items.get(position);
        initView(convertView, post);
        setBoardView(post);
        return convertView;
    }

    public interface OnUserClickListener {
        public void onUserClick(View view, Post post);
    }

    OnUserClickListener mListener;

    public void setOnUserClickListener(OnUserClickListener listener) {
        mListener = listener;
    }

    public interface OnPostClickListener {
        public void onPostClick(View view, Post post);
    }

    OnPostClickListener pListener;

    public void setOnPostClickListener(OnPostClickListener listener) {
        pListener = listener;
    }

    private void initView(View v, final Post post) {

        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.user_info_layout);
        RelativeLayout relativeLayout = (RelativeLayout) v.findViewById(R.id.post_info_layout);
        profileImageView = (ImageView) v.findViewById(R.id.image_profile);
        nickNameTextView = (TextView) v.findViewById(R.id.text_nickname);
        dateView = (TextView) v.findViewById(R.id.text_post_date);
        postImageView = (ImageView) v.findViewById(R.id.image_post_first_image);
        imageCountView = (TextView) v.findViewById(R.id.text_image_count);
        postContentView = (TextView) v.findViewById(R.id.text_post_content);
        commentImageView = (ImageView) v.findViewById(R.id.image_comment);
        commentCountView = (TextView) v.findViewById(R.id.text_comment_count);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onUserClick(v, post);
                }

            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pListener != null) {
                    pListener.onPostClick(v, post);
                }
            }
        });

    }

    private void setBoardView(Post post) {
        profileImageView.setImageDrawable(post.getProfileImage());
        nickNameTextView.setText(post.getNickName());
        dateView.setText(post.getDate());
        postImageView.setImageDrawable(post.getPostImage());
        imageCountView.setText(post.getImageCount());
        postContentView.setText(post.getPostContent());
        commentCountView.setText(post.getReplyCount());
    }
}
