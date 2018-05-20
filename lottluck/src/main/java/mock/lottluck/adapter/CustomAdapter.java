package mock.lottluck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mock.lottluck.callbacks.ItemClickCallback;
import mock.lottluck.model.BaseModel;
import mock.lottluck.model.LottLuckItems;
import mock.lottluck.model.LottLuckMainModal;
import mock.lottluck.R;
import retrofit2.Response;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context mContext;
    ItemClickCallback onItemClick;
    Response<BaseModel> response;

    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public ImageView imageView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            imageView = v.findViewById(R.id.icon);
        }
    }

    public void add(int position) {
        notifyItemInserted(position);
    }

    public CustomAdapter(Context context,ItemClickCallback onItemClick){
        mContext = context;
        this.onItemClick = onItemClick;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext().getApplicationContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        final ViewHolder vh = new ViewHolder(v);
        vh.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = vh.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION && null != onItemClick){
                    onItemClick.onItemClick(adapterPos);
                }
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String name = "";
        String url = "";
        if(response != null ){
            if(response.body() instanceof LottLuckMainModal){
                LottLuckMainModal lottLuckMainModal = (LottLuckMainModal) response.body();
                if(position < lottLuckMainModal.getCompanies().size()){
                    LottLuckItems lottLuckItems = lottLuckMainModal.getCompanies().get(position);
                    name = lottLuckItems.getCompanyDescription();
                    url = lottLuckItems.getCompanyLogoUrl();
                }
            }

        }
        holder.txtHeader.setText(name);
        Glide.with(mContext).load(url).into(holder.imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        int count = 0;
        if(response !=null){
            if(response.body() instanceof LottLuckMainModal) {
                LottLuckMainModal lottLuckMainModal = (LottLuckMainModal) response.body();
                count = lottLuckMainModal.getCompanies().size();
            }
        }
        return count;
    }

    public void updateDataset(Response<BaseModel> response){
        this.response = response;
    }

}
