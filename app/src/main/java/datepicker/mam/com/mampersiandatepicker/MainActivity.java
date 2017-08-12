package datepicker.mam.com.mampersiandatepicker;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import datepicker.mam.com.mampersiandatepicker.calender.MyPersianConvertor;
import datepicker.mam.com.persiandatepickermam.MAMPersianDatePickerDialog;
import datepicker.mam.com.persiandatepickermam.OnDialogResultSetListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLanguage("fa", getApplicationContext());
        final Button btnStart=(Button)findViewById(R.id.btnStarDate);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                MAMPersianDatePickerDialog persianDatePickerDialog = new MAMPersianDatePickerDialog();
                Bundle mBundle = new Bundle();
                mBundle.putCharSequence("dialogTitle", "تاریخ شروع را انتخاب نمایید");
                String fdate = MyPersianConvertor.persianDateTimeConvertor( MyPersianConvertor.yearMonthDay());
                mBundle.putString("initDate", fdate);
                persianDatePickerDialog.setArguments(mBundle);
                persianDatePickerDialog.show(fm, "fragDateTime");
                persianDatePickerDialog.setOnDateTimeSetListener(new OnDialogResultSetListener() {
                    @Override
                    public void ResultSet(String date) {
                        btnStart.setText(date);
                    }
                });
            }
        });
    }
    //set the languaue
    public static void setLanguage(String langCode, Context ctx) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        ctx.getResources().updateConfiguration(config, ctx.getResources().getDisplayMetrics());
    }

}
