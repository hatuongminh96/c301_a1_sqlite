package com.example.minhnguyen.tuongmin_sizebook;

import android.provider.BaseColumns;

/**
 * Created by MinhNguyen on 14/1/17.
 */

public final class PersonContract {
    private PersonContract() {}
    public static class PersonEntry implements BaseColumns{
        public static final String TABLE_NAME = "Person";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_DATE = "Date";
        public static final String COLUMN_NAME_NECK = "Neck";
        public static final String COLUMN_NAME_BUST = "Bust";
        public static final String COLUMN_NAME_CHEST = "Chest";
        public static final String COLUMN_NAME_WAIST = "Waist";
        public static final String COLUMN_NAME_HIP = "Hip";
        public static final String COLUMN_NAME_INSEAM = "Inseam";
        public static final String COLUMN_NAME_COMMENT = "Comment";

    }
}
