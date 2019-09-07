package org.kuaidi.iservice;

import com.github.pagehelper.PageInfo;

import org.kuaidi.bean.domain.EforcesLogisticStracking;
import org.kuaidi.bean.domain.EforcesTransportedscan;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/8/7 15:01
 */
public interface IEforcesTransportedscanService {
    /**
     * 动态添加转运记录
     * g
     * @param record
     * @return
     */
    int insertSelective(EforcesTransportedscan record,EforcesLogisticStracking  strackingInfo);
    
    /*
     * 添加转运
     */
    int insertSelective(EforcesTransportedscan record);

    /**
     * 根据网点id 查询对应的转运记录列表
     * g
     * @param incid
     * @return
     */
    PageInfo<EforcesTransportedscan> selectByIncid(Integer pageNum, Integer pageSize, Integer incid);

    /**
     * 查找所有start为0 的转运订单（转运中的订单）
     * g
     * @return
     */
    List<EforcesTransportedscan> selectState0();

    PageInfo<EforcesTransportedscan> selectAllByState0(Integer pageNum, Integer pageSize);

    /**
     * 根据主键 查找对应的转运记录详细
     * g
     * @param id
     * @return
     */
    EforcesTransportedscan selectByPrimaryKey(Integer id);

    /**
     * 根据订单号 查找对应的转运记录详细
     * g
     * @param billsnumber
     * @return
     */
    EforcesTransportedscan selectByBillsnumber(String billsnumber);

    /**
     * 根据转运后的订单号 查找对应的转运记录详细
     * g
     * @param nextnumber
     * @return
     */
    EforcesTransportedscan selectByNextnumber(String nextnumber);

    /**
     * 根据主键 动态修改对应的转运记录详情
     * g
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EforcesTransportedscan record);

    int updateState(List<String> billsNumber);
}
