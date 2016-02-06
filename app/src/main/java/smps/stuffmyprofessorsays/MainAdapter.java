package smps.stuffmyprofessorsays;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MainAdapter extends FragmentStatePagerAdapter {

    public MainAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     *
     * @param position position of viewPager
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        switch(position){
            default:
                return new TrendingFeed();
            case 1:
                return new NewFeed();
        }
    }

    /**
     *
     * @param object Fragment in the viewpager
     * @return
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }
}
