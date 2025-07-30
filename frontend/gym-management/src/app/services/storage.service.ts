import { Injectable, inject } from '@angular/core';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  private platformId = inject(PLATFORM_ID);

  /**
   * Salva um item no localStorage de forma segura
   */
  setItem(key: string, value: string): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem(key, value);
    }
  }

  /**
   * Obtém um item do localStorage de forma segura
   */
  getItem(key: string): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem(key);
    }
    return null;
  }

  /**
   * Remove um item do localStorage de forma segura
   */
  removeItem(key: string): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem(key);
    }
  }

  /**
   * Limpa todo o localStorage de forma segura
   */
  clear(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.clear();
    }
  }

  /**
   * Verifica se o localStorage está disponível
   */
  isAvailable(): boolean {
    return isPlatformBrowser(this.platformId);
  }
} 