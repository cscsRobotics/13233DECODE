package org.firstinspires.ftc.teamcode.Utils_13233;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;
import java.util.Map;

public class TeleOpCommonMethods {
    public Map<String, Boolean> prev = new HashMap<>();
    LinearOpMode opMode;

    public TeleOpCommonMethods(LinearOpMode opMode) {
        this.opMode = opMode;
    }


    public boolean toggleButton(String name, boolean button, boolean toggle) {
        boolean previous = prev.getOrDefault(name, false);

        if (button && !previous) {
            toggle = !toggle;
        }

        prev.put(name, button);
        return toggle;
    }

}
