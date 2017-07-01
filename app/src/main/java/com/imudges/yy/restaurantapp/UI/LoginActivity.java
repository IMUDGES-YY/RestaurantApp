package com.imudges.yy.restaurantapp.UI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.imudges.yy.restaurantapp.R;
import com.imudges.yy.restaurantapp.Tool.MyParamsBuilder;
import com.imudges.yy.restaurantapp.Tool.Toasty;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by yangyang on 2017/7/1.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.et_username)
    private EditText etUsername;

    @ViewInject(R.id.et_password)
    private EditText etPassword;

    @ViewInject(R.id.btn_login)
    private Button btnLogin;

    @Event(value=R.id.btn_login,
            type = View.OnClickListener.class)
    private void onLoginClick(View view){
        //确认是否填写了用户名密码
        if (etUsername == null || etPassword == null || etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")) {
            Toasty.toasty(LoginActivity.this,"请输入用户名与密码");
            return;
        }
        //获取输入的用户名与密码
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        //创建请求参数对象
        RequestParams params = new MyParamsBuilder("login", true)
                .addParameter("username", username)
                .addParameter("password", password)
                .builder();

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(result);
                int ret = jsonObject.get("ret").getAsInt();
                if(ret == 0){
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("config", LoginActivity.this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ak", jsonObject.get("data").getAsJsonObject().get("ak").getAsString());
                    editor.commit();
                    Toasty.toasty(LoginActivity.this,jsonObject.get("data").getAsJsonObject().get("ak").getAsString());
                } else {
                    Toasty.toasty(LoginActivity.this,"用户名或密码错误");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toasty.toasty(LoginActivity.this,"内部错误");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
