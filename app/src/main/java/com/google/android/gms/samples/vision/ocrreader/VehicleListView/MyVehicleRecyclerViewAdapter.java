package com.google.android.gms.samples.vision.ocrreader.VehicleListView;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.samples.vision.ocrreader.R;
import com.google.android.gms.samples.vision.ocrreader.VehicleListView.VehicleFragment.OnListFragmentInteractionListener;
import com.google.android.gms.samples.vision.ocrreader.database.FraudVehicleDatabase;
import com.google.android.gms.samples.vision.ocrreader.database.VehicleDatabase;

import java.util.List;

/**
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyVehicleRecyclerViewAdapter extends RecyclerView.Adapter<MyVehicleRecyclerViewAdapter.ViewHolder> {

    private final List<VehicleDatabase> mValues;
    private final List<FraudVehicleDatabase> mFraudValues;
    private final OnListFragmentInteractionListener mListener;

    public MyVehicleRecyclerViewAdapter(List<VehicleDatabase> items, OnListFragmentInteractionListener listener, List<FraudVehicleDatabase> fraudItems) {
        mValues = items;
        mListener = listener;
        mFraudValues = fraudItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_vehicle, parent, false);
        return new ViewHolder(view);
    }

    public boolean isFraud(String number){
        Log.i("Fraud number",number);
        for(int i=0;i<mFraudValues.size();i++){
            Log.i("Fraud values",mFraudValues.get(i).getNumber());
            if(mFraudValues.get(i).getNumber().equals(number)) {
                Log.i("Fraud values",mFraudValues.get(i).getNumber());
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNumber());
        //holder.mContentView.setText(mValues.get(position).content);
        if(isFraud(mValues.get(position).getNumber())){
            holder.mIdView.setTextColor(Color.parseColor("#ff0000"));
        }
        else{
            holder.mIdView.setTextColor(Color.parseColor("#ffffff"));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public VehicleDatabase mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
