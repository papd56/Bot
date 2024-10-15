package com.ruoyi.project.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class VideoSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        // 从页面中提取视频信息
        page.putField("title", page.getHtml().xpath("//h1/text()").toString());
        page.putField("videoUrl", page.getHtml().xpath("//div[@id='addition-content']/h1/text()").toString());

        // 从页面中提取下一页的链接
        page.addTargetRequests(page.getHtml().links().regex("https://sezy9.xyz/vod-read-id-/\\d+\\.html").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new VideoSpider())
                // 从起始页开始爬取
                .addUrl("https://sezy9.xyz/vod-play-id-199653-sid-0-pid-1.html")
                .run();
    }
}
