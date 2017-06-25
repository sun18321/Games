package com.sun.bean;

/**
 * Created by sun on 2017/6/23.
 */

public class BetBean {

    /**
     * status : 0
     * data : {"roomId":"13423256","diamond":"490"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * roomId : 13423256
         * diamond : 490
         */

        private String roomId;
        private String diamond;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getDiamond() {
            return diamond;
        }

        public void setDiamond(String diamond) {
            this.diamond = diamond;
        }
    }
}
