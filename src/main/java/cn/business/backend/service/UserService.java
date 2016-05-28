package cn.business.backend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IUserDao;
import cn.business.backend.model.Role;
import cn.business.backend.model.User;
import cn.business.backend.model.UserRole;
import cn.core.framework.common.Constant;

/**
 * 服务类：用户
 * @author JC
 * @date 2015-8-1
 */
@Service("userService")
public class UserService{

	@Resource  
    private IUserDao userDao; 
	
    public User loadById(int id) {  
		return userDao.loadById(id);
    }

	public User loadByName(String name) {
		return userDao.loadByName(name);
	}

	public void update(User User) {
		userDao.update(User);
	}

	public void save(User User) {
		userDao.save(User);
	}
	
	public void saveUserRole(UserRole userRole){
		userDao.saveUserRole(userRole);
	}

	public List<User> queryAll(Map<String,Object> params) {
		return userDao.queryAll(params);
	}
	
	public int queryCount() {
		return userDao.queryCount();
	}

	public void delUserRole(Map<String,Object> params){
		userDao.delUserRole(params);
	}
	
	/**
	 * 用户登入处理逻辑
	 * @param name
	 * @return map
	 */
	public Map<String,String> login(String name,String password){
		Map<String,String> map = new HashMap<>();
		User u = this.loadByName(name);
		if(null == u){
			map.put("status", Constant.CONST_ERROR);
			map.put("info", "用户不存在！");
		}else{
			//if(MD5Util.encodeByMD5(password).equals(u.getPassword())){
			if(password.equals(u.getPassword())){
				map.put("status", Constant.CONST_SUCCESS);
				map.put("info", "登入成功！");
			}else{
				map.put("status", Constant.CONST_ERROR);
				map.put("info", "密码错误！");
			}
		}
		return map;
	}
	
	/**
	 * 更新用户-角色信息
	 * @param roleIds 待更新角色的id集合
	 * @param user 用户
	 */
	public void updateUserRole(List<String> roleIds, User user){
		if(user.getRoles() == null || user.getRoles().size()<1){
			/**
			 * 保存选择的用户角色
			 */
			for (String roleId : roleIds) {
				UserRole ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(Integer.parseInt(roleId));
				this.saveUserRole(ur);
			}
		}else{
			//未选择的用户角色集合
			List<String> temp = new ArrayList<>();
			
			for (Role role : user.getRoles()) {
				String id = String.valueOf(role.getId());
				if(roleIds.contains(id)){
					roleIds.remove(id);
				}else{
					temp.add(id);
				}
			}
			
			/**
			 * 保存选择的用户角色
			 */
			for (String roleId : roleIds) {
				UserRole ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(Integer.parseInt(roleId));
				this.saveUserRole(ur);
			}
			
			/**
			 * 删除未选择的角色关系
			 */
			Map<String,Object> params = new HashMap<String,Object>();
			for (String id : temp) {
				params.put("userId", user.getId());
				params.put("roleId", id);
				this.delUserRole(params);
				params.clear();
			}
		}
	}
}
