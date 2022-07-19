package tw.com.fasttrackspending;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import tw.com.fasttrackspending.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Boolean bool=false;
    private EditText edit;
    //private String sum="0";
    private SharedPreferences spBreakfast,spLunch,spdinner,spdrinks;
    private int sum=0;//用來記錄總金額
    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 kk:mm分");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        String ss =sdf.format(new Date());
        Amount_binding_event();
        spBreakfast = getSharedPreferences("breakfast",MODE_PRIVATE);
        spLunch = getSharedPreferences("Lunch",MODE_PRIVATE);
        spdinner = getSharedPreferences("dinner",MODE_PRIVATE);
        spdrinks = getSharedPreferences("drinks",MODE_PRIVATE);
        //edit.setText(spBreakfast.getString("amount","查無資料"));

        //歷史
        binding.historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog_history();
                Log.d("abc","早餐:"+spBreakfast.getString("amount","查無資料"));
                Log.d("abc","午餐:"+spLunch.getString("amount","查無資料"));
                Log.d("abc","晚餐:"+spdinner.getString("amount","查無資料"));
                Log.d("abc","飲料:"+spdrinks.getString("amount","查無資料"));
            }
        });

    }


    //綁定共用事件
    private void Amount_binding_event(){

        binding.breakfast30.setOnClickListener(clickListenerbreakfast);
        binding.breakfast40.setOnClickListener(clickListenerbreakfast);
        binding.breakfast50.setOnClickListener(clickListenerbreakfast);
        binding.breakfast60.setOnClickListener(clickListenerbreakfast);
        binding.breakfast70.setOnClickListener(clickListenerbreakfast);
        binding.breakfast80.setOnClickListener(clickListenerbreakfast);
        binding.breakfasEdit.setOnClickListener(clickListener);
        binding.BreakfastCustomAmount.setOnClickListener(clickListener);

        binding.Lunch30.setOnClickListener(clickListenerLunch);
        binding.Lunch40.setOnClickListener(clickListenerLunch);
        binding.Lunch50.setOnClickListener(clickListenerLunch);
        binding.Lunch60.setOnClickListener(clickListenerLunch);
        binding.Lunch70.setOnClickListener(clickListenerLunch);
        binding.Lunch80.setOnClickListener(clickListenerLunch);
        binding.LunchEdit.setOnClickListener(clickListener);
        binding.LunchCustomAmount.setOnClickListener(clickListener);

        binding.dinner30.setOnClickListener(clickListenerdinner);
        binding.dinner40.setOnClickListener(clickListenerdinner);
        binding.dinner50.setOnClickListener(clickListenerdinner);
        binding.dinner60.setOnClickListener(clickListenerdinner);
        binding.dinner70.setOnClickListener(clickListenerdinner);
        binding.dinner80.setOnClickListener(clickListenerdinner);
        binding.dinnerEdit.setOnClickListener(clickListener);
        binding.dinnerCustomAmount.setOnClickListener(clickListener);

        binding.drinks20.setOnClickListener(clickListenerdrinks);
        binding.drinks25.setOnClickListener(clickListenerdrinks);
        binding.drinks30.setOnClickListener(clickListenerdrinks);
        binding.drinks40.setOnClickListener(clickListenerdrinks);
        binding.drinks50.setOnClickListener(clickListenerdrinks);
        binding.drinks60.setOnClickListener(clickListenerdrinks);
        binding.drinksEdit.setOnClickListener(clickListener);
        binding.drinksCustomAmount.setOnClickListener(clickListener);


    }

    //修改顏色
    private void EditcolorRED(int color){
        color = getColor(R.color.grey2);
        Toast.makeText(this, "請選擇要修改金額的按鈕", Toast.LENGTH_SHORT).show();
        binding.breakfast30.setBackgroundColor(color);
        binding.breakfast40.setBackgroundColor(color);
        binding.breakfast50.setBackgroundColor(color);
        binding.breakfast60.setBackgroundColor(color);
        binding.breakfast70.setBackgroundColor(color);
        binding.breakfast80.setBackgroundColor(color);

        binding.Lunch30.setBackgroundColor(color);
        binding.Lunch40.setBackgroundColor(color);
        binding.Lunch50.setBackgroundColor(color);
        binding.Lunch60.setBackgroundColor(color);
        binding.Lunch70.setBackgroundColor(color);
        binding.Lunch80.setBackgroundColor(color);

        binding.dinner30.setBackgroundColor(color);
        binding.dinner40.setBackgroundColor(color);
        binding.dinner50.setBackgroundColor(color);
        binding.dinner60.setBackgroundColor(color);
        binding.dinner70.setBackgroundColor(color);
        binding.dinner80.setBackgroundColor(color);
        binding.drinks20.setBackgroundColor(color);
        binding.drinks25.setBackgroundColor(color);
        binding.drinks30.setBackgroundColor(color);
        binding.drinks40.setBackgroundColor(color);
        binding.drinks50.setBackgroundColor(color);
        binding.drinks60.setBackgroundColor(color);
    }
    //早餐
    View.OnClickListener clickListenerbreakfast=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edit=(EditText)view;
            String str=edit.getText().toString();
            if(str.equals("修改金額"))
            {
                EditcolorRED(Color.RED);
                bool=true;
            }
            else if(bool)
            {
                dialog();
                bool=false;
            }else
            {

                spBreakfast.edit()
                        .putString("amount",str)
                        // .clear()
                        .commit();

                binding.totle.setText(str);
            }

        }
    };
    //午餐
    View.OnClickListener clickListenerLunch=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edit=(EditText)view;
            String str=edit.getText().toString();
            if(str.equals("修改金額"))
            {
                EditcolorRED(Color.RED);
                bool=true;
            }
            else if(bool)
            {
                dialog();
                bool=false;
            }else
            {
                spLunch.edit()
                        .putString("amount",str)
                        // .clear()
                        .commit();
                binding.totle.setText(str);
            }
        }
    };
    //晚餐
    View.OnClickListener clickListenerdinner=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edit=(EditText)view;
            String str=edit.getText().toString();
            if(str.equals("修改金額"))
            {
                EditcolorRED(Color.RED);
                bool=true;
            }
            else if(bool)
            {
                dialog();
                bool=false;
            }else
            {
                spdinner.edit()
                        .putString("amount",str)
                        // .clear()
                        .commit();
                binding.totle.setText(str);
            }
        }
    };
    //飲料
    View.OnClickListener clickListenerdrinks=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edit=(EditText)view;
            String str=edit.getText().toString();
            if(str.equals("修改金額"))
            {
                EditcolorRED(Color.RED);
                bool=true;
            }
            else if(bool)
            {
                dialog();
                bool=false;
            }else
            {
                spdrinks.edit()
                        .putString("amount",str)
                        // .clear()
                        .commit();
                binding.totle.setText(str);
            }
        }
    };

    //金額共用事件
    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view){

            edit=(EditText)view;
            String str=edit.getText().toString();
            if(str.equals("自訂金額")){
                dialog();
            }
            if(str.equals("修改金額"))
            {
                EditcolorRED(Color.RED);
                bool=true;
            }
            else if(bool)
            {
                dialog();
                bool=false;
            }
            else
            {
                binding.totle.setText(str);
                //Log.d("abc",str.substring(0, str.indexOf("元")));
                int totle=Integer.parseInt(str.substring(0, str.indexOf("元")));
                sum+=totle;
                Log.d("abc",String.valueOf(sum));
//                System.exit(0);//正常退出App9
            }
        }
    };
    //彈跳視窗
    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(MainActivity.this); //final一個editText
        editText.setInputType( InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setTitle("請輸入金額");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(MainActivity.this, editText.getText().toString()+"元", Toast.LENGTH_SHORT).show();

                    //Log.d("abc",editText.getText().subSequence(0,editText.getText().toString().indexOf("元")).toString());
                    binding.totle.setText(editText.getText().toString());
                    edit.setText(editText.getText().toString()+"元");
                /*spBreakfast.edit()
                        .putString("amount",editText.getText().toString()+"元")
                        // .clear()
                        .commit();
                spLunch.edit()
                        .putString("amount",editText.getText().toString()+"元")
                        // .clear()
                        .commit();
                spdinner.edit()
                        .putString("amount",editText.getText().toString()+"元")
                        // .clear()
                        .commit();
                spdrinks.edit()
                        .putString("amount",editText.getText().toString()+"元")
                        // .clear()
                        .commit();*/
                    EditcolorRED(Color.parseColor("#FF0080FF"));

                //將get到的文字轉成字串才可以給Toast顯示哦
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    // 歷史按鈕
    private void dialog_history(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.mylistview,null);
        ListView l1 =(ListView) row.findViewById(R.id.listView);
        l1.setAdapter(new adapter(this));

        builder.setView(row);

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}


