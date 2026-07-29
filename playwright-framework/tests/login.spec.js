// tests/login.spec.js
const { test, expect } = require('@playwright/test');
const { LoginPage } = require('../pages/login-page');

test.describe('Login Authentication', () => {

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

    // Negative Scenarios
    const negativeScenarios = [
        {
            testName: 'invalid username',
            user: 'wrongUser',
            pass: 'SuperSecretPassword!',
            expectError: 'Your username is invalid!'
            
        },
        { 
            testName: 'invalid password', 
            user: 'practice', 
            pass: 'wrongPassword123!', 
            expectedError: 'Your password is invalid!' 
        },
        { 
            testName: 'empty credentials', 
            user: '', 
            pass: '', 
            expectedError: 'Your username is invalid!' 
        },
    ];
    for (const scenario of negativeScenarios) {
    test(`Negative Test: Should fail with ${scenario.testName}`, async ({ page }) => {
        const loginPage = new LoginPage(page);
          
          const appUrl = 'https://practice.expandtesting.com/login';
        
        console.log(`Navigating to target web workspace: ${appUrl}`);
        await page.goto(appUrl);
        await loginPage.login(scenario.user, scenario.pass);

        const flashMessage = await loginPage.getFlashMessageText();
        expect(flashMessage).toContain(scenario.expectedError);
        });
    }
});