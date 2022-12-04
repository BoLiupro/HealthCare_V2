package com.healthcare.config;

import javax.bluetooth.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class RemoteDeviceDiscovery {
    public final static Set<RemoteDevice> devicesDiscovered = new HashSet<RemoteDevice>();
    
    public final static Vector<String> serviceFound = new Vector<String>();
    
    final static Object serviceSearchCompletedEvent = new Object();
    final static Object inquiryCompletedEvent = new Object();
    
    
    private static DiscoveryListener listener = new DiscoveryListener() {
        public void inquiryCompleted(int discType) {
            System.out.println("#" + "搜索完成");
            synchronized (inquiryCompletedEvent) {
                inquiryCompletedEvent.notifyAll();
            }
        }

        @Override
        public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
            devicesDiscovered.add(remoteDevice);

            try {
                System.out.println("#发现设备" + remoteDevice.getFriendlyName(false));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        @Override
        public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            for (int i = 0; i < servRecord.length; i++) {
                String url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                if (url == null) {
                    continue;
                }
                serviceFound.add(url);
                DataElement serviceName = servRecord[i].getAttributeValue(0x0100);
                if (serviceName != null) {
                    System.out.println("service " + serviceName.getValue() + " found " + url);
                } else {
                    System.out.println("service found " + url);
                }
            }
            System.out.println("#" + "servicesDiscovered");
        }

        @Override
        public void serviceSearchCompleted(int arg0, int arg1) {
            System.out.println("#" + "serviceSearchCompleted");
            synchronized(serviceSearchCompletedEvent){
                serviceSearchCompletedEvent.notifyAll();
            }
        }
    };


    private static void findDevices() throws IOException, InterruptedException {

        devicesDiscovered.clear();

        synchronized (inquiryCompletedEvent) {

            LocalDevice ld = LocalDevice.getLocalDevice();

            System.out.println("#本机蓝牙名称:" + ld.getFriendlyName());

            boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC,listener);

            if (started) {
                System.out.println("#" + "等待搜索完成...");
                inquiryCompletedEvent.wait();
                    LocalDevice.getLocalDevice().getDiscoveryAgent().cancelInquiry(listener);
                System.out.println("#发现设备数量：" + devicesDiscovered.size());
            }
        }
    }
    
    public static Set<RemoteDevice> getDevices() throws IOException, InterruptedException {
        findDevices();
        return devicesDiscovered;
    }
    
    public static String searchService(RemoteDevice btDevice, String serviceUUID) throws IOException, InterruptedException {
        UUID[] searchUuidSet = new UUID[] { new UUID(serviceUUID, false) };
        
        int[] attrIDs =  new int[] {
                0x0100 // Service name
        };
        
        synchronized(serviceSearchCompletedEvent) {
            System.out.println("search services on " + btDevice.getBluetoothAddress() + " " + btDevice.getFriendlyName(false));
            LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, btDevice, listener);
            serviceSearchCompletedEvent.wait();
        }
        
        if (serviceFound.size() > 0) {
            return serviceFound.elementAt(0);
        } else {
            return "";
        }
    }
}