package com.mu.compet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mu.compet.data.Reply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mu on 2016-10-20.
 */
public class ReplyAdapter extends BaseAdapter {
    private List<Reply> items = new ArrayList<>();
    Reply reply;

    private ImageView profileImageView;
    private TextView nickNameTextView;
    private TextView dateView;
    private TextView commentView;

    public void add(Reply reply) {
        items.add(reply);
        notifyDataSetChanged();
    }

    public void addAll(List<Reply> items) {
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
            convertView = inflater.inflate(R.layout.view_comment, parent, false);
        }
        reply = items.get(position);
        initView(convertView, reply);
        setReplyView(reply);
        return convertView;
    }

    public interface OnReplyClickListener {
        public void onReplyClick(View view, Reply reply);
    }

    OnReplyClickListener mListener;

    public void setOnUserClickListener(OnReplyClickListener listener) {
        mListener = listener;
    }

    private void initView(View v, Reply reply) {

        profileImageView = (ImageView) v.findViewById(R.id.image_profile);
        nickNameTextView = (TextView) v.findViewById(R.id.text_nickname);
        dateView = (TextView) v.findViewById(R.id.text_time);
        commentView = (TextView) v.findViewById(R.id.text_comment_content);

    }

    private void setReplyView(Reply reply) {
        profileImageView.setImageDrawable(reply.getProfileImage());
        nickNameTextView.setText(reply.getNickName());
        dateView.setText(reply.getDate());
        commentView.setText(reply.getContent());
    }
}
