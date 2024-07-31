import Foundation
import Capacitor

@objc(TelemetryPlugin)
public class TelemetryPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "TelemetryPlugin"
    public let jsName = "Telemetry"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "getData", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = Telemetry()

    @objc func getData(_ call: CAPPluginCall) {
        call.resolve([
            "value": implementation.getData()
        ])
    }
}
