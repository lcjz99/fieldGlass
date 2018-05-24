package com.abeam.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.abeam.system.dao.UserDaoI;
import com.abeam.system.model.SysUser;

@Repository
public class UserDaoImpl extends BaseDaoImpl<SysUser> implements UserDaoI {

}
