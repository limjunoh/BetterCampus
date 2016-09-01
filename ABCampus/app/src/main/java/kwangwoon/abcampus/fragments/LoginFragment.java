package kwangwoon.abcampus.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import kwangwoon.abcampus.Asynctask.LoginTask;
import kwangwoon.abcampus.R;
import kwangwoon.abcampus.models.User;

/**
 * Created by Administrator on 2016-09-01.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_studentid, et_password;
    private ProgressBar progress;
    private SharedPreferences pref;
    private TextView tv_findout;
    private LoginTask loginTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_findout = (TextView)view.findViewById(R.id.tv_findout);
        et_studentid = (EditText)view.findViewById(R.id.et_studentid);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_findout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.tv_findout:
                goToFindOut();
                break;

            case R.id.btn_login:
                String studentid = et_studentid.getText().toString();
                String password = et_password.getText().toString();

                if(!studentid.isEmpty() && !password.isEmpty()){
                    progress.setVisibility(View.VISIBLE);
                    loginProcess(studentid,password);
                }
                else{
                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
        }
    }
    private boolean loginProcess(String student_id, String password){      //여기를 바꿔야함 패킷 분석 필요
        if(!check_form())
            return false;

        User user = new User();
        user.setStudent_id(student_id);
        user.setPassword(password);

        loginTask = new LoginTask(student_id,password);
        loginTask.execute("11");

        return true;
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setStudent_id(student_id);
        user.setPassword(password);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                Snackbar.make(rootview, resp.getMessage(), Snackbar.LENGTH_LONG).show();
                if(resp.getResult().equals(Constants.SUCCESS)){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean(Constants.IS_LOGGED_IN, true);
                    editor.putString(Constants.STUDENT_ID, resp.getUser().getStudent_id());
                    editor.putString(Constants.NAME, resp.getUser().getUnique_id());
                    editor.putString(Constants.UNIQUE_ID, resp.getUser().getUnique_id());
                    editor.apply();
                    goToProfile();
                }
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG, "Failed");
                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
        });*/
    }

    private void goToFindOut(){
        Fragment findout = new FindOutFragment();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, findout);
        ft.commit();
    }

    private void goToProfile(){
        Fragment profile = new ProfileFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, profile);
        ft.commit();
    }

    private boolean check_form(){
        String id = et_studentid.getText().toString();
        String password = et_password.getText().toString();

        if(id.equals("")){
            Snackbar.make(getView(), "학번을 입력하세요", Snackbar.LENGTH_LONG).show();
            et_studentid.setFocusable(true);
            return false;
        }

        if(password.equals("")){
            Snackbar.make(getView(), "비밀번호를 입력하세요", Snackbar.LENGTH_LONG).show();
            et_password.setFocusable(true);
            return false;
        }

        return true;
    }
}
