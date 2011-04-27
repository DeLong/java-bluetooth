package vsbluetooth.library;

import java.io.IOException;
import java.util.ArrayList;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import vsbluetooth.BluetoothHandler;

/**
 *
 * @author ricardo
 */
public class DeviceDiscovery implements DiscoveryListener {

    public static final ArrayList<RemoteDevice> devicesDiscovered = new ArrayList<RemoteDevice>();

    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
        try {
            System.out.println("Aparelho do " + btDevice.getFriendlyName(true) + " com ID: " + btDevice.getBluetoothAddress());
            devicesDiscovered.add(btDevice);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
        System.out.println("servicesDiscovered");
    }

    public void serviceSearchCompleted(int transID, int respCode) {
        System.out.println("serviceSearchCompleted");
    }

    public void inquiryCompleted(int discType) {
        System.out.println("Busca por dispositivos finalizada!");
        synchronized (BluetoothHandler.inquiryCompletedEvent) {
            BluetoothHandler.inquiryCompletedEvent.notifyAll();
        }
    }
}
