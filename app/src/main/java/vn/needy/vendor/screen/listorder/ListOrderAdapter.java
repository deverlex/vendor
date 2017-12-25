package vn.needy.vendor.screen.listorder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by lion on 18/12/2017.
 */

public class ListOrderAdapter extends BaseRecyclerViewAdapter<ListOrderAdapter.ItemViewHolder> {


    protected ListOrderAdapter(@NonNull Context context) {
        super(context);
    }

    @Override
    public ListOrderAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListOrderAdapter.ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
