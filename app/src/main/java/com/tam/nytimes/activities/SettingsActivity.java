package com.tam.nytimes.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.tam.nytimes.R;
import com.tam.nytimes.fragments.DatePickerFragment;
import com.tam.nytimes.models.FilterOptions;
import com.tam.nytimes.models.SortOrder;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Bind(R.id.spinnerSortOrder)
    Spinner spinnerSortOrder;

    @Bind(R.id.etBeginDate)
    EditText etBeginDate;

    @Bind(R.id.cbArts)
    CheckBox cbArts;

    @Bind(R.id.cbFashion)
    CheckBox cbFashion;

    @Bind(R.id.cbSports)
    CheckBox cbSports;

    private void setupView() {
        ButterKnife.bind(this);
        //FilterOptions filterOptions = (FilterOptions) getIntent().getSerializableExtra("filterOptions");
        FilterOptions filterOptions = SearchActivity.filterOptions;
        if (filterOptions.getSortOrder() == SortOrder.Newest) {
            spinnerSortOrder.setSelection(0);
        } else {
            spinnerSortOrder.setSelection(1);
        }
        cbArts.setChecked(filterOptions.isCbArts());
        cbFashion.setChecked(filterOptions.isCbFashionStyle());
        cbSports.setChecked(filterOptions.isCbSports());
        Calendar c = filterOptions.getBeginDate();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        etBeginDate.setText(day + "/" + (month + 1) + "/" + year);
        etBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onSaveSettings(View view) {
        SearchActivity.filterOptions.setCbArts(cbArts.isChecked());
        SearchActivity.filterOptions.setCbFashionStyle(cbFashion.isChecked());
        SearchActivity.filterOptions.setCbSports(cbSports.isChecked());
        if (spinnerSortOrder.getSelectedItem().toString().equals(SortOrder.Newest.toString())) {
            SearchActivity.filterOptions.setSortOrder(SortOrder.Newest);
        } else {
            SearchActivity.filterOptions.setSortOrder(SortOrder.Oldest);
        }
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        SearchActivity.filterOptions.setBeginDate(c);
        etBeginDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year );
    }
}
