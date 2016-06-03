package com.mrcode.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.List;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimerRecyclerView;
    private RecyclerView.Adapter<CrimeHolder> mAdapter;


    /**
     * A crimeHolder {@link android.support.v7.widget.RecyclerView.ViewHolder} subclass
     * */
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mCheckBox;
        private Crime mCrime;

        public CrimeHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
            itemView.setOnClickListener(this);
        }

        public void bindData(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(crime.getTitle());
            mDateTextView.setText(crime.getDate().toString());
            mCheckBox.setChecked(crime.isSolved());
        }

        /**
         * Implements method of {@link android.view.View.OnClickListener}
         * */
        @Override public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CrimeActivity.class);
            intent.putExtra(CrimeActivity.EXTRA_CRIME_ID, mCrime.getID());
            getActivity().startActivity(intent);
        }
    }

    /**
     * A subclass of {@link android.support.v7.widget.RecyclerView.Adapter}
     * */
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override public void onBindViewHolder(CrimeHolder holder, int position) {
            holder.bindData(mCrimes.get(position));
        }

        @Override public int getItemCount() {
            return mCrimes.size();
        }
    }


    public CrimeListFragment(){}

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimerRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimerRecyclerView.setAdapter(mAdapter);
    }

    @Override public void onResume(){
        super.onResume();
        updateUI();
    }


}
