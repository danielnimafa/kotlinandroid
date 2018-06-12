package com.danielnimafa.android.androidkotlinapp.utils.realmdb;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by danielnimafa on 04/04/18.
 */

public class DBMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0) {
                    /*schema.get("DrawerMenu")
                            .addField("username", String.class)
                            .addField("address", String.class)
                            .addField("phoneNumber", String.class)
                            .addField("email", String.class);*/

            oldVersion++;
        }

        if (oldVersion == 1) {
                    /*schema.get("DrawerMenu")
                            .renameField("username", "companyUsername")
                            .renameField("address", "companyAddress")
                            .renameField("phoneNumber", "companyPhoneNumber")
                            .renameField("email", "companyEmail");

                    schema.get("Profile").addField("fullname", String.class);*/

            oldVersion++;
        }

        if (oldVersion == 2) {
            /*schema.create("EmailAutoComplete");
            schema.get("EmailAutoComplete")
                    .addField("email", String.class);*/
            oldVersion++;
        }

        System.out.println("realm version " + oldVersion);
    }
}
