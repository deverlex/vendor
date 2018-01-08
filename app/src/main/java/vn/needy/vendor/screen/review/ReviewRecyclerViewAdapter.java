package vn.needy.vendor.screen.review;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by minh_dai on 05/01/2018.
 */

public class ReviewRecyclerViewAdapter extends BaseRecyclerViewAdapter<ReviewRecyclerViewAdapter.ViewHolder> {

    protected ReviewRecyclerViewAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
