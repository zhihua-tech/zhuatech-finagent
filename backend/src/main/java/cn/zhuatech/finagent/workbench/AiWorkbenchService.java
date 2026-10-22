/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.finagent.workbench;

import static cn.zhuatech.finagent.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "currency", "lines");
    String currency = text(r, "currency", 3);
    if (!currency.matches("[A-Z]{3}")) throw new IllegalArgumentException("币种须为三位大写代码");
    BigDecimal debit = BigDecimal.ZERO, credit = BigDecimal.ZERO;
    Set<String> ids = new HashSet<>();
    List<Map<String, Object>> out = new ArrayList<>();
    for (var line : rows(r, "lines", 2, 500)) {
      keys(line, "id", "account", "debit", "credit");
      String id = id(line, "id");
      unique(ids, id);
      String account = id(line, "account");
      BigDecimal d = money(line, "debit"), c = money(line, "credit");
      if ((d.signum() > 0) == (c.signum() > 0)) throw new IllegalArgumentException("每行必须且只能有一侧正金额");
      debit = debit.add(d);
      credit = credit.add(c);
      out.add(Map.of("id", id, "account", account, "debit", d, "credit", c));
    }
    return Map.of(
        "currency",
        currency,
        "balanced",
        debit.compareTo(credit) == 0,
        "debitTotal",
        debit,
        "creditTotal",
        credit,
        "difference",
        debit.subtract(credit),
        "lines",
        out);
  }
}
