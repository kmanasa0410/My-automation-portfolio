// tests/login.spec.js
const { test, expect } = require('@playwright/test');
const { LoginPage } = require('../pages/login-page');

test.describe('Successful Login', () => {

    test('Verify application landing page authentication workflow accepts valid users credentials', async ({ page }) => {
        const loginPage = new LoginPage(page);
        const appUrl = 'https://practice.expandtesting.com/login';
        
        console.log(`Navigating to target web workspace: ${appUrl}`);
        await page.goto(appUrl);

        console.log('Executing happy path login script sequence...');
        // Enters username and password
        await loginPage.login('practice', 'SuperSecretPassword!');

        // Asserting Successful login message
        const successMessage = await loginPage.getFlashMessageText();
        console.log("Message on the page "+successMessage);
        
        expect(successMessage).toContain('You logged into a secure area!');
    });
});