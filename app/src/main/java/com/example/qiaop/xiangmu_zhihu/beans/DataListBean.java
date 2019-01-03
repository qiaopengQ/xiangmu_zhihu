package com.example.qiaop.xiangmu_zhihu.beans;

import java.util.List;

public class DataListBean {

    /**
     * ERRORCODE : 0
     * RESULT : {"newsList":[{"publishTime":"2019-01-01 09:26:35","newsId":"a019a308dbdbe7bffe618567c72ef66e","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7090250022_640330/0","source":"鏈鐣�","category":"濞变箰","title":"EXO鎴愬憳閲戦挓浠佹亱鎯呮洕鍏夛紝SM涓嶻G椤剁骇鐖辫眴鐢滆湝绾︿細"},{"publishTime":"2019-01-01 08:58:41","newsId":"b928d4a4a9ae0485838a7fff6ebeb948","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7089740360_640330/0","source":"瑙嗚涓浗","category":"濞变箰","title":"棣欐腐瀵兼紨鏋楀箔涓滃鍎挎畵鎴胯灏革紝姣嶅瓙浜屼汉閬潰鐗垫墜鐤捐"},{"publishTime":"2019-01-01 00:26:06","newsId":"3976316747dc48ad43ac75e6e70df1da","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7089172208_640330/0","source":"浼氱伀","category":"濞变箰","title":"鍔堣吙銆佽瘓鎹愩�佸嚭杞紝2018鍚冪摐骞村巻浣犵湅浜嗘病锛�"},{"publishTime":"2018-12-31 23:36:42","newsId":"02b0823f031f6c40c5d69fcfc51fec95","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7085995576_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾縷闃垮▏濠氬悗澶敎铚� 璺ㄥ勾婕斿敱鏀跺満绔熷繕杞韩"},{"publishTime":"2018-12-31 23:05:45","newsId":"5ed4352324a4feb0c3baecdb88a1e029","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7086776158_640330/0","source":"濞变箰鐙鍏�","category":"濞变箰","title":"2018鏈�鍚庝竴涓鏅氬悇澶у崼瑙嗚法骞达紝娴侀噺or鎯呮��浣犲皢pick璋�"},{"publishTime":"2018-12-31 23:01:12","newsId":"6679a55fbdf9dc34d5cecc02913fdb31","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7089696067_640330/0","source":"璋堣祫","category":"濞变箰","title":"2018锛屽悆鐡滃悆鍒板悙鐨勪竴骞�"},{"publishTime":"2018-12-31 20:39:48","newsId":"fb6944064486ccbe7216fb5ce9773c8c","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7084215090_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾縷2018骞存渶鍚庝竴澶� 鍛ㄨ繀鍙戠埖澹柊鍗曟洸绁濈鏂板勾"},{"publishTime":"2018-12-31 17:56:11","newsId":"95a148772c0469239a23e391491a4349","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7082352685_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾夸辅鏉ㄧ传璺ㄥ勾鐢ㄧ殑鏄�滃ぇ闀胯吙鈥� 浠婃櫄鍚婂▉浜氬嚭鍦洪潪甯糕�滀粰鈥�"},{"publishTime":"2018-12-31 17:02:54","newsId":"dd57665a3416208ded3ed4c45bab7f05","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7081255503_640330/0","source":"","category":"濞变箰","title":"澶у | 鎷涢粦鐨勫叚灏忛緞绔ワ紝鍏跺疄鏄垜浠ぇ閮ㄥ垎鐖惰緢鐨勬牱瀛�"},{"publishTime":"2018-12-31 16:13:12","newsId":"851f80aaf10787ec8b0920f50246b253","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7081126445_640330/0","source":"濞变箰榄旂帇","category":"濞变箰","title":"鍏皬榫勭鍥犺礋闈㈡秷鎭繃澶氬彇娑堢鍞細閲囪锛屾浘绉扳�滄湁灏忕粍缁囬粦鎴戔��"},{"publishTime":"2018-12-31 15:18:02","newsId":"3b9e7c5a0fe00a6095585c23735454f5","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7080493542_240180/0","source":"韬虹潃鐪嬫悶绗戠殑娈靛瓙","category":"濞变箰","title":"鑻忔墦缁垮惔闈掑嘲鐪熺殑鍗曢浜嗭紒鍐冲畾鑷繁鍞憋紝璇翠笉鎬曟槸楠椾汉锛�"},{"publishTime":"2018-12-31 15:18:02","newsId":"331859b33ea1c8cce862a89edf1ff11c","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7081426393_640330/0","source":"韬虹潃鐪嬫悶绗戠殑娈靛瓙","category":"濞变箰","title":"鍚撮潚宄板甯冧笌鈥滆嫃鎵撶豢鈥濇墍灞炲叕鍙稿悎绾﹀埌鏈燂紝灏嗙嫭绔嬪鐞嗗伐浣滀簨鍔�"},{"publishTime":"2018-12-31 14:04:14","newsId":"f8d17062ee8690676e1948aee3e4a6c1","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7079695051_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾匡綔2018鍗佸ぇ鍥戒骇褰辩墖鍑虹倝锛屻�婅嵂绁炪�嬪崰姒滈锛岀幇瀹炰富涔夎儨鍒�"}],"page":1,"allPage":1}
     */

