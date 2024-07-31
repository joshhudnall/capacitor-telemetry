import { WebPlugin } from '@capacitor/core';

import type {TelemetryData, TelemetryPlugin} from './definitions';

export class TelemetryWeb extends WebPlugin implements TelemetryPlugin {
  async getData(): Promise<{value: TelemetryData}> {
    throw this.unimplemented('Not implemented on web.');
  }
}
