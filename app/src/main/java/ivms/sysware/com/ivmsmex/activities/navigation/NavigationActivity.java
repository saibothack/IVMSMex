package ivms.sysware.com.ivmsmex.activities.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ivms.sysware.com.ivmsmex.R;
import ivms.sysware.com.ivmsmex.activities.BaseActivity;
import ivms.sysware.com.ivmsmex.activities.intro.VideoActivity;
import ivms.sysware.com.ivmsmex.fragments.reports.ReportFragment;
import ivms.sysware.com.ivmsmex.fragments.suggestions.SuggestionFragment;
import ivms.sysware.com.ivmsmex.fragments.tracking.TrackingFragment;
import ivms.sysware.com.ivmsmex.fragments.profiler.ProfilerFragment;
import ivms.sysware.com.ivmsmex.fragments.vehicle.VehicleFragment;
import ivms.sysware.com.ivmsmex.utils.SharedPreferenceUtil;

public class NavigationActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public SharedPreferenceUtil sharedPreferences;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        sharedPreferences = getMyApplication().getSharedPreferenceUtil();
        showToolbar();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        setFrame(TrackingFragment.class, R.string.tracking);

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.lblNameUser);
        navUsername.setText(sharedPreferences.getString(SharedPreferenceUtil.Key.nameUser));

        ImageView imageUser  = (ImageView) headerView.findViewById(R.id.imageView);
    }

    public void showToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.close_session) {
            closeSession();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void closeSession() {
        sharedPreferences.put(SharedPreferenceUtil.Key.bLogin, false);
        sharedPreferences.put(SharedPreferenceUtil.Key.idUser, null);
        sharedPreferences.put(SharedPreferenceUtil.Key.idVehicle, null);
        sharedPreferences.put(SharedPreferenceUtil.Key.nameUser, null);
        sharedPreferences.put(SharedPreferenceUtil.Key.platesVehicle, null);
        redirect(VideoActivity.class, true);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        item.setChecked(true);

        switch(item.getItemId()) {
            case R.id.nav_tracking:
                setFrame(TrackingFragment.class, R.string.tracking);
                break;
            case R.id.nav_profiler:
                setFrame(ProfilerFragment.class, R.string.profiler);
                break;
            case R.id.nav_vehicle:
                setFrame(VehicleFragment.class, R.string.vehicle);
                break;
            case R.id.nav_report:
                setFrame(ReportFragment.class, R.string.reports);
                break;
            case R.id.nav_suggestion:
                setFrame(SuggestionFragment.class, R.string.suggestions);
                break;

            default:
                setFrame(TrackingFragment.class, R.string.tracking);
        }

        return true;
    }

    public void setFrame(Class FragmentCls, int iTitle) {
        Fragment fragment = null;
        Class fragmentClass = FragmentCls;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        getSupportActionBar().setTitle(iTitle);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


}
