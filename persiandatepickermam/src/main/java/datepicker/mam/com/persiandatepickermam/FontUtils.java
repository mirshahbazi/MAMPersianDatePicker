package datepicker.mam.com.persiandatepickermam;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;

/**
 * Created by mmirshahbazi on 8/3/2017.
 */

public class FontUtils {

    public static SpannableString typeface(Typeface typeface, CharSequence string) {
        SpannableString s = new SpannableString(string);
        s.setSpan(new TypefaceSpan(typeface), 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }
}