/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.finagent.controller;import cn.zhuatech.finagent.common.ApiResponse;import cn.zhuatech.finagent.service.CloseReadinessService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/finagent/insights/close-readiness") public class CloseReadinessController{private final CloseReadinessService service;/**
                                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                             */
public CloseReadinessController(CloseReadinessService service){this.service=service;}/**
                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                  */
@PostMapping ApiResponse<CloseReadinessService.Result> evaluate(@Valid @RequestBody CloseReadinessService.Request r){return ApiResponse.ok(service.evaluate(r));}}
