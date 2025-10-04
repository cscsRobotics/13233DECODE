package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;



public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width -- CHANGE TRACK WIDTH
                .setConstraints(100, 100, Math.toRadians(360), Math.toRadians(360), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(24, 61, Math.toRadians(-90)))
                .strafeTo(new Vector2d(24, 24))
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-11.5,24))
                .turn(Math.toRadians(-90))
                .strafeTo(new Vector2d(-11.5,46))
                .strafeToLinearHeading(new Vector2d(-23,23), Math.toRadians(135))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_DECODE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

//                .lineToX(38)
//                .turn(Math.toRadians(90))
//                .lineToY(30)
//                .turn(Math.toRadians(90))
//                .lineToX(0)
//                .turn(Math.toRadians(90))
//                .lineToY(0)
//                .turn(Math.toRadians(90))