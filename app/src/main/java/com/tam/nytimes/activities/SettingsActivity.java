package com.tam.nytimes.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.tam.nytimes.R;
import com.tam.nytimes.models.FilterOptions;
import com.tam.nytimes.models.SortOrder;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

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
        FilterOptions filterOptions = (FilterOptions) getIntent().getSerializableExtra("filterOptions");
        if (filterOptions.getSortOrder() == SortOrder.Newest) {
            spinnerSortOrder.setSelection(0);
        } else {
            spinnerSortOrder.setSelection(1);
        }
        cbArts.setSelected(filterOptions.isCbArts());
        cbFashion.setSelected(filterOptions.isCbSports());
        cbSports.setSelected(filterOptions.isCbSports());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}
