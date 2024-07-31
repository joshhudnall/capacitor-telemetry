import { WebPlugin } from '@capacitor/core';

import type { TelemetryPlugin } from './definitions';

export class TelemetryWeb extends WebPlugin implements TelemetryPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
