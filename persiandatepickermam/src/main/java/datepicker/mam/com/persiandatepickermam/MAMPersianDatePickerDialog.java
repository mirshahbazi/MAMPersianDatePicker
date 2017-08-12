package datepicker.mam.com.persiandatepickermam;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aigestudio.wheelpicker.WheelPicker;
import com.aigestudio.wheelpicker.widgets.WheelYearPicker;

import java.util.ArrayList;

/**
 * Created by mam
 */
public class MAMPersianDatePickerDialog extends DialogFragment {
    public static final String TAG_FRAG_DATE_TIME = "fragDateTime";
    private static final String KEY_DIALOG_TITLE = "dialogTitle";
    private static final String KEY_INIT_DATE = "initDate";
    Typeface font;
    private Context context;
    private OnDialogResultSetListener mOnDialogResultSetListener;
    private Bundle mArgument;
    private WheelYearPicker npYear;
    private WheelPicker npMonth;
    private WheelYearPicker npDay;
    String[] years;
    String[] monthes;
    int lastDay;

    // DialogFragment constructor must be empty
    public MAMPersianDatePickerDialog() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
        font = Typeface.createFromAsset(context.getAssets(), getResources().getString(R.string.font));
    }

    /**
     * @param dialogTitle Title of the DateTimePicker DialogFragment
     * @param initDate    Initial Date and Time set to the Date and Time Picker
     * @return Instance of the DateTimePicker DialogFragment
     */
    public static MAMPersianDatePickerDialog newInstance(CharSequence dialogTitle,
                                                      String initDate) {
        // Create a new instance of DateTimePicker
        MAMPersianDatePickerDialog mDateTimePicker = new MAMPersianDatePickerDialog();
        // Setup the constructor parameters as arguments
        Bundle mBundle = new Bundle();
        mBundle.putCharSequence(KEY_DIALOG_TITLE, dialogTitle);
        mBundle.putString(KEY_INIT_DATE, initDate);
        mDateTimePicker.setArguments(mBundle);
        // Return instance with arguments
        return mDateTimePicker;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Retrieve Argument passed to the constructor
        mArgument = getArguments();
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context, R.style.MAMAlertDialogStyle);
        mBuilder.setIcon(getResources().getDrawable(R.drawable.date));
        mBuilder.setTitle(FontUtils.typeface(font, mArgument.getCharSequence(KEY_DIALOG_TITLE)));
        AlertDialog mDialog = mBuilder.create();
        mDialog.setView(createDateTimeView(mDialog.getLayoutInflater(),
                mArgument.getString(KEY_INIT_DATE)));
        return mDialog;
    }

    private View createDateTimeView(LayoutInflater layoutInflater,
                                    String initDate) {
        // Inflate the XML Layout using the inflater from the created Dialog
        View mView = layoutInflater.inflate(R.layout.dialog_date_picker, null);
        String[] ymd = initDate.split("/");
        npYear = (WheelYearPicker) mView.findViewById(R.id.npYear);
        npMonth = (WheelPicker) mView.findViewById(R.id.npMonth);
        npDay = (WheelYearPicker) mView.findViewById(R.id.npDay);
        Button select = (Button) mView.findViewById(R.id.btnSelect);
        Button cancel = (Button) mView.findViewById(R.id.btnCancel);
        select.setTypeface(font);
        cancel.setTypeface(font);
        ArrayList<String> monthes = new ArrayList<String>();
        monthes.add("فروردین");
        monthes.add("اردیبهشت");
        monthes.add("خرداد");
        monthes.add("تیر");
        monthes.add("مرداد");
        monthes.add("شهریور");
        monthes.add("مهر");
        monthes.add("آبان");
        monthes.add("آذر");
        monthes.add("دی");
        monthes.add("بهمن");
        monthes.add("اسفند");
        npYear.setYearStart(1376);
        npYear.setYearEnd(1416);
        npYear.setTypeface(font);
        npMonth.setTypeface(font);
        npMonth.setData(monthes);
        npDay.setYearStart(1);
        npDay.setYearEnd(31);
        npDay.setTypeface(font);

        //check if year is not in rang sent error to user
        if (Integer.parseInt(ymd[0])>= 1376 && Integer.parseInt(ymd[0])<= 1416)
        npYear.setSelectedYear(Integer.parseInt(ymd[0]));
        else {
            mOnDialogResultSetListener.ResultSet("Err:Year( "+ymd[0]+" )is not in range");
            dismiss();
        }
        //check if month is not in range sent error to user
        if (Integer.parseInt(ymd[0])>= 1 && Integer.parseInt(ymd[1])<= 12)
            npMonth.setSelectedItemPosition(Integer.parseInt(ymd[1])-1);
        else {
            mOnDialogResultSetListener.ResultSet("Err:Month( "+ymd[1]+" )is not in range");
            dismiss();
        }
        //check if day is not in range sent error to user
        if (Integer.parseInt(ymd[0])>= 1 && Integer.parseInt(ymd[2])<= 31)
            npDay.setSelectedYear(Integer.parseInt(ymd[2]));
        else {
            mOnDialogResultSetListener.ResultSet("Err:day( "+ymd[2]+" )is not in range");
            dismiss();
        }
        //for check day in range
        setDayOfMonth(Integer.parseInt(ymd[0]),Integer.parseInt(ymd[1]),Integer.parseInt(ymd[2]));



        npYear.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                checkAndSetDay(npYear.getCurrentYear(),npMonth.getCurrentItemPosition() + 1,npDay.getCurrentItemPosition() + 1);
            }
        });
        npMonth.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker wheelPicker, Object o, int i) {
                checkAndSetDay(npYear.getCurrentYear(),npMonth.getCurrentItemPosition() + 1,npDay.getCurrentItemPosition() + 1);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date;
                npYear.getSelectedYear();
                String m = String.valueOf(npMonth.getCurrentItemPosition() + 1);
                if (npMonth.getCurrentItemPosition() + 1 < 10)
                    m = "0" + m;
                String d = String.valueOf(npDay.getCurrentItemPosition() + 1);
                if (npDay.getCurrentItemPosition() + 1 < 10)
                    d = "0" + d;
                date = npYear.getCurrentYear() + "/" + m + "/" + d;
                mOnDialogResultSetListener.ResultSet(date);
                dismiss();
            }
        });
        return mView;
    }



    public void setOnDateTimeSetListener(
            OnDialogResultSetListener onDialogResultSetListener) {
        mOnDialogResultSetListener = onDialogResultSetListener;
    }



    public void setDayOfMonth(int year,int month ,int day) {
        if (day < 1){
            mOnDialogResultSetListener.ResultSet("Err:day( "+day+" )is not in range");
            dismiss();
        }

        if (month <= 6 && day > 31){
            mOnDialogResultSetListener.ResultSet("Err:day( "+day+" )is not in range");
                    dismiss();}

        if (month > 6 && month <= 12 && day > 30){
            mOnDialogResultSetListener.ResultSet("Err:day( "+day+" )is not in range");
               dismiss();}

        if (isLeapYear(year) && month == 12 && day > 30)
        {
            mOnDialogResultSetListener.ResultSet("Err:day( "+day+" )is not in range");
            dismiss();
        }
        if ((!isLeapYear(year)) && month == 12 && day > 29)
        {
            mOnDialogResultSetListener.ResultSet("Err:day( "+day+" )is not in range");
            dismiss();
        }
    }
    public boolean isLeapYear(int year) {
        int y;
        if (year > 0)
            y = year - 474;
        else
            y = 473;
        return (((((y % 2820) + 474) + 38) * 682) % 2816) < 682;
    }

    public void checkAndSetDay(int year,int month ,int day){
        if (month <= 5) {
            npDay.setYearStart(1);
            npDay.setYearEnd(31);
        }
        if (month > 5) {
            npDay.setYearStart(1);
            npDay.setYearEnd(30);
        }
        if (isLeapYear(year) && month == 12)
        {
            npDay.setYearStart(1);
            npDay.setYearEnd(30);
        }
        if ((!isLeapYear(year)) && month == 12)
        {
            npDay.setYearStart(1);
            npDay.setYearEnd(29);
        }
    }




}
