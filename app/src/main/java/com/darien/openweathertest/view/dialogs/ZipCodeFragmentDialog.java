package com.darien.openweathertest.view.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.darien.openweathertest.R;
import com.darien.openweathertest.controllers.WeatherController;
import com.darien.openweathertest.db.Zip;
import com.darien.openweathertest.view.activities.BaseActivity;
import com.darien.openweathertest.view.fragments.OnFragmentInteractionListener;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Darien
 * on 5/27/16.
 */
public class ZipCodeFragmentDialog extends DialogFragment {

    @BindView(R.id.defaultCheckBox)
    CheckBox defaultCheckBox;
    @BindView(R.id.zipCodeEditText)
    EditText zipCodeEditText;

    @Inject
    WeatherController controller;

    private OnFragmentInteractionListener mListener;

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.mListener = listener;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ZipCodeFragmentDialog.
     */
    public static ZipCodeFragmentDialog newInstance() {
        return new ZipCodeFragmentDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_zip_code,
                container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.inject(this);
        if (getView() != null) {
            ButterKnife.bind(this, getView());
        }
        getDialog().setTitle(R.string.title_add_zip_code);
    }

    @OnClick(R.id.addZipCodeBtn)
    void saveZipCode() {
        boolean current = defaultCheckBox.isChecked();
        String zipCode = zipCodeEditText.getText().toString();

        Zip zip = new Zip(null, zipCode, current, new Date());
        controller.addZipCodeToDatabase(zip);
        mListener.onFragmentInteraction();
        dismiss();
    }


}
