package com.qtcteam.loginmvc.model.api;

import com.qtcteam.loginmvc.model.scheme.in.LoginInRO;
import com.qtcteam.loginmvc.model.scheme.in.UserInRO;
import com.qtcteam.loginmvc.model.scheme.out.BaseOutRO;
import com.qtcteam.loginmvc.model.scheme.out.UserOutRO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("user/login")
    Call<UserOutRO> login(@Body LoginInRO user);

    @POST("user/create")
    Call<BaseOutRO> createUser(@Body UserInRO user);

}
