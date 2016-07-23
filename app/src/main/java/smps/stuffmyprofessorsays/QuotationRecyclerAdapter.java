package smps.stuffmyprofessorsays;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by chris on 6/19/16.
 */
class QuotationRecyclerAdapter extends RecyclerView.Adapter<QuotationRecyclerAdapter.ViewHolder>  {

    private List<Quotation> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView quotationTextView, professorTextView;
        ViewHolder(View v) {
            super(v);
            quotationTextView = (TextView) v.findViewById(R.id.quotationTextView);
            professorTextView = (TextView) v.findViewById(R.id.professorTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    QuotationRecyclerAdapter(List<Quotation> data) {
        mDataset = data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuotationRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quote_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d(TAG, "onBindViewHolder: current position = " + position);
        if(mDataset.get(position) != null) {
            Quotation currentQuotation = mDataset.get(position);
            holder.quotationTextView.setText(currentQuotation.getQuote());
            String professor = "-" + currentQuotation.getProfessor();
            holder.professorTextView.setText(professor);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
