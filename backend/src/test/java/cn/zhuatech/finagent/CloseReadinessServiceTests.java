/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.finagent;import cn.zhuatech.finagent.service.CloseReadinessService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class CloseReadinessServiceTests{private final CloseReadinessService s=new CloseReadinessService();/**
                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                    */
@Test void blocksIncompleteCloseAtDeadline(){var r=s.evaluate(new CloseReadinessService.Request(10,5,100,50,4,5,1));assertEquals("BLOCK_CLOSE",r.status());}/**
                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                */
@Test void approvesCompleteClose(){var r=s.evaluate(new CloseReadinessService.Request(10,10,100,100,0,0,3));assertEquals("READY",r.status());}}
