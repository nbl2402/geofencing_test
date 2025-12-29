package org.utilities;

public class MapEnums {

    public enum AccessType {
        WHILE_USING_APP("permission_allow_foreground_only_button"),
        ONLY_THIS_TIME("permission_allow_one_time_button"),
        DO_NOT_ALLOW("permission_deny_button");

        private final String type;

        AccessType(String type)
        {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
