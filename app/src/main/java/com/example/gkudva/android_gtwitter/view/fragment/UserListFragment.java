package com.example.gkudva.android_gtwitter.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gkudva.android_gtwitter.R;
import com.example.gkudva.android_gtwitter.TwitterApplication;
import com.example.gkudva.android_gtwitter.model.User;
import com.example.gkudva.android_gtwitter.util.AppConstants;
import com.example.gkudva.android_gtwitter.util.DividerItemDecoration;
import com.example.gkudva.android_gtwitter.util.ItemClickSupport;
import com.example.gkudva.android_gtwitter.util.TwitterClient;
import com.example.gkudva.android_gtwitter.view.activities.ProfileActivity;
import com.example.gkudva.android_gtwitter.view.adapter.UsersAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListFragment extends Fragment {

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;
    @BindView(R.id.pbLoading)
    AVLoadingIndicatorView pbLoading;

    protected Context mContext;
    protected TwitterClient mClient;
    protected UsersAdapter mAdapter;
    protected List<User> mUserList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mContext = getActivity();
        mClient = TwitterApplication.getRestClient();

        initUserList();
    }

    private void initUserList() {
        mUserList = new ArrayList<>();
        mAdapter = new UsersAdapter(mContext, mUserList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvUsers.setLayoutManager(linearLayoutManager);
        rvUsers.setAdapter(mAdapter);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST);
        rvUsers.addItemDecoration(itemDecoration);

        ItemClickSupport.addTo(rvUsers).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        User user = mUserList.get(position);
                        Intent intent = new Intent(mContext, ProfileActivity.class);
                        intent.putExtra(AppConstants.USER_EXTRA, Parcels.wrap(user));
                        startActivity(intent);
                    }
                });
    }

    public void addItemList(List<User> userList) {
        int curSize = mUserList.size();
        mUserList.addAll(userList);
        mAdapter.notifyDataSetChanged();
//    mAdapter.notifyItemRangeInserted(curSize, userList.size());
    }

    public void showProgress() {
        pbLoading.show();
    }

    public void hideProgress() {
        pbLoading.hide();
    }
}
