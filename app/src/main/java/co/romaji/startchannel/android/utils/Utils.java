package co.romaji.startchannel.android.utils;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DecimalFormat;

import co.romaji.startchannel.android.StarChannelApplication;

/**
 * Created by nguyenvanhien on 4/3/18.
 */

public class Utils {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }



    public static String convertTimeMillisToHHMMSS(int i) {
        try {
            long j = (long) ((i / 1000) % 60);
            long j2 = (long) ((i / 60000) % 60);
            long j3 = (long) ((i / 3600000) % 24);
            if (i > 3600000) {
                return String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(j3), Long.valueOf(j2), Long.valueOf(j)});
            }
            return String.format("%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j)});
        } catch (Exception ex) {
            return "";
        }
    }

    public static void fetSourceForImageView(ImageView imv, String url){
        Glide.with(StarChannelApplication.getInstance().getApplicationContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imv);
    }

    public static String convertNumberFormat(String strNumber){
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        long number;
        try {
            number = Long.parseLong(strNumber);
            String yourFormattedString = formatter.format(number);
            return yourFormattedString;
        }catch (Exception ex){
            return "";
        }
    }

    public static void applyFontForView(View v){
        StarChannelApplication.getFontUtils().applyFontToView(v,
                StarChannelApplication.getTypeface());
    }

}
