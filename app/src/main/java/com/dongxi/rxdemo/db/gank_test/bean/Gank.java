package com.dongxi.rxdemo.db.gank_test.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;

/**
 * Created by macmini002 on 17/12/13.
 */

public class Gank {

    /**
     * error : false
     * results : [{"_id":"59fefc00421aa90fef2034fd","createdAt":"2017-11-05T19:54:40.126Z","desc":"别的妹子被撩VS 你被撩，哈哈哈哈我选择胖子！！！！！！[笑cry][笑cry][笑cry] \u200b","publishedAt":"2017-12-11T08:43:39.682Z","source":"chrome","type":"休息视频","url":"https://weibo.com/tv/v/FtACAlNjr?fid=1034:af7f2c4cddc6b2d8e373293be47bdc8b&display=0&retcode=6102","used":true,"who":"lxxself"},{"_id":"5a274946421aa90fe2f02cb8","createdAt":"2017-12-06T09:35:02.292Z","desc":"九张图让你了解最神秘的团体\u2014\u2014程序员","publishedAt":"2017-12-11T08:43:39.682Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s?__biz=MzU3NzA0ODQzMw==&mid=2247483743&idx=1&sn=b9484e36e8712195cf35f152e0c575af","used":true,"who":"陈宇明"},{"_id":"5a27faaf421aa90fef20358a","createdAt":"2017-12-06T22:11:59.321Z","desc":"一只技术amazui实现的vue.js组件库","publishedAt":"2017-12-11T08:43:39.682Z","source":"web","type":"前端","url":"https://github.com/sunshineJi/amaze-vue","used":true,"who":"Xuecong"},{"_id":"5a289393421aa90fe2f02cbb","createdAt":"2017-12-07T09:04:19.514Z","desc":"一篇文章告诉你FFmpeg环境的搭建和编译","publishedAt":"2017-12-11T08:43:39.682Z","source":"web","type":"Android","url":"http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001398&idx=1&sn=e2f10368a6146669b483c249e13b3fee&chksm=6b476ae85c30e3fe509ec489028d071c199de24a0660e047b87980ef5ba0f216e7d69893f8a8#rd","used":true,"who":"codeGoogler"},{"_id":"5a289ff8421aa90fe50c0264","createdAt":"2017-12-07T09:57:12.869Z","desc":"fish-ui 一套基于vue2的桌面ui组件库，css借鉴semantic-ui","images":["http://img.gank.io/3fcd4763-30c7-4802-adcd-d546783a0d61"],"publishedAt":"2017-12-11T08:43:39.682Z","source":"web","type":"前端","url":"https://github.com/myliang/fish-ui","used":true,"who":"myliang"},{"_id":"5a2b5b1d421aa90fe50c026d","createdAt":"2017-12-09T11:40:13.338Z","desc":"ToastCompat: An Android library to HOOK and FIX Toast BadTokenException","images":["http://img.gank.io/31bc3e71-69ea-48ad-b282-2ea34b57bde2"],"publishedAt":"2017-12-11T08:43:39.682Z","source":"web","type":"Android","url":"https://github.com/drakeet/ToastCompat","used":true,"who":"drakeet"},{"_id":"5a2dd04e421aa90fe2f02ccc","createdAt":"2017-12-11T08:24:46.981Z","desc":"12-11","publishedAt":"2017-12-11T08:43:39.682Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171211082435_CCblJd_Screenshot.jpeg","used":true,"who":"daimajia"},{"_id":"5a2dd246421aa90fe50c026f","createdAt":"2017-12-11T08:33:10.107Z","desc":"Powermode 输入效果，shaking，shaking！","images":["http://img.gank.io/9a4bf261-4863-4072-a153-ced9adbc05b2"],"publishedAt":"2017-12-11T08:43:39.682Z","source":"chrome","type":"iOS","url":"https://github.com/younatics/PowerMode","used":true,"who":"代码家"},{"_id":"5a2dd27e421aa90fef203593","createdAt":"2017-12-11T08:34:06.631Z","desc":"最漂亮的一个音频采样，作曲工具。","images":["http://img.gank.io/4cba6556-573f-41e7-9efe-0e5850089d06"],"publishedAt":"2017-12-11T08:43:39.682Z","source":"chrome","type":"iOS","url":"https://github.com/AudioKit/ROMPlayer","used":true,"who":"代码家"},{"_id":"5a119204421aa90fe50c021c","createdAt":"2017-11-19T22:15:32.45Z","desc":"韩国主播被虐哭后续 精神崩溃直播剃头【游民新闻】(2)","publishedAt":"2017-12-06T08:49:34.303Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av16326606/index_2.html#page=2","used":true,"who":"LHF"}]
     */

    private boolean error;
    private List<GankItem> results;

    public void setError(boolean error) {
        this.error = error;
    }

    public void setResults(List<GankItem> results) {
        this.results = results;
    }

    public boolean getError() {
        return error;
    }

    public List<GankItem> getResults() {
        return results;
    }

    @Entity
    public static class GankItem implements MultiItemEntity {
        /**
         * _id : 59fefc00421aa90fef2034fd
         * createdAt : 2017-11-05T19:54:40.126Z
         * desc : 别的妹子被撩VS 你被撩，哈哈哈哈我选择胖子！！！！！！[笑cry][笑cry][笑cry] ​
         * publishedAt : 2017-12-11T08:43:39.682Z
         * source : chrome
         * type : 休息视频
         * url : https://weibo.com/tv/v/FtACAlNjr?fid=1034:af7f2c4cddc6b2d8e373293be47bdc8b&display=0&retcode=6102
         * used : true
         * who : lxxself
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        @Generated(hash = 924053644)
        public GankItem(String _id, String createdAt, String desc, String publishedAt, String source, String type,
                        String url, boolean used, String who, int itemType, int spanSize) {
            this._id = _id;
            this.createdAt = createdAt;
            this.desc = desc;
            this.publishedAt = publishedAt;
            this.source = source;
            this.type = type;
            this.url = url;
            this.used = used;
            this.who = who;
            this.itemType = itemType;
            this.spanSize = spanSize;
        }
        @Generated(hash = 1292024746)
        public GankItem() {
        }
        public String get_id() {
            return this._id;
        }
        public void set_id(String _id) {
            this._id = _id;
        }
        public String getCreatedAt() {
            return this.createdAt;
        }
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        public String getDesc() {
            return this.desc;
        }
        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getPublishedAt() {
            return this.publishedAt;
        }
        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
        public String getSource() {
            return this.source;
        }
        public void setSource(String source) {
            this.source = source;
        }
        public String getType() {
            return this.type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getUrl() {
            return this.url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public boolean getUsed() {
            return this.used;
        }
        public void setUsed(boolean used) {
            this.used = used;
        }
        public String getWho() {
            return this.who;
        }
        public void setWho(String who) {
            this.who = who;
        }

        public static final int TEXT = 1;
        public static final int IMG = 2;
        public static final int TEXT_SPAN_SIZE = 3;
        public static final int IMG_SPAN_SIZE = 1;
        private int itemType;
        private int spanSize;


        public int getSpanSize() {
            return spanSize;
        }

        public void setSpanSize(int spanSize) {
            this.spanSize = spanSize;
        }


        @Override
        public int getItemType() {
            return itemType;
        }
        public void setItemType(int itemType) {
            this.itemType = itemType;
        }
    }


}
