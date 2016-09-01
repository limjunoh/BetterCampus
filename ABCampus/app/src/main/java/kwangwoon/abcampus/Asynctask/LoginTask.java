package kwangwoon.abcampus.Asynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import kwangwoon.abcampus.Constants;

/**
 * Created by Administrator on 2016-09-01.
 */
public class LoginTask extends AsyncTask<String, Void, Void> {

    private String student_id, password;
    // 첫번째 Parameter는 입력
    // 두번째 Parameter는 progress
    // 세번째 Parameter는 Result 리턴값
    public LoginTask(String student_id, String password){
        this.student_id = student_id;
        this.password = password;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... params) {
        try{
            URL url = new URL(Constants.BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setAllowUserInteraction(true);
            conn.setConnectTimeout(6000);          // 타임아웃

            //Http Header Setting
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            //Http Body Setting
            String body = "login_type=2&redirect_url=http%3A%2F%2Finfo.kw.ac.kr%2F&layout_opt=N&gubun_code=11&member_no="+student_id+"&password="+password;
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes("euc-kr"));
            os.flush();
            os.close();

            //Http Parameter Sending
            String strCookie = conn.getHeaderField("Set-Cookie");

            InputStream is = conn.getInputStream();

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"euc-kr"));
            String line;

            while((line = reader.readLine()) != null){
                builder.append(line+ "\n");
            }

            String result = builder.toString();
            Log.d(Constants.TAG,result);

            reader.close();
        }catch(MalformedURLException exception){
            exception.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
