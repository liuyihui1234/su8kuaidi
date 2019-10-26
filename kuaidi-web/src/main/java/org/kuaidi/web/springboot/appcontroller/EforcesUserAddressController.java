package org.kuaidi.web.springboot.appcontroller;


import javax.servlet.http.HttpServletRequest;
import org.kuaidi.bean.domain.EforcesUser;
import org.kuaidi.bean.domain.EforcesUserAddress;
import org.kuaidi.bean.vo.PageVo;
import org.kuaidi.bean.vo.ResultUtil;
import org.kuaidi.bean.vo.ResultVo;
import org.kuaidi.iservice.IEforcesUserAddressService;
import org.kuaidi.web.springboot.core.authorization.Authorization;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/app/useraddress/")
public class EforcesUserAddressController {

	@Reference(version = "1.0.0")
	private IEforcesUserAddressService  userAddressService; 
	
	@RequestMapping("getUserAddressByParam")
    @ResponseBody
    @Authorization
    public PageVo getUserAddressByParam(HttpServletRequest request,
    					Integer pageNum, Integer pageSize ,String param){
		try {
			EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
			Integer userId = userInfo.getId();
			// 分页显示
			PageInfo<EforcesUserAddress> page =  userAddressService.findByNameOrPhone(pageNum, pageSize , userId, param);
			return ResultUtil.exec(pageNum, pageSize, page.getTotal(), "查询成功", page.getList());
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return ResultUtil.exec(pageNum, pageSize, 0,null);
	}
	
	
	@RequestMapping("addUserAddress")
    @ResponseBody
    @Authorization
    public ResultVo addUserAddress(HttpServletRequest request,EforcesUserAddress userAddress) {
		if(userAddress.getUserid() == null ) {
			EforcesUser userInfo = (EforcesUser)request.getAttribute("user");
			Integer userId = userInfo.getId();
			userAddress.setUserid(userId);
		}
		System.out.println(userAddress.getCityname() + ">>>>>>>");
		Integer rst = userAddressService.insertUserAddress(userAddress);
		if(rst != null && rst > 0 ) {
			return ResultUtil.exec(true,"添加地址成功",null);
		}
		return ResultUtil.exec(false,"添加地址失败",null);
	}
	
	@RequestMapping("delUserAddress")
    @ResponseBody
    @Authorization
    public ResultVo delUserAddress(Integer Id) {
		try {
			Integer rst = userAddressService.delUserAddress(Id);
			if(rst != null && rst > 0 ) {
				return ResultUtil.exec(true,"删除地址成功",null);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return ResultUtil.exec(false,"删除地址失败",null);
	}
	
	@RequestMapping("modifyUserAddress")
    @ResponseBody
    @Authorization
    public ResultVo modifyUserAddress(EforcesUserAddress userAddress) {
		try {
			Integer rst = userAddressService.updateUserAddress(userAddress);
			if(rst != null && rst > 0 ) {
				return ResultUtil.exec(true,"修改地址成功",null);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return ResultUtil.exec(false,"修改地址失败",null);
	}
	
}
