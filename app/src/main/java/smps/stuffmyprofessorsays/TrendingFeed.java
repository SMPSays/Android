package smps.stuffmyprofessorsays;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrendingFeed.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrendingFeed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrendingFeed extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String trendingFeedUrl = "http://www.smpsays-api.xyz/RUEf2i15kex8nXhmJxCW2ozA5SNIyfLn/search/quotes?";

    private OnFragmentInteractionListener mListener;

    public TrendingFeed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrendingFeed.
     */
    // TODO: Rename and change types and number of parameters
    private static TrendingFeed newInstance(String param1, String param2) {
        TrendingFeed fragment = new TrendingFeed();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_trending_feed, container, false);

        RequestQueue queue = Volley.newRequestQueue(getContext());
        mRecyclerView = (RecyclerView) view.findViewById(R.id.quoteRecyclerView);

        Drawable separator = ContextCompat.getDrawable(getContext(), R.drawable.line_divider);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(separator);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);




        StringRequest stringRequest = new StringRequest(Request.Method.GET, trendingFeedUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String data) {
                        // Use an adapter here to populate the recycler view with quotations.
                        List<Quotation> quotations = parseJsonResponse(data);

                        mAdapter = new QuotationRecyclerAdapter(quotations);
                        mRecyclerView.setAdapter(mAdapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Handle error
                        Log.d("Error: ", volleyError.toString());
                    }
                }
        );
        queue.add(stringRequest);

        // Inflate the layout for this fragment
        return view;
    }

    private List<Quotation> parseJsonResponse(String response){
        List<Quotation> quotationList = new ArrayList<>();
        JSONArray reader;
        JSONObject currentQuote;
        int currentQuoteNdx = 0;

        try {
            reader = new JSONArray(response);
            currentQuote = reader.getJSONObject(currentQuoteNdx++);
        }catch (JSONException e){
            Log.d("Error: ", e.toString());
            return null;
        }

        while (currentQuoteNdx < reader.length()){
            try{
                String quoteText = currentQuote.getString("quotation");
                String professor = currentQuote.getString("professor");
                String subject = currentQuote.getString("subject");
                String school = currentQuote.getString("school");
                Quotation quotation = new Quotation(quoteText, professor, subject, school);
                quotationList.add(quotation);
                currentQuote = reader.getJSONObject(currentQuoteNdx++);
            }catch (JSONException e){
                Log.d("Error: ", e.toString());
                return null;
            }
        }

        return quotationList;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
