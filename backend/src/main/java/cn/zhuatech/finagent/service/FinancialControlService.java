/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.finagent.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 为财务智能体提供异常交易和凭证完整性控制。 */
@Service
public class FinancialControlService {
    public ControlResult evaluate(ControlRequest request) {
        int riskScore = Math.min(100,
            (int) Math.round(request.anomalyScore() * 55)
                + (request.amount() >= 1_000_000 ? 20 : request.amount() >= 200_000 ? 10 : 0)
                + (request.crossBorder() ? 15 : 0)
                + (!request.supportingDocumentsComplete() ? 25 : 0)
                + (request.overrideRequested() ? 15 : 0));
        List<String> controls = new ArrayList<>();
        if (!request.supportingDocumentsComplete()) controls.add("补齐合同、发票和审批凭证");
        if (request.crossBorder()) controls.add("执行跨境支付合规检查");
        if (request.overrideRequested()) controls.add("覆盖操作需双人复核");
        String decision = !request.supportingDocumentsComplete() || request.anomalyScore() >= 0.9 ? "BLOCK"
            : riskScore >= 50 ? "REVIEW" : "PASS";
        return new ControlResult(decision, riskScore, List.copyOf(controls), !"PASS".equals(decision),
            "PASS".equals(decision) ? "允许进入自动处理队列" : "提交财务负责人复核");
    }

    public record ControlRequest(
        @NotBlank(message = "请输入交易编号") String transactionNo,
        @Positive double amount,
        @DecimalMin("0.0") @DecimalMax("1.0") double anomalyScore,
        boolean crossBorder,
        boolean supportingDocumentsComplete,
        boolean overrideRequested
    ) {}

    public record ControlResult(String decision, int riskScore, List<String> requiredControls, boolean humanReview, String nextAction) {}
}