    private String ERRORCODE;
    private RESULTBean RESULT;

    public String getERRORCODE() {
        return ERRORCODE;
    }

    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public RESULTBean getRESULT() {
        return RESULT;
    }

    public void setRESULT(RESULTBean RESULT) {
        this.RESULT = RESULT;
    }

    public static class RESULTBean {
        /**
         * newsList : [{"publishTime":"2019-01-01 09:26:35","newsId":"a019a308dbdbe7bffe618567c72ef66e","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7090250022_640330/0","source":"鏈鐣�","category":"濞变箰","title":"EXO鎴愬憳閲戦挓浠佹亱鎯呮洕鍏夛紝SM涓嶻G椤剁骇鐖辫眴鐢滆湝绾︿細"},{"publishTime":"2019-01-01 08:58:41","newsId":"b928d4a4a9ae0485838a7fff6ebeb948","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7089740360_640330/0","source":"瑙嗚涓浗","category":"濞变箰","title":"棣欐腐瀵兼紨鏋楀箔涓滃鍎挎畵鎴胯灏革紝姣嶅瓙浜屼汉閬潰鐗垫墜鐤捐"},{"publishTime":"2019-01-01 00:26:06","newsId":"3976316747dc48ad43ac75e6e70df1da","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7089172208_640330/0","source":"浼氱伀","category":"濞变箰","title":"鍔堣吙銆佽瘓鎹愩�佸嚭杞紝2018鍚冪摐骞村巻浣犵湅浜嗘病锛�"},{"publishTime":"2018-12-31 23:36:42","newsId":"02b0823f031f6c40c5d69fcfc51fec95","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7085995576_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾縷闃垮▏濠氬悗澶敎铚� 璺ㄥ勾婕斿敱鏀跺満绔熷繕杞韩"},{"publishTime":"2018-12-31 23:05:45","newsId":"5ed4352324a4feb0c3baecdb88a1e029","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7086776158_640330/0","source":"濞变箰鐙鍏�","category":"濞变箰","title":"2018鏈�鍚庝竴涓鏅氬悇澶у崼瑙嗚法骞达紝娴侀噺or鎯呮��浣犲皢pick璋�"},{"publishTime":"2018-12-31 23:01:12","newsId":"6679a55fbdf9dc34d5cecc02913fdb31","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7089696067_640330/0","source":"璋堣祫","category":"濞变箰","title":"2018锛屽悆鐡滃悆鍒板悙鐨勪竴骞�"},{"publishTime":"2018-12-31 20:39:48","newsId":"fb6944064486ccbe7216fb5ce9773c8c","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7084215090_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾縷2018骞存渶鍚庝竴澶� 鍛ㄨ繀鍙戠埖澹柊鍗曟洸绁濈鏂板勾"},{"publishTime":"2018-12-31 17:56:11","newsId":"95a148772c0469239a23e391491a4349","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7082352685_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾夸辅鏉ㄧ传璺ㄥ勾鐢ㄧ殑鏄�滃ぇ闀胯吙鈥� 浠婃櫄鍚婂▉浜氬嚭鍦洪潪甯糕�滀粰鈥�"},{"publishTime":"2018-12-31 17:02:54","newsId":"dd57665a3416208ded3ed4c45bab7f05","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7081255503_640330/0","source":"","category":"濞变箰","title":"澶у | 鎷涢粦鐨勫叚灏忛緞绔ワ紝鍏跺疄鏄垜浠ぇ閮ㄥ垎鐖惰緢鐨勬牱瀛�"},{"publishTime":"2018-12-31 16:13:12","newsId":"851f80aaf10787ec8b0920f50246b253","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7081126445_640330/0","source":"濞变箰榄旂帇","category":"濞变箰","title":"鍏皬榫勭鍥犺礋闈㈡秷鎭繃澶氬彇娑堢鍞細閲囪锛屾浘绉扳�滄湁灏忕粍缁囬粦鎴戔��"},{"publishTime":"2018-12-31 15:18:02","newsId":"3b9e7c5a0fe00a6095585c23735454f5","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7080493542_240180/0","source":"韬虹潃鐪嬫悶绗戠殑娈靛瓙","category":"濞变箰","title":"鑻忔墦缁垮惔闈掑嘲鐪熺殑鍗曢浜嗭紒鍐冲畾鑷繁鍞憋紝璇翠笉鎬曟槸楠椾汉锛�"},{"publishTime":"2018-12-31 15:18:02","newsId":"331859b33ea1c8cce862a89edf1ff11c","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7081426393_640330/0","source":"韬虹潃鐪嬫悶绗戠殑娈靛瓙","category":"濞变箰","title":"鍚撮潚宄板甯冧笌鈥滆嫃鎵撶豢鈥濇墍灞炲叕鍙稿悎绾﹀埌鏈燂紝灏嗙嫭绔嬪鐞嗗伐浣滀簨鍔�"},{"publishTime":"2018-12-31 14:04:14","newsId":"f8d17062ee8690676e1948aee3e4a6c1","newsImg":"https://inews.gtimg.com/newsapp_ls/0/7079695051_640330/0","source":"鑵捐鏂伴椈涓�绾�","category":"濞变箰","title":"涓�绾匡綔2018鍗佸ぇ鍥戒骇褰辩墖鍑虹倝锛屻�婅嵂绁炪�嬪崰姒滈锛岀幇瀹炰富涔夎儨鍒�"}]
         * page : 1
         * allPage : 1
         */

        private int page;
        private int allPage;
        private List<NewsListBean> newsList;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getAllPage() {
            return allPage;
        }

        public void setAllPage(int allPage) {
            this.allPage = allPage;
        }

        public List<NewsListBean> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListBean> newsList) {
            this.newsList = newsList;
        }

        public static class NewsListBean {
            /**
             * publishTime : 2019-01-01 09:26:35
             * newsId : a019a308dbdbe7bffe618567c72ef66e
             * newsImg : https://inews.gtimg.com/newsapp_ls/0/7090250022_640330/0
             * source : 鏈鐣�
             * category : 濞变箰
             * title : EXO鎴愬憳閲戦挓浠佹亱鎯呮洕鍏夛紝SM涓嶻G椤剁骇鐖辫眴鐢滆湝绾︿細
             */

            private String publishTime;
            private String newsId;
            private String newsImg;
            private String source;
            private String category;
            private String title;

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getNewsId() {
                return newsId;
            }

            public void setNewsId(String newsId) {
                this.newsId = newsId;
            }

            public String getNewsImg() {
                return newsImg;
            }

            public void setNewsImg(String newsImg) {
                this.newsImg = newsImg;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
