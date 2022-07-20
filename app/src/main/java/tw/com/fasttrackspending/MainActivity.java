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
    private SharedPreferences spBreakfast,spLunch,spdinner,spdrinks;
    private int sum=0;//用來記錄總金額
    private String Month_Day;
    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        Month_Day =sdf.format(new Date());

        Log.d("abc",Month_Day);

        Amount_binding_event();//元件綁定事件
        spBreakfast = getSharedPreferences("breakfast",MODE_PRIVATE);//早餐
        spLunch = getSharedPreferences("Lunch",MODE_PRIVATE);//午餐
        spdinner = getSharedPreferences("dinner",MODE_PRIVATE);//晚餐
        spdrinks = getSharedPreferences("drinks",MODE_PRIVATE);//飲料
        get_Component_Amount();//取得元件金額
        //歷史
        binding.historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog_history();
            }
        });

    }
    //取得元件金額
    private void get_Component_Amount(){
        binding.breakfast30.setText(spBreakfast.getString("breakfast1","30元"));
        binding.breakfast40.setText(spBreakfast.getString("breakfast2","40元"));
        binding.breakfast50.setText(spBreakfast.getString("breakfast3","50元"));
        binding.breakfast60.setText(spBreakfast.getString("breakfast4","60元"));
        binding.breakfast70.setText(spBreakfast.getString("breakfast5","70元"));
        binding.breakfast80.setText(spBreakfast.getString("breakfast6","80元"));

        binding.Lunch30.setText(spLunch.getString("Lunch1","30元"));
        binding.Lunch40.setText(spLunch.getString("Lunch2","40元"));
        binding.Lunch50.setText(spLunch.getString("Lunch3","50元"));
        binding.Lunch60.setText(spLunch.getString("Lunch4","60元"));
        binding.Lunch70.setText(spLunch.getString("Lunch5","70元"));
        binding.Lunch80.setText(spLunch.getString("Lunch6","80元"));

        binding.dinner30.setText(spdinner.getString("dinner1","30元"));
        binding.dinner30.setText(spdinner.getString("dinner2","40元"));
        binding.dinner30.setText(spdinner.getString("dinner3","50元"));
        binding.dinner30.setText(spdinner.getString("dinner4","60元"));
        binding.dinner30.setText(spdinner.getString("dinner5","70元"));
        binding.dinner30.setText(spdinner.getString("dinner6","80元"));

        binding.drinks20.setText(spdrinks.getString("drinks1","20元"));
        binding.drinks25.setText(spdrinks.getString("drinks2","25元"));
        binding.drinks30.setText(spdrinks.getString("drinks3","30元"));
        binding.drinks40.setText(spdrinks.getString("drinks4","40元"));
        binding.drinks50.setText(spdrinks.getString("drinks5","50元"));
        binding.drinks60.setText(spdrinks.getString("drinks6","60元"));

        //計算累積金額
        Calculate_the_accumulated_amount();


    }
    //計算累積金額
    private void Calculate_the_accumulated_amount(){
        int breakfast,Lunch,dinner,drinks;
        breakfast=Integer.parseInt(spBreakfast.getString("amount","0").substring(0, spBreakfast.getString("amount","0").indexOf("元")));
        Lunch=Integer.parseInt(spLunch.getString("amount","0").substring(0, spLunch.getString("amount","0").indexOf("元")));
        dinner=Integer.parseInt(spdinner.getString("amount","0").substring(0, spdinner.getString("amount","0").indexOf("元")));
        drinks=Integer.parseInt(spdrinks.getString("amount","0").substring(0, spdrinks.getString("amount","0").indexOf("元")));
        binding.totle.setText(breakfast+Lunch+dinner+drinks+"元");
    }
    //儲存元件金額
    private void Component_Amount_Storage_Breakfast(){
        spBreakfast.edit()
                .putString("breakfast1",binding.breakfast30.getText().toString())
                .putString("breakfast2",binding.breakfast40.getText().toString())
                .putString("breakfast3",binding.breakfast50.getText().toString())
                .putString("breakfast4",binding.breakfast60.getText().toString())
                .putString("breakfast5",binding.breakfast70.getText().toString())
                .putString("breakfast6",binding.breakfast80.getText().toString())
                .commit();
        spLunch.edit()
                .putString("Lunch1",binding.Lunch30.getText().toString())
                .putString("Lunch2",binding.Lunch40.getText().toString())
                .putString("Lunch3",binding.Lunch50.getText().toString())
                .putString("Lunch4",binding.Lunch60.getText().toString())
                .putString("Lunch5",binding.Lunch70.getText().toString())
                .putString("Lunch6",binding.Lunch80.getText().toString())
                .commit();
        spdinner.edit()
                .putString("dinner1",binding.dinner30.getText().toString())
                .putString("dinner2",binding.dinner40.getText().toString())
                .putString("dinner3",binding.dinner50.getText().toString())
                .putString("dinner4",binding.dinner60.getText().toString())
                .putString("dinner5",binding.dinner70.getText().toString())
                .putString("dinner6",binding.dinner80.getText().toString())
                .commit();
        spdrinks.edit()
                .putString("drinks1",binding.drinks20.getText().toString())
                .putString("drinks2",binding.drinks25.getText().toString())
                .putString("drinks3",binding.drinks30.getText().toString())
                .putString("drinks4",binding.drinks40.getText().toString())
                .putString("drinks5",binding.drinks50.getText().toString())
                .putString("drinks6",binding.drinks60.getText().toString())
                .commit();
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
                //儲存元件金額8

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
                Component_Amount_Storage_Breakfast();

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


