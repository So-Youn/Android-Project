package multi.android.network.http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import multi.android.network.R;

public class JSONTestActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ListView listView;
    ArrayList<ProductDTO> list;
    ProductAdapter itemAdapter;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n_test);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
       // progressDialog = new ProgressDialog(this);
        HttpTest task = new HttpTest();
        task.execute();

    }
    //백단에서 작업 - AsyncTask
    class HttpTest extends AsyncTask<Void,Void,String>{

       /* @Override
        protected void onPreExecute() {
            progressDialog.setTitle("HTTP Connect ..");
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }*/
        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            BufferedReader in=null;
            // Background 작업이 모두 완료되면  onPostEXecute를 호출하며 전달할 데이터
            //=======-> 웹 서버에서 받아온 json 데이터
            String data="";
            //progressDialog.dismiss();
            try {
                //android.os.NetworkOnMainThreadException이 발생한다.
                //메인쓰레드는 정지시킬 수 없다.
                // 웹 상의 리소스를 가져오기 위해서  URL객체를 생성
                String path = "http://70.12.115.64:8088/bigdataShop/product/show_json";
                 url = new URL(path);
                 //웹 서버에 연결
                 HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                 //연결된 HttpURLConnection에 정보 설정주기
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json"); // 설정정보
                //응답을 정상으로 받았을 때 실행하겠다는 의미
                if (connection.getResponseCode()==HttpURLConnection.HTTP_OK) {
                    //연결된 서버에서 응답메시지를 받은 경우 응답 메시지를
                    // BufferedReader로 읽어온다. - Json 데이터가 모두  BufferedReader에서 읽을 수 있도록 설정
                    in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream(),"UTF-8")
                            );
                    data = in.readLine(); //br에 저장된 모든 데이터를 한 줄로 읽는다.
                    Log.d("myhttp",data);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String s) { //doInBackground 이후 작업됨
            super.onPostExecute(s);
           // progressDialog.dismiss();
            /*
            웹 서버에서 가져온 데이터가 json 형식이므로
            파싱해서 jsonobject를 ProductDTO로 변환
            변환한 ProductDTO를 ArrayList에 저장
             */
            JSONArray ja = null; //arrayList 와 비슷 - 서버에서 받아온 데이터
            // [] : JsonArray
            // {} : JsonObject
            try {
                ja = new JSONArray(s);
                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String name = jo.getString("prd_no");
                    String id = jo.getString("prd_nm");
                    String img = jo.getString("img_gen_file_nm");

                    ProductDTO item = new ProductDTO(id,name,img);
                    list.add(item);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            itemAdapter = new ProductAdapter(list);
            listView.setAdapter(itemAdapter);

        }
    }
    class ProductAdapter extends BaseAdapter {
        ArrayList<ProductDTO> alist;

        public ProductAdapter(ArrayList<ProductDTO> alist) {
            this.alist = alist;
        }

        @Override
        public int getCount() {
            return alist.size();
        }

        @Override
        public Object getItem(int position) {
            return alist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item, container, true);
            TextView prd_no = itemView.findViewById(R.id.textView);
            TextView prd_nm = itemView.findViewById(R.id.textView2);

            final ImageView imageView = itemView.findViewById(R.id.imageView);

            prd_no.setText(alist.get(position).getPrd_no());
            prd_nm.setText(alist.get(position).getPrd_nm());

            String img = alist.get(position).getImg_gen_file_nm();
            img = "http://70.12.115.64:8088/bigdataShop/images/product/"+img;
            final String finalImg = img;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() { // 이미지 decodeStream를 통해 저장.
                    URL url = null;
                    InputStream is = null;
                    try{
                        url = new URL(finalImg);
                        is = url.openStream();
                        final Bitmap bm = BitmapFactory.decodeStream(is);
                        runOnUiThread(new Runnable() { //Thread에서 뷰 접근

                            @Override
                            public void run() {
                                imageView.setImageBitmap(bm);
                            }
                        });
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            return itemView;


        }
    }
}
