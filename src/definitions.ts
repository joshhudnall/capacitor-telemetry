export interface TelemetryData {
    device?: {
        name?: string
        uuid?: string
        isSecure?: boolean
        model?: string
        batteryLevel?: number
        batteryState?: string
        os?: string
        osVersion?: string
        screenBrightness?: number
        audioLevel?: number
        isGuidedAccessEnabled?: string
    }
    app?: {
        version?: string
        build?: string
        bundleId?: string
    }
    network?: {
        connection?: string
    }
}

export interface TelemetryPlugin {
    getData(): Promise<{ value: TelemetryData }>;
}
