package vn.needy.vendor.screen.place;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;

import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBufferResponse;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import vn.needy.vendor.R;
import vn.needy.vendor.databinding.ItemPlaceBinding;
import vn.needy.vendor.model.Place;
import vn.needy.vendor.screen.BaseRecyclerViewAdapter;

/**
 * Created by truongpq on 12/01/2018.
 */

public class PlacesAdapter extends BaseRecyclerViewAdapter<PlacesAdapter.ItemViewHolder>{

    private List<Place> mPlaces;
    private GeoDataClient mGeoDataClient;
    private LatLngBounds mBounds;
    private AutocompleteFilter mFilter;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Place>
            mItemClickListener;

    protected PlacesAdapter(@NonNull Context context, List<Place> places,
                            GeoDataClient geoDataClient,
                            LatLngBounds bounds,
                            AutocompleteFilter filter) {
        super(context);
        mPlaces = places;
        mGeoDataClient = geoDataClient;
        mBounds = bounds;
        mFilter = filter;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPlaceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_place, parent, false);
        return new PlacesAdapter.ItemViewHolder(binding, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(mPlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public void setItemClickListener(OnRecyclerViewItemClickListener<Place> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setData(List<Place> places) {
        mPlaces.clear();
        mPlaces.addAll(places);
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence charSequence) {
                FilterResults results = new FilterResults();
                // Skip the autocomplete query if no constraints are given.
                if (charSequence != null) {
                    // Query the autocomplete API for the (constraint) search string.
                    mPlaces = getAutocomplete(charSequence);
                    if (mPlaces != null) {
                        // The API successfully returned results.
                        results.values = mPlaces;
                        results.count = mPlaces.size();
                    }
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                if (filterResults != null && filterResults.count > 0) {
                    // The API returned at least one result, update the data.
                    notifyDataSetChanged();
                } else {
                    // The API did not return any results, invalidate the data set.
                    //notifyDataSetInvalidated();
                }
            }
        };
    }

    private List<Place> getAutocomplete(CharSequence query) {
        Task<AutocompletePredictionBufferResponse> results =
                mGeoDataClient.getAutocompletePredictions(query.toString(), mBounds,
                        mFilter);

        // This method should have been called off the main UI thread. Block and wait for at most
        // 60s for a result from the API.
        try {
            Tasks.await(results, 60, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }

        try {
            AutocompletePredictionBufferResponse autocompletePredictions = results.getResult();
            final List<Place> places = new ArrayList<>();
            for (AutocompletePrediction prediction : autocompletePredictions) {
                Place place = new Place();
                place.setId(prediction.getPlaceId());
                place.setName(prediction.getPrimaryText(null).toString());
                place.setAddress(prediction.getSecondaryText(null).toString());
                places.add(place);
            }
            return places;
        } catch (RuntimeExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemPlaceBinding mBinding;
        private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Place>
                mItemClickListener;

        public ItemViewHolder(ItemPlaceBinding binding, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Place> itemClickListener) {
            super(binding.getRoot());
            mBinding = binding;
            mItemClickListener = itemClickListener;
        }

        void bind(Place place) {
            mBinding.setViewModel(new ItemPlaceViewModel(place, mItemClickListener));
            mBinding.executePendingBindings();
        }
    }
}
