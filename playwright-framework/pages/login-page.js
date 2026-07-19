// pages/login-page.js

class LoginPage {
    /**
     * @param {import('@playwright/test').Page} page
     */
    constructor(page) {
        this.page = page;
        
        // 1. UI Selectors (Encapsulated Locators)
        this.usernameInput = page.locator('#username');
        this.passwordInput = page.locator('#password');
        this.loginButton = page.locator('button[id="submit-login"]');
        // Looks for either the success alert banner or the logout link button
        this.secureAreaHeader = page.locator('#flash');
    }

    // 2. Structural Interactivity Actions
    async enterUsername(username) {
        console.log('Entering username parameter credentials.');
        await this.usernameInput.fill(username);
    }

    async enterPassword(password) {
        console.log('Entering password security tokens securely.');
        await this.passwordInput.fill(password);
    }

    async clickLogin() {
        console.log('Submitting authentication execution forms.');
        await this.loginButton.click();
    }

    // High-level action wrapper flow
    async login(username, password) {
        await this.enterUsername(username);
        await this.enterPassword(password);
        await this.clickLogin();
    }

  async getFlashMessageText() {
        console.log('Retrieving the Successful message text ...');
        // Wait for the flash element to attach and pull its inner text
        const message = await this.page.locator('#flash').textContent();
        return message;
    }
}

module.exports = { LoginPage };