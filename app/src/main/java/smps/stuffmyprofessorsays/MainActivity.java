package smps.stuffmyprofessorsays;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    private MainAdapter theMainAdapter;
    private ViewPager   theViewPager;
    private FragmentSwitcherManager fragmentSwitcher;
    private Bundle bun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        bun = savedInstanceState;
        if(bun == null){
            bun = new Bundle();
            bun.putInt("Current", 1);
        }

        theMainAdapter = new MainAdapter(getSupportFragmentManager());
        theViewPager = (ViewPager) findViewById(R.id.main_pages);
        theViewPager.setAdapter(theMainAdapter);

        theViewPager.setOffscreenPageLimit(2);

        if(fragmentSwitcher == null) {
            fragmentSwitcher = new FragmentSwitcherManager(theViewPager, 0);

            Button button;
            View view;

            button = (Button) findViewById(R.id.main_button_1);
            //button.setText("Trending");
            view = findViewById(R.id.main_bar_1);
            view.setVisibility(View.INVISIBLE);
            fragmentSwitcher.add(button, view);

            button = (Button) findViewById(R.id.main_button_2);
            //button.setText("New");
            view = findViewById(R.id.main_bar_2);
            view.setVisibility(View.INVISIBLE);
            fragmentSwitcher.add(button, view);


        }else {
            fragmentSwitcher.setViewPager(theViewPager);
        }
        fragmentSwitcher.setPage(bun.getInt("Current"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
