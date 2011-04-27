package vsbluetooth;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import vsbluetooth.library.DeviceDiscovery;

/**
 *
 * @author ricardo
 */
public class BluetoothHandler {

    public static Object inquiryCompletedEvent = new Object();

    public BluetoothHandler() {
        
    }

    void start() {
        DiscoveryListener listener = new DeviceDiscovery();

        synchronized (inquiryCompletedEvent) {
            try {
                boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
                if (started) {
                    System.out.println("Buscando todos os dispositivos...");
                    inquiryCompletedEvent.wait();
                }
            } catch (BluetoothStateException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
}
