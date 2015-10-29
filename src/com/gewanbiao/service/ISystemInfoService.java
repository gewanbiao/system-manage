package com.gewanbiao.service;

import javax.jws.WebService;

import org.hyperic.sigar.SigarException;

/**
 * 系统内存和cpu信息
 * @author wanbiao
 *
 */
@WebService
public interface ISystemInfoService {

	public String cpu();
	public String memory();
}
