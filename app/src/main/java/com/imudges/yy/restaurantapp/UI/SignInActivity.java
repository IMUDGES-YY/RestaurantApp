package com.imudges.yy.restaurantapp.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.imudges.yy.restaurantapp.R;
import com.imudges.yy.restaurantapp.Tool.CheckPhone;
import com.imudges.yy.restaurantapp.Tool.CountDownTimerUtils;
import com.imudges.yy.restaurantapp.Tool.Toasty;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by yangyang on 2017/7/1.
 */
@ContentView(R.layout.activity_sign_in)
public class SignInActivity extends BaseActivity {

    @ViewInject(R.id.et_sign_captcha)
    private EditText etSignCaptcha;

    @ViewInject(R.id.et_sign_phone_num)
    private EditText etPhoneNum;

    @ViewInject(R.id.et_sign_password)
    private EditText etPassword;

    @ViewInject(R.id.btn_commit)
    private Button btnCommit;

    /**
     * 点击提交注册按钮，依次检查：验证码、密码
     * */
    @Event(value = R.id.btn_commit,
            type = View.OnClickListener.class)
    private void onCommitClick(View view){
        if(etSignCaptcha == null || etSignCaptcha.getText().toString().equals("")){
            Toasty.toasty(SignInActivity.this,"请输入验证码后提交");
            return ;
        } else if(etPassword == null || etPassword.getText().equals("")){
            Toasty.toasty(SignInActivity.this,"请输入密码后提交");
            return ;
        } else {
            //TODO 注册
        }
    }

    @ViewInject(R.id.btn_get_captcha)
    private Button btnGetCaptcha;

    @Event(value = R.id.btn_get_captcha,
            type = View.OnClickListener.class)
    private void onGetCaptchaClick(View view){
        if(etPhoneNum == null || etPhoneNum.getText().toString().equals("")){
            Toasty.toasty(SignInActivity.this,"请输入手机号码后获取验证码");
            return ;
        }
        String phoneNum = etPhoneNum.getText().toString();
        boolean isPhone = CheckPhone.isPhone(phoneNum);
        if(isPhone){
            //TODO 短信验证码
            CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(btnGetCaptcha,60000,1000);
            countDownTimerUtils.start();
        } else {
            Toasty.toasty(SignInActivity.this,"请输入正确的手机号码后获取验证码");
            return ;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
