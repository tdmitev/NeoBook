import { inject, Injectable } from '@angular/core';
import Keycloak, { KeycloakProfile } from 'keycloak-js';

export type UserRole = 'student' | 'teacher' | 'headmaster';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly keycloak = inject(Keycloak);

  public async login(): Promise<void> {
    try {
      console.log('KeycloakService login', this.keycloak);
      await this.keycloak.login({
        redirectUri: window.location.origin
      });
    } catch (error) {
      console.error('Failed to login', error);
    }
  }

  public async logout(): Promise<void> {
    try {
      await this.keycloak.logout({ redirectUri: window.location.origin });
    } catch (error) {
      console.error('Failed to logout', error);
    }
  }

  public async register(): Promise<void> {
    try {
      await this.keycloak.register({
        redirectUri: window.location.origin
      });
    } catch (error) {
      console.error('Failed to register', error);
    }
  }

  public isLoggedIn(): boolean {
    return this.keycloak.authenticated ?? false;
  }

  public getUserProfile(): Promise<KeycloakProfile | null> {
    return this.keycloak.loadUserProfile();
  }

  public getToken(): string | undefined {
    return this.keycloak.token;
  }

  public async getUserRoles(): Promise<UserRole[]> {
    const roles = this.keycloak.realmAccess?.roles ?? [];
    return roles.filter(role =>
      ['student', 'teacher', 'headmaster'].includes(role)
    ) as UserRole[];
  }

  public async hasRole(role: UserRole): Promise<boolean> {
    const roles = await this.getUserRoles();
    return roles.includes(role);
  }

  public async hasAnyRole(roles: UserRole[]): Promise<boolean> {
    const userRoles = await this.getUserRoles();
    return roles.some(role => userRoles.includes(role));
  }
}