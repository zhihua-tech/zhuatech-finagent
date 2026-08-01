/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.finagent.repository; import cn.zhuatech.finagent.model.OperatingUnit; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface OperatingUnitRepository extends JpaRepository<OperatingUnit,Long>{Optional<OperatingUnit> findByCode(String code);}
