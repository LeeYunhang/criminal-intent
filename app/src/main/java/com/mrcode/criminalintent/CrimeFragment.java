package com.mrcode.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import java.util.UUID;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE  = "DialogDate";

    private Crime mCrime;
    @BindView(R.id.editText)   protected EditText mTitleField;
    @BindView(R.id.crime_date) protected Button mDateButton;
    @BindView(R.id.checkBox)   protected CheckBox mSolvedCheckBox;
    private Unbinder mUnbinder;



    public CrimeFragment() {}

    @OnClick(R.id.crime_date)
    protected void clickDateButton(Button btn){
        FragmentManager fragmentManager = getFragmentManager();
        DatePickerFragment dialog = new DatePickerFragment();
        dialog.show(fragmentManager, DIALOG_DATE);
    }

    @OnCheckedChanged(R.id.checkBox)
    protected void checkChange(boolean checked){
        mCrime.setSolved(checked);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
            mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mDateButton.setText(mCrime.getDate().toString());
        mTitleField.setText(mCrime.getTitle());
        mDateButton.setText(mCrime.getDate().toString());

        mSolvedCheckBox.setChecked(mCrime.isSolved());

        return view;
    }

    public static CrimeFragment newInstance(UUID crimeId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_CRIME_ID, crimeId);
        CrimeFragment crimeFragment = new CrimeFragment();
        crimeFragment.setArguments(bundle);

        return crimeFragment;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
