package com.mu.compet;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.mu.compet.data.Reply;
import com.mu.compet.data.ResultMessage;
import com.mu.compet.manager.NetworkManager;
import com.mu.compet.manager.NetworkRequest;
import com.mu.compet.request.DeleteReplyRequest;

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
    private TextView replyTextView;
    private EditText editReplyView;


    private ViewSwitcher viewSwitcher;



    private String boardNum;

    public void add(Reply reply) {
        items.add(reply);
        notifyDataSetChanged();
    }

    public void addAll(List<Reply> replyList) {
        if (!items.isEmpty()) items.clear();
        items.addAll(replyList);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public ReplyAdapter(String boardNum) {
        this.boardNum = boardNum;
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
            convertView = inflater.inflate(R.layout.view_reply, parent, false);
        }
        reply = items.get(position);
        initView(convertView, reply);
        setReplyTextView(reply);
        return convertView;
    }

    public interface OnReplyClickListener {
        public void onReplyClick(View view, Reply reply);
    }

    OnReplyClickListener mListener;

    public void setOnUserClickListener(OnReplyClickListener listener) {
        mListener = listener;
    }

    private void initView(View v, final Reply reply) {
        ImageButton deleteButton;
        ImageButton updateButton;
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        viewSwitcher = (ViewSwitcher) v.findViewById(R.id.view_switcher);
        profileImageView = (ImageView) v.findViewById(R.id.image_profile);
        nickNameTextView = (TextView) v.findViewById(R.id.text_nickname);
        dateView = (TextView) v.findViewById(R.id.text_time);
        replyTextView = (TextView) v.findViewById(R.id.text_comment_content);
        editReplyView = (EditText) v.findViewById(R.id.edit_comment_content);
        deleteButton = (ImageButton) v.findViewById(R.id.btn_delete_reply);
        updateButton = (ImageButton) v.findViewById(R.id.btn_update_reply);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                builder.setMessage("삭제하시겠습니까?")
                        .setCancelable(true)
                        .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                performDelete(view, reply);

                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSwitcher.showNext();
                String content = replyTextView.getText().toString();
                editReplyView.setText(content);

            }
        });

    }

    private void performDelete(View view, Reply reply) {
        String replyNum = String.valueOf(reply.getReplyNum());
        DeleteReplyRequest request = new DeleteReplyRequest(view.getContext(), boardNum, replyNum);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<ResultMessage>() {
            @Override
            public void onSuccess(NetworkRequest<ResultMessage> request, ResultMessage result) {

                Log.d("DetailBoardActivity", "성공 : " + result.getMessage());
            }

            @Override
            public void onFail(NetworkRequest<ResultMessage> request, int errorCode, String errorMessage, Throwable e) {

                Log.d("DetailBoardActivity", "실패 : " + errorMessage);

            }
        });
    }

    private void setReplyTextView(Reply reply) {
//        profileImageView.setImageDrawable(rep());
        nickNameTextView.setText(reply.getUserNick());
        dateView.setText(reply.getReplyRegDate());
        replyTextView.setText(reply.getReplyContent());

    }
}
