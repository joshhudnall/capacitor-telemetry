# capacitor-telemetry

A plugin for generating telemetry data for remote devices.

## Install

```bash
npm install capacitor-telemetry
npx cap sync
```

## API

<docgen-index>

* [`getData()`](#getdata)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getData()

```typescript
getData() => Promise<{ value: TelemetryData; }>
```

**Returns:** <code>Promise&lt;{ value: <a href="#telemetrydata">TelemetryData</a>; }&gt;</code>

--------------------


### Interfaces


#### TelemetryData

| Prop          | Type                                                                                                                                                                                                                                              |
| ------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`device`**  | <code>{ name?: string; uuid?: string; isSecure?: boolean; model?: string; batteryLevel?: number; batteryState?: string; os?: string; osVersion?: string; screenBrightness?: number; audioLevel?: number; isGuidedAccessEnabled?: string; }</code> |
| **`app`**     | <code>{ version?: string; build?: string; bundleId?: string; }</code>                                                                                                                                                                             |
| **`network`** | <code>{ connection?: string; }</code>                                                                                                                                                                                                             |

</docgen-api>
