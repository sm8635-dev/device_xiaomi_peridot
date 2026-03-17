/*
 * Copyright (C) 2023-2024 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.xiaomi.settings.battery;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class BatteryUtils {
    private static final String TAG = "XiaomiBatteryUtils";
    public static final String PREF_CHARGING_CTRL = "charging_control";
    public static final String PREF_CHARGING_LIMIT = "charging_limit";
    private static final String NODE_SUSPEND = "/sys/class/qcom-battery/input_suspend";

    public static void setChargingSuspend(boolean suspend) {
        File file = new File(NODE_SUSPEND);
        if (!file.exists()) return;
        String value = suspend ? "1" : "0";
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(value.getBytes(StandardCharsets.UTF_8));
            fos.flush();
            try {
                fos.getFD().sync();
            } catch (IOException ignored) {}
            Log.d(TAG, "Successfully wrote " + value + " to " + NODE_SUSPEND);
        } catch (IOException e) {
            Log.e(TAG, "Failed to write to " + NODE_SUSPEND, e);
        }
    }

    public static void setChargingSuspendAsync(boolean suspend) {
        new Thread(() -> setChargingSuspend(suspend)).start();
    }
}
