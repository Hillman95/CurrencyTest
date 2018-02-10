package com.hillman.currencytest.mvp;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.hillman.currencytest.R;
import com.hillman.currencytest.adapters.CurrenciesAdapter;
import com.hillman.currencytest.api.model.Currency;
import com.hillman.currencytest.ui.DividerItemDecoration;
import java.util.List;




public class CurrencyView extends AppCompatActivity {

    Handler handler;
    MenuItem menuItem;
    RecyclerView recyclerView;
    CurrenciesAdapter adapter;
    ProgressBar loadProgress, updateProgress;
    CurrencyPresenter presenter;
    CurrencyModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        model = new CurrencyModel();
        presenter = new CurrencyPresenter(model);
        presenter.attachView(this);
        presenter.viewIsReady();
        updateListByInterval();

    }


    public void initViews(){
        loadProgress = findViewById(R.id.load_progress);
        updateProgress = findViewById(R.id.update_progres);
        loadProgress.setVisibility(View.VISIBLE);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

    }

    public void updateListByInterval() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                presenter.updateCurrenciesList();
                handler.postDelayed(this, 15000);
            }
        }, 15000);
    }


    public void listInit(List<Currency> currencies){
        adapter = new CurrenciesAdapter(currencies);
        updateProgress.setVisibility(View.GONE);
        loadProgress.setVisibility(View.GONE);
        recyclerView.setAdapter(adapter);
        if (menuItem!=null){
        menuItem.setEnabled(true);}
    }

    public void listUpdate(){
        updateProgress.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuItem = item;
        int id = item.getItemId();
        switch (id){
            case R.id.update :
              if (adapter!=null){
              presenter.updateCurrenciesList();
              menuItem.setEnabled(false);}
        }

        return super.onOptionsItemSelected(item);
    }
}

