package com.example.terrible;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
      EditText edt1;
      Button txt1,btn2;
      TextView txt2,input;
    String[] w = null;
     ProgressDialog progressDialog;
    String last="";
    int count;
    int[] r = null;
      String txt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=findViewById(R.id.edt1);
        //input=findViewById(R.id.input);
        txt1=findViewById(R.id.btn1);
        txt2=findViewById(R.id.txt2);
        btn2=findViewById(R.id.btn2);

        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt1.getText()))
                {
                    edt1.setError("Required Field..");
                    return;

                }
                else
                {
                    progressDialog=new ProgressDialog(MainActivity.this);
                    progressDialog.setMessage("loading...");
                    progressDialog.show();
                    ReadFileTask tsk=new ReadFileTask ();
                    tsk.execute("https://terriblytinytales.com/test.txt");
                    count=1;
                }




            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(count==1)
                {

                    find();
                    init();
                    count=2;

                }else if(count==2)
                {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Kindly Refresh for better Results");
                    alertDialogBuilder.setPositiveButton("Refresh", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(MainActivity.this,MainActivity.class));
                        }
                    });
                    alertDialogBuilder.show();

                }
                else
                {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Kindly click the first Button");
                    alertDialogBuilder.setPositiveButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialogBuilder.show();
                }


            }
        });

    }
    private class ReadFileTask extends AsyncTask<String,Integer,Void> {

        protected Void doInBackground(String...params){
            URL url;
            try {
                url = new URL(params[0]);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                InputStream is=con.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line;
                while((line=br.readLine())!=null){
                    txt+=line;

                }
                progressDialog.cancel();
                txt2.setText(txt);

                txt2.setMovementMethod(new ScrollingMovementMethod());

                br.close();

            }catch (Exception e) {
                e.printStackTrace();
            }

            return null;

        }}
        public void find()
        {



                    try {
                        int n =Integer.parseInt( edt1.getText().toString());
                        w = new String[n];
                        r = new int[n];
                        String[] words = txt.split(" ");
                        String[] Labels;
                        int count = 0;
                        Labels = getLabels(words);
                        for(int j=0; j<n; j++){
                            r[j] = 0;
                        }
                        for(String l: Labels)
                        {
                            if("".equals(l) || null == l)
                            {
                                break;
                            }
                            for(String s : words)
                            {
                                if(l.equals(s))
                                {
                                    count++;
                                }
                            }

                            for(int i=0; i<n; i++){
                                if(count>r[i]){
                                    r[i] = count;
                                    w[i] = l;
                                    break;
                                }
                            }
                            count=0;
                        }
                       // display(n);
                    } catch (Exception e) {
                        //Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
                    }
                }

                public  String[] getLabels(String[] name)
                {
                    String[] uniqueKeys = new String[name.length];

                    uniqueKeys[0] = name[0];
                    int uniqueKeyIndex = 1;
                    boolean keyAlreadyExists = false;

                    for(int i=1; i<name.length ; i++)
                    {
                        for(int j=0; j<=uniqueKeyIndex; j++)
                        {
                            if(name[i].equals(uniqueKeys[j]))
                            {
                                keyAlreadyExists = true;
                            }
                        }

                        if(!keyAlreadyExists)
                        {
                            uniqueKeys[uniqueKeyIndex] = name[i];
                            uniqueKeyIndex++;
                        }
                        keyAlreadyExists = false;
                    }
                    return uniqueKeys;
                }
    public void init() {
        int n=Integer.parseInt(edt1.getText().toString());
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("          frequency words          ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" frequency ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);

        stk.addView(tbrow0);
        for (int i = 0; i < n; i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + w[i]);
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(""+r[i]);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);

            stk.addView(tbrow);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("refresh"))
        {
            startActivity(new Intent(MainActivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}