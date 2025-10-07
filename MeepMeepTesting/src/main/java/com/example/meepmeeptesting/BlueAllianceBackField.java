package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueAllianceBackField {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width -- CHANGE TRACK WIDTH
            .setConstraints(100, 100, Math.toRadians(360), Math.toRadians(360), 15)
            .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(61, -12, Math.toRadians(180)))
            // Line up with goal to shoot preloads
            .strafeToLinearHeading(new Vector2d(-22.7, -23), Math.toRadians(225))
            // Wait to simulate time to shoot
            .waitSeconds(1)
            //line up with artifacts
            .strafeToLinearHeading(new Vector2d(-11.5, -23), Math.toRadians(270))
            // Pickup artifacts
            .strafeTo(new Vector2d(-11.5, -46))
            // Move to shooting position
            .strafeToLinearHeading(new Vector2d(-22.7, -23), Math.toRadians(225))
            // Wait to simulate time to shoot
            .waitSeconds(1)
            // Move to line up with next artifacts
            .strafeToLinearHeading(new Vector2d(12.5, -23), Math.toRadians(270))
            // Collect artifacts
            .strafeTo(new Vector2d(12.5, -46))
            // Move to shooting position
            .strafeToLinearHeading(new Vector2d(-22.7, -23), Math.toRadians(225))
            // Wait to simulate time to shoot
            .waitSeconds(1)
            // Move to line up with next artifacts
            .strafeToLinearHeading(new Vector2d(36, -23), Math.toRadians(270))
            // Collect artifacts
            .strafeTo(new Vector2d(36, -46))
            // Move to shooting position
            .strafeToLinearHeading(new Vector2d(-22.7, -23), Math.toRadians(225))
            // Wait to simulate time to shoot
            .waitSeconds(1)
            .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
            .setDarkMode(true)
            .setBackgroundAlpha(0.95f)
            .addEntity(myBot)
            .start();
    }
}
