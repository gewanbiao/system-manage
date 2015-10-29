package com.gewanbiao.service.impl;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

import com.gewanbiao.service.ISystemInfoService;
/**
 * 系统cpu和内存信息
 * @author wanbiao
 *
 */
@WebService(endpointInterface="com.gewanbiao.service.ISystemInfoService")
public class SystemInfoServiceImpl implements ISystemInfoService {

	/**
	 * cpu信息
	 * @throws SigarException
	 */
	public String cpu() {
		StringBuilder stringBuilder = new StringBuilder();
        try {
			Sigar sigar = new Sigar();
			CpuInfo infos[] = sigar.getCpuInfoList();
			CpuPerc cpuList[] = null;
			cpuList = sigar.getCpuPercList();
			for (int i = 0; i < infos.length; i++) {
			    CpuInfo info = infos[i];
			    stringBuilder.append("第" + (i + 1) + "块CPU信息");
			    stringBuilder.append("CPU的总量MHz:    " + info.getMhz());
			    stringBuilder.append("CPU缓存数量:    " + info.getCacheSize());
			    stringBuilder.append("CPU用户使用率:    " + CpuPerc.format(cpuList[i].getUser()));// 用户使用率
			    stringBuilder.append("CPU系统使用率:    " + CpuPerc.format(cpuList[i].getSys()));// 系统使用率
			    stringBuilder.append("CPU当前等待率:    " + CpuPerc.format(cpuList[i].getWait()));// 当前等待率
			    stringBuilder.append("CPU当前错误率:    " + CpuPerc.format(cpuList[i].getNice()));//
			    stringBuilder.append("CPU当前空闲率:    " + CpuPerc.format(cpuList[i].getIdle()));// 当前空闲率
			    stringBuilder.append("CPU总的使用率:    " + CpuPerc.format(cpuList[i].getCombined()));// 总的使用率
			}
		} catch (SigarException e) {
			e.printStackTrace();
		}
        return stringBuilder.toString();
    }
	/**
	 * 内存信息
	 * @throws SigarException
	 */
	public String memory() {
		StringBuilder stringBuilder = new StringBuilder();
        try {
			Sigar sigar = new Sigar();
			Mem mem = sigar.getMem();
			// 内存总量
			stringBuilder.append("内存总量:    " + mem.getTotal() / 1024L + "K av");
			// 当前内存使用量
			stringBuilder.append("当前内存使用量:    " + mem.getUsed() / 1024L + "K used");
			// 当前内存剩余量
			stringBuilder.append("当前内存剩余量:    " + mem.getFree() / 1024L + "K free");
			Swap swap = sigar.getSwap();
			// 交换区总量
			stringBuilder.append("交换区总量:    " + swap.getTotal() / 1024L + "K av");
			// 当前交换区使用量
			stringBuilder.append("当前交换区使用量:    " + swap.getUsed() / 1024L + "K used");
			// 当前交换区剩余量
			stringBuilder.append("当前交换区剩余量:    " + swap.getFree() / 1024L + "K free");
		} catch (SigarException e) {
			e.printStackTrace();
		}
        return stringBuilder.toString();
    }
}
