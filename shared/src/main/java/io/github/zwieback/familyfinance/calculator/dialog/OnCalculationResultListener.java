package io.github.zwieback.familyfinance.calculator.dialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;

public interface OnCalculationResultListener {

    void onCalculationResult(@Nullable BigDecimal result);
}
