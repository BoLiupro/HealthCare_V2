package com.utf8coding.healthcare.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.utf8coding.healthcare.data.*
import com.utf8coding.healthcare.networkRelated.NetworkUtils

object GenerateTestContentUtils {
    fun generateArticleList(): ArrayList<ArticleData>{
        val list = arrayListOf<ArticleData>(
            ArticleData(1, "测试1",
            "1测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试",
        null),
            ArticleData(2, "测试2",
                        "　伏尔泰曾经说过，坚持意志伟大的事业需要始终不渝的精神。这不禁令我深思。 既然如此， " +
                                "问题的关键究竟为何? 歌德在不经意间这样说过，没有人事先了解自己到底有多大的力量，" +
                                "直到他试过以后才知道。带着这句话，我们还要更加慎重的审视这个问题： 德谟克利特曾经" +
                                "说过，节制使快乐增加并使享受加强。这启发了我， 总结的来说， 了解清楚测试到底是一" +
                                "种怎么样的存在，是解决一切问题的关键。 而这些并不是完全重要，更加重要的问题是， " +
                                "既然如此， 要想清楚，测试，到底是一种怎么样的存在。 本人也是经过了深思熟虑，在" +
                                "每个日日夜夜思考这个问题。 现在，解决测试的问题，是非常非常重要的。 所以， 而这些" +
                                "并不是完全重要，更加重要的问题是， 既然如何， 马尔顿在不经意间这样说过，坚强的信" +
                                "心，能使平凡的人做出惊人的事业。我希望诸位也能好好地体会这句话。\n" +
                                "　　赫尔普斯在不经意间这样说过，有时候读书是一种巧妙地避开思考的方法。这启发了" +
                                "我， 测试，到底应该如何实现。 带着这些问题，我们来审视一下测试。 要想清楚，测试" +
                                "，到底是一种怎么样的存在。 克劳斯·莫瑟爵士在不经意间这样说过，教育需要花费钱，而" +
                                "无知也是一样。这启发了我， 测试，发生了会如何，不发生又会如何。 了解清楚测试到底" +
                                "是一种怎么样的存在，是解决一切问题的关键。 老子在不经意间这样说过，知人者智，自知" +
                                "者明。胜人者有力，自胜者强。我希望诸位也能好好地体会这句话。 就我个人来说，测试对" +
                                "我的意义，不能不说非常重大。 爱尔兰在不经意间这样说过，越是无能的人，越喜欢挑剔" +
                                "别人的错儿。我希望诸位也能好好地体会这句话。 在这种困难的抉择下，本人思来想去，" +
                                "食难安。 既然如何， 一般来说， 一般来讲，我们都必须务必慎重的考虑考虑。 生活中" +
                                "，若测试出现了，我们就不得不考虑它出现了的事实。 总结的来说， 在这种困难的抉择下" +
                                "，本人思来想去，寝食难安。 吉格·金克拉在不经意间这样说过，如果你能做梦，你就能实现" +
                                "它。这启发了我， 一般来讲，我们都必须务必慎重的考虑考虑。 马云曾经说过，最大的挑" +
                                "战和突破在于用人，而用人最大的突破在于信任人。我希望诸位也能好好地体会这句话。 " +
                                "阿卜·日·法拉兹曾经说过，学问是异常珍贵的东西，从任何源泉吸收都不可耻。带着这句话" +
                                "，我们还要更加慎重的审视这个问题： 笛卡儿曾经说过，我的努力求学没有得到别的好处，" +
                                "只不过是愈来愈发觉自己的无知。这不禁令我深思。 现在，解决测试的问题，是非常非常重" +
                                "要的。 所以， 既然如此， 本人也是经过了深思熟虑，在每个日日夜夜思考这个问题。 要想" +
                                "清楚，测试，到底是一种怎么样的存在。 经过上述讨论白哲特在不经意间这样说过，坚强的" +
                                "信念能赢得强者的心，并使他们变得更坚强。 这句话语虽然很短，但令我浮想联翩。 歌德曾" +
                                "经说过，没有人事先了解自己到底有多大的力量，直到他试过以后才知道。这启发了我， 这种" +
                                "事实对本人来说意义重大，相信对这个世界也是有一定意义的。 吕凯特曾经说过，生命不可能" +
                                "有两次，但许多人连一次也不善于度过。这句话语虽然很短，但令我浮想联翩。 测试的发生，" +
                                "到底需要如何做到，不测试的发生，又会如何产生。 裴斯泰洛齐在不经意间这样说过，今天应" +
                                "做的事没有做，明天再早也是耽误了。这句话语虽然很短，但令我浮想联翩。 那么， 本人也" +
                                "是经过了深思熟虑，在每个日日夜夜思考这个问题。 测试的发生，到底需要如何做到，不测试" +
                                "的发生，又会如何产生。\n" +
                                "　　富兰克林在不经意间这样说过，你热爱生命吗？那么别浪费时间，因为时间是组成生命" +
                                "的材料。这不禁令我深思。 而这些并不是完全重要，更加重要的问题是， 我们一般认为" +
                                "，抓住了问题的关键，其他一切则会迎刃而解。 就我个人来说，测试对我的意义，不能不" +
                                "说非常重大。 左拉曾经说过，生活的道路一旦选定，就要勇敢地走到底，决不回头。这不禁令我" +
                                "深思。 所谓测试，关键是测试需要如何写。 测试，到底应该如何实现。 一般来说， 问题的关键" +
                                "究竟为何? 在这种困难的抉择下，本人思来想去，寝食难安。 测试的发生，到底需要如何做到，" +
                                "不测试的发生，又会如何产生。 测试的发生，到底需要如何做到，不测试的发生，又会如何产生" +
                                "。 既然如何， 笛卡儿曾经说过，读一切好书，就是和许多高尚的人谈话。我希望诸位也能好好地" +
                                "体会这句话。 经过上述讨论测试，发生了会如何，不发生又会如何。 我认为， 每个人都不得不" +
                                "面对这些问题。 在面对这种问题时， 带着这些问题，我们来审视一下测试。 " +
                                "。 在面对这种问题时， 米歇潘曾经说过，生命是一条艰险的峡谷，只有勇敢的人才能通过。这启" +
                                "发了我， 富兰克林在不经意间这样说过，读书是易事，思索是难事，但两者缺一，便全无用处。" +
                                "我希望诸位也能好好地体会这句话。 那么， 我们不得不面对一个非常尴尬的事实，那就是。",
                "https://s1.ax1x.com/2022/05/19/OHR5JP.jpg")
        )
        return list
    }

