import Foundation
import AVFoundation
import LocalAuthentication
import Reachability
import CoreTelephony

@objc public class Telemetry: NSObject {
    @objc public func getData() -> [String:[String:Any]] {
        return [
            "device" : [
                "name": deviceName(),
                "uuid": deviceUuid(),
                "isSecure": deviceIsSecure(),
                "model": deviceModel(),
                "batteryLevel": deviceBatteryLevel(),
                "batteryState": deviceBatteryState(),
                "os": deviceOs(),
                "osVersion": deviceOsVersion(),
                "screenBrightness": deviceScreenBrightness(),
                "audioLevel": deviceAudioLevel(),
                "isGuidedAccessEnabled": deviceIsGuidedAccessEnabled(),
            ],
            "app" : [
                "version": appVersion(),
                "build": appBuild(),
                "bundleId": appBundleId(),
            ],
            "network" : [
                "connection": networkConnection(),
            ],
        ]
    }
    
    
    private func deviceName() -> String {
        return UIDevice.current.name
    }
    
    private func deviceUuid() -> String {
        return UIDevice.current.identifierForVendor?.uuidString ?? "Unknown"
    }
    
    private func deviceIsSecure() -> Bool {
        return LAContext().canEvaluatePolicy(.deviceOwnerAuthentication, error: nil)
    }
    
    private func deviceModel() -> String {
        if let simulatorModelIdentifier = ProcessInfo().environment["SIMULATOR_MODEL_IDENTIFIER"] { return simulatorModelIdentifier }
        var sysinfo = utsname()
        uname(&sysinfo)
        return String(bytes: Data(bytes: &sysinfo.machine, count: Int(_SYS_NAMELEN)), encoding: .ascii)!.trimmingCharacters(in: .controlCharacters)
    }
    
    private func deviceBatteryLevel() -> Float {
        return UIDevice.current.batteryLevel
    }
    
    private func deviceBatteryState() -> String {
        switch UIDevice.current.batteryState {
        case .full:
            return "Full"
        case .charging:
            return "Charging"
        case .unplugged:
            return "Discharging"
        default:
            return "Unknown"
        }
    }
    
    private func deviceOs() -> String {
        return UIDevice.current.systemName
    }
    
    private func deviceOsVersion() -> String {
        return UIDevice.current.systemVersion
    }
    
    private func deviceScreenBrightness() -> Float {
        return Float(UIScreen.main.brightness)
    }
    
    private func deviceAudioLevel() -> Float {
        return AVAudioSession.sharedInstance().outputVolume
    }
    
    private func deviceIsGuidedAccessEnabled() -> Bool {
        return UIAccessibility.isGuidedAccessEnabled
    }
    
    private func appVersion() -> String {
        return Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String ?? "Unknown"
    }
    
    private func appBuild() -> String {
        return Bundle.main.infoDictionary?["CFBundleVersion"] as? String ?? "Unknown"
    }
    
    private func appBundleId() -> String {
        return Bundle.main.bundleIdentifier ?? "Unknown"
    }
    
    private func networkConnection() -> String {
        switch try! Reachability().connection {
        case .wifi:
            return "Wifi"
        case .cellular:
            return "Cellular"
        case .unavailable:
            return "Unavailable"
        }
    }
}
