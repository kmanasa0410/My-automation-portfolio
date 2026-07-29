// playwright-framework/tests/register.spec.js
import { test, expect } from '@playwright/test';
import { RegisterPage } from '../pages/register-page.js';

test.describe('User Registration Security Suite', () => {

    test('Verify new user registration with dynamically generated credentials', async ({ page }) => {
        const registerPage = new RegisterPage(page);

        // Dynamic Username Generation (e.g., user_1722158400000)
        const dynamicUsername = `user_${Date.now()}`;
        const securePassword = 'SecurePassword123!';

        await registerPage.navigate();
        await registerPage.register(dynamicUsername, securePassword);

        // Assert registration success alert
        const alertText = await registerPage.getFlashMessageText();
        console.log(`Alert message returned: ${alertText}`);

        expect(alertText).toContain('Successfully registered');
    });

    test('Verify registration fails when password and confirm password do not match', async ({ page }) => {
        const registerPage = new RegisterPage(page);
        const dynamicUsername = `user_${Date.now()}`;

        await registerPage.navigate();
        
        // Passing non-matching password confirmation
        await registerPage.register(dynamicUsername, 'SecurePassword123!', 'WrongPassword123!');

        const alertText = await registerPage.getFlashMessageText();
        expect(alertText).toContain('Passwords do not match');
    });
});