    fun generateArticleListFromWeb(): MutableLiveData<ArrayList<ArticleData>>{
        val list: MutableLiveData<ArrayList<ArticleData>> = MutableLiveData<ArrayList<ArticleData>>()
        list.value?.clear()
        for (i in 1..5){
            NetworkUtils.getArticleById(1, object: NetworkUtils.GetArticleByIdListener{
                override fun onSuccess(articleData: ArticleData) {
                    list.value?.add(articleData)
                }
                override fun onFail() {
                    list.value = ArrayList()
                    makeELog("get test article list from web failed!")
                }
            })
        }
        return list
    }

    fun generateMedData(): ArrayList<MedData>{
        return arrayListOf(
            MedData(1, "马来酸非尼拉敏盐酸萘甲唑啉滴眼1",
                "滴眼液", "0.15g * 12片每盒",
                14f, "湖南盛方制药有限公司"),

            MedData(2, "马来酸非尼拉敏盐酸萘甲唑啉滴眼2",
                "滴眼液", "0.15g * 12片每盒",
                14f, "湖南盛方制药有限公司")
        )
    }

    fun generateCommentData(): ArrayList<CommentData>{
        return arrayListOf(
            CommentData("一个用户名1", "1评论评论评论评论评论评论评论评论评论评论评论评论"),
            CommentData("一个用户名2", "2评论评论评论评论评论评论评论评论评论评论评论评论"),
            CommentData("一个用户名3", "3评论评论评论评论评论评论评论评论评论评论评论评论")
        )
    }

    fun generateEpidemicData(): EpidemicData{
        return EpidemicData("123456", "123456", "0", "123456", "2022-05-17 11:19:25")
    }

    fun generateLifeIndexData(): LifeIndexData{
        return LifeIndexData("好", "好", "好", "好", "好")
    }

    //tool:
    private fun makeILog(text: String){
        Log.i("GenerateTestContentUtils:", text)
    }
    private fun makeWLog(text: String){
        Log.w("GenerateTestContentUtils:", text)
    }
    private fun makeELog(text: String){
        Log.e("GenerateTestContentUtils:", text)
    }
}