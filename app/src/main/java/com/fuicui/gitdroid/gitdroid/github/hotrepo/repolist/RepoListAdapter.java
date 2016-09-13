package com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.Repo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
public class RepoListAdapter extends BaseAdapter {
    private static final String TAG = "RepoListAdapter";
    private ArrayList<Repo> datas;
    public RepoListAdapter() {
        datas = new ArrayList<>();
        Log.d(TAG, "RepoListAdapter: "+datas.size());
    }
    public void addAll(Collection<Repo> repos) {//数据是在这边来的，也就是说是从自动更新呢的时候用了这个方法
        datas.addAll(repos);
        Log.d(TAG, "addAll: "+datas.size());
        notifyDataSetChanged();
    }
    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }
    @Override
    public Repo getItem(int position) {
        return datas.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_repo, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Repo repo = datas.get(position);
        viewHolder.tvRepoName.setText(repo.getFullName());
        viewHolder.tvRepoInfo.setText(repo.getDescription());
        viewHolder.tvRepoStars.setText(repo.getStarCount() + "");
        ImageLoader.getInstance().displayImage(repo.getOwner().getAvatar(), viewHolder.ivIcon);
        return convertView;
    }
    static class ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvRepoName)
        TextView tvRepoName;
        @BindView(R.id.tvRepoInfo)
        TextView tvRepoInfo;
        @BindView(R.id.tvRepoStars)
        TextView tvRepoStars;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
