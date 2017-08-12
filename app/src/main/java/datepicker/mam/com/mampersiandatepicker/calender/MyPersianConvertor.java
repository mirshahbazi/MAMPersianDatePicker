package datepicker.mam.com.mampersiandatepicker.calender;


import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPersianConvertor
{

  public static String persianDateTimeConvertor(String paramString)
  {
    String[] p1 = paramString.split(" ");
    Object localObject = p1[0].split("-");

    // localObject = DateConverter.civilToPersian(new CivilDate(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2])));
    // return  String.valueOf(((PersianDate)localObject).getYear())+"0"+((PersianDate)localObject).getMonth()+"0" +  String.valueOf(((PersianDate)localObject).getDayOfMonth())+p1[3]+p1[4]+p1[5];

    String ret="";
    localObject = DateConverter.civilToPersian(new CivilDate(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2])));
    String Lmonth =  String.valueOf(((PersianDate) localObject).getMonth());
    String Lday =String.valueOf(((PersianDate) localObject).getDayOfMonth());//get len day
    if (Lmonth.length() ==1 && Lday.length()== 1) {
      localObject = DateConverter.civilToPersian(new CivilDate(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2])));
      ret=  String.valueOf(((PersianDate)localObject).getYear())+"/"+"0"+((PersianDate)localObject).getMonth()+"/"+"0" +  String.valueOf(((PersianDate)localObject).getDayOfMonth());
    }
    if (Lmonth.length() ==1 && Lday.length()== 2) {
      localObject = DateConverter.civilToPersian(new CivilDate(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2])));
      ret=  String.valueOf(((PersianDate)localObject).getYear())+"/"+"0"+((PersianDate)localObject).getMonth()+"/"+  String.valueOf(((PersianDate)localObject).getDayOfMonth());
    }
    if (Lmonth.length() ==2 && Lday.length()== 1) {
      localObject = DateConverter.civilToPersian(new CivilDate(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2])));
      ret=  String.valueOf(((PersianDate)localObject).getYear())+"/"+((PersianDate)localObject).getMonth()+"/"+"0" +  String.valueOf(((PersianDate)localObject).getDayOfMonth());
    }
    if (Lmonth.length() ==2 && Lday.length()== 2) {
      localObject = DateConverter.civilToPersian(new CivilDate(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2])));
      ret=  String.valueOf(((PersianDate)localObject).getYear())+"/"+((PersianDate)localObject).getMonth()+"/"+  String.valueOf(((PersianDate)localObject).getDayOfMonth());
    }
    return ret;
  }


  public static String yearMonthDay() {
    SimpleDateFormat formatdate = new SimpleDateFormat("yyyy MM dd");
    Date curDate = new Date(System.currentTimeMillis());
    String str = formatdate.format(curDate);
    return str;
  }
}
