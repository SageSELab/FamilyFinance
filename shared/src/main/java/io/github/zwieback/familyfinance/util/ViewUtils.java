package io.github.zwieback.familyfinance.util;

import android.view.View;

import androidx.core.view.ViewCompat;

public final class ViewUtils {

    public static boolean isLeftToRightLayoutDirection(View view) {
        return ViewCompat.getLayoutDirection(view) == ViewCompat.LAYOUT_DIRECTION_LTR;
    }

    private ViewUtils() {
    }
}
