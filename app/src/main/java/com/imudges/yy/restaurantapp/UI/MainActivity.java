package com.imudges.yy.restaurantapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import com.imudges.yy.restaurantapp.Bean.User;
import com.imudges.yy.restaurantapp.R;
import com.imudges.yy.restaurantapp.UI.Fragment.MainFragment;
import com.imudges.yy.restaurantapp.UI.Fragment.OrderFragment;
import com.imudges.yy.restaurantapp.UI.Fragment.UserFragment;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity{
    private MainFragment mainFragment;
    private OrderFragment orderFragment;
    private UserFragment userFragment;

    private Fragment isFragment= null;

    @ViewInject(R.id.img_btn_home)
    private ImageButton imgBtnHome;

    @ViewInject(R.id.img_btn_order)
    private ImageButton imgBtnOrder;

    @ViewInject(R.id.img_btn_user)
    private ImageButton imgBtnUser;

    private Toolbar toolbar;

    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(mainFragment!=null){
            fragmentTransaction.hide(mainFragment);
        }
        if(orderFragment!=null){
            fragmentTransaction.hide(orderFragment);
        }
        if(userFragment!=null){
            fragmentTransaction.hide(userFragment);
        }
    }

    private void initFragment(){
        mainFragment = new MainFragment();
        orderFragment = new OrderFragment();
        userFragment = new UserFragment();
        isFragment = mainFragment;
    }

    private void setSelect(int postion){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (postion){
            case 0:
                fragmentTransaction.hide(isFragment).show(mainFragment);
                imgBtnHome.setImageResource(R.mipmap.home_fill);
                fragmentTransaction.replace(R.id.id_content,mainFragment);
                supportInvalidateOptionsMenu();
                break;
            case 1:
                if(!orderFragment.isAdded()){
                    fragmentTransaction.add(R.id.id_content,orderFragment).hide(isFragment).show(orderFragment);
                } else {
                    fragmentTransaction.hide(isFragment).show(orderFragment);
                }
                imgBtnOrder.setImageResource(R.mipmap.form_fill);
                fragmentTransaction.replace(R.id.id_content,orderFragment);
                supportInvalidateOptionsMenu();
                break;
            case 2:
                if(!userFragment.isAdded()){
                    fragmentTransaction.add(R.id.id_content,userFragment).hide(isFragment).show(userFragment);
                } else {
                    fragmentTransaction.hide(isFragment).show(userFragment);
                }
                imgBtnUser.setImageResource(R.mipmap.my_fill);
                fragmentTransaction.replace(R.id.id_content,userFragment);
                supportInvalidateOptionsMenu();
                break;
        }
        fragmentTransaction.commit();
    }


    @Event(value = R.id.linear_layout_main,type = View.OnClickListener.class)
    private void onclickImgBtnHome(View view){
        changeNormalPic();
        setSelect(0);
    }

    @Event(value = R.id.linear_layout_order,type = View.OnClickListener.class)
    private void onclickImgBtnOrder(View view){
        changeNormalPic();
        setSelect(1);
    }

    @Event(value = R.id.linear_layout_user,type = View.OnClickListener.class)
    private void onclickImgBtnUser(View view){
        changeNormalPic();
        setSelect(2);
    }

    private void changeNormalPic(){
        imgBtnHome.setImageResource(R.mipmap.home);
        imgBtnOrder.setImageResource(R.mipmap.form);
        imgBtnUser.setImageResource(R.mipmap.my);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragment();
        setSelect(0);
    }

}