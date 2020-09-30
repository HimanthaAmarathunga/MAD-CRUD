package com.rasanjana.anyhelp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NurseUserMenuAdapter extends RecyclerView.Adapter<NurseUserMenuAdapter.ViewHolder> {
    private static final String TAG = NurseUserMenuAdapter.class.getSimpleName();

    private LayoutInflater mInflater;
    private OnItemClickListener mItemClickListener;
    private List<Nurse> nurses = new ArrayList<>();

    public NurseUserMenuAdapter(final Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_service_privider, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Nurse nurse = nurses.get(position);
        //holder.txtName.setText(nurse.getName());
        holder.txtLocation.setText(nurse.getLocation());
        holder.txtAvailableTime.setText(nurse.getAvailableTime());
    }

    @Override
    public int getItemCount() {
        return nurses.size();
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName, txtLocation, txtAvailableTime;
        LinearLayout frmCategory;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.TvName);
            txtLocation = itemView.findViewById(R.id.TvLocation);
            txtAvailableTime = itemView.findViewById(R.id.TvAvailableTime);
            frmCategory = itemView.findViewById(R.id.frm_category);
            frmCategory.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i(TAG, "onClick: ");
            int position = getAdapterPosition();
            if(mItemClickListener != null && position >= 0) {
                mItemClickListener.onItemClicked(position);
            }
        }
    }

    public void setOnItemClickListener(OnItemClickListener ItemClickListener) {
        this.mItemClickListener = ItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
