# MAMPersianDatePicker
Persian date picker with calender

![Hero Image](https://github.com/mirshahbazi/MAMPersianDatePicker/blob/master/preview/datepicker.gif)


## Using with gradle
- Add the JitPack repository to your root build.gradle:
```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```

- Add the dependency to your sub build.gradle:
```gradle
dependencies {
    compile 'com.github.mirshahbazi:MAMPersianDatePicker:-SNAPSHOT'
}


```
## Using with maven
- Add the JitPack repository to your build file
```maven:
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
 ``` 
-  Add the dependency
 ```maven: 
  	<dependency>
	    <groupId>com.github.mirshahbazi</groupId>
	    <artifactId>MAMPersianDatePicker</artifactId>
	    <version>-SNAPSHOT</version>
	</dependency>
```

How to use:

	
## Java
```java        
        
//in your button add this

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
      
``` 

دوستان عزیز خوشحال میشم نظرات خودتون برای بهبود بیشتر اعلام کنید
