import Foundation

@objc public class Telemetry: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
