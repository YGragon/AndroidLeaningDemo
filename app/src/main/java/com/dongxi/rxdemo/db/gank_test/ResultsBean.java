package com.dongxi.rxdemo.db.gank_test;

import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 */


public class ResultsBean{

    /**
     * error : false
     * results : [{"_id":"5a273d40421aa90fef203585","createdAt":"2017-12-06T08:43:44.386Z","desc":"12-6","publishedAt":"2017-12-06T08:49:34.303Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171206084331_wylXWG_misafighting_6_12_2017_8_43_16_390.jpeg","used":true,"who":"daimajia"},{"_id":"5a20ace0421aa90fe50c024c","createdAt":"2017-12-01T09:14:08.63Z","desc":"12-1","publishedAt":"2017-12-05T08:48:31.384Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171201091356_OPqmuO_kanna399_1_12_2017_9_13_42_126.jpeg","used":true,"who":"daimajia"},{"_id":"5a1ad043421aa90fe725366c","createdAt":"2017-11-26T22:31:31.363Z","desc":"11-26","publishedAt":"2017-11-30T13:11:10.665Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171126223118_jeCYtY_chayexiaoguo_apple_26_11_2017_22_30_59_409.jpeg","used":true,"who":"代码家"},{"_id":"5a16171d421aa90fef203553","createdAt":"2017-11-23T08:32:29.546Z","desc":"11-23","publishedAt":"2017-11-24T11:08:03.624Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171123083218_5mhRLg_sakura.gun_23_11_2017_8_32_9_312.jpeg","used":true,"who":"daimajia"},{"_id":"5a121895421aa90fe50c021e","createdAt":"2017-11-20T07:49:41.797Z","desc":"2017-11-20","publishedAt":"2017-11-20T12:42:06.454Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171120074925_ZXDh6l_joanne_722_20_11_2017_7_49_16_336.jpeg","used":true,"who":"daimajia"},{"_id":"5a0e4a0d421aa90fe7253643","createdAt":"2017-11-17T10:31:41.155Z","desc":"11-17","publishedAt":"2017-11-17T12:39:48.189Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-11-17-22794158_128707347832045_9158114204975104000_n.jpg","used":true,"who":"代码家"},{"_id":"5a0d0c97421aa90fe2f02c60","createdAt":"2017-11-16T11:57:11.4Z","desc":"11-16","publishedAt":"2017-11-16T12:01:05.619Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171116115656_vnsrab_Screenshot.jpeg","used":true,"who":"代码家"},{"_id":"5a0a5141421aa90fef203525","createdAt":"2017-11-14T10:13:21.137Z","desc":"11-14","publishedAt":"2017-11-14T10:43:36.180Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171114101305_NIAzCK_rakukoo_14_11_2017_10_12_58_703.jpeg","used":true,"who":"daimajia"},{"_id":"5a08ea7b421aa90fe7253628","createdAt":"2017-11-13T08:42:35.306Z","desc":"11-13","publishedAt":"2017-11-13T12:10:58.643Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171113084220_LuJgqv_sakura.gun_13_11_2017_8_42_12_311.jpeg","used":true,"who":"daimajia"},{"_id":"5a03b502421aa90fe7253618","createdAt":"2017-11-09T09:53:06.802Z","desc":"11-9","publishedAt":"2017-11-10T08:10:02.838Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/20171109095254_dOw5qh_bluenamchu_9_11_2017_9_52_47_256.jpeg","used":true,"who":"daimajia"}]
     */

    private boolean error;
    private List<ResultsEntity> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsEntity> getResults() {
        return results;
    }

    public void setResults(List<ResultsEntity> results) {
        this.results = results;
    }
}
