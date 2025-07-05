import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { CalendarModule, DateAdapter, MOMENT } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { SchedulerModule } from 'angular-calendar-scheduler';
import moment from 'moment';
import { provideAnimations } from '@angular/platform-browser/animations';
import { KeycloakService, provideKeycloak } from 'keycloak-angular';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { SchoolService } from './services/school.service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    importProvidersFrom([
      CalendarModule.forRoot({
        provide: DateAdapter,
        useFactory: adapterFactory,
      }),
      SchedulerModule.forRoot({
        locale: 'en',
        headerDateFormat: 'daysRange',
        logEnabled: true,
      })
    ]),
    { provide: MOMENT, useValue: moment },
    provideAnimations(),
    provideHttpClient(withInterceptors([])),
    provideKeycloak({
      config: {
        url: 'http://localhost:8080',
        realm: 'NeoBook',
        clientId: 'angular-client'
      },
      initOptions: {
        onLoad: 'check-sso',
        pkceMethod: 'S256',
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      }
    }),
    KeycloakService,
    SchoolService,
  ]
};
