package io.github.zwieback.familyfinance.business.chart.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import io.github.zwieback.familyfinance.R;
import io.github.zwieback.familyfinance.business.chart.adapter.ChartFragmentPagerAdapter;
import io.github.zwieback.familyfinance.business.chart.display.ChartDisplay;
import io.github.zwieback.familyfinance.business.chart.fragment.ChartFragment;
import io.github.zwieback.familyfinance.business.chart.listener.ChartDisplayListener;
import io.github.zwieback.familyfinance.business.operation.filter.OperationFilter;
import io.github.zwieback.familyfinance.business.operation.listener.OperationFilterListener;
import io.github.zwieback.familyfinance.core.activity.DataActivityWrapper;

public class ChartActivity extends DataActivityWrapper
        implements OperationFilterListener<OperationFilter>, ChartDisplayListener {

    private static final String TAB_POSITION = "tabPosition";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ChartFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTabs();
    }

    @Override
    protected void setupContentView() {
        setContentView(R.layout.activity_chart);
    }

    @Override
    protected int getTitleStringId() {
        return R.string.chart_activity_title;
    }

    private void setupTabs() {
        pagerAdapter = new ChartFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TAB_POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(TAB_POSITION));
    }

    // -----------------------------------------------------------------------------------------
    // Fragment methods
    // -----------------------------------------------------------------------------------------

    private ChartFragment findFragment() {
        return pagerAdapter.findFragment(tabLayout.getSelectedTabPosition());
    }

    // -----------------------------------------------------------------------------------------
    // Filter methods
    // -----------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    @Override
    public void onApplyFilter(OperationFilter filter) {
        findFragment().onApplyFilter(filter);
    }

    // -----------------------------------------------------------------------------------------
    // Display methods
    // -----------------------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    @Override
    public void onApplyDisplay(ChartDisplay display) {
        findFragment().onApplyDisplay(display);
    }
}
