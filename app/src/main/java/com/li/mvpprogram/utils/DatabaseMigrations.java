/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */
package com.li.mvpprogram.utils;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * [数据库数据迁移工具类]
 *
 * @author lison
 * @version v 1.0.0 10/11/2016 13:51 XLXZ Exp $
 * @email zhanglisan@xianglin.cn
 */
public class DatabaseMigrations implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        LogUtils.d("oldversion: " + oldVersion + "  new version: " + newVersion);
        // Migrate to version 1: Add BankBusinessRankingBean and BankBusinessStationBean
        if (oldVersion == 0) {
            schema.create("BankBusinessRankingBean")
                    .addField("balance", String.class)
                    .addField("balanceRank", String.class)
                    .addField("nodePartyId", String.class)
                    .addField("cardCount", String.class)
                    .addField("nodeName", String.class)
                    .addField("mark", String.class)
                    .addField("flowers", String.class)
                    .addField("rewords", String.class)
                    .addField("nodeManagerPartyId", String.class)
                    .addField("rewarksMark", String.class);


            schema.create("BankBusinessStationBean")
                    .addField("balance", String.class)
                    .addField("balanceRank", long.class)
                    .addField("balanceTag", int.class)
                    .addField("cardCount", long.class)
                    .addField("cardRank", long.class)
                    .addField("cardTag", int.class)
                    .addField("flowers", long.class)
                    .addField("nodePartyId", long.class);

            oldVersion++;
        }

        if (oldVersion == 1) { // 迁移至版本2，删除除NodeBean之外的所有表, nodeBean表增加userType字段
            if (schema.contains("BankBusinessRankingBean")) {
                schema.remove("BankBusinessRankingBean");
            }
            if (schema.contains("BankBusinessStationBean")) {
                schema.remove("BankBusinessStationBean");
            }
            if (schema.contains("UploadAppsBean")) {
                schema.remove("UploadAppsBean");
            }
            if (schema.contains("HomeBean")) {
                schema.remove("HomeBean");
            }
            if (schema.contains("BusiVisitBean")) {
                schema.remove("BusiVisitBean");
            }

            if (schema.contains("NodeBean") && (!schema.get("NodeBean").hasField("userType"))) {
                schema.get("NodeBean").addField("userType", String.class);
            }
            oldVersion++;
        }

        if (oldVersion == 2) { // 迁移至版本3，新增User表，保存用户信息（包含站长和村民，用字段区分）,删除NodeBean表
            schema.create("User")
                    .addField("loginName", String.class)
                    .addField("partyId", long.class)
                    .addField("userType", String.class)
                    .addField("ryToken", String.class);
            if (schema.contains("NodeBean")) {
                schema.remove("NodeBean");
            }
            oldVersion++;
        }

        if (oldVersion == 3) {
            schema.create("StepData")
                    .addField("userId", String.class)
                    .addField("today", String.class)
                    .addField("morning_step", String.class)
                    .addField("am_step", String.class)
                    .addField("pm_step", String.class)
                    .addField("evening_step", String.class);
            if (schema.contains("step")) {
                schema.remove("step");
            }
            oldVersion++;
        }
    }
}
