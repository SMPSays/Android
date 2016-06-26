package smps.stuffmyprofessorsays;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

/**
 * Created by chris on 6/19/16.
 */
public class QuotationRecyclerAdapter extends RecyclerView.Adapter<QuotationRecyclerAdapter.ViewHolder>  {

    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }
}
