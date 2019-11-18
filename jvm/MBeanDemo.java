package com.weishao.test;

import java.util.List;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryManagerMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import javax.management.MBeanServerConnection;

/**
 * ����� java.lang.management
 * �ṩ����ӿڣ����ڼ��Ӻ͹��� Java ������Լ� Java ��������������еĲ���ϵͳ��
 * �ο���ַ��https://blog.csdn.net/yinghuananhai/article/details/80815569
 * 
 * @author tang
 *
 */
public class MBeanDemo {

	public static void main(String[] args) {

		showJvmInfo();
		showMemoryInfo();
		showSystem();
		showClassLoading();
		showCompilation();
		showThread();
		showGarbageCollector();
		showMemoryManager();
		showMemoryPool();
	}

	/**
	 * Java �����������ʱϵͳ
	 */
	public static void showJvmInfo() {
		RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
		String vendor = mxbean.getVmVendor();
		System.out.println("jvm name:" + mxbean.getVmName());
		System.out.println("jvm version:" + mxbean.getVmVersion());
		System.out.println("jvm bootClassPath:" + mxbean.getBootClassPath());
		System.out.println("jvm start time:" + mxbean.getStartTime());
	}

	/**
	 * Java ��������ڴ�ϵͳ
	 */
	public static void showMemoryInfo() {
		MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
		MemoryUsage heap = mem.getHeapMemoryUsage();
		System.out.println("Heap committed:" + heap.getCommitted() + " init:" + heap.getInit() + " max:" + heap.getMax()
				+ " used:" + heap.getUsed());
	}

	/**
	 * Java ��������������еĲ���ϵͳ
	 */
	public static void showSystem() {
		OperatingSystemMXBean op = ManagementFactory.getOperatingSystemMXBean();
		System.out.println("Architecture: " + op.getArch());
		System.out.println("Processors: " + op.getAvailableProcessors());
		System.out.println("System name: " + op.getName());
		System.out.println("System version: " + op.getVersion());
		System.out.println("Last minute load: " + op.getSystemLoadAverage());
	}

	/**
	 * Java ������������ϵͳ
	 */
	public static void showClassLoading() {
		ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
		System.out.println("TotalLoadedClassCount: " + cl.getTotalLoadedClassCount());
		System.out.println("LoadedClassCount:" + cl.getLoadedClassCount());
		System.out.println("UnloadedClassCount:" + cl.getUnloadedClassCount());
	}

	/**
	 * Java ������ı���ϵͳ
	 */
	public static void showCompilation() {
		CompilationMXBean com = ManagementFactory.getCompilationMXBean();
		System.out.println("TotalCompilationTime:" + com.getTotalCompilationTime());
		System.out.println("name:" + com.getName());
	}

	/**
	 * Java ��������߳�ϵͳ
	 */
	public static void showThread() {
		ThreadMXBean thread = ManagementFactory.getThreadMXBean();
		System.out.println("ThreadCount:" + thread.getThreadCount());
		System.out.println("AllThreadIds:" + thread.getAllThreadIds());
		System.out.println("CurrentThreadUserTime:" + thread.getCurrentThreadUserTime());
		// ......���������ܶ���Ϣ
	}

	/**
	 * Java ������е�������������
	 */
	public static void showGarbageCollector() {
		List<GarbageCollectorMXBean> gc = ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean GarbageCollectorMXBean : gc) {
			System.out.println("name:" + GarbageCollectorMXBean.getName());
			System.out.println("CollectionCount:" + GarbageCollectorMXBean.getCollectionCount());
			System.out.println("CollectionTime" + GarbageCollectorMXBean.getCollectionTime());
			System.out.println("-------------------------");
		}
	}

	/**
	 * Java ������е��ڴ������
	 */
	public static void showMemoryManager() {
		List<MemoryManagerMXBean> mm = ManagementFactory.getMemoryManagerMXBeans();
		for (MemoryManagerMXBean eachmm : mm) {
			System.out.println("name:" + eachmm.getName());
			System.out.println("MemoryPoolNames:" + eachmm.getMemoryPoolNames().toString());
		}
	}

	/**
	 * Java ������е��ڴ��
	 */
	public static void showMemoryPool() {
		List<MemoryPoolMXBean> mps = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mp : mps) {
			System.out.println("name:" + mp.getName());
			System.out.println("CollectionUsage:" + mp.getCollectionUsage());
			System.out.println("type:" + mp.getType());
		}
	}

	/**
	 * ���� MXBean �ķ��������ַ���
	 */
	public static void visitMBean() {

		// ��һ��ֱ�ӵ���ͬһ Java ������ڵ� MXBean �еķ�����
		RuntimeMXBean mxbean = ManagementFactory.getRuntimeMXBean();
		String vendor1 = mxbean.getVmVendor();
		System.out.println("vendor1:" + vendor1);

		// �ڶ���ͨ��һ�����ӵ��������е��������ƽ̨ MBeanServer �� MBeanServerConnection��
		MBeanServerConnection mbs = null;
		// Connect to a running JVM (or itself) and get MBeanServerConnection
		// that has the JVM MXBeans registered in it

		/*
		 * try { // Assuming the RuntimeMXBean has been registered in mbs ObjectName
		 * oname = new ObjectName(ManagementFactory.RUNTIME_MXBEAN_NAME); String vendor2
		 * = (String) mbs.getAttribute(oname, "VmVendor"); System.out.println("vendor2:"
		 * + vendor2); } catch (Exception e) { e.printStackTrace(); }
		 */

		// ������ʹ�� MXBean ����
//        MBeanServerConnection mbs3 = null;
//        RuntimeMXBean proxy;
//        try {
//            proxy = ManagementFactory.newPlatformMXBeanProxy(mbs3,ManagementFactory.RUNTIME_MXBEAN_NAME,
//                                                     RuntimeMXBean.class);
//            String vendor = proxy.getVmVendor();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

	}

}