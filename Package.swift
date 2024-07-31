// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorTelemetry",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorTelemetry",
            targets: ["TelemetryPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "TelemetryPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/TelemetryPlugin"),
        .testTarget(
            name: "TelemetryPluginTests",
            dependencies: ["TelemetryPlugin"],
            path: "ios/Tests/TelemetryPluginTests")
    ]
)