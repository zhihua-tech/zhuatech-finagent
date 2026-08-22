/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.finagent.agent;
import org.springframework.stereotype.Component; import java.util.List; import java.util.Map;
/** 财务智能体运行边界；社区版只生成建议，不自动记账或支付。 */
public interface AgentRuntime {AgentResult run(AgentRequest request);record AgentRequest(String objective,Map<String,String> context){}record AgentStep(String name,String status,String evidence){}record AgentResult(String runtime,String summary,List<AgentStep> steps,Map<String,Object> metrics){}}
@Component class DemoAgentRuntime implements AgentRuntime {public AgentResult run(AgentRequest request){return new AgentResult("local-finance-demo","已完成勾稽检查并生成差异解释，凭证建议等待复核。",List.of(new AgentStep("数据勾稽","COMPLETED","核对总账与子账余额"),new AgentStep("差异分析","COMPLETED","定位 3 条未达项"),new AgentStep("凭证建议","PENDING","需会计与审批人双重确认")),Map.of("reconciledItems",128,"exceptions",3,"objectiveLength",request.objective().length()));}}
