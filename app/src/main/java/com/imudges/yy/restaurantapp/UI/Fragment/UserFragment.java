package com.imudges.yy.restaurantapp.UI.Fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.ViewUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.imudges.yy.restaurantapp.R;
import com.imudges.yy.restaurantapp.Tool.Config;
import com.imudges.yy.restaurantapp.Tool.SharePreferenceManager;
import com.imudges.yy.restaurantapp.Tool.Toasty;
import com.imudges.yy.restaurantapp.UI.LoginActivity;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by yangyang on 2017/7/3.
 */
@ContentView(R.layout.fragment_user)
public class UserFragment extends BaseFragment{

    private String ak = null;

    @ViewInject(R.id.tv_user_name)
    private TextView tvUsername;

    @ViewInject(R.id.linear_layout_login)
    private LinearLayout linearLayout;

    @Event(value = R.id.linear_layout_login,type = View.OnClickListener.class)
    private void onclickLogin(View view){
        if(checkLoginStatus()){
            //跳转至个人信息界面
            Toasty.toasty(getContext(),"用户已登录");
        } else {
            //跳转至登陆界面
            Toasty.toasty(getContext(),"用户未登陆");
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    private boolean checkLoginStatus(){
        ak = SharePreferenceManager.readString(getContext(),"restaurant_ak");
        if(ak == null || ak.equals("")){
            return false;
        } else {
            return true;
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!checkLoginStatus()){
            tvUsername.setText("登陆/注册");
        } else {
            String username = SharePreferenceManager.readString(getContext(),"restaurant_username");
            tvUsername.setText(username);
        }
    }
}
