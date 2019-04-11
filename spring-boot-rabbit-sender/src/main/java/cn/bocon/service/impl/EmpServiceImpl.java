package cn.bocon.service.impl;

import cn.bocon.entity.Emp;
import cn.bocon.mapper.EmpMapper;
import cn.bocon.service.IEmpService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wj
 * @since 2018-06-22
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

}
